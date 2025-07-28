package com.example.movie;

import com.example.movie.config.AppConfig;
import com.example.movie.entity.Genre;
import com.example.movie.entity.Movie;
import com.example.movie.service.GenreService;
import com.example.movie.service.MovieService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static AnnotationConfigApplicationContext context;
    private static MovieService movieService;
    private static GenreService genreService;
    private static TransactionTemplate transactionTemplate;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // 1. Khởi tạo Spring Context và các beans
            context = new AnnotationConfigApplicationContext(AppConfig.class);
            movieService = context.getBean(MovieService.class);
            genreService = context.getBean(GenreService.class);

            // 2. Lấy TransactionTemplate để quản lý transaction
            PlatformTransactionManager transactionManager = context.getBean(PlatformTransactionManager.class);
            transactionTemplate = new TransactionTemplate(transactionManager);

            // 3. Bắt đầu vòng lặp menu chính
            mainMenuLoop();

        } catch (Exception e) {
            System.err.println("An error occurred during application startup: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 4. Dọn dẹp tài nguyên khi kết thúc
            if (context != null) {
                context.close();
            }
            scanner.close();
            System.out.println("Application has been shut down.");
        }
    }

    private static void mainMenuLoop() {
        while (true) {
            printMainMenu();
            int choice = readIntInput();
            switch (choice) {
                case 1:
                    handleMovieMenu();
                    break;
                case 2:
                    handleGenreMenu();
                    break;
                case 0:
                    return; // Thoát khỏi vòng lặp và kết thúc chương trình
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // --- Menu Handlers ---

    private static void handleMovieMenu() {
        while (true) {
            printMovieMenu();
            int choice = readIntInput();
            switch (choice) {
                case 1:
                    listAllMovies();
                    break;
                case 2:
                    findMovieById();
                    break;
                case 3:
                    findMovieByTitle();
                    break;
                case 4:
                    findMovieByYear();
                    break;
                case 5:
                    addNewMovie();
                    break;
                case 6:
                    updateMovieGenres();
                    break;
                case 7:
                    deleteMovie();
                    break;
                case 0:
                    return; // Quay lại menu chính
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleGenreMenu() {
        while (true) {
            printGenreMenu();
            int choice = readIntInput();
            switch (choice) {
                case 1:
                    listAllGenres();
                    break;
                case 2:
                    findGenreById();
                    break;
                case 3:
                    addNewGenre();
                    break;
                case 4:
                    updateGenre();
                    break;
                case 5:
                    deleteGenre();
                    break;
                case 0:
                    return; // Quay lại menu chính
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // --- Movie Service Callers ---

    private static void listAllMovies() {
        transactionTemplate.execute(status -> {
            System.out.println("\n--- All Movies ---");
            List<Movie> movies = movieService.getAllMovies();
            if (movies.isEmpty()) {
                System.out.println("No movies found in the database.");
            } else {
                movies.forEach(Main::printMovieDetails);
            }
            return null;
        });
        waitForEnter();
    }

    private static void findMovieById() {
        System.out.print("Enter Movie ID to find: ");
        long id = readLongInput();
        transactionTemplate.execute(status -> {
            Optional<Movie> movieOpt = movieService.getMovieById(id);
            movieOpt.ifPresentOrElse(
                    Main::printMovieDetails,
                    () -> System.out.println("Movie with ID " + id + " not found."));
            return null;
        });
        waitForEnter();
    }

    private static void findMovieByTitle() {
        System.out.print("Enter Movie Title to search for: ");
        String title = scanner.nextLine();
        transactionTemplate.execute(status -> {
            List<Movie> movies = movieService.getMovieByTitle(title);
            if (movies.isEmpty()) {
                System.out.println("No movies found with title containing '" + title + "'.");
            } else {
                System.out.println("Found " + movies.size() + " movie(s):");
                movies.forEach(Main::printMovieDetails);
            }
            return null;
        });
        waitForEnter();
    }

    private static void findMovieByYear() {
        System.out.print("Enter Release Year to search for: ");
        int year = readIntInput();
        transactionTemplate.execute(status -> {
            List<Movie> movies = movieService.getMovieByReleaseYear(Year.of(year));
            if (movies.isEmpty()) {
                System.out.println("No movies found released in " + year + ".");
            } else {
                System.out.println("Found " + movies.size() + " movie(s):");
                movies.forEach(Main::printMovieDetails);
            }
            return null;
        });
        waitForEnter();
    }

    private static void addNewMovie() {
        try {
            System.out.print("Enter new movie title: ");
            String title = scanner.nextLine();
            System.out.print("Enter release year: ");
            int year = readIntInput();
            System.out.print("Enter director's name: ");
            String director = scanner.nextLine();

            // Hiển thị danh sách thể loại có sẵn để người dùng chọn
            System.out.println("Available Genres:");
            listAllGenres();
            System.out.print("Enter genre IDs (comma-separated, e.g., 1,3): ");
            String[] idsStr = scanner.nextLine().split(",");
            Set<Long> genreIds = Arrays.stream(idsStr)
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());

            transactionTemplate.execute(status -> {
                Movie createdMovie = movieService.createMovie(title, Year.of(year), director, genreIds);
                System.out.println("\nSUCCESS: Movie created successfully!");
                printMovieDetails(createdMovie);
                return null;
            });
        } catch (Exception e) {
            System.err.println("ERROR: Could not create movie. " + e.getMessage());
        }
        waitForEnter();
    }

    private static void updateMovieGenres() {
        try {
            System.out.print("Enter Movie ID to update: ");
            long movieId = readLongInput();

            // Hiển thị danh sách thể loại có sẵn để người dùng chọn
            System.out.println("Available Genres:");
            listAllGenres();
            System.out.print("Enter NEW genre IDs (comma-separated, e.g., 2,4): ");
            String[] idsStr = scanner.nextLine().split(",");
            Set<Long> genreIds = Arrays.stream(idsStr)
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());

            transactionTemplate.execute(status -> {
                Movie updatedMovie = movieService.updateMovieGenres(movieId, genreIds);
                System.out.println("\nSUCCESS: Movie genres updated!");
                printMovieDetails(updatedMovie);
                return null;
            });
        } catch (Exception e) {
            System.err.println("ERROR: Could not update movie. " + e.getMessage());
        }
        waitForEnter();
    }

    private static void deleteMovie() {
        try {
            System.out.print("Enter Movie ID to delete: ");
            long id = readLongInput();

            System.out.print("Are you sure you want to delete movie with ID " + id + "? (y/n): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                transactionTemplate.execute(status -> {
                    movieService.deleteMovie(id);
                    return null;
                });
                System.out.println("SUCCESS: Movie with ID " + id + " has been deleted.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.err.println("ERROR: Could not delete movie. " + e.getMessage());
        }
        waitForEnter();
    }

    // --- Genre Service Callers ---

    private static void listAllGenres() {
        transactionTemplate.execute(status -> {
            List<Genre> genres = genreService.getAllGenres();
            if (genres.isEmpty()) {
                System.out.println("No genres found.");
            } else {
                genres.forEach(g -> System.out.printf("  ID: %d, Name: %s%n", g.getId(), g.getName()));
            }
            return null;
        });
    }

    private static void findGenreById() {
        System.out.print("Enter Genre ID to find: ");
        long id = readLongInput();
        transactionTemplate.execute(status -> {
            Optional<Genre> genreOpt = genreService.getGenreById(id);
            genreOpt.ifPresentOrElse(
                    g -> System.out.printf("Found Genre -> ID: %d, Name: %s%n", g.getId(), g.getName()),
                    () -> System.out.println("Genre with ID " + id + " not found."));
            return null;
        });
        waitForEnter();
    }

    private static void addNewGenre() {
        try {
            System.out.print("Enter new genre name: ");
            String name = scanner.nextLine();
            transactionTemplate.execute(status -> {
                Genre createdGenre = genreService.createGenre(name);
                System.out.printf("SUCCESS: Genre '%s' created with ID %d.%n", createdGenre.getName(),
                        createdGenre.getId());
                return null;
            });
        } catch (Exception e) {
            System.err.println("ERROR: Could not create genre. " + e.getMessage());
        }
        waitForEnter();
    }

    private static void updateGenre() {
        try {
            System.out.print("Enter Genre ID to update: ");
            long id = readLongInput();
            System.out.print("Enter new name for the genre: ");
            String newName = scanner.nextLine();
            transactionTemplate.execute(status -> {
                Genre updatedGenre = genreService.updateGenre(id, newName);
                System.out.printf("SUCCESS: Genre ID %d updated to '%s'.%n", updatedGenre.getId(),
                        updatedGenre.getName());
                return null;
            });
        } catch (Exception e) {
            System.err.println("ERROR: Could not update genre. " + e.getMessage());
        }
        waitForEnter();
    }

    private static void deleteGenre() {
        try {
            System.out.print("Enter Genre ID to delete: ");
            long id = readLongInput();

            System.out.print("Are you sure? This will remove the genre from all associated movies. (y/n): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                transactionTemplate.execute(status -> {
                    genreService.deleteGenre(id);
                    return null;
                });
                System.out.println("SUCCESS: Genre with ID " + id + " has been deleted.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.err.println("ERROR: Could not delete genre. " + e.getMessage());
        }
        waitForEnter();
    }

    // --- Helper & UI Methods ---

    private static void printMainMenu() {
        System.out.println("\n===== MOVIE DATABASE MAIN MENU =====");
        System.out.println("1. Manage Movies");
        System.out.println("2. Manage Genres");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void printMovieMenu() {
        System.out.println("\n--- Movie Management ---");
        System.out.println("1. List all movies");
        System.out.println("2. Find movie by ID");
        System.out.println("3. Find movie by Title");
        System.out.println("4. Find movie by Release Year");
        System.out.println("5. Add a new movie");
        System.out.println("6. Update a movie's genres");
        System.out.println("7. Delete a movie");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void printGenreMenu() {
        System.out.println("\n--- Genre Management ---");
        System.out.println("1. List all genres");
        System.out.println("2. Find genre by ID");
        System.out.println("3. Add a new genre");
        System.out.println("4. Update a genre");
        System.out.println("5. Delete a genre");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void printMovieDetails(Movie movie) {
        // Lấy tên các thể loại một cách an toàn
        String genreNames = movie.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.joining(", "));

        System.out.println("------------------------------------");
        System.out.printf("ID            : %d%n", movie.getId());
        System.out.printf("Title         : %s%n", movie.getTitle());
        System.out.printf("Release Year  : %s%n", movie.getReleaseYear());
        System.out.printf("Director      : %s%n", movie.getDirector());
        System.out.printf("Genres        : %s%n", genreNames.isEmpty() ? "N/A" : genreNames);
        System.out.printf("Created At    : %s%n", movie.getCreatedAt());
        System.out.printf("Last Updated  : %s%n", movie.getUpdatedAt());
        System.out.println("------------------------------------");
    }

    private static int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static long readLongInput() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid ID number: ");
            }
        }
    }

    private static void waitForEnter() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}