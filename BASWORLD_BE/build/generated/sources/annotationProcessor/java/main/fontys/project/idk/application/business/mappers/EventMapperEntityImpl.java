package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.BASWorldEventEntity;
import fontys.project.idk.application.dal.entity.definitions.ElementInteractionEventEntity;
import fontys.project.idk.application.dal.entity.definitions.ElementVisibilityEventEntity;
import fontys.project.idk.application.dal.entity.definitions.ItemEntity;
import fontys.project.idk.application.dal.entity.definitions.OtherEventEntity;
import fontys.project.idk.application.dal.entity.definitions.SelectItemEventEntity;
import fontys.project.idk.application.dal.entity.definitions.ViewItemListEventEntity;
import fontys.project.idk.application.dal.entity.eventHeaders.EventHeadersEntity;
import fontys.project.idk.application.dal.entity.eventPayload.EventPayloadEntity;
import fontys.project.idk.application.dal.entity.eventPayload.application.BASWorldApplicationEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.ContextEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.client.BrowserEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.client.ClientEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.client.DeviceEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.client.EngineEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.client.GeolocationEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.client.OperatingSystemEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.client.VendorClientIDsEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.page.PageEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.session.SessionEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.session.UtmParametersEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.session.VendorClicksIDEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.user.UserEntity;
import fontys.project.idk.application.models.bas_data.BasWorldEvent;
import fontys.project.idk.application.models.bas_data.definitions.ElementInteractionEvent;
import fontys.project.idk.application.models.bas_data.definitions.ElementVisibilityEvent;
import fontys.project.idk.application.models.bas_data.definitions.Item;
import fontys.project.idk.application.models.bas_data.definitions.OtherEvent;
import fontys.project.idk.application.models.bas_data.definitions.SelectItemEvent;
import fontys.project.idk.application.models.bas_data.definitions.ViewItemListEvent;
import fontys.project.idk.application.models.bas_data.eventHeaders.EventHeaders;
import fontys.project.idk.application.models.bas_data.eventPayload.EventPayload;
import fontys.project.idk.application.models.bas_data.eventPayload.application.BASWorldApplication;
import fontys.project.idk.application.models.bas_data.eventPayload.context.Context;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.Browser;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.Client;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.Device;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.Engine;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.Geolocation;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.OperatingSystem;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.VendorClientIds;
import fontys.project.idk.application.models.bas_data.eventPayload.context.page.Page;
import fontys.project.idk.application.models.bas_data.eventPayload.context.session.Session;
import fontys.project.idk.application.models.bas_data.eventPayload.context.session.UtmParameters;
import fontys.project.idk.application.models.bas_data.eventPayload.context.session.VendorClicksId;
import fontys.project.idk.application.models.bas_data.eventPayload.context.user.User;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T13:19:51+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
public class EventMapperEntityImpl implements EventMapperEntity {

    @Override
    public BasWorldEvent toDomain(BASWorldEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BasWorldEvent.BasWorldEventBuilder basWorldEvent = BasWorldEvent.builder();

        basWorldEvent.event( entity.getEvent() );
        basWorldEvent.eventPayload( eventPayloadEntityToEventPayload( entity.getEventPayload() ) );
        basWorldEvent.eventHeaders( toDomain( entity.getEventHeaders() ) );

        return basWorldEvent.build();
    }

    @Override
    public BASWorldEventEntity toEntity(BasWorldEvent domain) {
        if ( domain == null ) {
            return null;
        }

        BASWorldEventEntity.BASWorldEventEntityBuilder bASWorldEventEntity = BASWorldEventEntity.builder();

        bASWorldEventEntity.event( domain.getEvent() );
        bASWorldEventEntity.eventPayload( eventPayloadToEventPayloadEntity( domain.getEventPayload() ) );
        bASWorldEventEntity.eventHeaders( toEntity( domain.getEventHeaders() ) );

        return bASWorldEventEntity.build();
    }

    @Override
    public List<BasWorldEvent> toDomain(List<BASWorldEventEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BasWorldEvent> list = new ArrayList<BasWorldEvent>( entities.size() );
        for ( BASWorldEventEntity bASWorldEventEntity : entities ) {
            list.add( toDomain( bASWorldEventEntity ) );
        }

        return list;
    }

