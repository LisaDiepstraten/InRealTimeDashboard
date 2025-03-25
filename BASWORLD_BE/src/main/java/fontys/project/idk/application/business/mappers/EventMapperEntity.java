package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.controller.mappers.EventMapper;
import fontys.project.idk.application.dal.entity.BASWorldEventEntity;
import fontys.project.idk.application.dal.entity.definitions.*;
import fontys.project.idk.application.dal.entity.eventHeaders.EventHeadersEntity;
import fontys.project.idk.application.dal.entity.eventPayload.EventPayloadEntity;
import fontys.project.idk.application.dal.entity.eventPayload.application.BASWorldApplicationEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.ContextEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.client.*;
import fontys.project.idk.application.dal.entity.eventPayload.context.page.PageEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.session.SessionEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.session.UtmParametersEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.session.VendorClicksIDEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.user.UserEntity;
import fontys.project.idk.application.dal.entity.eventPayload.events.EventEntity;
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
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Mapper
public interface EventMapperEntity {
    EventMapperEntity INSTANCE = Mappers.getMapper(EventMapperEntity.class);

    // Inject the specific mappers as dependencies
    PageVisitEventEntityMapper pageVisitEventEntityMapper = PageVisitEventEntityMapper.INSTANCE;
    ElementInteractionEventEntityMapper elementInteractionEventEntityMapper = ElementInteractionEventEntityMapper.INSTANCE;
    ElementVisibilityEventEntityMapper elementVisibilityEventEntityMapper = ElementVisibilityEventEntityMapper.INSTANCE;
    SelectItemEventEntityMapper selectItemEventEntityMapper = SelectItemEventEntityMapper.INSTANCE;
    ViewItemListEventEntityMapper viewItemListEventEntityMapper = ViewItemListEventEntityMapper.INSTANCE;
    OtherEventEntityMapper otherEventEntityMapper = OtherEventEntityMapper.INSTANCE;

    default Event toDomain(EventEntity entity) {
        if (entity == null) return null;

        Event event;
        switch (entity.getEventName()) {
            case "page_visit":
                event = pageVisitEventEntityMapper.toDomain((PageVisitEventEntity) entity);
                break;
            case "element_interaction":
                event = elementInteractionEventEntityMapper.toDomain((ElementInteractionEventEntity) entity);
                break;
            case "element_visibility":
                event = elementVisibilityEventEntityMapper.toDomain((ElementVisibilityEventEntity) entity);
                break;
            case "select_item":
                event = selectItemEventEntityMapper.toDomain((SelectItemEventEntity) entity);
                break;
            case "view_item_list":
                event = viewItemListEventEntityMapper.toDomain((ViewItemListEventEntity) entity);
                break;
            case "other":
                event = otherEventEntityMapper.toDomain((OtherEventEntity) entity);
                break;
            default:
                throw new IllegalArgumentException("Unknown event type: " + entity.getEventName());
        }

        // Set additionalData and eventName after mapping
        event.setAdditionalData(entity.getAdditionalData());
        event.setEventName(entity.getEventName());

        return event;
    }

    default EventEntity toEntity(Event domain) {
        if (domain == null) return null;

        EventEntity entity;
        if (domain instanceof PageVisitEvent) {
            entity = pageVisitEventEntityMapper.toEntity((PageVisitEvent) domain);
        } else if (domain instanceof ElementInteractionEvent) {
            entity = elementInteractionEventEntityMapper.toEntity((ElementInteractionEvent) domain);
        } else if (domain instanceof ElementVisibilityEvent) {
            entity = elementVisibilityEventEntityMapper.toEntity((ElementVisibilityEvent) domain);
        } else if (domain instanceof SelectItemEvent) {
            entity = selectItemEventEntityMapper.toEntity((SelectItemEvent) domain);
        } else if (domain instanceof ViewItemListEvent) {
            entity = viewItemListEventEntityMapper.toEntity((ViewItemListEvent) domain);
        } else if (domain instanceof OtherEvent) {
            entity = otherEventEntityMapper.toEntity((OtherEvent) domain);
        } else {
            throw new IllegalArgumentException("Unknown event type: " + domain.getClass().getName());
        }

        // Set additionalData and eventName after mapping
        entity.setAdditionalData(domain.getAdditionalData());
        entity.setEventName(domain.getEventName());

        return entity;
    }



