package fontys.project.idk.application.business.mappers;


import fontys.project.idk.application.dal.entity.definitions.OtherEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.OtherEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OtherEventEntityMapper {
    OtherEventEntityMapper INSTANCE = Mappers.getMapper(OtherEventEntityMapper.class);

    OtherEvent toDomain(OtherEventEntity entity);
    OtherEventEntity toEntity(OtherEvent domain);
}
