package fontys.project.idk.application.controller.mappers;

import fontys.project.idk.application.controller.dtos.BasWorldEventDTO;
import fontys.project.idk.application.controller.dtos.eventHeaders.EventHeadersDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.EventPayloadDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.application.ApplicationDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.ContextDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.client.*;
import fontys.project.idk.application.controller.dtos.eventPayload.context.page.DesignSizeEnumDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.page.PageDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.session.SessionDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.session.UtmParametersDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.session.VendorClicksIdDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.user.UserDTO;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
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

import java.util.ArrayList;

public class BasWorldEventMapper {
    public BasWorldEventDTO eventToDto(BasWorldEvent event) {
        return BasWorldEventDTO.builder()
                .eventPayload(EventPayloadDTO.builder()
                        .application(ApplicationDTO.builder()
                                .name(event.getEventPayload().getApplication().getName())
                                .version(event.getEventPayload().getApplication().getVersion())
                                .environment(event.getEventPayload().getApplication().getEnvironment())
                                .build())
                        .context(ContextDTO.builder()
                                .client(ClientDTO.builder()
                                        .rawUserAgent(event.getEventPayload().getContext().getClient().getRawUserAgent())
                                        .buttClientId(event.getEventPayload().getContext().getClient().getButtClientId())
                                        .browser(BrowserDTO.builder()
                                                .name(event.getEventPayload().getContext().getClient().getBrowser().getName())
                                                .version(event.getEventPayload().getContext().getClient().getBrowser().getVersion())
                                                .viewportWidth(event.getEventPayload().getContext().getClient().getBrowser().getViewportWidth())
                                                .viewportHeight(event.getEventPayload().getContext().getClient().getBrowser().getViewportHeight())
                                                .language(event.getEventPayload().getContext().getClient().getBrowser().getLanguage())
                                                .cookiesEnabled(event.getEventPayload().getContext().getClient().getBrowser().isCookiesEnabled())
                                                .javaScriptEnabled(event.getEventPayload().getContext().getClient().getBrowser().isJavaScriptEnabled())
                                                .doNotTrackEnabled(event.getEventPayload().getContext().getClient().getBrowser().isDoNotTrackEnabled())
                                                .build())
                                        .device(DeviceDTO.builder()
                                                .type(event.getEventPayload().getContext().getClient().getDevice().getType())
                                                .vendor(event.getEventPayload().getContext().getClient().getDevice().getVendor())
                                                .model(event.getEventPayload().getContext().getClient().getDevice().getModel())
                                                .screenWidth(event.getEventPayload().getContext().getClient().getDevice().getScreenWidth())
                                                .screenHeight(event.getEventPayload().getContext().getClient().getDevice().getScreenHeight())
                                                .build())
                                        .engine(EngineDTO.builder()
                                                .name(event.getEventPayload().getContext().getClient().getEngine().getName())
                                                .version(event.getEventPayload().getContext().getClient().getEngine().getVersion())
                                                .build())
                                        .geolocation(GeolocationDTO.builder()
                                                .latitude(event.getEventPayload().getContext().getClient().getGeolocation().getLatitude())
                                                .longitude(event.getEventPayload().getContext().getClient().getGeolocation().getLongitude())
                                                .country(event.getEventPayload().getContext().getClient().getGeolocation().getCountry())
                                                .region(event.getEventPayload().getContext().getClient().getGeolocation().getRegion())
                                                .city(event.getEventPayload().getContext().getClient().getGeolocation().getCity())
                                                .build())
                                        .operatingSystem(OperatingSystemDTO.builder()
                                                .name(event.getEventPayload().getContext().getClient().getOperatingSystem().getName())
                                                .version(event.getEventPayload().getContext().getClient().getOperatingSystem().getVersion())
                                                .build())
                                        .vendorClientIds(VendorClientIdsDTO.builder()
                                                .googleAnalytics(event.getEventPayload().getContext().getClient().getVendorClientIds().getGoogleAnalytics())
                                                .bing(event.getEventPayload().getContext().getClient().getVendorClientIds().getBing())
                                                .facebook(event.getEventPayload().getContext().getClient().getVendorClientIds().getFacebook())
                                                .build())
                                        .build())
                                .page(PageDTO.builder()
                                        .title(event.getEventPayload().getContext().getPage().getTitle())
                                        .url(event.getEventPayload().getContext().getPage().getUrl())
                                        .canonicalUrl(event.getEventPayload().getContext().getPage().getCanonicalUrl())
                                        .designSize(DesignSizeEnumDTO.fromString(event.getEventPayload().getContext().getPage().getDesignSize().toString()))
                                        .build())
                                .session(SessionDTO.builder()
                                        .buttSessionId(event.getEventPayload().getContext().getSession().getButtSessionId())
                                        .referer(event.getEventPayload().getContext().getSession().getButtSessionId())
                                        .utmParameters(UtmParametersDTO.builder()
                                                .term(event.getEventPayload().getContext().getSession().getUtmParameters().getTerm())
                                                .campaign(event.getEventPayload().getContext().getSession().getUtmParameters().getCampaign())
                                                .content(event.getEventPayload().getContext().getSession().getUtmParameters().getContent())
                                                .medium(event.getEventPayload().getContext().getSession().getUtmParameters().getMedium())
                                                .source(event.getEventPayload().getContext().getSession().getUtmParameters().getSource())
                                                .build())
                                        .vendorClicksIds(VendorClicksIdDTO.builder()
                                                .bing(event.getEventPayload().getContext().getSession().getVendorClicksIds().getBing())
                                                .facebook(event.getEventPayload().getContext().getSession().getVendorClicksIds().getFacebook())
                                                .google(event.getEventPayload().getContext().getSession().getVendorClicksIds().getGoogle())
                                                .linkedIn(event.getEventPayload().getContext().getSession().getVendorClicksIds().getLinkedIn())
                                                .build())
                                        .build())
                                .user(UserDTO.builder()
                                        .userId(event.getEventPayload().getContext().getUser().getUserId())
                                        .userEmailAddress(event.getEventPayload().getContext().getUser().getEmail())
                                        .personId(event.getEventPayload().getContext().getUser().getPersonId())
                                        .personEmailAddress(event.getEventPayload().getContext().getUser().getPersonEmail())
                                        .companyId(event.getEventPayload().getContext().getUser().getCompanyId())
                                        .companyName(event.getEventPayload().getContext().getUser().getCompanyName())
                                        .build())
                                .build())
                        //.event
                        .event(new EventMapper().eventToDto(event.getEventPayload().getEvent()))
                        .build())
                .eventHeaders(EventHeadersDTO.builder()
                        .entity(event.getEventHeaders().getEntity())
                        .entityKey(event.getEventHeaders().getEntityKey())
                        .eventMainType(event.getEventHeaders().getEventMainType())
                        .eventSubType(event.getEventHeaders().getEventSubType())
                        .publishedBy(event.getEventHeaders().getPublishedBy())
                        .eventTimeStamp(event.getEventHeaders().getEventTimeStamp())
                        .policyVersion(event.getEventHeaders().getPolicyVersion())
                        .build())
                .event(event.getEvent())
                .build();
    }

