package org.example;

import org.example.Entity.Attendance;
import org.example.Entity.Employee;
import org.example.Service.AttendanceService;
import org.example.Service.AttendanceServiceIpml;
import org.example.Service.EmployeeService;
import org.example.config.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
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

        //Lấy nhân viên có id=1
        EmployeeService e =app.getBean(EmployeeService.class);
        Employee e1=e.findById(1l);


        //Chấm công cho nhân viên có id =1

         Attendance new_attendance = Attendance.builder().employee(e1)
                 .date(LocalDate.now())
                 .checkIn(LocalTime.of(8, 45))
                 .checkOut(LocalTime.of(17, 45))
                 .note("Đúng giờ")
                 .build();
        // a.createAttendance(new_attendance);

        //chỉnh sửa form chấm công
//        Attendance new_attendance2 = new_attendance;
//        sửa
//        a.updateAttendance(new_attendance2);

        //Xóa form chấm công
//        a.deleteAttendance(new_attendance2);
        
    }
}
