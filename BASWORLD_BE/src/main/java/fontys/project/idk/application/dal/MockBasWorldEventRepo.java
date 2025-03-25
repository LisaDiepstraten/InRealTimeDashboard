package fontys.project.idk.application.dal;

import fontys.project.idk.application.business.interfaces.BasWorldEventRepo;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import fontys.project.idk.application.models.bas_data.definitions.*;
import fontys.project.idk.application.models.bas_data.eventHeaders.EventHeaders;
import fontys.project.idk.application.models.bas_data.eventPayload.EventPayload;
import fontys.project.idk.application.models.bas_data.eventPayload.application.BASWorldApplication;
import fontys.project.idk.application.models.bas_data.eventPayload.context.Context;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.*;
import fontys.project.idk.application.models.bas_data.eventPayload.context.page.DesignSizeEnum;
import fontys.project.idk.application.models.bas_data.eventPayload.context.page.Page;
import fontys.project.idk.application.models.bas_data.eventPayload.context.session.Session;
import fontys.project.idk.application.models.bas_data.eventPayload.context.session.UtmParameters;
import fontys.project.idk.application.models.bas_data.eventPayload.context.session.VendorClicksId;
import fontys.project.idk.application.models.bas_data.eventPayload.context.user.User;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Repository
@Primary
@Qualifier("mockRepo")
public class MockBasWorldEventRepo implements BasWorldEventRepo {
    private final List<BasWorldEvent> events;

    public MockBasWorldEventRepo() {
        events = new ArrayList<>();
        populateMockData();
    }

    private void populateMockData() {
        String[] languages = {"en", "de", "fr", "es"};
        String[] pageTypes = {"homepage", "truck_listing", "contact_us", "product_page"};
        String[] devices = {"Desktop", "Mobile", "Tablet"};
        String[] marketingSources = {"Google", "Facebook", "Instagram", "LinkedIn"};
        String[] countries = {"USA", "Germany", "France", "Netherlands"};
        String[] cities = {"New York", "Berlin", "Paris", "Amsterdam"};
        String[] operatingSystems = {"Windows", "macOS", "iOS", "Android"};

        for (int i = 0; i < 120; i++) { // Loop to generate 120 diverse mock events for 12 months
            String language = languages[ThreadLocalRandom.current().nextInt(languages.length)];
            String pageType = pageTypes[ThreadLocalRandom.current().nextInt(pageTypes.length)];
            String device = devices[ThreadLocalRandom.current().nextInt(devices.length)];
            String source = marketingSources[(int) (Math.random() * marketingSources.length)];
            String country = countries[ThreadLocalRandom.current().nextInt(countries.length)];
            String city = cities[ThreadLocalRandom.current().nextInt(cities.length)];
            String operatingSystem = operatingSystems[ThreadLocalRandom.current().nextInt(operatingSystems.length)];

            // Generate a random date in the last 12 months
            LocalDateTime eventTimeStamp = LocalDateTime.now().minusMonths(ThreadLocalRandom.current().nextInt(0, 12))
                    .withHour(ThreadLocalRandom.current().nextInt(0, 24))
                    .withMinute(ThreadLocalRandom.current().nextInt(0, 60))
                    .withSecond(ThreadLocalRandom.current().nextInt(0, 60));

            // Create a mock event with randomized data
            EventHeaders eventHeaders = EventHeaders.builder()
                    .entity("userEvent")
                    .entityKey(UUID.randomUUID().toString())
                    .eventMainType("CREATE")
                    .eventSubType("newUserEvent")
                    .eventTimeStamp(eventTimeStamp)
                    .publishedBy("TruckApp")
                    .policyVersion("1.0.0")
                    .build();

            Page page = Page.builder()
                    .title(pageType)
                    .url(URI.create("https://truckapp.com/" + pageType))
                    .canonicalUrl(URI.create("https://truckapp.com/" + pageType))
                    .designSize(DesignSizeEnum.DESIGN_SIZE_LG)
                    .build();

            Session session = Session.builder()
                    .buttSessionId(UUID.randomUUID().toString())
                    .vendorClicksIds(VendorClicksId.builder()
                            .google("google-123")
                            .bing("bing-456")
                            .facebook("facebook-789")
                            .linkedIn("linkedin-123")
                            .build())
                    .referer("https://google.com")
                    .utmParameters(UtmParameters.builder()
                            .source(source) // Use randomized source
                            .medium("cpc")
                            .campaign("truck-sales")
                            .term("truck-buying")
                            .content("ad-" + i)
                            .build())
                    .build();

            Client client = Client.builder()
                    .buttClientId(UUID.randomUUID().toString())
                    .rawUserAgent("Mozilla/5.0")
                    .vendorClientIds(VendorClientIds.builder()
                            .googleAnalytics("ga-123")
                            .bing("bing-123")
                            .facebook("fb-123")
                            .build())
                    .device(Device.builder()
                            .type(device) // Use randomized device type
                            .vendor("Apple")
                            .model("iPhone " + (i % 5 + 10)) // Vary models between 10 to 15
                            .screenWidth(1170 + (ThreadLocalRandom.current().nextInt(-50, 51))) // Slight variation in screen width
                            .screenHeight(2532 + (ThreadLocalRandom.current().nextInt(-50, 51))) // Slight variation in screen height
                            .build())
                    .operatingSystem(OperatingSystem.builder()
                            .name(operatingSystem) // Use randomized OS
                            .version("15." + ThreadLocalRandom.current().nextInt(0, 5)) // Vary OS version
                            .build())
                    .browser(Browser.builder()
                            .name("Safari")
                            .version("15." + ThreadLocalRandom.current().nextInt(0, 5))
                            .viewportWidth(1170 + (ThreadLocalRandom.current().nextInt(-50, 51)))
                            .viewportHeight(2532 + (ThreadLocalRandom.current().nextInt(-50, 51)))
                            .language(language) // Use randomized language
                            .cookiesEnabled(true)
                            .javaScriptEnabled(true)
                            .doNotTrackEnabled(false)
                            .build())
                    .engine(Engine.builder()
                            .name("WebKit")
                            .version("605." + ThreadLocalRandom.current().nextInt(1, 10))
                            .build())
                    .geolocation(Geolocation.builder()
                            .latitude("40.7" + ThreadLocalRandom.current().nextInt(0, 10))
                            .longitude("-74.0" + ThreadLocalRandom.current().nextInt(0, 10))
                            .country(country) // Use randomized country
                            .region("Region-" + ThreadLocalRandom.current().nextInt(1, 6)) // Randomized region
                            .city(city) // Use randomized city
                            .build())
                    .build();

            User user = User.builder()
                    .userId(UUID.randomUUID().toString())
                    .email("user" + i + "@example.com")
                    .personId(UUID.randomUUID().toString())
                    .personEmail("person" + i + "@example.com")
                    .companyId(UUID.randomUUID().toString())
                    .companyName("Truck World Inc.")
                    .build();

            Context context = Context.builder()
                    .page(page)
                    .session(session)
                    .client(client)
                    .user(user)
                    .build();

            Event event = PageVisitEvent.builder()
                    .data(PageVisitEvent.Data.builder()
                            .pageLanguage(language)
                            .pageType(pageType)
                            .build())
                    .build();

            EventPayload eventPayload = EventPayload.builder()
                    .application(createMockApplication())
                    .context(context)
                    .event(event)
                    .build();

            events.add(BasWorldEvent.builder()
                    .event("bas-user-event")
                    .eventPayload(eventPayload)
                    .eventHeaders(eventHeaders)
                    .build());
        }
    }

