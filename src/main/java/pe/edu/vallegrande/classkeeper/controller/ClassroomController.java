package pe.edu.vallegrande.classkeeper.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.vallegrande.classkeeper.model.dto.ClassroomDTO;
import pe.edu.vallegrande.classkeeper.model.entity.Classroom;
import pe.edu.vallegrande.classkeeper.model.entity.Student;
import pe.edu.vallegrande.classkeeper.repository.ClassroomRepository;
import pe.edu.vallegrande.classkeeper.repository.StudentRepository;
import pe.edu.vallegrande.classkeeper.service.ClassroomService;
import pe.edu.vallegrande.classkeeper.service.StudentService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    @GetMapping
    public ResponseEntity<List<Classroom>> findAll() {
        List<Classroom> classrooms = classroomService.findAll();
        classrooms.forEach(classroom -> {
            classroom.setGrado(classroom.getGrado().trim());
            classroom.setSeccion(classroom.getSeccion().trim());
            classroom.setTurno(classroom.getTurno().trim());
        });
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Classroom>> findAllTodos() {
        return ResponseEntity.ok(classroomService.findTodos());
    }

    @GetMapping("/primaria")
    public ResponseEntity<List<Classroom>> findAllClassroomPrimary() {
        return ResponseEntity.ok(classroomService.findAllPrimary());
    }

    @Operation(summary = "Create new classroom")
    @PostMapping
    public ResponseEntity<Classroom> save(@RequestBody Classroom classroom) {
        Classroom saveClassroom = classroomService.save(classroom);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveClassroom);
    }

    // Busca por su id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Classroom>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findById(id));
    }

    // Elimina un registro (Fisico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classroomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Actualiza un registro
    @Operation(summary = "Update classroom by id")
    @PutMapping("/{id}")
    public ResponseEntity<Classroom> update(@PathVariable Long id, @RequestBody Classroom classroomUpdate) {
        Classroom classroom = classroomService.update(id, classroomUpdate);
        return ResponseEntity.ok(classroom);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getActiveStudentsByClassroomId(@PathVariable Long id) {
        List<Student> students = classroomService.findActiveStudentsByClassroomId(id);
        return ResponseEntity.ok(students);
    }

}
