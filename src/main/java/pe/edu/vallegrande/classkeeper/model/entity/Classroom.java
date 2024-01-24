package pe.edu.vallegrande.classkeeper.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "grade")
    private String grado;

    @Column(name = "section")
    private String seccion;

    @Column(name = "shift")
    private String turno;

    //@Column(name = "users_id")
    //private Long users_id;

    @Null
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "users_id")
    private User user;

}
