package org.example;

import org.example.config.AppConfig;
import org.example.models.*;
import org.example.service.StudentService;
import org.example.service.StudentSubjectService;
import org.example.service.SubjectService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TestSubjectStudentCRUD {
    public static void main(String[] args) {
        System.out.println("--- Bắt đầu ứng dụng Spring ---");

        // Khởi tạo Spring Application Context từ AppConfig
        // AnnotationConfigApplicationContext đọc các lớp @Configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Spring Context đã được khởi tạo thành công.");

        try {
            // Lấy StudentRepository bean từ Spring Context
            StudentService studentService = context.getBean(StudentService.class);
            SubjectService subjectService = context.getBean(SubjectService.class);
            StudentSubjectService studentSubjectService = context.getBean(StudentSubjectService.class);
            List<StudentSubject> listSS = studentSubjectService.getAll();
            System.out.println("Đã lấy được StudentRepository bean.");

            // Thực hiện thao tác với Repository để kiểm tra DB
            System.out.println("Kết nối DB thành công! Đã tìm thấy " + listSS.size() + " bảng ghi điểm sinh viên.");

            System.out.println("Danh sách điểm sinh viên: ");
            for (StudentSubject ss : listSS) {
                System.out.println(ss);
            }

            //Thêm 1 sinh viên - môn học mới (cùng với điểm)
            Scanner input = new Scanner(System.in);
            System.out.println("Bạn muốn thêm 1 sinh viên - môn học mới (cùng với điểm)? (1: Có, 2: Không)");
            int option = input.nextInt();
            input.nextLine();

            if (option == 1) {
                // 1. Hiển thị danh sách sinh viên
                List<Student> students = studentService.getAllStudents();
                System.out.println("Danh sách sinh viên:");
                for (Student s : students) {
                    System.out.println(s);
                }

                // 2. Hiển thị danh sách môn học
                Iterable<Subject> subjects = subjectService.getAll();
                System.out.println("Danh sách môn học:");
                for (Subject sub : subjects) {
                    System.out.println(sub);
                }

                // 3. Nhập thông tin
                System.out.print("Nhập ID sinh viên: ");
                Long studentId = input.nextLong();
                input.nextLine();

                System.out.print("Nhập ID môn học: ");
                Long subjectId = input.nextLong();
                input.nextLine();

                System.out.print("Nhập điểm số: ");
                Double score = input.nextDouble();
                input.nextLine();

                // 4. Tạo đối tượng StudentSubject
                StudentSubject newStudentSubject = getNewStudentSubject(studentId, subjectId, score);

                try {
                    StudentSubject saved = studentSubjectService.add(newStudentSubject);
                    System.out.println("Đã thêm sinh viên-môn học thành công: SV ID " + saved.getStudent().getId()
                            + " - Môn học ID " + saved.getSubject().getId()
                            + " - Điểm: " + saved.getScore());
                } catch (Exception e) {
                    System.out.println("Lỗi khi thêm sinh viên-môn học: " + e.getMessage());
                }
            } else {
                System.out.println("Không thêm sinh viên-môn học nào.");
            }

            // Update điểm
            System.out.println("Bạn muốn cập nhật điểm số? (1: có, 2: không)");
            int editOption = input.nextInt();
            input.nextLine();

            if (editOption == 1) {
                List<StudentSubject> listSS2 = studentSubjectService.getAll();


                System.out.println("Danh sách sinh viên-môn học:");
                for (StudentSubject s : listSS2) {
                    System.out.println(s);
                }

                System.out.print("Nhập ID sinh viên cần cập nhật điểm: ");
                Long studentId = input.nextLong();
                input.nextLine();

                System.out.print("Nhập ID môn học: ");
                Long subjectId = input.nextLong();
                input.nextLine();

                StudentSubjectId ssId = new StudentSubjectId();
                ssId.setStudentId(studentId);
                ssId.setSubjectId(subjectId);

                Optional<StudentSubject> optionalSS = studentSubjectService.findById(ssId);
                if (optionalSS.isEmpty()) {
                    System.out.println("Không tìm thấy bản ghi với SV ID " + studentId + " và Môn học ID " + subjectId);
                } else {
                    StudentSubject ss = optionalSS.get();
                    System.out.println("Điểm hiện tại: " + ss.getScore());

                    System.out.print("Nhập điểm mới: ");
                    Double newScore = input.nextDouble();
                    input.nextLine();

                    ss.setScore(newScore);

                    try {
                        StudentSubject updated = studentSubjectService.updateScore(ss.getId(), ss);
                        System.out.println("Đã cập nhật điểm mới: " + updated.getScore());
                    } catch (Exception e) {
                        System.out.println("Có lỗi khi cập nhật điểm: " + e.getMessage());
                    }

                }
            } else {
                System.out.println("Không thực hiện chỉnh sửa.");
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

    private static StudentSubject getNewStudentSubject(Long studentId, Long subjectId, Double score) {
        StudentSubject newStudentSubject = new StudentSubject();

        StudentSubjectId ssId = new StudentSubjectId();
        ssId.setStudentId(studentId);
        ssId.setSubjectId(subjectId);

        Student student = new Student();
        student.setId(studentId);

        Subject subject = new Subject();
        subject.setId(subjectId);

        newStudentSubject.setId(ssId);
        newStudentSubject.setStudent(student);
        newStudentSubject.setSubject(subject);
        newStudentSubject.setScore(score);
        return newStudentSubject;
    }
}
