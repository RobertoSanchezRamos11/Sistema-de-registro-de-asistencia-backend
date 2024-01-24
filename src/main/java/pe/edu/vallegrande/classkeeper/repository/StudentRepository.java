package pe.edu.vallegrande.classkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import pe.edu.vallegrande.classkeeper.model.entity.Classroom;
import pe.edu.vallegrande.classkeeper.model.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findAllByStates(String states);

	@Query("SELECT s FROM Student s WHERE s.classroomId = :classroomId")
	List<Student> findByClassroomId(@Param("classroomId") Long classroomId);

	//Eliminado Logico
	@Modifying
	@Query(value = "update Student s set s.states = 'I' where s.id = ?1")
	void inactiveStudent(Long id);

	//Activar Estudiante
	@Modifying
	@Query(value = "update Student s set s.states = 'A' where s.id = ?1")
	void activeStudent(Long id);

	//Eliminado Fisico
	@Modifying
	@Query(value = "delete Student s where s.id = ?1")
	void deleteStudent(Long id);
	
}
