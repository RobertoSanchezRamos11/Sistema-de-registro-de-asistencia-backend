package pe.edu.vallegrande.classkeeper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.classkeeper.model.entity.Attendance;
import pe.edu.vallegrande.classkeeper.repository.AttendanceRepository;
import pe.edu.vallegrande.classkeeper.service.AttendanceService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/create")
    public List<Attendance> saveAttendance(@RequestBody List<Attendance> attendanceList) {
        return attendanceService.saveAttendanceList(attendanceList);
    }

    @GetMapping("/listAttendance")
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        List<Attendance> attendanceList = attendanceService.getAllAttendance();
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(attendanceList);
    }

    /*
    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudentId(@PathVariable Long studentId) {
        return attendanceService.getAttendanceByStudentId(studentId);
    }
    */

}
