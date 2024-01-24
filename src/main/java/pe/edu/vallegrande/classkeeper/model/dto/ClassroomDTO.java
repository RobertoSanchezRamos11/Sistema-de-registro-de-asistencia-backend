package pe.edu.vallegrande.classkeeper.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClassroomDTO {

    private String grado;

    private String seccion;

    private String turno;

    private Long userId;

}
