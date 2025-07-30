package org.example;

import org.example.config.AppConfig;
import org.example.entity.Player;
import org.example.entity.Team;
import org.example.service.PlayerService;
import org.example.service.TeamService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        TeamService teamService = context.getBean(TeamService.class);
        PlayerService playerService = context.getBean(PlayerService.class);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Thêm đội bóng");
            System.out.println("2. Hiển thị danh sách đội bóng");
            System.out.println("3. Tìm đội bóng theo ID");
            System.out.println("4. Xóa đội bóng theo ID");
            System.out.println("5. Thêm cầu thủ");
            System.out.println("6. Hiển thị tất cả cầu thủ");
            System.out.println("7. Tìm cầu thủ theo ID");
            System.out.println("8. Xóa cầu thủ theo ID");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Tên đội bóng: ");
                    String name = scanner.nextLine();
                    teamService.saveTeam(new Team(name));
                    System.out.println("Đã thêm đội bóng " + name + " thành công!");
                }
                case 2 -> teamService.getAllTeams().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Nhập ID đội bóng: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    System.out.println(teamService.getTeamById(id));
                }
                case 4 -> {
                    System.out.print("Nhập ID đội bóng muốn xóa: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    teamService.deleteTeamById(id);
                }
                case 5 -> {
                    System.out.print("Tên cầu thủ: ");
                    String name = scanner.nextLine();
                    System.out.print("Số áo: ");
                    int number = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID đội bóng: ");
                    Long teamId = Long.parseLong(scanner.nextLine());
                    playerService.savePlayer(new Player(name, number), teamId);
                }
                case 6 -> playerService.getAllPlayers().forEach(System.out::println);
                case 7 -> {
                    System.out.print("Nhập ID cầu thủ: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    System.out.println(playerService.getPlayerById(id));
                }
                case 8 -> {
                    System.out.print("Nhập ID cầu thủ muốn xóa: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    playerService.deletePlayerById(id);
                }
            }
        } while (choice != 0);

        context.close();
        scanner.close();
    }
}
