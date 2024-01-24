package pe.edu.vallegrande.classkeeper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.classkeeper.model.entity.Attendance;
import pe.edu.vallegrande.classkeeper.repository.AttendanceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> saveAttendanceList(List<Attendance> attendanceList) {
        List<Attendance> savedAttendances = new ArrayList<>();

        for (Attendance attendance : attendanceList) {
            // Verifica si la asistencia ya existe en la base de datos por alguna lógica (puede variar)
            boolean attendanceExists = attendanceRepository.existsById(attendance.getId());

            if (attendanceExists) {
                // Si la asistencia ya existe, puedes actualizarla o realizar alguna otra lógica
                // En este ejemplo, simplemente la actualizamos
                savedAttendances.add(attendanceRepository.save(attendance));
            } else {
                // Si la asistencia no existe, la guardamos como una nueva entrada en la base de datos
                savedAttendances.add(attendanceRepository.save(attendance));
            }
        }

        return savedAttendances;
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }


    /*
    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }
    */
}
