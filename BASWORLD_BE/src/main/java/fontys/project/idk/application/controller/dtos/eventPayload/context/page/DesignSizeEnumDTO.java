package fontys.project.idk.application.controller.dtos.eventPayload.context.page;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public @JsonDeserialize(using = DesignSizeEnumDeserializerDTO.class)
enum DesignSizeEnumDTO {
    DESIGN_SIZE_XS("designSize-xs"),
    DESIGN_SIZE_SM("designSize-sm"),
    DESIGN_SIZE_MD("designSize-md"),
    DESIGN_SIZE_LG("designSize-lg"),
    DESIGN_SIZE_XL("designSize-xl");

    private final String size;

    DesignSizeEnumDTO(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }


    public static DesignSizeEnumDTO fromString(String size) {
        for (DesignSizeEnumDTO value : DesignSizeEnumDTO.values()) {
            if (value.size.equals(size)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + size);
    }
}
