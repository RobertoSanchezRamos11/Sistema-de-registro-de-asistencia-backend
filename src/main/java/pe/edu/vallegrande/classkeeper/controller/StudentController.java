package pe.edu.vallegrande.classkeeper.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import pe.edu.vallegrande.classkeeper.model.entity.Student;
import pe.edu.vallegrande.classkeeper.repository.StudentRepository;
import pe.edu.vallegrande.classkeeper.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/students")
public class StudentController {
	
	private final StudentService studentService;

	// Lista todos los registros
	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		List<Student> studentList = studentService.findAll();
		return ResponseEntity.ok()
				.header("Content-Type", "application/json")
				.body(studentList);
	}

	//Listar todos los registros con estado "I"
	@GetMapping("/inactives")
	public ResponseEntity<List<Student>> findAllInactive() {
		return ResponseEntity.ok(studentService.findAllInactiveStudents());
	}

	// Agrega un Registro
	@PostMapping
	public ResponseEntity<Student> save(@RequestBody Student student){
		return ResponseEntity.ok(studentService.save(student));
	}

	// Busca por su id
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Student>> findById(@PathVariable Long id) {
		return ResponseEntity.ok(studentService.findById(id));
	}

	// Elimina un registro (Fisico)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		studentService.delete(id);
		return ResponseEntity.noContent().build();
	}

	//Eliminado Logico
	@DeleteMapping("/inactive/{id}")
	public ResponseEntity<Optional<Student>> inactive(@PathVariable Long id) {
		return ResponseEntity.ok(studentService.inactive(id));
	}

	//Activar estudiante
	@PutMapping("/active/{id}")
	public ResponseEntity<Optional<Student>> active(@PathVariable Long id) {
		return ResponseEntity.ok(studentService.active(id));
	}

	// Actualiza un registro
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student updateStudent) {
		Student student = studentService.update(id, updateStudent);
		return ResponseEntity.ok(student);
	}

}
