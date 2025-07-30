package org.example;

import org.example.config.AppConfig;
import org.example.entity.Movie;
import org.example.service.MovieService; // MovieService của bạn

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MovieService movieService = context.getBean(MovieService.class);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU QUẢN LÝ PHIM =====");
            System.out.println("1. Thêm Phim Mới");
            System.out.println("2. Hiển thị Tất Cả Phim");
            System.out.println("3. Tìm Phim theo ID");
            System.out.println("4. Cập Nhật Thông Tin Phim");
            System.out.println("5. Xóa Phim theo ID");
            System.out.println("6. Tìm Phim theo Tiêu Đề (chứa từ khóa)");
            System.out.println("7. Tìm Phim theo Năm Phát Hành");
            System.out.println("0. Thoát Chương Trình");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- THÊM PHIM MỚI ---");
                    System.out.print("Nhập Tiêu Đề Phim: ");
                    String title = scanner.nextLine();
                    System.out.print("Nhập Năm Phát Hành: ");
                    int releaseYear;
                    try {
                        releaseYear = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Năm phát hành không hợp lệ. Thêm phim thất bại.");
                        break;
                    }
                    System.out.print("Nhập Thể Loại: ");
                    String genre = scanner.nextLine();

                    Movie newMovie = new Movie(title, releaseYear, genre);
                    newMovie = movieService.saveMovie(newMovie);
                    System.out.println("==> Đã thêm phim: " + newMovie);
                }
                case 2 -> {
                    System.out.println("\n--- DANH SÁCH TẤT CẢ PHIM ---");
                    List<Movie> allMovies = movieService.getAllMovies();
                    if (allMovies.isEmpty()) {
                        System.out.println("Không có phim nào trong cơ sở dữ liệu.");
                    } else {
                        allMovies.forEach(m -> System.out.println("ID: " + m.getId() + " - " + m.getTitle() + " (" + m.getReleaseYear() + ") - Thể loại: " + m.getGenre()));
                    }
                }
                case 3 -> {
                    System.out.println("\n--- TÌM PHIM THEO ID ---");
                    System.out.print("Nhập ID Phim cần tìm: ");
                    Long idToFind;
                    try {
                        idToFind = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID không hợp lệ. Vui lòng nhập số.");
                        break;
                    }
                    Optional<Movie> foundMovie = movieService.getMovieById(idToFind);
                    foundMovie.ifPresentOrElse(
                            movie -> System.out.println("Tìm thấy: " + movie),
                            () -> System.out.println("Không tìm thấy phim với ID " + idToFind + ".")
                    );
                }
                case 4 -> {
                    System.out.println("\n--- CẬP NHẬT THÔNG TIN PHIM ---");
                    System.out.print("Nhập ID Phim cần cập nhật: ");
                    Long idToUpdate;
                    try {
                        idToUpdate = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID không hợp lệ. Vui lòng nhập số.");
                        break;
                    }
                    Optional<Movie> existingMovieOpt = movieService.getMovieById(idToUpdate);
                    if (existingMovieOpt.isPresent()) {
                        Movie movieToUpdate = existingMovieOpt.get();
                        System.out.println("Phim hiện tại: " + movieToUpdate);
                        System.out.print("Nhập Tiêu Đề Mới (để trống nếu không đổi): ");
                        String newTitle = scanner.nextLine();
                        if (!newTitle.isEmpty()) {
                            movieToUpdate.setTitle(newTitle);
                        }
                        System.out.print("Nhập Năm Phát Hành Mới (để trống nếu không đổi): ");
                        String newYearStr = scanner.nextLine();
                        if (!newYearStr.isEmpty()) {
                            try {
                                movieToUpdate.setReleaseYear(Integer.parseInt(newYearStr));
                            } catch (NumberFormatException e) {
                                System.out.println("Năm phát hành không hợp lệ. Bỏ qua cập nhật năm.");
                            }
                        }
                        System.out.print("Nhập Thể Loại Mới (để trống nếu không đổi): ");
                        String newGenre = scanner.nextLine();
                        if (!newGenre.isEmpty()) {
                            movieToUpdate.setGenre(newGenre);
                        }
                        try {
                            Movie updatedMovie = movieService.updateMovie(movieToUpdate);
                            System.out.println("==> Đã cập nhật: " + updatedMovie);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Lỗi cập nhật: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Không tìm thấy phim với ID " + idToUpdate + " để cập nhật.");
                    }
                }
                case 5 -> {
                    System.out.println("\n--- XÓA PHIM THEO ID ---");
                    System.out.print("Nhập ID Phim cần xóa: ");
                    Long idToDelete;
                    try {
                        idToDelete = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID không hợp lệ. Vui lòng nhập số.");
                        break;
                    }
                    movieService.deleteMovieById(idToDelete);
                    System.out.println("==> Đã xóa phim với ID " + idToDelete + ".");
                }
                case 6 -> {
                    System.out.println("\n--- TÌM PHIM THEO TIÊU ĐỀ ---");
                    System.out.print("Nhập từ khóa tiêu đề: ");
                    String keyword = scanner.nextLine();
                    List<Movie> results = movieService.findMoviesByTitleContaining(keyword);
                    if (results.isEmpty()) {
                        System.out.println("Không tìm thấy phim nào chứa từ khóa '" + keyword + "'.");
                    } else {
                        System.out.println("Kết quả tìm kiếm:");
                        results.forEach(m -> System.out.println("ID: " + m.getId() + " - " + m.getTitle() + " (" + m.getReleaseYear() + ") - Thể loại: " + m.getGenre()));
                    }
                }
                case 7 -> {
                    System.out.println("\n--- TÌM PHIM THEO NĂM PHÁT HÀNH ---");
                    System.out.print("Nhập năm phát hành: ");
                    int yearToSearch;
                    try {
                        yearToSearch = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Năm không hợp lệ. Vui lòng nhập số.");
                        break;
                    }
                    List<Movie> results = movieService.findMoviesByReleaseYear(yearToSearch);
                    if (results.isEmpty()) {
                        System.out.println("Không tìm thấy phim nào phát hành năm " + yearToSearch + ".");
                    } else {
                        System.out.println("Kết quả tìm kiếm:");
                        results.forEach(m -> System.out.println("ID: " + m.getId() + " - " + m.getTitle() + " (" + m.getReleaseYear() + ") - Thể loại: " + m.getGenre()));
                    }
                }
                case 0 -> System.out.println("==> Thoát chương trình.");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);

        context.close();
        scanner.close();
        System.out.println("Chương trình đã kết thúc.");
    }
}