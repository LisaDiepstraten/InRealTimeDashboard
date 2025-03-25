package fontys.project.idk.application.business.mappers;


import fontys.project.idk.application.dal.entity.definitions.PageVisitEventEntity;
import fontys.project.idk.application.models.bas_data.definitions.PageVisitEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PageVisitEventEntityMapper {
    PageVisitEventEntityMapper INSTANCE = Mappers.getMapper(PageVisitEventEntityMapper.class);

    @Mapping(target = "data.pageType", source = "pageType")
    @Mapping(target = "data.pageLanguage", source = "pageLanguage")
    PageVisitEvent toDomain(PageVisitEventEntity entity);

    @Mapping(target = "pageType", source = "data.pageType")
    @Mapping(target = "pageLanguage", source = "data.pageLanguage")
    PageVisitEventEntity toEntity(PageVisitEvent domain);
}