    @Override
    public List<BASWorldEventEntity> toEntity(List<BasWorldEvent> domain) {
        if ( domain == null ) {
            return null;
        }

        List<BASWorldEventEntity> list = new ArrayList<BASWorldEventEntity>( domain.size() );
        for ( BasWorldEvent basWorldEvent : domain ) {
            list.add( toEntity( basWorldEvent ) );
        }

        return list;
    }

    @Override
    public BASWorldApplication toDomain(BASWorldApplicationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BASWorldApplication.BASWorldApplicationBuilder bASWorldApplication = BASWorldApplication.builder();

        bASWorldApplication.name( entity.getName() );
        bASWorldApplication.version( entity.getVersion() );
        bASWorldApplication.environment( entity.getEnvironment() );

        return bASWorldApplication.build();
    }

    @Override
    public BASWorldApplicationEntity toEntity(BASWorldApplication domain) {
        if ( domain == null ) {
            return null;
        }

        BASWorldApplicationEntity.BASWorldApplicationEntityBuilder bASWorldApplicationEntity = BASWorldApplicationEntity.builder();

        bASWorldApplicationEntity.name( domain.getName() );
        bASWorldApplicationEntity.version( domain.getVersion() );
        bASWorldApplicationEntity.environment( domain.getEnvironment() );

        return bASWorldApplicationEntity.build();
    }

    @Override
    public User toDomain(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( entity.getUserId() );
        user.email( entity.getEmail() );
        user.personId( entity.getPersonId() );
        user.personEmail( entity.getPersonEmail() );
        user.companyId( entity.getCompanyId() );
        user.companyName( entity.getCompanyName() );

        return user.build();
    }

