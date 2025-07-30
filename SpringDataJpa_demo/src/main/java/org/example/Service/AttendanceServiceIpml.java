package org.example.Service;


import jakarta.transaction.Transactional;
import org.example.Entity.Attendance;
import org.example.Repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceIpml implements AttendanceService {
    private AttendanceRepository attendanceRepository;
    @Autowired
    public AttendanceServiceIpml(AttendanceRepository attendanceRepository){
        this.attendanceRepository=attendanceRepository;
    }

    @Override
    @Transactional
    public List<Attendance> findAll(){
        return this.attendanceRepository.findAll();
    }

    @Override
    @Transactional
    public Attendance createAttendance(Attendance attendance) {
        return this.attendanceRepository.save(attendance);
    }

    @Override
    @Transactional
    public Attendance updateAttendance(Attendance attendance) {
        return this.attendanceRepository.save(attendance);
    }

    @Override
    @Transactional
    public Long deleteAttendance(Attendance attendance) {
        this.attendanceRepository.deleteById(attendance.getId());
        return attendance.getId();
    }
}
