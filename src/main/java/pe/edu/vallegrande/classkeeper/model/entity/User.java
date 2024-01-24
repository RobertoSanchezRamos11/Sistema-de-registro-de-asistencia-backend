package pe.edu.vallegrande.classkeeper.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "names")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "type_document")
    private String typeDocument;

    @Column(name = "number_document")
    private String numberDocument;

    @Column(name = "email")
    private String email;

    @Column(name = "addres")
    private String address;

    @Column(name = "celphone")
    private String celphone;

    @Column(name = "pasword")
    private String password;

    @Column(name = "rol")
    private String rol;

    @Column(name = "states")
    private String states;



}
