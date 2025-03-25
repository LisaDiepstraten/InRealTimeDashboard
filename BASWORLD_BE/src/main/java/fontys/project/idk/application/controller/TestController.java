package fontys.project.idk.application.controller;

import fontys.project.idk.application.business.interfaces.BWEventService;
import fontys.project.idk.application.controller.dtos.BasWorldEventDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.EventPayloadDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.application.ApplicationDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.ContextDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import fontys.project.idk.application.controller.mappers.BasWorldEventMapper;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import fontys.project.idk.application.models.bas_data.eventPayload.context.page.DesignSizeEnum;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class TestController {

    @Qualifier("BWEService")
    BWEventService basWorldService;


    public TestController(BWEventService test) {
        this.basWorldService = test;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BasWorldEventDTO postEvent(@Valid @RequestBody BasWorldEvent event) {
        return new BasWorldEventMapper().eventToDto(event);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BasWorldEvent> getEventsTest() {
        List<BasWorldEvent> eventsList = basWorldService.getAllBWEvents();
        return eventsList;
    }
}

