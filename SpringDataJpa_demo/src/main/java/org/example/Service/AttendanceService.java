package org.example.Service;


import org.example.Entity.Attendance;

import java.util.List;


public interface AttendanceService {
    public List<Attendance> findAll();
    public Attendance createAttendance(Attendance attendance);
    public Attendance updateAttendance(Attendance attendance);
    public Long deleteAttendance(Attendance attendance);
}
