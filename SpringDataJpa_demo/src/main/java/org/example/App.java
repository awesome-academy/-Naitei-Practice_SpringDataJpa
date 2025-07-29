package org.example;

import org.example.Entity.Attendance;
import org.example.Entity.Employee;
import org.example.Service.AttendanceService;
import org.example.Service.AttendanceServiceIpml;
import org.example.config.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext app=new  AnnotationConfigApplicationContext(Configuration.class);
       AttendanceService a =app.getBean(AttendanceService.class);
       //Xem danh sách chấm công
        List<Attendance> list=a.findAll();
        list.stream().forEach(System.out::println);

        //Chấm công
        //Còn thêm employee nữa mới chấm công được
        Attendance new_attendance = Attendance.builder().build();
        a.createAttendance(new_attendance);

        //chỉnh sửa form chấm công
        Attendance new_attendance2 = Attendance.builder().build();
        a.updateAttendance(new_attendance2);

        //Xóa form chấm công
        a.deleteAttendance(new_attendance2);
    }
}
