package fontys.project.idk.application.business.ServiceImpl;

import fontys.project.idk.application.business.interfaces.BWEventService;
import fontys.project.idk.application.business.mappers.EventMapperEntity;
import fontys.project.idk.application.dal.BasWorldEventRepository;
import fontys.project.idk.application.dal.entity.BASWorldEventEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.user.UserEntity;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BWEventServiceImpl implements BWEventService {

    private final BasWorldEventRepository basWorldEventRepository;
    private final EventMapperEntity eventMapper = EventMapperEntity.INSTANCE;


    @Override
    @Transactional
    public BasWorldEvent createBWEvent(BasWorldEvent basWorldEvent) {
        BASWorldEventEntity eventEntity = EventMapperEntity.INSTANCE.toEntity(basWorldEvent);

        return eventMapper.toDomain(basWorldEventRepository.save(eventEntity));
    }

    @Override
    public BasWorldEvent deleteBWEventByEntityKey(BasWorldEvent event) {
        /*BasWorldEvent existingEvent = basWorldEventRepo.getByEventName(event.getEvent());
        if (existingEvent != null) {
            basWorldEventRepo.deleteByEntityKey(event.getEventHeaders().getEntityKey());
        }*/
        return null;
    }

    @Override
    public List<BasWorldEvent> getAllBWEvents() {
        return eventMapper.toDomain(basWorldEventRepository.findAll());
    }

    @Override
    public List<BasWorldEvent> getBWEventByEventName(String event) {
        return eventMapper.toDomain(basWorldEventRepository.getByEventName(event));
    }

    @Override
    public List<String> getRecentEventLogs() {
        List<String> logs = new ArrayList<>();

        // Pageable request to get the last 20 events
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("timestamp"))); // Sort by timestamp in descending order

        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));


        List<Object[]> events = basWorldEventRepository.getLastEventTypeAndUser(pageable, nowInAmsterdam.toLocalDateTime());

        // Loop through the results and create logs
        for (Object[] event : events) {
            UserEntity user = (UserEntity) event[0]; // Assuming 'user' is a UserEntity, adjust if it's a String
            String country = (String) event[1]; // Country as String
            String eventName = (String) event[2]; // Event name as String
            LocalDateTime timestamp = (LocalDateTime) event[3]; // Timestamp of the event

            // Use DateTimeFormatter to format the LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");
            String formattedTimestamp = timestamp.format(formatter);  // Format the LocalDateTime to a string

            // Construct the log string
            String log = String.format("%s from location %s did %s event at %s", user.getUserId(), country, eventName, formattedTimestamp);
            logs.add(log);
        }

        return logs;
    }

    @Override
    public List<String> getRecentProductLogs() {
        List<String> logs = new ArrayList<>();

        // Pageable request to get the last 20 events
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("timestamp"))); // Sort by timestamp in descending order

        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));


        List<Object[]> events = basWorldEventRepository.getLastViewedProductsWithCategories(nowInAmsterdam.toLocalDateTime(), pageable);

        // Loop through the results and create logs
        for (Object[] event : events) {
            UserEntity user = (UserEntity) event[0]; // Assuming 'user' is a UserEntity, adjust if it's a String
            String country = (String) event[1]; // Country as String
            String itemName = (String) event[2]; // Event name as String
            LocalDateTime timestamp = (LocalDateTime) event[3]; // Timestamp of the event

            // Use DateTimeFormatter to format the LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");
            String formattedTimestamp = timestamp.format(formatter);  // Format the LocalDateTime to a string

            // Construct the log string
            String log = String.format("%s from location %s viewed %s at %s", user.getUserId(), country, itemName, formattedTimestamp);
            logs.add(log);
        }

        return logs;
    }

    @Override
    public Map<String, Long> getMostVisitedCategoriesOfPastHour() {
        // Get the timestamp for one hour ago
        ZonedDateTime fiveHourAgo = ZonedDateTime.now().minusHours(5);

        // Fetch data from the repository
        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));
        List<Object[]> results = basWorldEventRepository.getMostVisitedCategoriesOfPastHour(fiveHourAgo.toLocalDateTime(), nowInAmsterdam.toLocalDateTime());

        // Convert List<Object[]> to Map<String, Long>
        Map<String, Long> categoryCountMap = results.stream()
                .filter(result -> result[0] != null && result[1] != null) // Ensure no null values
                .collect(Collectors.toMap(
                        result -> (String) result[0],           // category name
                        result -> (Long) result[1],             // count
                        (existing, replacement) -> existing     // Handle duplicates (optional)
                ));

        return categoryCountMap;
    }

    @Override
    public Map<String, Long> getEventsOfLastTwelveMonths() {
        ZonedDateTime twelveMonthsAgo = ZonedDateTime.now().minusMonths(11); // Get the date 12 months ago

        // Fetch data from the repository
        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));
        List<Object[]> results = basWorldEventRepository.getEventsOfLastTwelveMonths(twelveMonthsAgo.toLocalDateTime(), nowInAmsterdam.toLocalDateTime());

        // Map to hold the event count for each month in "M/YYYY" format (without leading zero)
        Map<String, Long> eventCountMap = new LinkedHashMap<>();

        // Process each result and format date as "M/YYYY"
        for (Object[] result : results) {
            String year = String.valueOf(result[0]); // Year
            String month = String.valueOf(result[1]); // Month
            Long count = (Long) result[2]; // Event count

            // Format the date as "M/YYYY" (no leading zero for month)
            String date = month + "/" + year;

            // Add the count to the map for the corresponding month
            eventCountMap.put(date, count);
        }

        // Sort the map chronologically based on the month/year key
        Map<String, Long> sortedEventCountMap = eventCountMap.entrySet().stream()
                .sorted((entry1, entry2) -> {
                    LocalDate date1 = parseDate(entry1.getKey());
                    LocalDate date2 = parseDate(entry2.getKey());
                    return date1.compareTo(date2); // Chronological order
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Maintain the order after sorting
                ));

        return sortedEventCountMap;
    }

    @Override
    public Map<String, Long> getEventsOfLastTwelveMonthsByCountry() {
        ZonedDateTime twelveMonthsAgo = ZonedDateTime.now().minusMonths(12);

        // Fetch data from the repository
        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));
        List<Object[]> results = basWorldEventRepository.getEventsOfLastTwelveMonthsByCountry(twelveMonthsAgo.toLocalDateTime(), nowInAmsterdam.toLocalDateTime());

        // Convert List<Object[]> to Map<String, Long> where country is the key and total count is the value
        Map<String, Long> countryEventMap = new LinkedHashMap<>();

        // Process results
        for (Object[] result : results) {
            String country = (String) result[0];  // Country
            Long count = (Long) result[3];        // Event count

            // Aggregate count for each country
            countryEventMap.merge(country, count, Long::sum);
        }

        return countryEventMap;
    }


    @Override
    public Map<String, Map<String, Long>> getEventsOfLastTwelveMonthsByEventType() {
        ZonedDateTime twelveMonthsAgo = ZonedDateTime.now().minusMonths(11);

        // Fetch data from the repository
        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));
        List<Object[]> results = basWorldEventRepository.getEventsOfLastTwelveMonthsByEventTypeAndMonth(twelveMonthsAgo.toLocalDateTime(), nowInAmsterdam.toLocalDateTime());

        // Convert List<Object[]> to Map<String, Map<String, Long>> with event name as key and date as key for inner map
        Map<String, Map<String, Long>> eventMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            String event = (String) result[0]; // event name
            int year = (Integer) result[1];   // event year
            int month = (Integer) result[2];  // event month
            Long count = (Long) result[3];    // count of events for that month

            // Construct the date string in "M/YYYY" format (removes leading zero from month)
            String date = month + "/" + year;

            // Add data to the event map, grouping by event name and date
            eventMap
                    .computeIfAbsent(event, k -> new LinkedHashMap<>()) // Create inner map if not exist for event
                    .put(date, count);  // Add count for the date

        }

        return eventMap;
    }


    // Helper method to parse MM/YYYY string into LocalDate for comparison
    private LocalDate parseDate(String date) {
        String[] parts = date.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        return LocalDate.of(year, month, 1); // Use the first day of the month for comparison
    }


    @Override
    public Map<String, Map<String, Long>> getEventsOfLastTwelveMonthsByDevice() {
        ZonedDateTime twelveMonthsAgo = ZonedDateTime.now().minusMonths(11);

        // Fetch data from the repository
        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));
        List<Object[]> results = basWorldEventRepository.getEventsOfLastTwelveMonthsByDeviceAndMonth(twelveMonthsAgo.toLocalDateTime(), nowInAmsterdam.toLocalDateTime());

        // Convert List<Object[]> to Map<String, Map<String, Integer>>
        Map<String, Map<String, Long>> deviceEventMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            String deviceType = ((String) result[0]).toLowerCase();
            String year = String.valueOf(result[1]); // Convert year to String if needed
            String month = String.valueOf(result[2]); // Convert month to String if needed
            Long count = (Long) result[3];

            // Construct the date string in "month/year" format
            String date = month + "/" + year;

            // Use deviceType as the key in the outer map and date as the key in the inner map
            deviceEventMap
                    .computeIfAbsent(deviceType, k -> new LinkedHashMap<>())
                    .put(date, count); // Use the constructed date as the inner map key
        }

        return deviceEventMap;
    }

    @Override
    public Map<String, Map<String, Long>> getEventsOfLastTwelveMonthsByMarketing() {
        ZonedDateTime twelveMonthsAgo = ZonedDateTime.now().minusMonths(11);

        // Fetch data from the repository
        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));
        List<Object[]> results = basWorldEventRepository.getEventsOfLastTwelveMonthsByMarketingAndMonth(twelveMonthsAgo.toLocalDateTime(), nowInAmsterdam.toLocalDateTime());

        // Convert List<Object[]> to Map<String, Map<String, Integer>>
        Map<String, Map<String, Long>> marketingEventMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            String source = (String) result[0];
            String year = String.valueOf(result[1]); // Convert year to String if needed
            String month = String.valueOf(result[2]); // Convert month to String if needed
            Long count = (Long) result[3];

            // Construct the date string in "month/year" format
            String date = month + "/" + year;

            // Use deviceType as the key in the outer map and date as the key in the inner map
            marketingEventMap
                    .computeIfAbsent(source, k -> new LinkedHashMap<>())
                    .put(date, count); // Use the constructed date as the inner map key
        }

        return marketingEventMap;
    }
}
//    @Override
//    public List<String> getRecentProductViewLogs() {
//        List<String> logs = new ArrayList<>();
//
//        // Pageable request to get the last 20 product view events
//        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("timestamp"))); // Sort by timestamp in descending order
//
//        ZonedDateTime nowInAmsterdam = ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"));
//
//        // Assuming getProductViewEvents gets the latest product view events with user, location, product name, and timestamp
//        List<Object[]> events = basWorldEventRepository.getLastProductViewEvents(pageable, nowInAmsterdam.toLocalDateTime());
//
//        // Loop through the results and create logs
//        for (Object[] event : events) {
//            String location = (String) event[0]; // Location as String
//            String productName = (String) event[1]; // Product name as String
//            LocalDateTime timestamp = (LocalDateTime) event[2]; // Timestamp of the event
//
//            // Use DateTimeFormatter to format the LocalDateTime
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");
//            String formattedTimestamp = timestamp.format(formatter); // Format the LocalDateTime to a string
//
//            // Construct the log string
//            String log = String.format("Someone from location %s viewed product %s at %s", location, productName, formattedTimestamp);
//            logs.add(log);
//        }
//
//        return logs;
//    }
//
//}
