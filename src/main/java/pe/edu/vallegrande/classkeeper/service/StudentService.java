package pe.edu.vallegrande.classkeeper.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.vallegrande.classkeeper.model.entity.Classroom;
import pe.edu.vallegrande.classkeeper.model.entity.Student;
import pe.edu.vallegrande.classkeeper.repository.StudentRepository;



@Service
@RequiredArgsConstructor
public class StudentService {
	
private final StudentRepository studentRepository;

	//Listar todos estudiantes con estado "A"
	public List<Student> findAll() {
		return studentRepository.findAllByStates("A");
	}

	//Listar todos estudiantes con estadp "I"
	public List<Student> findAllInactiveStudents() {
		return studentRepository.findAllByStates("I");
	}

	//Insertar un estudiante
	public Student save(Student student) {
		if (student.getStates() == null || student.getStates().isEmpty()) {
			student.setStates("A");
		}
		return studentRepository.save(student);
	}

	//Buscar por Id
	public Optional<Student> findById(Long id) {
		return studentRepository.findById(id);
	}

	//Eliminado Logico
	@Transactional
	public Optional<Student> inactive(Long id) {
		studentRepository.inactiveStudent(id);
		return studentRepository.findById(id);
	}

	//Activar estudiante
	@Transactional
	public Optional<Student> active(Long id) {
		studentRepository.activeStudent(id);
		return studentRepository.findById(id);
	}

	//Eliminado Fisico
	@Transactional
	public void delete(Long id) {
		studentRepository.deleteStudent(id);
	}

	//Actualizar estudiante
	@Transactional
	public Student update(Long id, Student updatedStudent) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		if (optionalStudent.isPresent()) {
			Student existingStudent = optionalStudent.get();
			existingStudent.setName(updatedStudent.getName());
			existingStudent.setSurname(updatedStudent.getSurname());
			existingStudent.setTypeDocument(updatedStudent.getTypeDocument());
			existingStudent.setNumberDocument(updatedStudent.getNumberDocument());
			existingStudent.setEmail(updatedStudent.getEmail());
			existingStudent.setAddres(updatedStudent.getAddres());
			existingStudent.setCelphone(updatedStudent.getCelphone());
			existingStudent.setShift(updatedStudent.getShift());
			existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
			existingStudent.setClassroomId(updatedStudent.getClassroomId());
			existingStudent.setStates(updatedStudent.getStates());

			return studentRepository.save(existingStudent);
		} else {
			// Manejar el caso cuando el estudiante con el ID dado no se encuentra
			throw new RuntimeException("Estudiante no encontrado con el ID: " + id);
		}
	}

}
