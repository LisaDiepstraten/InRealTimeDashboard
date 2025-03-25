package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.ItemEntity;
import fontys.project.idk.application.dal.entity.definitions.SelectItemEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.Item;
import fontys.project.idk.application.models.bas_data.definitions.SelectItemEvent;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T13:19:51+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
public class SelectItemEventEntityMapperImpl implements SelectItemEventEntityMapper {

    @Override
    public SelectItemEvent toDomain(SelectItemEventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SelectItemEvent.SelectItemEventBuilder selectItemEvent = SelectItemEvent.builder();

        selectItemEvent.data( selectItemEventEntityToData( entity ) );

        return selectItemEvent.build();
    }

    @Override
    public SelectItemEventEntity toEntity(SelectItemEvent domain) {
        if ( domain == null ) {
            return null;
        }

        SelectItemEventEntity.SelectItemEventEntityBuilder selectItemEventEntity = SelectItemEventEntity.builder();

        selectItemEventEntity.itemListID( domainDataItem_list_id( domain ) );
        selectItemEventEntity.itemListName( domainDataItem_list_name( domain ) );
        selectItemEventEntity.item( itemToItemEntity( domainDataItem( domain ) ) );

        return selectItemEventEntity.build();
    }

    protected Item itemEntityToItem(ItemEntity itemEntity) {
        if ( itemEntity == null ) {
            return null;
        }

        Item.ItemBuilder item = Item.builder();

        item.item_id( itemEntity.getItem_id() );
        item.item_name( itemEntity.getItem_name() );
        item.affiliation( itemEntity.getAffiliation() );
        item.item_brand( itemEntity.getItem_brand() );
        item.item_category( itemEntity.getItem_category() );
        item.item_category2( itemEntity.getItem_category2() );
        item.item_list_id( itemEntity.getItem_list_id() );
        item.item_list_name( itemEntity.getItem_list_name() );
        item.index( itemEntity.getIndex() );
        item.item_variant( itemEntity.getItem_variant() );
        item.price( itemEntity.getPrice() );
        item.quantity( itemEntity.getQuantity() );

        return item.build();
    }

    protected SelectItemEvent.Data selectItemEventEntityToData(SelectItemEventEntity selectItemEventEntity) {
        if ( selectItemEventEntity == null ) {
            return null;
        }

        SelectItemEvent.Data.DataBuilder data = SelectItemEvent.Data.builder();

        data.item_list_id( selectItemEventEntity.getItemListID() );
        data.item_list_name( selectItemEventEntity.getItemListName() );
        data.item( itemEntityToItem( selectItemEventEntity.getItem() ) );

        return data.build();
    }

    private String domainDataItem_list_id(SelectItemEvent selectItemEvent) {
        if ( selectItemEvent == null ) {
            return null;
        }
        SelectItemEvent.Data data = selectItemEvent.getData();
        if ( data == null ) {
            return null;
        }
        String item_list_id = data.getItem_list_id();
        if ( item_list_id == null ) {
            return null;
        }
        return item_list_id;
    }

    private String domainDataItem_list_name(SelectItemEvent selectItemEvent) {
        if ( selectItemEvent == null ) {
            return null;
        }
        SelectItemEvent.Data data = selectItemEvent.getData();
        if ( data == null ) {
            return null;
        }
        String item_list_name = data.getItem_list_name();
        if ( item_list_name == null ) {
            return null;
        }
        return item_list_name;
    }

    private Item domainDataItem(SelectItemEvent selectItemEvent) {
        if ( selectItemEvent == null ) {
            return null;
        }
        SelectItemEvent.Data data = selectItemEvent.getData();
        if ( data == null ) {
            return null;
        }
        Item item = data.getItem();
        if ( item == null ) {
            return null;
        }
        return item;
    }

    protected ItemEntity itemToItemEntity(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemEntity.ItemEntityBuilder itemEntity = ItemEntity.builder();

        itemEntity.item_id( item.getItem_id() );
        itemEntity.item_name( item.getItem_name() );
        itemEntity.affiliation( item.getAffiliation() );
        itemEntity.item_brand( item.getItem_brand() );
        itemEntity.item_category( item.getItem_category() );
        itemEntity.item_category2( item.getItem_category2() );
        itemEntity.item_list_id( item.getItem_list_id() );
        itemEntity.item_list_name( item.getItem_list_name() );
        itemEntity.index( item.getIndex() );
        itemEntity.item_variant( item.getItem_variant() );
        itemEntity.price( item.getPrice() );
        itemEntity.quantity( item.getQuantity() );

        return itemEntity.build();
    }
}
