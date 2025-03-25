package fontys.project.idk.application.controller.mappers;

import fontys.project.idk.application.controller.dtos.BasWorldEventDTO;
import fontys.project.idk.application.controller.dtos.definitions.*;
import fontys.project.idk.application.controller.dtos.eventHeaders.EventHeadersDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.EventPayloadDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import fontys.project.idk.application.models.bas_data.definitions.*;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;

public class EventMapper {

    public EventDTO eventToDto(Event event) {
        EventDTO eventdto = OtherEventDTO.builder().build();
        switch(event.eventName){
            case "other":
                eventdto = new OtherEventDTO(event.getAdditionalData());
                break;
            case "element_interaction":
                ElementInteractionEvent elementInteractionEvent = (ElementInteractionEvent) event;
                eventdto = new ElementInteractionEventDTO(elementInteractionEvent.getData().getElementName(), elementInteractionEvent.getAdditionalData());
                break;
            case "element_visibility":
                ElementVisibilityEvent elementVisibilityEvent = (ElementVisibilityEvent) event;
                eventdto = new ElementVisibilityEventDTO(elementVisibilityEvent.getData().getElementName(), elementVisibilityEvent.getAdditionalData());
                break;
            case "page_visit":
                PageVisitEvent pageVisitEvent = (PageVisitEvent) event;
                eventdto = new PageVisitEventDTO(pageVisitEvent.getData().getPageType(), pageVisitEvent.getData().getPageLanguage(), pageVisitEvent.getAdditionalData());
                break;
            case "select_item":
                SelectItemEvent selectItemEvent = (SelectItemEvent) event;
                eventdto = new SelectItemEventDTO(selectItemEvent.getData().getItem_list_id(), selectItemEvent.getData().getItem_list_name(), new ItemMapper().itemToDto(selectItemEvent.getData().getItem()), selectItemEvent.getAdditionalData());
                break;
            case "view_item":
                ViewItemListEvent viewItemListEvent = (ViewItemListEvent) event;
                eventdto = new ViewItemListEventDTO(viewItemListEvent.getData().getItem_list_id(), viewItemListEvent.getData().getItem_list_name(), new ItemMapper().itemsToDtos(viewItemListEvent.getData().getItems()), viewItemListEvent.getAdditionalData());
                break;
            default:
                break;
        }
        return eventdto;
    }

    public Event dtoToEvent(EventDTO dto) {
        Event event = OtherEvent.builder().build();
        switch(dto.eventName){
            case "other":
                event = new OtherEvent(dto.getAdditionalData());
                break;
            case "element_interaction":
                ElementInteractionEventDTO elementInteractionEventDTO = (ElementInteractionEventDTO) dto;
                event = new ElementInteractionEvent(elementInteractionEventDTO.getData().getElementName(), elementInteractionEventDTO.getAdditionalData());
                break;
            case "element_visibility":
                ElementVisibilityEventDTO elementVisibilityEventDTO = (ElementVisibilityEventDTO) dto;
                event = new ElementVisibilityEvent(elementVisibilityEventDTO.getData().getElementName(), elementVisibilityEventDTO.getAdditionalData());
                break;
            case "page_visit":
                PageVisitEventDTO pageVisitEventDTO = (PageVisitEventDTO) dto;
                event = new PageVisitEvent(pageVisitEventDTO.getData().getPageType(), pageVisitEventDTO.getData().getPageLanguage(), pageVisitEventDTO.getAdditionalData());
                break;
            case "select_item":
                SelectItemEventDTO selectItemEventDTO = (SelectItemEventDTO) dto;
                event = new SelectItemEvent(selectItemEventDTO.getData().getItem_list_id(), selectItemEventDTO.getData().getItem_list_name(), new ItemMapper().dtoToItem(selectItemEventDTO.getData().getItem()), selectItemEventDTO.getAdditionalData());
                break;
            case "view_item":
                ViewItemListEventDTO viewItemListEvent = (ViewItemListEventDTO) dto;
                event = new ViewItemListEvent(viewItemListEvent.getData().getItem_list_id(), viewItemListEvent.getData().getItem_list_name(), new ItemMapper().dtosToEvents(viewItemListEvent.getData().getItems()), viewItemListEvent.getAdditionalData());
                break;
            default:
                break;
        }
        return event;
    }
}