    // Helper method to create mock Application data
    private BASWorldApplication createMockApplication() {
        return BASWorldApplication.builder()
                .name("TruckApp")
                .version("1.0.0")
                .environment("production")
                .build();
    }

    @Override
    public BasWorldEvent save(BasWorldEvent basWorldEvent) {
        this.events.add(basWorldEvent);
        return basWorldEvent;
    }

    @Override
    public void deleteByEntityKey(String entityKey) {
        events.removeIf(e -> e.getEventHeaders().getEntityKey().equals(entityKey));
    }

    @Override
    public List<BasWorldEvent> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public BasWorldEvent getByEventName(String event) {
        return events.stream()
                .filter(e -> e.getEvent().equals(event))
                .findFirst()
                .orElse(null);
    }

    @Override
    public BasWorldEvent getByEntityKey(String entKey) {
        return events.stream()
                .filter(e -> e.getEventHeaders().getEntityKey().equals(entKey))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<BasWorldEvent> findProductViewEventsSortedByDate() {
        return events.stream()
                .filter(event -> "product_page".equals(event.getEventPayload().getContext().getPage().getTitle())) // Filter by product page
                .sorted((e1, e2) -> e2.getEventHeaders().getEventTimeStamp().compareTo(e1.getEventHeaders().getEventTimeStamp())) // Sort by date, newest first
                .toList();
    }
    public Map<String, Integer> getMostVisitedCategoriesOfPastHour() {
        ZonedDateTime oneHourAgo = ZonedDateTime.now().minusHours(1);
        // Filter events from the last hour, group by product category, and count occurrences
        return events.stream()
                .filter(event -> event.getEventHeaders().getEventTimeStamp().isAfter(ChronoLocalDateTime.from(oneHourAgo))) // Filter for past hour
                .filter(event -> "product_page".equals(event.getEventPayload().getContext().getPage().getTitle())) // Filter for product page events
                .collect(Collectors.groupingBy(
                        event -> event.getEventPayload().getContext().getPage().getTitle(), // Group by product category
                        Collectors.summingInt(e -> 1) // Count occurrences
                ));
    }
    @Override
    public Map<String, Integer> getEventsOfLastTwelveMonths() {
        // Define the start date as 12 months ago
        ZonedDateTime twelveMonthsAgo = ZonedDateTime.now().minusMonths(12);

        // Filter events from the last 12 months and group by month
        return events.stream()
                .filter(event -> event.getEventHeaders().getEventTimeStamp().isAfter(ChronoLocalDateTime.from(twelveMonthsAgo))) // Filter events within the last 12 months
                .collect(Collectors.groupingBy(
                        event -> event.getEventHeaders().getEventTimeStamp().getYear() + "-" +
                                String.format("%02d", event.getEventHeaders().getEventTimeStamp().getMonthValue()), // Format as "YYYY-MM"
                        Collectors.summingInt(e -> 1)
                ));
    }
    @Override
    public Map<String, Map<String, Integer>> getEventsOfLastTwelveMonthsByDevice() {
        ZonedDateTime twelveMonthsAgo = ZonedDateTime.now().minusMonths(12);
        Map<String, Map<String, Integer>> events = new HashMap<>();
        return events;
        // Filter events from the last twelve months, group by device type, and count occurrences
    }
    @Override
    public Map<String, Map<String, Integer>> getEventsOfLastTwelveMonthsByMarketing() {
        ZonedDateTime twelveMonthsAgo = ZonedDateTime.now().minusMonths(12);
        Map<String, Map<String, Integer>> events = new HashMap<>();
        return events;
    }
}
