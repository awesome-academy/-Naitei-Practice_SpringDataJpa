package org.example;

import org.example.config.AppConfig;
import org.example.models.School;
import org.example.models.Student;
import org.example.repository.SchoolRepository;
import org.example.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TestStudentCRUD {

    public static void main(String[] args) {
        System.out.println("--- Bắt đầu ứng dụng Spring ---");

        // Khởi tạo Spring Application Context từ AppConfig
        // AnnotationConfigApplicationContext đọc các lớp @Configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Spring Context đã được khởi tạo thành công.");

        try {
            // Lấy StudentRepository bean từ Spring Context
            StudentService studentService = context.getBean(StudentService.class);
            SchoolRepository schoolRepository = context.getBean(SchoolRepository.class);
            List<Student> students = studentService.getAllStudents();
            System.out.println("Đã lấy được StudentRepository bean.");

            // Thực hiện thao tác với Repository để kiểm tra DB
            System.out.println("Kết nối DB thành công! Đã tìm thấy " + students.size() + " sinh viên.");

            //Thêm 1 sv mẫu nếu danh sách rỗng
            if (students.isEmpty()) {
                System.out.println("Vì danh sách trống, tiến hành thêm một sinh viên mẫu vào DB...");
                Student newStudent = new Student();
                newStudent.setName("Nguyen Van AAA");
                newStudent.setEmail("nguyenvana@example.com");
                newStudent.setAge(20);

                School sampleSchool = new School(); // Tạo hoặc lấy từ DB
                sampleSchool.setId(1L); // School 1 đã tồn tại trong DB
                newStudent.setSchool(sampleSchool);

                Student savedStudent = studentService.add(newStudent);
                System.out.println("Đã lưu sinh viên: " + savedStudent.getName() + " với ID: " + savedStudent.getId());

                List<Student> updatedStudents = studentService.getAllStudents();
                System.out.println("Tổng số sinh viên sau khi thêm: " + updatedStudents.size());
            }

            System.out.println("Danh sách sinh viên: ");
            for (Student student : students) {
                System.out.println(student);
            }

            //Thêm 1 sinh viên mới
            Scanner input = new Scanner(System.in);
            System.out.println("Bạn muốn thêm 1 sinh viên mới? (1: Có, 2: Không)");
            int option = input.nextInt();
            input.nextLine();

            if (option == 1) {
                Student newStudent = new Student();

                System.out.print("Nhập tên sinh viên: ");
                String name = input.nextLine();
                newStudent.setName(name);

                System.out.print("Nhập email sinh viên: ");
                String email = input.nextLine();
                newStudent.setEmail(email);

                System.out.print("Nhập tuổi sinh viên: ");
                int age = input.nextInt();
                input.nextLine();
                newStudent.setAge(age);

                System.out.println("Danh sách trường:");
                Iterable<School> listSchool = schoolRepository.findAll();
                for (School school : listSchool) {
                    System.out.println(school);
                }
                System.out.print("Nhập ID của trường (School ID): ");
                Long schoolId = input.nextLong();
                input.nextLine();

                School school = new School();
                school.setId(schoolId);
                newStudent.setSchool(school);

                try {
                    Student savedStudent = studentService.add(newStudent);
                    System.out.println("Đã lưu sinh viên: " + savedStudent.getName() + " với ID: " + savedStudent.getId());

                    List<Student> updatedStudents = studentService.getAllStudents();
                    System.out.println("Tổng số sinh viên sau khi thêm: " + updatedStudents.size());
                } catch (Exception e) {
                    System.out.println("Lỗi khi lưu sinh viên: " + e.getMessage());
                }
            } else {
                System.out.println("Không thêm sinh viên nào.");
            }

            // tìm kiếm theo email
            System.out.println("Test hàm tìm theo email (nguyenvana@example.com)");
            studentService.findStudentByEmail("nguyenvana@example.com").ifPresentOrElse(
                    s -> System.out.println("Tìm thấy sinh viên theo email: " + s.getName()),
                    () -> System.out.println("Không tìm thấy sinh viên với email đã cho.")
            );

            // tìm kiếm theo string kết thúc trong tên
            System.out.println("Test hàm findByNameEndingWithIgnoreCase");
            List<Student> listStudentNameTinh = studentService.findByNameEndingWith("tinh");
            if (!listStudentNameTinh.isEmpty()) {
                System.out.println("Danh sách sinh viên có tên \"tinh\": ");
                for (Student student : listStudentNameTinh) {
                    System.out.println(student);
                }
            }

            // Update sinh viên
            System.out.println("Bạn muốn chỉnh sửa sinh viên? (1: có, 2: không)");
            int editOption = input.nextInt();
            input.nextLine();

            if (editOption == 1) {
                List<Student> students2 = studentService.getAllStudents();

                if (students2.isEmpty()) {
                    System.out.println("Không có sinh viên nào trong danh sách.");
                } else {
                    System.out.println("Danh sách sinh viên:");
                    for (Student s : students2) {
                        System.out.println(s);
                    }

                    System.out.print("Nhập ID của sinh viên bạn muốn chỉnh sửa: ");
                    Long editId = input.nextLong();
                    input.nextLine();

                    Optional<Student> optionalStudent = studentService.findById(editId);
                    if (optionalStudent.isEmpty()) {
                        System.out.println("Không tìm thấy sinh viên với ID: " + editId);
                    } else {
                        Student student = optionalStudent.get();
                        System.out.println("Thông tin hiện tại:");
                        System.out.println("Tên: " + student.getName());
                        System.out.println("Email: " + student.getEmail());
                        System.out.println("Tuổi: " + student.getAge());
                        System.out.println("School ID: " + (student.getSchool() != null ? student.getSchool().getId() : "N/A"));

                        // Nhập lại thông tin mới
                        System.out.print("Nhập tên mới (bỏ trống nếu không đổi): ");
                        String newName = input.nextLine();
                        if (!newName.trim().isEmpty()) {
                            student.setName(newName);
                        }

                        System.out.print("Nhập email mới (bỏ trống nếu không đổi): ");
                        String newEmail = input.nextLine();
                        if (!newEmail.trim().isEmpty()) {
                            student.setEmail(newEmail);
                        }

                        System.out.print("Nhập tuổi mới (0 nếu không đổi): ");
                        int newAge = input.nextInt();
                        input.nextLine();
                        if (newAge > 0) {
                            student.setAge(newAge);
                        }

                        System.out.print("Nhập School ID mới (0 nếu không đổi): ");
                        Long newSchoolId = input.nextLong();
                        input.nextLine();
                        if (newSchoolId > 0) {
                            School newSchool = new School();
                            newSchool.setId(newSchoolId);
                            student.setSchool(newSchool);
                        }

                        try {
                            Student updated = studentService.update(student.getId(), student);
                            System.out.println("Đã cập nhật sinh viên: " + updated.getName());
                        } catch (Exception e) {
                            System.out.println("Có lỗi khi cập nhật: " + e.getMessage());
                        }
                    }
                }
            } else {
                System.out.println("Không thực hiện chỉnh sửa.");
            }

            //Xóa dinh viên
            System.out.println("Bạn muốn xóa 1 sinh viên? (1: có, 2: không)");
            int deleteOption = input.nextInt();
            input.nextLine();

            if (deleteOption == 1) {
                List<Student> students3 = studentService.getAllStudents();

                if (students3.isEmpty()) {
                    System.out.println("Không có sinh viên nào trong danh sách.");
                } else {
                    System.out.println("Danh sách sinh viên:");
                    for (Student s : students3) {
                        System.out.println(s);
                    }

                    System.out.print("Nhập ID của sinh viên bạn muốn Xóa: ");
                    Long daleteId = input.nextLong();
                    input.nextLine();
                    try {
                        studentService.delete(daleteId);
                        System.out.println("Đã xóa sinh viên có id = " + daleteId);
                    } catch (Exception e) {
                        System.out.println("Có lỗi khi xóa: " + e.getMessage());
                    }
                }
            }

        } catch (
                Exception e) {
            System.err.println("Xảy ra lỗi trong quá trình kiểm tra DB: " + e.getMessage());
            e.printStackTrace();
        } finally {

            ((AnnotationConfigApplicationContext) context).close();
            System.out.println("--- Ứng dụng Spring đã kết thúc ---");
        }
    }

}