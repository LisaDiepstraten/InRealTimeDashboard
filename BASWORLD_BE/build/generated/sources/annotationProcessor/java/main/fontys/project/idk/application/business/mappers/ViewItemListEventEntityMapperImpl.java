package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.ViewItemListEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.Item;
import fontys.project.idk.application.models.bas_data.definitions.ViewItemListEvent;
import java.util.ArrayList;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T13:19:51+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
public class ViewItemListEventEntityMapperImpl implements ViewItemListEventEntityMapper {

    @Override
    public ViewItemListEvent toDomain(ViewItemListEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ViewItemListEvent.ViewItemListEventBuilder viewItemListEvent = ViewItemListEvent.builder();

        viewItemListEvent.data( viewItemListEventEntityToData( entity ) );

        return viewItemListEvent.build();
    }

    @Override
    public ViewItemListEventEntity toEntity(ViewItemListEvent domain) {
        if ( domain == null ) {
            return null;
        }

        ViewItemListEventEntity.ViewItemListEventEntityBuilder viewItemListEventEntity = ViewItemListEventEntity.builder();

        viewItemListEventEntity.itemListID( domainDataItem_list_id( domain ) );
        viewItemListEventEntity.itemListName( domainDataItem_list_name( domain ) );
        ArrayList<Item> items = domainDataItems( domain );
        viewItemListEventEntity.items( mapListToSet( items ) );

        return viewItemListEventEntity.build();
    }

    protected ViewItemListEvent.Data viewItemListEventEntityToData(ViewItemListEventEntity viewItemListEventEntity) {
        if ( viewItemListEventEntity == null ) {
            return null;
        }

        ViewItemListEvent.Data.DataBuilder data = ViewItemListEvent.Data.builder();

        data.item_list_id( viewItemListEventEntity.getItemListID() );
        data.item_list_name( viewItemListEventEntity.getItemListName() );
        data.items( mapSetToList( viewItemListEventEntity.getItems() ) );

        return data.build();
    }

    private String domainDataItem_list_id(ViewItemListEvent viewItemListEvent) {
        if ( viewItemListEvent == null ) {
            return null;
        }
        ViewItemListEvent.Data data = viewItemListEvent.getData();
        if ( data == null ) {
            return null;
        }
        String item_list_id = data.getItem_list_id();
        if ( item_list_id == null ) {
            return null;
        }
        return item_list_id;
    }

    private String domainDataItem_list_name(ViewItemListEvent viewItemListEvent) {
        if ( viewItemListEvent == null ) {
            return null;
        }
        ViewItemListEvent.Data data = viewItemListEvent.getData();
        if ( data == null ) {
            return null;
        }
        String item_list_name = data.getItem_list_name();
        if ( item_list_name == null ) {
            return null;
        }
        return item_list_name;
    }

    private ArrayList<Item> domainDataItems(ViewItemListEvent viewItemListEvent) {
        if ( viewItemListEvent == null ) {
            return null;
        }
        ViewItemListEvent.Data data = viewItemListEvent.getData();
        if ( data == null ) {
            return null;
        }
        ArrayList<Item> items = data.getItems();
        if ( items == null ) {
            return null;
        }
        return items;
    }
}
