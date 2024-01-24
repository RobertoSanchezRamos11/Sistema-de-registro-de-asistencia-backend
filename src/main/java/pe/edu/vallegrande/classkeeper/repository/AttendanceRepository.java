package pe.edu.vallegrande.classkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.vallegrande.classkeeper.model.entity.Attendance;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
