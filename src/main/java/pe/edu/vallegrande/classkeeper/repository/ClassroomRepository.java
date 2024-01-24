package pe.edu.vallegrande.classkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pe.edu.vallegrande.classkeeper.model.entity.Classroom;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {


    List<Classroom> findAllByTurno(String turno);
    @Modifying
    @Query(value = "delete Classroom c where c.id = ?1")
    void deleteClassroom(Long id);
}