    @Override
    public UserEntity toEntity(User domain) {
        if ( domain == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userId( domain.getUserId() );
        userEntity.email( domain.getEmail() );
        userEntity.personId( domain.getPersonId() );
        userEntity.personEmail( domain.getPersonEmail() );
        userEntity.companyId( domain.getCompanyId() );
        userEntity.companyName( domain.getCompanyName() );

        return userEntity.build();
    }

    @Override
    public VendorClicksId toDomain(VendorClicksIDEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VendorClicksId.VendorClicksIdBuilder vendorClicksId = VendorClicksId.builder();

        vendorClicksId.google( entity.getGoogle() );
        vendorClicksId.bing( entity.getBing() );
        vendorClicksId.facebook( entity.getFacebook() );
        vendorClicksId.linkedIn( entity.getLinkedIn() );

        return vendorClicksId.build();
    }

    @Override
    public VendorClicksIDEntity toEntity(VendorClicksId domain) {
        if ( domain == null ) {
            return null;
        }

        VendorClicksIDEntity.VendorClicksIDEntityBuilder vendorClicksIDEntity = VendorClicksIDEntity.builder();

        vendorClicksIDEntity.google( domain.getGoogle() );
        vendorClicksIDEntity.bing( domain.getBing() );
        vendorClicksIDEntity.facebook( domain.getFacebook() );
        vendorClicksIDEntity.linkedIn( domain.getLinkedIn() );

        return vendorClicksIDEntity.build();
    }

    @Override
    public UtmParameters toDomain(UtmParametersEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UtmParameters.UtmParametersBuilder utmParameters = UtmParameters.builder();

        utmParameters.source( entity.getSource() );
        utmParameters.medium( entity.getMedium() );
        utmParameters.campaign( entity.getCampaign() );
        utmParameters.term( entity.getTerm() );
        utmParameters.content( entity.getContent() );

        return utmParameters.build();
    }

    @Override
    public UtmParametersEntity toEntity(UtmParameters domain) {
        if ( domain == null ) {
            return null;
        }

        UtmParametersEntity.UtmParametersEntityBuilder utmParametersEntity = UtmParametersEntity.builder();

        utmParametersEntity.source( domain.getSource() );
        utmParametersEntity.medium( domain.getMedium() );
        utmParametersEntity.campaign( domain.getCampaign() );
        utmParametersEntity.term( domain.getTerm() );
        utmParametersEntity.content( domain.getContent() );

        return utmParametersEntity.build();
    }

    @Override
    public Session toDomain(SessionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Session.SessionBuilder session = Session.builder();

        session.buttSessionId( entity.getButtSessionId() );
        session.vendorClicksIds( toDomain( entity.getVendorClicksIds() ) );
        session.referer( entity.getReferer() );
        session.utmParameters( toDomain( entity.getUtmParameters() ) );

        return session.build();
    }

    @Override
    public SessionEntity toEntity(Session domain) {
        if ( domain == null ) {
            return null;
        }

        SessionEntity.SessionEntityBuilder sessionEntity = SessionEntity.builder();

        sessionEntity.buttSessionId( domain.getButtSessionId() );
        sessionEntity.referer( domain.getReferer() );
        sessionEntity.vendorClicksIds( toEntity( domain.getVendorClicksIds() ) );
        sessionEntity.utmParameters( toEntity( domain.getUtmParameters() ) );

        return sessionEntity.build();
    }

    @Override
    public VendorClientIds toDomain(VendorClientIDsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VendorClientIds.VendorClientIdsBuilder vendorClientIds = VendorClientIds.builder();

        vendorClientIds.googleAnalytics( entity.getGoogleAnalytics() );
        vendorClientIds.bing( entity.getBing() );
        vendorClientIds.facebook( entity.getFacebook() );

        return vendorClientIds.build();
    }

    @Override
    public VendorClientIDsEntity toEntity(VendorClientIds domain) {
        if ( domain == null ) {
            return null;
        }

        VendorClientIDsEntity.VendorClientIDsEntityBuilder vendorClientIDsEntity = VendorClientIDsEntity.builder();

        vendorClientIDsEntity.googleAnalytics( domain.getGoogleAnalytics() );
        vendorClientIDsEntity.bing( domain.getBing() );
        vendorClientIDsEntity.facebook( domain.getFacebook() );

        return vendorClientIDsEntity.build();
    }

    @Override
    public Device toDomain(DeviceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Device.DeviceBuilder device = Device.builder();

        device.type( entity.getType() );
        device.vendor( entity.getVendor() );
        device.model( entity.getModel() );
        device.screenWidth( entity.getScreenWidth() );
        device.screenHeight( entity.getScreenHeight() );

        return device.build();
    }

    @Override
    public DeviceEntity toEntity(Device domain) {
        if ( domain == null ) {
            return null;
        }

        DeviceEntity.DeviceEntityBuilder deviceEntity = DeviceEntity.builder();

        deviceEntity.type( domain.getType() );
        deviceEntity.vendor( domain.getVendor() );
        deviceEntity.model( domain.getModel() );
        deviceEntity.screenWidth( domain.getScreenWidth() );
        deviceEntity.screenHeight( domain.getScreenHeight() );

        return deviceEntity.build();
    }

    @Override
    public Engine toDomain(EngineEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Engine.EngineBuilder engine = Engine.builder();

        engine.name( entity.getName() );
        engine.version( entity.getVersion() );

        return engine.build();
    }

    @Override
    public EngineEntity toEntity(Engine domain) {
        if ( domain == null ) {
            return null;
        }

        EngineEntity.EngineEntityBuilder engineEntity = EngineEntity.builder();

        engineEntity.name( domain.getName() );
        engineEntity.version( domain.getVersion() );

        return engineEntity.build();
    }

    @Override
    public Geolocation toDomain(GeolocationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Geolocation.GeolocationBuilder geolocation = Geolocation.builder();

        geolocation.latitude( entity.getLatitude() );
        geolocation.longitude( entity.getLongitude() );
        geolocation.country( entity.getCountry() );
        geolocation.region( entity.getRegion() );
        geolocation.city( entity.getCity() );

        return geolocation.build();
    }

    @Override
    public GeolocationEntity toEntity(Geolocation domain) {
        if ( domain == null ) {
            return null;
        }

        GeolocationEntity.GeolocationEntityBuilder geolocationEntity = GeolocationEntity.builder();

        geolocationEntity.latitude( domain.getLatitude() );
        geolocationEntity.longitude( domain.getLongitude() );
        geolocationEntity.country( domain.getCountry() );
        geolocationEntity.region( domain.getRegion() );
        geolocationEntity.city( domain.getCity() );

        return geolocationEntity.build();
    }

    @Override
    public OperatingSystem toDomain(OperatingSystemEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OperatingSystem.OperatingSystemBuilder operatingSystem = OperatingSystem.builder();

        operatingSystem.name( entity.getName() );
        operatingSystem.version( entity.getVersion() );

        return operatingSystem.build();
    }

    @Override
    public OperatingSystemEntity toEntity(OperatingSystem domain) {
        if ( domain == null ) {
            return null;
        }

        OperatingSystemEntity.OperatingSystemEntityBuilder operatingSystemEntity = OperatingSystemEntity.builder();

        operatingSystemEntity.name( domain.getName() );
        operatingSystemEntity.version( domain.getVersion() );

        return operatingSystemEntity.build();
    }

    @Override
    public Browser toDomain(BrowserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Browser.BrowserBuilder browser = Browser.builder();

        browser.name( entity.getName() );
        browser.version( entity.getVersion() );
        browser.viewportWidth( entity.getViewportWidth() );
        browser.viewportHeight( entity.getViewportHeight() );
        browser.language( entity.getLanguage() );
        browser.cookiesEnabled( entity.isCookiesEnabled() );
        browser.doNotTrackEnabled( entity.isDoNotTrackEnabled() );

        return browser.build();
    }

    @Override
    public BrowserEntity toEntity(Browser domain) {
        if ( domain == null ) {
            return null;
        }

        BrowserEntity.BrowserEntityBuilder browserEntity = BrowserEntity.builder();

        browserEntity.name( domain.getName() );
        browserEntity.version( domain.getVersion() );
        browserEntity.viewportWidth( domain.getViewportWidth() );
        browserEntity.viewportHeight( domain.getViewportHeight() );
        browserEntity.language( domain.getLanguage() );
        browserEntity.cookiesEnabled( domain.isCookiesEnabled() );
        browserEntity.doNotTrackEnabled( domain.isDoNotTrackEnabled() );

        return browserEntity.build();
    }

    @Override
    public Client toDomain(ClientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.buttClientId( entity.getButtClientId() );
        client.rawUserAgent( entity.getRawUserAgent() );
        client.vendorClientIds( toDomain( entity.getVendorClientIds() ) );
        client.device( toDomain( entity.getDevice() ) );
        client.operatingSystem( toDomain( entity.getOperatingSystem() ) );
        client.browser( toDomain( entity.getBrowser() ) );
        client.engine( toDomain( entity.getEngine() ) );
        client.geolocation( toDomain( entity.getGeolocation() ) );

        return client.build();
    }

    @Override
    public ClientEntity toEntity(Client domain) {
        if ( domain == null ) {
            return null;
        }

        ClientEntity.ClientEntityBuilder clientEntity = ClientEntity.builder();

        clientEntity.buttClientId( domain.getButtClientId() );
        clientEntity.rawUserAgent( domain.getRawUserAgent() );
        clientEntity.vendorClientIds( toEntity( domain.getVendorClientIds() ) );
        clientEntity.device( toEntity( domain.getDevice() ) );
        clientEntity.operatingSystem( toEntity( domain.getOperatingSystem() ) );
        clientEntity.browser( toEntity( domain.getBrowser() ) );
        clientEntity.engine( toEntity( domain.getEngine() ) );
        clientEntity.geolocation( toEntity( domain.getGeolocation() ) );

        return clientEntity.build();
    }

    @Override
    public Item toDomain(ItemEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Item.ItemBuilder item = Item.builder();

        item.item_id( entity.getItem_id() );
        item.item_name( entity.getItem_name() );
        item.affiliation( entity.getAffiliation() );
        item.item_brand( entity.getItem_brand() );
        item.item_category( entity.getItem_category() );
        item.item_category2( entity.getItem_category2() );
        item.item_list_id( entity.getItem_list_id() );
        item.item_list_name( entity.getItem_list_name() );
        item.index( entity.getIndex() );
        item.item_variant( entity.getItem_variant() );
        item.price( entity.getPrice() );
        item.quantity( entity.getQuantity() );

        return item.build();
    }

    @Override
    public ItemEntity toEntity(Item domain) {
        if ( domain == null ) {
            return null;
        }

        ItemEntity.ItemEntityBuilder itemEntity = ItemEntity.builder();

        itemEntity.item_id( domain.getItem_id() );
        itemEntity.item_name( domain.getItem_name() );
        itemEntity.affiliation( domain.getAffiliation() );
        itemEntity.item_brand( domain.getItem_brand() );
        itemEntity.item_category( domain.getItem_category() );
        itemEntity.item_category2( domain.getItem_category2() );
        itemEntity.item_list_id( domain.getItem_list_id() );
        itemEntity.item_list_name( domain.getItem_list_name() );
        itemEntity.index( domain.getIndex() );
        itemEntity.item_variant( domain.getItem_variant() );
        itemEntity.price( domain.getPrice() );
        itemEntity.quantity( domain.getQuantity() );

        return itemEntity.build();
    }

    @Override
    public EventHeaders toDomain(EventHeadersEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EventHeaders.EventHeadersBuilder eventHeaders = EventHeaders.builder();

        eventHeaders.entity( entity.getEntity() );
        eventHeaders.entityKey( entity.getEntityKey() );
        eventHeaders.eventMainType( entity.getEventMainType() );
        eventHeaders.eventSubType( entity.getEventSubType() );
        eventHeaders.eventTimeStamp( entity.getEventTimeStamp() );
        eventHeaders.publishedBy( entity.getPublishedBy() );
        eventHeaders.policyVersion( entity.getPolicyVersion() );

        return eventHeaders.build();
    }

    @Override
    public EventHeadersEntity toEntity(EventHeaders domain) {
        if ( domain == null ) {
            return null;
        }

        EventHeadersEntity.EventHeadersEntityBuilder eventHeadersEntity = EventHeadersEntity.builder();

        eventHeadersEntity.entity( domain.getEntity() );
        eventHeadersEntity.entityKey( domain.getEntityKey() );
        eventHeadersEntity.eventMainType( domain.getEventMainType() );
        eventHeadersEntity.eventSubType( domain.getEventSubType() );
        eventHeadersEntity.eventTimeStamp( domain.getEventTimeStamp() );
        eventHeadersEntity.publishedBy( domain.getPublishedBy() );
        eventHeadersEntity.policyVersion( domain.getPolicyVersion() );

        return eventHeadersEntity.build();
    }

    @Override
    public Context toDomain(ContextEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Context.ContextBuilder context = Context.builder();

        context.page( toDomain( entity.getPage() ) );
        context.session( toDomain( entity.getSession() ) );
        context.client( toDomain( entity.getClient() ) );
        context.user( toDomain( entity.getUser() ) );

        return context.build();
    }

    @Override
    public ContextEntity toEntity(Context domain) {
        if ( domain == null ) {
            return null;
        }

        ContextEntity.ContextEntityBuilder contextEntity = ContextEntity.builder();

        contextEntity.page( toEntity( domain.getPage() ) );
        contextEntity.session( toEntity( domain.getSession() ) );
        contextEntity.client( toEntity( domain.getClient() ) );
        contextEntity.user( toEntity( domain.getUser() ) );

        return contextEntity.build();
    }

    @Override
    public Page toDomain(PageEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Page.PageBuilder page = Page.builder();

        page.designSize( toDesignSizeEnum( entity.getDesignSize() ) );
        page.url( stringToURI( entity.getUrl() ) );
        page.title( entity.getTitle() );
        page.canonicalUrl( stringToURI( entity.getCanonicalUrl() ) );

        return page.build();
    }

    @Override
    public PageEntity toEntity(Page domain) {
        if ( domain == null ) {
            return null;
        }

        PageEntity.PageEntityBuilder pageEntity = PageEntity.builder();

        pageEntity.designSize( toInteger( domain.getDesignSize() ) );
        pageEntity.title( domain.getTitle() );
        pageEntity.canonicalUrl( uriToString( domain.getCanonicalUrl() ) );

        pageEntity.url( uriToString(domain.getUrl()) );

        return pageEntity.build();
    }

    @Override
    public ElementInteractionEvent toDomain(ElementInteractionEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ElementInteractionEvent.ElementInteractionEventBuilder elementInteractionEvent = ElementInteractionEvent.builder();

        return elementInteractionEvent.build();
    }

    @Override
    public ElementInteractionEventEntity toEntity(ElementInteractionEvent domain) {
        if ( domain == null ) {
            return null;
        }

        ElementInteractionEventEntity.ElementInteractionEventEntityBuilder elementInteractionEventEntity = ElementInteractionEventEntity.builder();

        return elementInteractionEventEntity.build();
    }

    @Override
    public ElementVisibilityEvent toDomain(ElementVisibilityEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ElementVisibilityEvent.ElementVisibilityEventBuilder elementVisibilityEvent = ElementVisibilityEvent.builder();

        return elementVisibilityEvent.build();
    }

    @Override
    public ElementVisibilityEventEntity toEntity(ElementVisibilityEvent domain) {
        if ( domain == null ) {
            return null;
        }

        ElementVisibilityEventEntity.ElementVisibilityEventEntityBuilder elementVisibilityEventEntity = ElementVisibilityEventEntity.builder();

        return elementVisibilityEventEntity.build();
    }

    @Override
    public SelectItemEvent toDomain(SelectItemEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SelectItemEvent.SelectItemEventBuilder selectItemEvent = SelectItemEvent.builder();

        return selectItemEvent.build();
    }

    @Override
    public SelectItemEventEntity toEntity(SelectItemEvent domain) {
        if ( domain == null ) {
            return null;
        }

        SelectItemEventEntity.SelectItemEventEntityBuilder selectItemEventEntity = SelectItemEventEntity.builder();

        return selectItemEventEntity.build();
    }

    @Override
    public ViewItemListEvent toDomain(ViewItemListEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ViewItemListEvent.ViewItemListEventBuilder viewItemListEvent = ViewItemListEvent.builder();

        return viewItemListEvent.build();
    }

    @Override
    public ViewItemListEventEntity toEntity(ViewItemListEvent domain) {
        if ( domain == null ) {
            return null;
        }

        ViewItemListEventEntity.ViewItemListEventEntityBuilder viewItemListEventEntity = ViewItemListEventEntity.builder();

        return viewItemListEventEntity.build();
    }

    @Override
    public OtherEvent toDomain(OtherEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OtherEvent.OtherEventBuilder otherEvent = OtherEvent.builder();

        return otherEvent.build();
    }

    @Override
    public OtherEventEntity toEntity(OtherEvent domain) {
        if ( domain == null ) {
            return null;
        }

        OtherEventEntity otherEventEntity = new OtherEventEntity();

        otherEventEntity.setEventName( domain.getEventName() );
        Map<String, Object> map = domain.getAdditionalData();
        if ( map != null ) {
            otherEventEntity.setAdditionalData( new LinkedHashMap<String, Object>( map ) );
        }

        return otherEventEntity;
    }

    protected EventPayload eventPayloadEntityToEventPayload(EventPayloadEntity eventPayloadEntity) {
        if ( eventPayloadEntity == null ) {
            return null;
        }

        EventPayload.EventPayloadBuilder eventPayload = EventPayload.builder();

        eventPayload.application( toDomain( eventPayloadEntity.getApplication() ) );
        eventPayload.context( toDomain( eventPayloadEntity.getContext() ) );
        eventPayload.event( toDomain( eventPayloadEntity.getEvent() ) );

        return eventPayload.build();
    }

    protected EventPayloadEntity eventPayloadToEventPayloadEntity(EventPayload eventPayload) {
        if ( eventPayload == null ) {
            return null;
        }

        EventPayloadEntity.EventPayloadEntityBuilder eventPayloadEntity = EventPayloadEntity.builder();

        eventPayloadEntity.application( toEntity( eventPayload.getApplication() ) );
        eventPayloadEntity.context( toEntity( eventPayload.getContext() ) );
        eventPayloadEntity.event( toEntity( eventPayload.getEvent() ) );

        return eventPayloadEntity.build();
    }
}
