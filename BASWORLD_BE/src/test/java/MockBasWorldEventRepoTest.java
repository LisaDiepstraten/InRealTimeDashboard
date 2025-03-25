import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import fontys.project.idk.application.models.bas_data.eventHeaders.EventHeaders;
import fontys.project.idk.application.models.bas_data.eventPayload.EventPayload;
import fontys.project.idk.application.models.bas_data.eventPayload.application.BASWorldApplication;
import fontys.project.idk.application.models.bas_data.eventPayload.context.Context;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.Client;
import fontys.project.idk.application.models.bas_data.eventPayload.context.page.Page;
import fontys.project.idk.application.models.bas_data.eventPayload.context.session.Session;
import fontys.project.idk.application.models.bas_data.eventPayload.context.user.User;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import fontys.project.idk.application.dal.MockBasWorldEventRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MockBasWorldEventRepoTest {
    private MockBasWorldEventRepo mockBasWorldEventRepo;

    @BeforeEach
    void setUp() {
        mockBasWorldEventRepo = new MockBasWorldEventRepo();
    }

    @Test
    void testFindAll() {
        List<BasWorldEvent> allEvents = mockBasWorldEventRepo.findAll();

        assertEquals(120, allEvents.size());
    }

    @Test
    void testGetByEvent() {
        BasWorldEvent firstEvent = mockBasWorldEventRepo.findAll().get(0);
        Event eventToFind = firstEvent.getEventPayload().getEvent();

        BasWorldEvent foundEvent = mockBasWorldEventRepo.getByEntityKey(firstEvent.getEventHeaders().getEntityKey());

        assertNotNull(foundEvent);

        Event nonExistentEvent = new Event() {
            {
                eventName = "nonexistent";
                getAdditionalData().put("key", "value");
            }
        };

      BasWorldEvent notFoundEvent = mockBasWorldEventRepo.getByEventName(nonExistentEvent.eventName);
        assertNull(notFoundEvent);
    }

    @Test
   void testDeleteByEvent() {
        BasWorldEvent entToDelete = mockBasWorldEventRepo.findAll().get(0);
        String entityKey = entToDelete.getEventHeaders().getEntityKey();

        assertNotNull(mockBasWorldEventRepo.getByEventName(entToDelete.getEvent()));

        mockBasWorldEventRepo.deleteByEntityKey(entityKey);

        BasWorldEvent deletedEvent = mockBasWorldEventRepo.getByEntityKey(entityKey);
        assertNull(deletedEvent);

        assertEquals(119, mockBasWorldEventRepo.findAll().size());
    }

    @Test
    void testSave() {
        Event newEvent = new Event() {
            {
                eventName = "product_page";
                getAdditionalData().put("language", "es");
            }
        };

        Page mockPage = Page.builder()
                .title("Mock Page")
                .url(URI.create("https://example.com"))
                .canonicalUrl(URI.create("https://example.com"))
                .build();

        Session mockSession = Session.builder()
                .buttSessionId("mock-session-id")
                .build();

        Client mockClient = Client.builder()
                .buttClientId("mock-client-id")
                .build();

        User mockUser = User.builder()
                .userId("mock-user-id")
                .build();

        Context mockContext = Context.builder()
                .page(mockPage)
                .session(mockSession)
                .client(mockClient)
                .user(mockUser)
                .build();

        BASWorldApplication mockApplication = BASWorldApplication.builder()
                .name("MockApp")
                .version("1.0.0")
                .environment("production")
                .build();

        EventPayload eventPayload = new EventPayload(mockApplication, mockContext, newEvent);

        EventHeaders eventHeaders = EventHeaders.builder()
                .entity("userEvent")
                .entityKey(UUID.randomUUID().toString())
                .eventMainType("CREATE")
                .eventSubType("newUserEvent")
                .eventTimeStamp(ZonedDateTime.now().toLocalDateTime())
                .publishedBy("TestApp")
                .policyVersion("1.0.0")
                .build();

        BasWorldEvent newBasWorldEvent = BasWorldEvent.builder()
                .event("new-event")
                .eventPayload(eventPayload)
                .eventHeaders(eventHeaders)
                .build();

        mockBasWorldEventRepo.save(newBasWorldEvent);

        assertNotNull(mockBasWorldEventRepo
                .getByEntityKey(newBasWorldEvent.getEventHeaders().getEntityKey()));

        assertEquals(121, mockBasWorldEventRepo.findAll().size());
    }
}
