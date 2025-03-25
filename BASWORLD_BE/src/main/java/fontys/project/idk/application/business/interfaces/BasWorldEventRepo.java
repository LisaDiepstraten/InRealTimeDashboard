package fontys.project.idk.application.business.interfaces;

import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BasWorldEventRepo {


    BasWorldEvent save(BasWorldEvent basWorldEvent);
    void deleteByEntityKey(String entityKey);
    List<BasWorldEvent> findAll();
    BasWorldEvent getByEventName(String event);
    BasWorldEvent getByEntityKey(String entityKey);
    List<BasWorldEvent> findProductViewEventsSortedByDate();
    Map<String, Integer> getMostVisitedCategoriesOfPastHour();
    Map<String, Integer> getEventsOfLastTwelveMonths();
    Map<String, Map<String, Integer>> getEventsOfLastTwelveMonthsByDevice();
    Map<String, Map<String, Integer>> getEventsOfLastTwelveMonthsByMarketing();
}
