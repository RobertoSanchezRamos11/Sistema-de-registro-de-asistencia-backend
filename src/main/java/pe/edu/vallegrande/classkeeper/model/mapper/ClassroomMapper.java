package pe.edu.vallegrande.classkeeper.model.mapper;

import org.mapstruct.*;
import pe.edu.vallegrande.classkeeper.model.entity.Classroom;
import pe.edu.vallegrande.classkeeper.model.dto.ClassroomDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClassroomMapper {
    @Mapping(source = "userId", target = "user.id")
    Classroom toEntity(ClassroomDTO classroomDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(source = "userId", target = "user.id")
    Classroom partialUpdate(ClassroomDTO classroomDTO, @MappingTarget Classroom classroom);

}
