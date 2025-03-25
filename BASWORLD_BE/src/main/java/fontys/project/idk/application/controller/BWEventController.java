package fontys.project.idk.application.controller;

import fontys.project.idk.application.business.interfaces.BWEventService;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/events")
public class BWEventController {

    private final BWEventService bwEventService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public ResponseEntity<List<BasWorldEvent>> getEvents() {
        List<BasWorldEvent> events = bwEventService.getAllBWEvents();
        return ResponseEntity.ok(events);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{eventName}")
    public List<BasWorldEvent> getBWEventByEventName(@PathVariable String eventName) {

        return bwEventService.getBWEventByEventName(eventName);
        }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BasWorldEvent postEvent(@Valid @RequestBody BasWorldEvent event) {
        BasWorldEvent createdEvent = bwEventService.createBWEvent(event);
        messagingTemplate.convertAndSend("/topic/event-logs-products", createdEvent); // Broadcast the event via WebSocket
        return createdEvent;
    }

    @DeleteMapping()
    public BasWorldEvent deleteEvent(@Valid @RequestBody BasWorldEvent event) {
            return bwEventService.deleteBWEventByEntityKey(event);
    }


    @GetMapping("/logs")
    public ResponseEntity<List<String>> getLastEventLogsByDate() {
        // endpoint like “someone from location X viewed product Y” of all events sorted by date
          List<String> events = bwEventService.getRecentEventLogs(); // new ones at the start
          return ResponseEntity.ok(events);
    }

    @GetMapping("/logs/products")
    public ResponseEntity<List<String>> getLastViewedProductLogsByDate() {
        // endpoint like “someone from location X viewed product Y” of all events sorted by date
        List<String> events = bwEventService.getRecentProductLogs(); // new ones at the start
        return ResponseEntity.ok(events);
    }



    @GetMapping("/count")
    public ResponseEntity< Map<String, Long>> getCountOfEventsOfLastTwelveMonths() {
        // endpoint with number of events for last 12 months.
        Map<String, Long> events = bwEventService.getEventsOfLastTwelveMonths();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/count/products")
    public ResponseEntity<Map<String, Long>> getCountOfEventsOfPastHourByMostVisitedCategories() {
        // the most visited product categories in the past hour.
        Map<String, Long> events = bwEventService.getMostVisitedCategoriesOfPastHour();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/count/country")
    public ResponseEntity< Map<String, Long>> getCountOfEventsOfLastTwelveMonthsByCountry() {
        // endpoint with number of events for last 12 months.
        Map<String, Long> events = bwEventService.getEventsOfLastTwelveMonthsByCountry();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/count/event-type")
    public ResponseEntity< Map<String, Map<String, Long>>> getCountOfEventsOfLastTwelveMonthsByEventType() {
        // endpoint with number of events for last 12 months.
        Map<String, Map<String, Long>> events = bwEventService.getEventsOfLastTwelveMonthsByEventType();
        return ResponseEntity.ok(events);
    }
    @GetMapping("/count/device")
    public  ResponseEntity< Map<String, Map<String, Long>>> getCountOfEventsOfLastTwelveMonthsByDevice() {
        // endpoint  with number of events for last 12 months by device.
        Map<String, Map<String, Long>> events = bwEventService.getEventsOfLastTwelveMonthsByDevice();
        return ResponseEntity.ok(events);
    }
    @GetMapping("/count/marketing")
    public ResponseEntity< Map<String, Map<String, Long>>> getCountOfEventsOfLastTwelveMonthsByMarketing() {
        // endpoint with number of events for last 12 months by marketing source.
        Map<String, Map<String, Long>> events = bwEventService.getEventsOfLastTwelveMonthsByMarketing();
        return ResponseEntity.ok(events);
    }
}

