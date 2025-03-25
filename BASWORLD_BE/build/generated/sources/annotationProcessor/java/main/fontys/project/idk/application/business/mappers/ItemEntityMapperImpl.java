package fontys.project.idk.application.business.mappers;

import fontys.project.idk.application.dal.entity.definitions.ItemEntity;
import fontys.project.idk.application.models.bas_data.definitions.Item;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T13:19:50+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.1.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
public class ItemEntityMapperImpl implements ItemEntityMapper {

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
    public ItemEntity toEntity(Item item) {
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
