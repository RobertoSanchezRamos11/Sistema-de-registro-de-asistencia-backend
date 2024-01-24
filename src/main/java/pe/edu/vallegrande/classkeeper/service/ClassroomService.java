package pe.edu.vallegrande.classkeeper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import pe.edu.vallegrande.classkeeper.model.entity.Classroom;
import pe.edu.vallegrande.classkeeper.model.entity.Student;
import pe.edu.vallegrande.classkeeper.model.dto.ClassroomDTO;
import pe.edu.vallegrande.classkeeper.model.mapper.ClassroomMapper;
import pe.edu.vallegrande.classkeeper.repository.ClassroomRepository;
import pe.edu.vallegrande.classkeeper.repository.StudentRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomMapper classroomMapper;
    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;


    public List<Classroom> findTodos() {
        return classroomRepository.findAll();
    }

    //Listar aulas de secundaria
    public List<Classroom> findAll() {
        return classroomRepository.findAllByTurno("S");
    }

    //Listar aulas de primaria
    public List<Classroom> findAllPrimary() {
        return classroomRepository.findAllByTurno("P");
    }

    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Optional<Classroom> findById(Long id) {
        return classroomRepository.findById(id);
    }


   /* public Classroom update(Long id, ClassroomDTO dto) {
        return classroomRepository.findById(id)
                .map(classroom -> classroomRepository.save(classroomMapper.partialUpdate(dto, classroom)))
                .orElseThrow(() -> new NotFoundException("Classroom not found"));
    }*/

    @Transactional
    public void delete(Long id) {
        classroomRepository.deleteClassroom(id);
    }

    @Transactional
    public Classroom update(Long id, Classroom updatedClassroom) {
        Optional<Classroom> optionalClassroom = classroomRepository.findById(id);
        if (optionalClassroom.isPresent()) {
            Classroom existingClassroom = optionalClassroom.get();
            existingClassroom.setGrado(updatedClassroom.getGrado());
            existingClassroom.setSeccion(updatedClassroom.getSeccion());
            existingClassroom.setUser(updatedClassroom.getUser());
            // Actualiza otros campos según sea necesario

            return classroomRepository.save(existingClassroom);
        } else {
            // Manejar el caso cuando el estudiante con el ID dado no se encuentra
            throw new RuntimeException("Aula no encontrado con el ID: " + id);
        }
    }

    public List<Student> findStudentsByClassroomId(Long classroomId) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);
        if (classroomOptional.isPresent()) {
            Classroom classroom = classroomOptional.get();
            List<Student> students = studentRepository.findByClassroomId(classroom.getId());

            Collections.sort(students, Comparator.comparing(Student::getSurname));

            return students;
        } else {
            // Manejar el caso cuando el aula con el ID dado no se encuentra
            throw new RuntimeException("Aula no encontrada con el ID: " + classroomId);
        }
    }


    public List<Student> findActiveStudentsByClassroomId(Long classroomId) {
        List<Student> allStudents = findStudentsByClassroomId(classroomId);

        // Filtrar estudiantes activos (estado "A")
        List<Student> activeStudents = allStudents.stream()
                .filter(student -> "A".equals(student.getStates()))
                .collect(Collectors.toList());

        return activeStudents;
    }

}