    // In EventMapperEntity
    BasWorldEvent toDomain(BASWorldEventEntity entity);
    BASWorldEventEntity toEntity(BasWorldEvent domain);

    List<BasWorldEvent> toDomain(List<BASWorldEventEntity> entities);
    List<BASWorldEventEntity> toEntity(List<BasWorldEvent> domain);


    // Map BASWorldApplication
    BASWorldApplication toDomain(BASWorldApplicationEntity entity);
    BASWorldApplicationEntity toEntity(BASWorldApplication domain);

    // Map User
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);


    // Map VendorClicksID
    VendorClicksId toDomain(VendorClicksIDEntity entity);
    VendorClicksIDEntity toEntity(VendorClicksId domain);

    // Map UTMParameters
    UtmParameters toDomain(UtmParametersEntity entity);
    UtmParametersEntity toEntity(UtmParameters domain);

    // Map Session
    Session toDomain(SessionEntity entity);
    SessionEntity toEntity(Session domain);

    // Map VendorClientIDs
    VendorClientIds toDomain(VendorClientIDsEntity entity);
    VendorClientIDsEntity toEntity(VendorClientIds domain);

    // Map Device
    Device toDomain(DeviceEntity entity);
    DeviceEntity toEntity(Device domain);

    // Map Engine
    Engine toDomain(EngineEntity entity);
    EngineEntity toEntity(Engine domain);

    // Map Geolocation
    Geolocation toDomain(GeolocationEntity entity);
    GeolocationEntity toEntity(Geolocation domain);

    // Map OperatingSystem
    OperatingSystem toDomain(OperatingSystemEntity entity);
    OperatingSystemEntity toEntity(OperatingSystem domain);

    // Map Browser
    Browser toDomain(BrowserEntity entity);
    BrowserEntity toEntity(Browser domain);

    // Map Client
    Client toDomain(ClientEntity entity);
    ClientEntity toEntity(Client domain);



    // Map Item
    Item toDomain(ItemEntity entity);
    ItemEntity toEntity(Item domain);

    // Map EventHeaders
    EventHeaders toDomain(EventHeadersEntity entity);
    EventHeadersEntity toEntity(EventHeaders domain);

    // Map Context
    Context toDomain(ContextEntity entity);
    ContextEntity toEntity(Context domain);

    // Map Page
    @Mapping(target = "designSize", source = "designSize")
    @Mapping(target = "url", source = "entity.url") // Map String URL to URI
    Page toDomain(PageEntity entity);


    @Mapping(target = "designSize", source = "designSize")
    @Mapping(target = "url", expression = "java(uriToString(domain.getUrl()))") // Convert URI to String
    PageEntity toEntity(Page domain);


    // Mapping methods to convert between DesignSize and int
    // Convert from DesignSizeEnum to Integer
    default int toInteger(DesignSizeEnum designSize) {
        return designSize != null ? designSize.ordinal() : -1; // Use -1 or any default value for null
    }

    // Convert from Integer to DesignSizeEnum
    default DesignSizeEnum toDesignSizeEnum(int value) {
        if (value < 0 || value >= DesignSizeEnum.values().length) {
            throw new IllegalArgumentException("Unknown int value for DesignSizeEnum: " + value);
        }
        return DesignSizeEnum.values()[value]; // Gets the enum constant by ordinal
    }


    // Other mappings ...

    default URI stringToURI(String url) {
        if (url == null) return null; // Handle nulls gracefully
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL: " + url, e);
        }
    }

    default String uriToString(URI uri) {
        return uri != null ? uri.toString() : null; // Handle nulls gracefully
    }

    ElementInteractionEvent toDomain(ElementInteractionEventEntity entity);
    ElementInteractionEventEntity toEntity(ElementInteractionEvent domain);

    ElementVisibilityEvent toDomain(ElementVisibilityEventEntity entity);
    ElementVisibilityEventEntity toEntity(ElementVisibilityEvent domain);

    SelectItemEvent toDomain(SelectItemEventEntity entity);
    SelectItemEventEntity toEntity(SelectItemEvent domain);

    ViewItemListEvent toDomain(ViewItemListEventEntity entity);
    ViewItemListEventEntity toEntity(ViewItemListEvent domain);

    OtherEvent toDomain(OtherEventEntity entity);
    OtherEventEntity toEntity(OtherEvent domain);
}
