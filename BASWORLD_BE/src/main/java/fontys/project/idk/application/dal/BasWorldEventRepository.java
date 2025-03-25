package fontys.project.idk.application.dal;

import fontys.project.idk.application.dal.entity.BASWorldEventEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BasWorldEventRepository extends JpaRepository<BASWorldEventEntity, Integer> {


    // delete
    @Modifying
    @Transactional
    @Query("delete from BASWorldEventEntity b where b.eventHeaders.entityKey = :entityKey")
    void deleteByEntityKey(@Param("entityKey") String entityKey);


    // get all
    List<BASWorldEventEntity> findAll();


    // find by event
    @Query("SELECT e FROM BASWorldEventEntity e WHERE e.event = :event")
    List<BASWorldEventEntity> getByEventName(@Param("event") String event);

    //find by entity key
    @Query("SELECT e FROM BASWorldEventEntity e WHERE e.eventHeaders.entityKey = :entKey")
    BASWorldEventEntity getByEntityKey(@Param("entKey") String entKey);

    // get events by date
    @Query("SELECT e.eventPayload.context.user AS user, " +
            "e.eventPayload.context.client.geolocation.country AS country, " +
            "e.eventPayload.event.eventName AS eventName, " +
            "e.eventHeaders.eventTimeStamp AS timestamp " +
            "FROM BASWorldEventEntity e " +
            "WHERE e.eventHeaders.eventTimeStamp <= :timestamp " +
            "ORDER BY e.eventHeaders.eventTimeStamp DESC")
    List<Object[]> getLastEventTypeAndUser(Pageable pageable, @Param("timestamp") LocalDateTime dateTime);

    @Query("SELECT bwe.eventPayload.context.user AS user, " +
            "bwe.eventPayload.context.client.geolocation.country AS country, " +
            "i.item_name AS name, " +
            "eh.eventTimeStamp AS timestamp " +
            "FROM BASWorldEventEntity bwe " +
            "JOIN bwe.eventHeaders eh " +
            "JOIN bwe.eventPayload ep " +
            "JOIN ep.event e " +
            "JOIN ItemEntity i ON e.item.id = i.id " +
            "WHERE eh.eventTimeStamp <= :timestamp " +
            "AND TYPE(e) = SelectItemEventEntity " +
            "ORDER BY eh.eventTimeStamp DESC")
    List<Object[]> getLastViewedProductsWithCategories(@Param("timestamp") LocalDateTime timestamp, Pageable pageable);


    // Get the most visited categories in the past hour
    @Query("SELECT i.item_category, COUNT(i) " +
            "FROM BASWorldEventEntity bwe " +
            "JOIN bwe.eventHeaders eh " +
            "JOIN bwe.eventPayload ep " +
            "JOIN ep.event e " +
            "JOIN ItemEntity i ON e.item.id = i.id " +
            "WHERE eh.eventTimeStamp >= :oneHourAgo " +  // Events from the past 1 hour
            "AND eh.eventTimeStamp <= :currentTime " +  // Ensure no future events
            "AND TYPE(e) = SelectItemEventEntity " +
            "GROUP BY i.item_category")
    List<Object[]> getMostVisitedCategoriesOfPastHour(@Param("oneHourAgo") LocalDateTime oneHourAgo,
                                                      @Param("currentTime") LocalDateTime currentTime);


    @Query("SELECT e.eventPayload.context.client.geolocation.country AS country, " +
            "       EXTRACT(YEAR FROM e.eventHeaders.eventTimeStamp) AS year, " +
            "       EXTRACT(MONTH FROM e.eventHeaders.eventTimeStamp) AS month, " +
            "       COUNT(e) AS count " +
            "FROM BASWorldEventEntity e " +
            "WHERE e.eventHeaders.eventTimeStamp >= :twelveMonthsAgo " +  // Ensures we get events from the last 12 months
            "  AND e.eventHeaders.eventTimeStamp <= :currentTime " + // Ensures we get events up until the current time
            "GROUP BY e.eventPayload.context.client.geolocation.country, " +
            "         EXTRACT(YEAR FROM e.eventHeaders.eventTimeStamp), " +
            "         EXTRACT(MONTH FROM e.eventHeaders.eventTimeStamp) " +
            "ORDER BY year ASC, month ASC")
    List<Object[]> getEventsOfLastTwelveMonthsByCountry(@Param("twelveMonthsAgo") LocalDateTime twelveMonthsAgo,
                                                        @Param("currentTime") LocalDateTime currentTime);


    // Get events of last twelve months by event time
    @Query("SELECT e.eventPayload.event.eventName AS event, " +
            "EXTRACT(YEAR FROM e.eventHeaders.eventTimeStamp) AS year, " +
            "EXTRACT(MONTH FROM e.eventHeaders.eventTimeStamp) AS month, " +
            "COUNT(e) AS count " +
            "FROM BASWorldEventEntity e " +
            "WHERE e.eventHeaders.eventTimeStamp > :twelveMonthsAgo " +
            "AND e.eventHeaders.eventTimeStamp <= :currentTime " + // Use the currentTime parameter for comparison
            "GROUP BY e.eventPayload.event.eventName, year, month " + // Group by event name and year/month
            "ORDER BY year ASC, month ASC, event ASC") // Optional: Order by year, month, and event name
    List<Object[]> getEventsOfLastTwelveMonthsByEventTypeAndMonth(@Param("twelveMonthsAgo") LocalDateTime twelveMonthsAgo,
                                                                  @Param("currentTime") LocalDateTime currentTime);


    @Query("SELECT EXTRACT(YEAR FROM e.eventHeaders.eventTimeStamp) AS year, " +
            "EXTRACT(MONTH FROM e.eventHeaders.eventTimeStamp) AS month, " +
            "COUNT(e) AS count " +
            "FROM BASWorldEventEntity e " +
            "WHERE e.eventHeaders.eventTimeStamp > :twelveMonthsAgo " +
            "AND e.eventHeaders.eventTimeStamp <= :currentTime " + // Use the currentTime parameter for comparison
            "GROUP BY year, month " +
            "ORDER BY year ASC, month ASC")
    List<Object[]> getEventsOfLastTwelveMonths(@Param("twelveMonthsAgo") LocalDateTime twelveMonthsAgo,
                                               @Param("currentTime") LocalDateTime currentTime);

    // Get events of last twelve months by device
    @Query("SELECT e.eventPayload.context.client.device.type AS deviceType, " +
            "       YEAR(e.eventHeaders.eventTimeStamp) AS year, " +
            "       MONTH(e.eventHeaders.eventTimeStamp) AS month, " +
            "       COUNT(e) AS count " +
            "FROM BASWorldEventEntity e " +
            "WHERE e.eventHeaders.eventTimeStamp >= :twelveMonthsAgo " +
            "AND e.eventHeaders.eventTimeStamp <= :currentTime " + // Use the currentTime parameter for comparison
            "GROUP BY e.eventPayload.context.client.device.type, " +
            "         YEAR(e.eventHeaders.eventTimeStamp), " +
            "         MONTH(e.eventHeaders.eventTimeStamp) " +
            "ORDER BY year ASC, month ASC")
    List<Object[]> getEventsOfLastTwelveMonthsByDeviceAndMonth(@Param("twelveMonthsAgo") LocalDateTime twelveMonthsAgo,
                                                               @Param("currentTime") LocalDateTime currentTime);

    @Query("SELECT e.eventPayload.context.session.utmParameters.source AS source, " +
            "       EXTRACT(YEAR FROM e.eventHeaders.eventTimeStamp) AS year, " +
            "       EXTRACT(MONTH FROM e.eventHeaders.eventTimeStamp) AS month, " +
            "       COUNT(e) AS count " +
            "FROM BASWorldEventEntity e " +
            "WHERE e.eventHeaders.eventTimeStamp > :twelveMonthsAgo " +
            "AND e.eventHeaders.eventTimeStamp <= :currentTime " + // Use the currentTime parameter for comparison
            "GROUP BY e.eventPayload.context.session.utmParameters.source, " +
            "         EXTRACT(YEAR FROM e.eventHeaders.eventTimeStamp), " +
            "         EXTRACT(MONTH FROM e.eventHeaders.eventTimeStamp) " +
            "ORDER BY year ASC, month ASC")
    List<Object[]> getEventsOfLastTwelveMonthsByMarketingAndMonth(@Param("twelveMonthsAgo") LocalDateTime twelveMonthsAgo,
                                                                  @Param("currentTime") LocalDateTime currentTime);










}