    public ArrayList<BasWorldEventDTO> eventsToDtos(ArrayList<BasWorldEvent> events) {
        ArrayList<BasWorldEventDTO> eventsDTOs = new ArrayList<>();
        for (BasWorldEvent event : events) {
            eventsDTOs.add(eventToDto(event));
        }
        return eventsDTOs;
    }

    public BasWorldEvent dtoToEvent(BasWorldEventDTO dto) {
        return BasWorldEvent.builder()
                .eventPayload(EventPayload.builder()
                        .application(BASWorldApplication.builder()
                                .name(dto.getEventPayload().getApplication().getName())
                                .version(dto.getEventPayload().getApplication().getVersion())
                                .environment(dto.getEventPayload().getApplication().getEnvironment())
                                .build())
                        .context(Context.builder()
                                .client(Client.builder()
                                        .rawUserAgent(dto.getEventPayload().getContext().getClient().getRawUserAgent())
                                        .buttClientId(dto.getEventPayload().getContext().getClient().getButtClientId())
                                        .browser(Browser.builder()
                                                .name(dto.getEventPayload().getContext().getClient().getBrowser().getName())
                                                .version(dto.getEventPayload().getContext().getClient().getBrowser().getVersion())
                                                .viewportWidth(dto.getEventPayload().getContext().getClient().getBrowser().getViewportWidth())
                                                .viewportHeight(dto.getEventPayload().getContext().getClient().getBrowser().getViewportHeight())
                                                .language(dto.getEventPayload().getContext().getClient().getBrowser().getLanguage())
                                                .cookiesEnabled(dto.getEventPayload().getContext().getClient().getBrowser().isCookiesEnabled())
                                                .javaScriptEnabled(dto.getEventPayload().getContext().getClient().getBrowser().isJavaScriptEnabled())
                                                .doNotTrackEnabled(dto.getEventPayload().getContext().getClient().getBrowser().isDoNotTrackEnabled())
                                                .build())
                                        .device(Device.builder()
                                                .type(dto.getEventPayload().getContext().getClient().getDevice().getType())
                                                .vendor(dto.getEventPayload().getContext().getClient().getDevice().getVendor())
                                                .model(dto.getEventPayload().getContext().getClient().getDevice().getModel())
                                                .screenWidth(dto.getEventPayload().getContext().getClient().getDevice().getScreenWidth())
                                                .screenHeight(dto.getEventPayload().getContext().getClient().getDevice().getScreenHeight())
                                                .build())
                                        .engine(Engine.builder()
                                                .name(dto.getEventPayload().getContext().getClient().getEngine().getName())
                                                .version(dto.getEventPayload().getContext().getClient().getEngine().getVersion())
                                                .build())
                                        .geolocation(Geolocation.builder()
                                                .latitude(dto.getEventPayload().getContext().getClient().getGeolocation().getLatitude())
                                                .longitude(dto.getEventPayload().getContext().getClient().getGeolocation().getLongitude())
                                                .country(dto.getEventPayload().getContext().getClient().getGeolocation().getCountry())
                                                .region(dto.getEventPayload().getContext().getClient().getGeolocation().getRegion())
                                                .city(dto.getEventPayload().getContext().getClient().getGeolocation().getCity())
                                                .build())
                                        .operatingSystem(OperatingSystem.builder()
                                                .name(dto.getEventPayload().getContext().getClient().getOperatingSystem().getName())
                                                .version(dto.getEventPayload().getContext().getClient().getOperatingSystem().getVersion())
                                                .build())
                                        .vendorClientIds(VendorClientIds.builder()
                                                .googleAnalytics(dto.getEventPayload().getContext().getClient().getVendorClientIds().getGoogleAnalytics())
                                                .bing(dto.getEventPayload().getContext().getClient().getVendorClientIds().getBing())
                                                .facebook(dto.getEventPayload().getContext().getClient().getVendorClientIds().getFacebook())
                                                .build())
                                        .build())
                                .page(Page.builder()
                                        .title(dto.getEventPayload().getContext().getPage().getTitle())
                                        .url(dto.getEventPayload().getContext().getPage().getUrl())
                                        .canonicalUrl(dto.getEventPayload().getContext().getPage().getCanonicalUrl())
                                        .designSize(DesignSizeEnum.fromString(dto.getEventPayload().getContext().getPage().getDesignSize().toString())) // Convert DTO Enum to normal Enum
                                        .build())
                                .session(Session.builder()
                                        .buttSessionId(dto.getEventPayload().getContext().getSession().getButtSessionId())
                                        .referer(dto.getEventPayload().getContext().getSession().getReferer()) // Use correct field here
                                        .utmParameters(UtmParameters.builder()
                                                .term(dto.getEventPayload().getContext().getSession().getUtmParameters().getTerm())
                                                .campaign(dto.getEventPayload().getContext().getSession().getUtmParameters().getCampaign())
                                                .content(dto.getEventPayload().getContext().getSession().getUtmParameters().getContent())
                                                .medium(dto.getEventPayload().getContext().getSession().getUtmParameters().getMedium())
                                                .source(dto.getEventPayload().getContext().getSession().getUtmParameters().getSource())
                                                .build())
                                        .vendorClicksIds(VendorClicksId.builder()
                                                .bing(dto.getEventPayload().getContext().getSession().getVendorClicksIds().getBing())
                                                .facebook(dto.getEventPayload().getContext().getSession().getVendorClicksIds().getFacebook())
                                                .google(dto.getEventPayload().getContext().getSession().getVendorClicksIds().getGoogle())
                                                .linkedIn(dto.getEventPayload().getContext().getSession().getVendorClicksIds().getLinkedIn())
                                                .build())
                                        .build())
                                .user(User.builder()
                                        .userId(dto.getEventPayload().getContext().getUser().getUserId())
                                        .email(dto.getEventPayload().getContext().getUser().getUserEmailAddress())
                                        .personId(dto.getEventPayload().getContext().getUser().getPersonId())
                                        .personEmail(dto.getEventPayload().getContext().getUser().getPersonEmailAddress())
                                        .companyId(dto.getEventPayload().getContext().getUser().getCompanyId())
                                        .companyName(dto.getEventPayload().getContext().getUser().getCompanyName())
                                        .build())
                                .build())
                        .event(new EventMapper().dtoToEvent(dto.getEventPayload().getEvent()))
                        .build())
                .eventHeaders(EventHeaders.builder()
                        .entity(dto.getEventHeaders().getEntity())
                        .entityKey(dto.getEventHeaders().getEntityKey())
                        .eventMainType(dto.getEventHeaders().getEventMainType())
                        .eventSubType(dto.getEventHeaders().getEventSubType())
                        .publishedBy(dto.getEventHeaders().getPublishedBy())
                        .eventTimeStamp(dto.getEventHeaders().getEventTimeStamp())
                        .policyVersion(dto.getEventHeaders().getPolicyVersion())
                        .build())
                .event(dto.getEvent())
                .build();
    }

    public ArrayList<BasWorldEvent> dtosToEvents(ArrayList<BasWorldEventDTO> dtos) {
        ArrayList<BasWorldEvent> events = new ArrayList<>();
        for (BasWorldEventDTO eventDTO : dtos) {
            events.add(dtoToEvent(eventDTO));
        }
        return events;
    }
}
