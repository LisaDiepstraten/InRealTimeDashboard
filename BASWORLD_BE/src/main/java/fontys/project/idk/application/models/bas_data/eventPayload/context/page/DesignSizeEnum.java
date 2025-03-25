package fontys.project.idk.application.models.bas_data.eventPayload.context.page;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;


@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public @JsonDeserialize(using = DesignSizeEnumDeserializer.class)
enum DesignSizeEnum {
    DESIGN_SIZE_XS("designSize-xs"),
    DESIGN_SIZE_SM("designSize-sm"),
    DESIGN_SIZE_MD("designSize-md"),
    DESIGN_SIZE_LG("designSize-lg"),
    DESIGN_SIZE_XL("designSize-xl");

    private final String size;

    DesignSizeEnum(String size) {
        this.size = size;
    }

    @JsonCreator
    public static DesignSizeEnum fromString(String value) {
        for (DesignSizeEnum designSize : DesignSizeEnum.values()) {
            if (designSize.size.equalsIgnoreCase(value)) {
                return designSize;
            }
        }
        throw new IllegalArgumentException("Unknown enum type: " + value);
    }


    public static DesignSizeEnum fromInteger(int x) {
        switch(x) {
            case 0:
                return DESIGN_SIZE_XS;
            case 1:
                return DESIGN_SIZE_SM;
            case 2:
                return DESIGN_SIZE_MD;
            case 3:
                return DESIGN_SIZE_LG;
            case 4:
                return DESIGN_SIZE_XL;
        }
        return null;
    }


    @JsonValue
    public String toString() {
        return size;
    }
}
