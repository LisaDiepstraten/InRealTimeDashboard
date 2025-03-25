package fontys.project.idk.application.dal;

import fontys.project.idk.application.business.interfaces.BasWorldEventRepo;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Qualifier("dbRepo")
public class BasWorldEventRepoImpl implements BasWorldEventRepo {
    private final List<BasWorldEvent> events;

    public BasWorldEventRepoImpl() {
        events = new ArrayList<BasWorldEvent>();
    }

    @Override
    public BasWorldEvent save(BasWorldEvent basWorldEvent) {
        this.events.add(basWorldEvent);
        return basWorldEvent;
    }
    @Override
    public void deleteByEntityKey(String entityKey) {

        events.removeIf(bas -> bas.getEventHeaders().getEntityKey().equals(entityKey));
    }

    @Override
    public List<BasWorldEvent> findAll() {
        return events;
    }

    @Override
    public BasWorldEvent getByEventName(String event) {
//        return events.stream().filter(e -> e.getEventPayload().getEvent().equals(event)).findFirst().orElse(null);
        return events
                .stream()
                .filter(e -> e.getEvent().equals(event))
                .findFirst().orElse(null);
    }

    @Override
    public BasWorldEvent getByEntityKey(String entityKey) {
        return events
                .stream()
                .filter(e -> e.getEventHeaders().getEntityKey().equals(entityKey))
                .findFirst().orElse(null);
    }

    @Override
    public List<BasWorldEvent> findProductViewEventsSortedByDate() {
        return List.of();
    }

    @Override
    public Map<String, Integer> getMostVisitedCategoriesOfPastHour() {
        return Map.of();
    }

    @Override
    public Map<String, Integer> getEventsOfLastTwelveMonths() {
        return Map.of();
    }

    @Override
    public Map<String, Map<String, Integer>> getEventsOfLastTwelveMonthsByDevice() {
        return Map.of();
    }

    @Override
    public Map<String, Map<String, Integer>> getEventsOfLastTwelveMonthsByMarketing() {
        return Map.of();
    }
}
