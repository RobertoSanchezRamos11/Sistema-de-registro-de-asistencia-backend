package pe.edu.vallegrande.classkeeper.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
	
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

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "addres", nullable = true)
	private String addres;
	
	@Column(name = "celphone", nullable = true)
	private String celphone;
	
	@Column(name = "shift")
	private String shift;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "classroom_id")
	private Long  classroomId;

	@Column(name = "states")
	private String states;

}
