package fontys.project.idk.application.controller.mappers;

import fontys.project.idk.application.controller.dtos.definitions.ItemDTO;
import fontys.project.idk.application.models.bas_data.definitions.Item;

import java.util.ArrayList;

public class ItemMapper {

    public ItemDTO itemToDto(Item item) {

        return ItemDTO.builder()
                .item_id(item.getItem_id())
                .affiliation(item.getAffiliation())
                .item_brand(item.getItem_brand())
                .item_category(item.getItem_category())
                .item_category2(item.getItem_category2())
                .item_list_id(item.getItem_list_id())
                .item_list_name(item.getItem_list_name())
                .item_name(item.getItem_name())
                .index(item.getIndex())
                .item_variant(item.getItem_variant())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }

    public Item dtoToItem(ItemDTO dto) {
        return Item.builder()
                .item_id(dto.getItem_id())
                .affiliation(dto.getAffiliation())
                .item_brand(dto.getItem_brand())
                .item_category(dto.getItem_category())
                .item_category2(dto.getItem_category2())
                .item_list_id(dto.getItem_list_id())
                .item_list_name(dto.getItem_list_name())
                .item_name(dto.getItem_name())
                .index(dto.getIndex())
                .item_variant(dto.getItem_variant())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }


    public ArrayList<ItemDTO> itemsToDtos(ArrayList<Item> items) {
        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(itemToDto(item));
        }
        return itemDTOs;
    }


    public ArrayList<Item> dtosToEvents(ArrayList<ItemDTO> dtos) {
        ArrayList<Item> items = new ArrayList<>();
        for (ItemDTO itemDTO : dtos) {
            items.add(this.dtoToItem(itemDTO));
        }
        return items;
    }
}
