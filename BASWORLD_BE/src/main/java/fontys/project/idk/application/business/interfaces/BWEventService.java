package fontys.project.idk.application.business.interfaces;

import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Qualifier("BWEService")
@Primary
public interface BWEventService {

    BasWorldEvent createBWEvent(BasWorldEvent basWorldEvent);
    BasWorldEvent deleteBWEventByEntityKey(BasWorldEvent basWorldEvent);
    List<BasWorldEvent> getAllBWEvents();
    List<BasWorldEvent> getBWEventByEventName(String event);
    List<String> getRecentEventLogs();
    List<String> getRecentProductLogs();
    Map<String, Long> getMostVisitedCategoriesOfPastHour();
    Map<String, Long> getEventsOfLastTwelveMonths();
    Map<String, Long> getEventsOfLastTwelveMonthsByCountry();
    Map<String, Map<String, Long>> getEventsOfLastTwelveMonthsByEventType();
    Map<String, Map<String, Long>> getEventsOfLastTwelveMonthsByDevice();
    Map<String, Map<String, Long>> getEventsOfLastTwelveMonthsByMarketing();
//
//    List<String> getRecentProductViewLogs();
}
