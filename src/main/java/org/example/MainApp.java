package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.example.config.AppConfig;
import org.example.entity.Campaign;
import org.example.entity.ParticipationLog;
import org.example.entity.Skill;
import org.example.entity.SkillLevel;
import org.example.entity.Volunteer;
import org.example.service.CampaignService;
import org.example.service.SkillService;
import org.example.service.VolunteerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    
    private static VolunteerService volunteerService;
    private static CampaignService campaignService;
    private static SkillService skillService;
    private static Scanner scanner;
    private static SimpleDateFormat dateFormat;
    
    public static void main(String[] args) {
        System.out.println("🚀 Khởi động ứng dụng Quản lý Tình nguyện viên...");
        
        // Khởi tạo Spring Context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        try {
            // Lấy các service beans
            volunteerService = context.getBean(VolunteerService.class);
            campaignService = context.getBean(CampaignService.class);
            skillService = context.getBean(SkillService.class);
            
            // Khởi tạo các biến hỗ trợ
            scanner = new Scanner(System.in);
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            System.out.println("✅ Spring Context đã khởi tạo thành công!");
            
            // Khởi tạo dữ liệu mẫu
            DataInitializer dataInitializer = context.getBean(DataInitializer.class);
            dataInitializer.initializeData();
            
            // Chạy ứng dụng chính
            runApplication();
            
        } catch (Exception e) {
            System.err.println("❌ Lỗi: " + e.getMessage());
            e.printStackTrace();
        } finally {
            context.close();
            System.out.println("\n🔚 Đã đóng ứng dụng");
        }
    }
    
    private static void runApplication() {
        while (true) {
            showMainMenu();
            int choice = getIntInput("Nhập lựa chọn của bạn: ");
            
            switch (choice) {
                case 1:
                    volunteerMenu();
                    break;
                case 2:
                    campaignMenu();
                    break;
                case 3:
                    skillMenu();
                    break;
                case 4:
                    reportMenu();
                    break;
                case 0:
                    System.out.println("👋 Tạm biệt!");
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
            
            System.out.println("\n" + "=".repeat(50));
        }
    }
    
    private static void showMainMenu() {
        System.out.println("\n📋 === MENU CHÍNH ===");
        System.out.println("1. 👥 Quản lý Tình nguyện viên");
        System.out.println("2.  Quản lý Chiến dịch");
        System.out.println("3. 🛠️ Quản lý Kỹ năng");
        System.out.println("4.  Báo cáo & Thống kê");
        System.out.println("0. 🚪 Thoát");
    }
    
    // === VOLUNTEER MENU ===
    private static void volunteerMenu() {
        while (true) {
            System.out.println("\n === QUẢN LÝ TÌNH NGUYỆN VIÊN ===");
            System.out.println("1. ➕ Thêm tình nguyện viên mới");
            System.out.println("2.  Xem danh sách tình nguyện viên");
            System.out.println("3.  Tìm tình nguyện viên theo email");
            System.out.println("4.  Tìm tình nguyện viên theo tên");
            System.out.println("5. ️ Gán kỹ năng cho tình nguyện viên");
            System.out.println("6. 📊 Xem kỹ năng của tình nguyện viên");
            System.out.println("7. ❌ Xóa tình nguyện viên");
            System.out.println("0. 🔙 Quay lại");
            
            int choice = getIntInput("Nhập lựa chọn: ");
            
            switch (choice) {
                case 1:
                    addVolunteer();
                    break;
                case 2:
                    listVolunteers();
                    break;
                case 3:
                    findVolunteerByEmail();
                    break;
                case 4:
                    findVolunteerByName();
                    break;
                case 5:
                    assignSkillToVolunteer();
                    break;
                case 6:
                    viewVolunteerSkills();
                    break;
                case 7:
                    deleteVolunteer();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
    
    private static void addVolunteer() {
        System.out.println("\n➕ === THÊM TÌNH NGUYỆN VIÊN MỚI ===");
        
        Volunteer volunteer = new Volunteer();
        
        System.out.print("Nhập họ tên: ");
        volunteer.setFullName(scanner.nextLine());
        
        System.out.print("Nhập email: ");
        volunteer.setEmail(scanner.nextLine());
        
        System.out.print("Nhập số điện thoại: ");
        volunteer.setPhone(scanner.nextLine());
        
        volunteer.setJoinDate(new Date());
        
        try {
            Volunteer saved = volunteerService.saveVolunteer(volunteer);
            System.out.println("✅ Đã thêm tình nguyện viên: " + saved.getFullName());
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void listVolunteers() {
        System.out.println("\n === DANH SÁCH TÌNH NGUYỆN VIÊN ===");
        
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        
        if (volunteers.isEmpty()) {
            System.out.println("📭 Chưa có tình nguyện viên nào!");
            return;
        }
        
        System.out.printf("%-5s %-30s %-25s %-15s %-15s%n", 
            "ID", "Họ tên", "Email", "Số điện thoại", "Ngày tham gia");
        System.out.println("-".repeat(100));
        
        for (Volunteer v : volunteers) {
            System.out.printf("%-5s %-30s %-25s %-15s %-15s%n",
                v.getId(), v.getFullName(), v.getEmail(), v.getPhone(), 
                dateFormat.format(v.getJoinDate()));
        }
    }
    
    private static void findVolunteerByEmail() {
        System.out.println("\n === TÌM TÌNH NGUYỆN VIÊN THEO EMAIL ===");
        
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        
        try {
            Volunteer volunteer = volunteerService.findByEmail(email);
            if (volunteer != null) {
                System.out.println("✅ Tìm thấy:");
                System.out.println("ID: " + volunteer.getId());
                System.out.println("Họ tên: " + volunteer.getFullName());
                System.out.println("Email: " + volunteer.getEmail());
                System.out.println("Số điện thoại: " + volunteer.getPhone());
                System.out.println("Ngày tham gia: " + dateFormat.format(volunteer.getJoinDate()));
            } else {
                System.out.println("❌ Không tìm thấy tình nguyện viên với email: " + email);
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void findVolunteerByName() {
        System.out.println("\n === TÌM TÌNH NGUYỆN VIÊN THEO TÊN ===");
        
        System.out.print("Nhập tên (hoặc một phần tên): ");
        String name = scanner.nextLine();
        
        try {
            List<Volunteer> volunteers = volunteerService.findByFullNameContaining(name);
            
            if (volunteers.isEmpty()) {
                System.out.println("❌ Không tìm thấy tình nguyện viên nào!");
                return;
            }
            
            System.out.println("✅ Tìm thấy " + volunteers.size() + " tình nguyện viên:");
            System.out.printf("%-5s %-30s %-25s%n", "ID", "Họ tên", "Email");
            System.out.println("-".repeat(60));
            
            for (Volunteer v : volunteers) {
                System.out.printf("%-5s %-30s %-25s%n", v.getId(), v.getFullName(), v.getEmail());
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void assignSkillToVolunteer() {
        System.out.println("\n🛠️ === GÁN KỸ NĂNG CHO TÌNH NGUYỆN VIÊN ===");
        
        // Hiển thị danh sách volunteers
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        if (volunteers.isEmpty()) {
            System.out.println("❌ Chưa có tình nguyện viên nào!");
            return;
        }
        
        System.out.println("Danh sách tình nguyện viên:");
        for (Volunteer v : volunteers) {
            System.out.println(v.getId() + ". " + v.getFullName());
        }
        
        Long volunteerId = getLongInput("Nhập ID tình nguyện viên: ");
        
        // Hiển thị danh sách skills
        List<Skill> skills = skillService.getAllSkills();
        if (skills.isEmpty()) {
            System.out.println("❌ Chưa có kỹ năng nào!");
            return;
        }
        
        System.out.println("Danh sách kỹ năng:");
        for (Skill s : skills) {
            System.out.println(s.getId() + ". " + s.getName());
        }
        
        Long skillId = getLongInput("Nhập ID kỹ năng: ");
        
        // Chọn level
        System.out.println("Chọn mức độ:");
        System.out.println("1. BEGINNER");
        System.out.println("2. INTERMEDIATE");
        System.out.println("3. ADVANCED");
        
        int levelChoice = getIntInput("Nhập lựa chọn: ");
        SkillLevel.Level level;
        
        switch (levelChoice) {
            case 1: level = SkillLevel.Level.BEGINNER; break;
            case 2: level = SkillLevel.Level.INTERMEDIATE; break;
            case 3: level = SkillLevel.Level.ADVANCED; break;
            default: level = SkillLevel.Level.BEGINNER;
        }
        
        try {
            volunteerService.assignSkillToVolunteer(volunteerId, skillId, level);
            System.out.println("✅ Đã gán kỹ năng thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void viewVolunteerSkills() {
        System.out.println("\n === XEM KỸ NĂNG CỦA TÌNH NGUYỆN VIÊN ===");
        
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        if (volunteers.isEmpty()) {
            System.out.println("❌ Chưa có tình nguyện viên nào!");
            return;
        }
        
        System.out.println("Danh sách tình nguyện viên:");
        for (Volunteer v : volunteers) {
            System.out.println(v.getId() + ". " + v.getFullName());
        }
        
        Long volunteerId = getLongInput("Nhập ID tình nguyện viên: ");
        
        try {
            List<SkillLevel> skillLevels = volunteerService.getVolunteerSkillLevels(volunteerId);
            
            if (skillLevels.isEmpty()) {
                System.out.println("📭 Tình nguyện viên chưa có kỹ năng nào!");
                return;
            }
            
            System.out.println("Kỹ năng của tình nguyện viên:");
            System.out.printf("%-20s %-15s%n", "Kỹ năng", "Mức độ");
            System.out.println("-".repeat(35));
            
            for (SkillLevel sl : skillLevels) {
                System.out.printf("%-20s %-15s%n", 
                    sl.getSkill().getName(), sl.getLevel());
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void deleteVolunteer() {
        System.out.println("\n❌ === XÓA TÌNH NGUYỆN VIÊN ===");
        
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        if (volunteers.isEmpty()) {
            System.out.println("❌ Chưa có tình nguyện viên nào!");
            return;
        }
        
        System.out.println("Danh sách tình nguyện viên:");
        for (Volunteer v : volunteers) {
            System.out.println(v.getId() + ". " + v.getFullName());
        }
        
        Long volunteerId = getLongInput("Nhập ID tình nguyện viên cần xóa: ");
        
        System.out.print("Bạn có chắc chắn muốn xóa? (y/n): ");
        String confirm = scanner.nextLine();
        
        if ("y".equalsIgnoreCase(confirm)) {
            try {
                volunteerService.deleteVolunteer(volunteerId);
                System.out.println("✅ Đã xóa tình nguyện viên thành công!");
            } catch (Exception e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Đã hủy xóa!");
        }
    }
    
    // === CAMPAIGN MENU ===
    private static void campaignMenu() {
        while (true) {
            System.out.println("\n🎯 === QUẢN LÝ CHIẾN DỊCH ===");
            System.out.println("1. ➕ Thêm chiến dịch mới");
            System.out.println("2. 📋 Xem danh sách chiến dịch");
            System.out.println("3. 🔍 Tìm chiến dịch theo tên");
            System.out.println("4. 📍 Tìm chiến dịch theo địa điểm");
            System.out.println("5. ⏰ Xem chiến dịch đang diễn ra");
            System.out.println("6. 👥 Thêm tình nguyện viên vào chiến dịch");
            System.out.println("7.  Xem danh sách tham gia chiến dịch");
            System.out.println("8. ❌ Xóa chiến dịch");
            System.out.println("0. 🔙 Quay lại");
            
            int choice = getIntInput("Nhập lựa chọn: ");
            
            switch (choice) {
                case 1:
                    addCampaign();
                    break;
                case 2:
                    listCampaigns();
                    break;
                case 3:
                    findCampaignByName();
                    break;
                case 4:
                    findCampaignByLocation();
                    break;
                case 5:
                    listActiveCampaigns();
                    break;
                case 6:
                    addVolunteerToCampaign();
                    break;
                case 7:
                    viewCampaignParticipants();
                    break;
                case 8:
                    deleteCampaign();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
    
    private static void addCampaign() {
        System.out.println("\n➕ === THÊM CHIẾN DỊCH MỚI ===");
        
        Campaign campaign = new Campaign();
        
        System.out.print("Nhập tên chiến dịch: ");
        campaign.setName(scanner.nextLine());
        
        System.out.print("Nhập địa điểm: ");
        campaign.setLocation(scanner.nextLine());
        
        System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
        String startDateStr = scanner.nextLine();
        try {
            Date startDate = dateFormat.parse(startDateStr);
            campaign.setStartDate(startDate);
        } catch (Exception e) {
            System.out.println("❌ Định dạng ngày không đúng!");
            return;
        }
        
        System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
        String endDateStr = scanner.nextLine();
        try {
            Date endDate = dateFormat.parse(endDateStr);
            campaign.setEndDate(endDate);
        } catch (Exception e) {
            System.out.println("❌ Định dạng ngày không đúng!");
            return;
        }
        
        try {
            Campaign saved = campaignService.saveCampaign(campaign);
            System.out.println("✅ Đã thêm chiến dịch: " + saved.getName());
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void listCampaigns() {
        System.out.println("\n === DANH SÁCH CHIẾN DỊCH ===");
        
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        
        if (campaigns.isEmpty()) {
            System.out.println(" Chưa có chiến dịch nào!");
            return;
        }
        
        System.out.printf("%-5s %-30s %-15s %-12s %-12s%n", 
            "ID", "Tên chiến dịch", "Địa điểm", "Bắt đầu", "Kết thúc");
        System.out.println("-".repeat(80));
        
        for (Campaign c : campaigns) {
            System.out.printf("%-5s %-30s %-15s %-12s %-12s%n",
                c.getId(), c.getName(), c.getLocation(), 
                dateFormat.format(c.getStartDate()), dateFormat.format(c.getEndDate()));
        }
    }
    
    private static void findCampaignByName() {
        System.out.println("\n === TÌM CHIẾN DỊCH THEO TÊN ===");
        
        System.out.print("Nhập tên chiến dịch (hoặc một phần): ");
        String name = scanner.nextLine();
        
        try {
            List<Campaign> campaigns = campaignService.findByNameContaining(name);
            
            if (campaigns.isEmpty()) {
                System.out.println("❌ Không tìm thấy chiến dịch nào!");
                return;
            }
            
            System.out.println("✅ Tìm thấy " + campaigns.size() + " chiến dịch:");
            System.out.printf("%-5s %-30s %-15s%n", "ID", "Tên chiến dịch", "Địa điểm");
            System.out.println("-".repeat(50));
            
            for (Campaign c : campaigns) {
                System.out.printf("%-5s %-30s %-15s%n", c.getId(), c.getName(), c.getLocation());
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void findCampaignByLocation() {
        System.out.println("\n === TÌM CHIẾN DỊCH THEO ĐỊA ĐIỂM ===");
        
        System.out.print("Nhập địa điểm: ");
        String location = scanner.nextLine();
        
        try {
            List<Campaign> campaigns = campaignService.findByLocation(location);
            
            if (campaigns.isEmpty()) {
                System.out.println("❌ Không tìm thấy chiến dịch nào ở " + location);
                return;
            }
            
            System.out.println("✅ Tìm thấy " + campaigns.size() + " chiến dịch ở " + location + ":");
            System.out.printf("%-5s %-30s %-12s %-12s%n", "ID", "Tên chiến dịch", "Bắt đầu", "Kết thúc");
            System.out.println("-".repeat(60));
            
            for (Campaign c : campaigns) {
                System.out.printf("%-5s %-30s %-12s %-12s%n", 
                    c.getId(), c.getName(), 
                    dateFormat.format(c.getStartDate()), dateFormat.format(c.getEndDate()));
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void listActiveCampaigns() {
        System.out.println("\n⏰ === CHIẾN DỊCH ĐANG DIỄN RA ===");
        
        try {
            List<Campaign> campaigns = campaignService.findActiveCampaigns();
            
            if (campaigns.isEmpty()) {
                System.out.println("📭 Không có chiến dịch nào đang diễn ra!");
                return;
            }
            
            System.out.println("✅ Có " + campaigns.size() + " chiến dịch đang diễn ra:");
            System.out.printf("%-5s %-30s %-15s %-12s %-12s%n", 
                "ID", "Tên chiến dịch", "Địa điểm", "Bắt đầu", "Kết thúc");
            System.out.println("-".repeat(80));
            
            for (Campaign c : campaigns) {
                System.out.printf("%-5s %-30s %-15s %-12s %-12s%n",
                    c.getId(), c.getName(), c.getLocation(), 
                    dateFormat.format(c.getStartDate()), dateFormat.format(c.getEndDate()));
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void addVolunteerToCampaign() {
        System.out.println("\n === THÊM TÌNH NGUYỆN VIÊN VÀO CHIẾN DỊCH ===");
        
        // Hiển thị danh sách volunteers
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        if (volunteers.isEmpty()) {
            System.out.println("❌ Chưa có tình nguyện viên nào!");
            return;
        }
        
        System.out.println("Danh sách tình nguyện viên:");
        for (Volunteer v : volunteers) {
            System.out.println(v.getId() + ". " + v.getFullName());
        }
        
        Long volunteerId = getLongInput("Nhập ID tình nguyện viên: ");
        
        // Hiển thị danh sách campaigns
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        if (campaigns.isEmpty()) {
            System.out.println("❌ Chưa có chiến dịch nào!");
            return;
        }
        
        System.out.println("Danh sách chiến dịch:");
        for (Campaign c : campaigns) {
            System.out.println(c.getId() + ". " + c.getName() + " (" + c.getLocation() + ")");
        }
        
        Long campaignId = getLongInput("Nhập ID chiến dịch: ");
        
        System.out.print("Nhập vai trò: ");
        String role = scanner.nextLine();
        
        try {
            campaignService.addVolunteerToCampaign(volunteerId, campaignId, role);
            System.out.println("✅ Đã thêm tình nguyện viên vào chiến dịch thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void viewCampaignParticipants() {
        System.out.println("\n === DANH SÁCH THAM GIA CHIẾN DỊCH ===");
        
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        if (campaigns.isEmpty()) {
            System.out.println("❌ Chưa có chiến dịch nào!");
            return;
        }
        
        System.out.println("Danh sách chiến dịch:");
        for (Campaign c : campaigns) {
            System.out.println(c.getId() + ". " + c.getName());
        }
        
        Long campaignId = getLongInput("Nhập ID chiến dịch: ");
        
        try {
            List<ParticipationLog> participants = campaignService.getCampaignParticipants(campaignId);
            
            if (participants.isEmpty()) {
                System.out.println("📭 Chưa có ai tham gia chiến dịch này!");
                return;
            }
            
            System.out.println("Danh sách tham gia:");
            System.out.printf("%-5s %-30s %-20s %-12s%n", "ID", "Tình nguyện viên", "Vai trò", "Ngày tham gia");
            System.out.println("-".repeat(70));
            
            for (ParticipationLog pl : participants) {
                System.out.printf("%-5s %-30s %-20s %-12s%n",
                    pl.getId(), pl.getVolunteer().getFullName(), 
                    pl.getRole(), dateFormat.format(pl.getJoinedDate()));
            }
            
            Long count = campaignService.countCampaignVolunteers(campaignId);
            System.out.println("\n📊 Tổng số tình nguyện viên tham gia: " + count);
            
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void deleteCampaign() {
        System.out.println("\n❌ === XÓA CHIẾN DỊCH ===");
        
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        if (campaigns.isEmpty()) {
            System.out.println("❌ Chưa có chiến dịch nào!");
            return;
        }
        
        System.out.println("Danh sách chiến dịch:");
        for (Campaign c : campaigns) {
            System.out.println(c.getId() + ". " + c.getName());
        }
        
        Long campaignId = getLongInput("Nhập ID chiến dịch cần xóa: ");
        
        System.out.print("Bạn có chắc chắn muốn xóa? (y/n): ");
        String confirm = scanner.nextLine();
        
        if ("y".equalsIgnoreCase(confirm)) {
            try {
                campaignService.deleteCampaign(campaignId);
                System.out.println("✅ Đã xóa chiến dịch thành công!");
            } catch (Exception e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Đã hủy xóa!");
        }
    }
    
    // === SKILL MENU ===
    private static void skillMenu() {
        while (true) {
            System.out.println("\n️ === QUẢN LÝ KỸ NĂNG ===");
            System.out.println("1. ➕ Thêm kỹ năng mới");
            System.out.println("2.  Xem danh sách kỹ năng");
            System.out.println("3.  Tìm kỹ năng theo tên");
            System.out.println("4.  Xem tình nguyện viên có kỹ năng cụ thể");
            System.out.println("5.  Xem tình nguyện viên có kỹ năng cao cấp");
            System.out.println("6. ❌ Xóa kỹ năng");
            System.out.println("0. 🔙 Quay lại");
            
            int choice = getIntInput("Nhập lựa chọn: ");
            
            switch (choice) {
                case 1:
                    addSkill();
                    break;
                case 2:
                    listSkills();
                    break;
                case 3:
                    findSkillByName();
                    break;
                case 4:
                    findVolunteersBySkill();
                    break;
                case 5:
                    findAdvancedVolunteersBySkill();
                    break;
                case 6:
                    deleteSkill();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
    
    private static void addSkill() {
        System.out.println("\n➕ === THÊM KỸ NĂNG MỚI ===");
        
        Skill skill = new Skill();
        
        System.out.print("Nhập tên kỹ năng: ");
        skill.setName(scanner.nextLine());
        
        try {
            Skill saved = skillService.saveSkill(skill);
            System.out.println("✅ Đã thêm kỹ năng: " + saved.getName());
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void listSkills() {
        System.out.println("\n === DANH SÁCH KỸ NĂNG ===");
        
        List<Skill> skills = skillService.getAllSkills();
        
        if (skills.isEmpty()) {
            System.out.println("📭 Chưa có kỹ năng nào!");
            return;
        }
        
        System.out.printf("%-5s %-30s%n", "ID", "Tên kỹ năng");
        System.out.println("-".repeat(35));
        
        for (Skill s : skills) {
            System.out.printf("%-5s %-30s%n", s.getId(), s.getName());
        }
    }
    
    private static void findSkillByName() {
        System.out.println("\n🔍 === TÌM KỸ NĂNG THEO TÊN ===");
        
        System.out.print("Nhập tên kỹ năng (hoặc một phần): ");
        String name = scanner.nextLine();
        
        try {
            List<Skill> skills = skillService.findByNameContaining(name);
            
            if (skills.isEmpty()) {
                System.out.println("❌ Không tìm thấy kỹ năng nào!");
                return;
            }
            
            System.out.println("✅ Tìm thấy " + skills.size() + " kỹ năng:");
            System.out.printf("%-5s %-30s%n", "ID", "Tên kỹ năng");
            System.out.println("-".repeat(35));
            
            for (Skill s : skills) {
                System.out.printf("%-5s %-30s%n", s.getId(), s.getName());
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void findVolunteersBySkill() {
        System.out.println("\n === TÌM TÌNH NGUYỆN VIÊN CÓ KỸ NĂNG ===");
        
        List<Skill> skills = skillService.getAllSkills();
        if (skills.isEmpty()) {
            System.out.println("❌ Chưa có kỹ năng nào!");
            return;
        }
        
        System.out.println("Danh sách kỹ năng:");
        for (Skill s : skills) {
            System.out.println(s.getId() + ". " + s.getName());
        }
        
        Long skillId = getLongInput("Nhập ID kỹ năng: ");
        
        try {
            // Lấy skill để lấy tên
            Skill skill = skillService.getSkillById(skillId).orElse(null);
            if (skill == null) {
                System.out.println("❌ Không tìm thấy kỹ năng!");
                return;
            }
            
            List<Volunteer> volunteers = volunteerService.findVolunteerBySkillName(skill.getName());
            
            if (volunteers.isEmpty()) {
                System.out.println(" Không có tình nguyện viên nào có kỹ năng " + skill.getName());
                return;
            }
            
            System.out.println("✅ Tìm thấy " + volunteers.size() + " tình nguyện viên có kỹ năng " + skill.getName() + ":");
            System.out.printf("%-5s %-30s %-25s%n", "ID", "Họ tên", "Email");
            System.out.println("-".repeat(60));
            
            for (Volunteer v : volunteers) {
                System.out.printf("%-5s %-30s %-25s%n", v.getId(), v.getFullName(), v.getEmail());
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void findAdvancedVolunteersBySkill() {
        System.out.println("\n === TÌM TÌNH NGUYỆN VIÊN CÓ KỸ NĂNG CAO CẤP ===");
        
        List<Skill> skills = skillService.getAllSkills();
        if (skills.isEmpty()) {
            System.out.println("❌ Chưa có kỹ năng nào!");
            return;
        }
        
        System.out.println("Danh sách kỹ năng:");
        for (Skill s : skills) {
            System.out.println(s.getId() + ". " + s.getName());
        }
        
        Long skillId = getLongInput("Nhập ID kỹ năng: ");
        
        try {
            List<SkillLevel> skillLevels = skillService.findAdvancedVolunteersBySkill(skillId);
            
            if (skillLevels.isEmpty()) {
                System.out.println(" Không có tình nguyện viên nào có kỹ năng cao cấp!");
                return;
            }
            
            System.out.println("✅ Tìm thấy " + skillLevels.size() + " tình nguyện viên có kỹ năng cao cấp:");
            System.out.printf("%-5s %-30s %-20s %-15s%n", "ID", "Tình nguyện viên", "Kỹ năng", "Mức độ");
            System.out.println("-".repeat(70));
            
            for (SkillLevel sl : skillLevels) {
                System.out.printf("%-5s %-30s %-20s %-15s%n",
                    sl.getVolunteer().getId(), sl.getVolunteer().getFullName(),
                    sl.getSkill().getName(), sl.getLevel());
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void deleteSkill() {
        System.out.println("\n❌ === XÓA KỸ NĂNG ===");
        
        List<Skill> skills = skillService.getAllSkills();
        if (skills.isEmpty()) {
            System.out.println("❌ Chưa có kỹ năng nào!");
            return;
        }
        
        System.out.println("Danh sách kỹ năng:");
        for (Skill s : skills) {
            System.out.println(s.getId() + ". " + s.getName());
        }
        
        Long skillId = getLongInput("Nhập ID kỹ năng cần xóa: ");
        
        System.out.print("Bạn có chắc chắn muốn xóa? (y/n): ");
        String confirm = scanner.nextLine();
        
        if ("y".equalsIgnoreCase(confirm)) {
            try {
                skillService.deleteSkill(skillId);
                System.out.println("✅ Đã xóa kỹ năng thành công!");
            } catch (Exception e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Đã hủy xóa!");
        }
    }
    
    // === REPORT MENU ===
    private static void reportMenu() {
        while (true) {
            System.out.println("\n📊 === BÁO CÁO & THỐNG KÊ ===");
            System.out.println("1. 👥 Tổng số tình nguyện viên");
            System.out.println("2.  Tổng số chiến dịch");
            System.out.println("3. 🛠️ Tổng số kỹ năng");
            System.out.println("4. ⏰ Tình nguyện viên đang hoạt động");
            System.out.println("5.  Chiến dịch đang diễn ra");
            System.out.println("6. 🏆 Thống kê kỹ năng cao cấp");
            System.out.println("0. 🔙 Quay lại");
            
            int choice = getIntInput("Nhập lựa chọn: ");
            
            switch (choice) {
                case 1:
                    showVolunteerStats();
                    break;
                case 2:
                    showCampaignStats();
                    break;
                case 3:
                    showSkillStats();
                    break;
                case 4:
                    showActiveVolunteers();
                    break;
                case 5:
                    showActiveCampaigns();
                    break;
                case 6:
                    showAdvancedSkillStats();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
    
    private static void showVolunteerStats() {
        System.out.println("\n👥 === THỐNG KÊ TÌNH NGUYỆN VIÊN ===");
        
        try {
            List<Volunteer> volunteers = volunteerService.getAllVolunteers();
            System.out.println("📊 Tổng số tình nguyện viên: " + volunteers.size());
            
            if (!volunteers.isEmpty()) {
                System.out.println("\nDanh sách tình nguyện viên:");
                System.out.printf("%-5s %-30s %-25s%n", "ID", "Họ tên", "Email");
                System.out.println("-".repeat(60));
                
                for (Volunteer v : volunteers) {
                    System.out.printf("%-5s %-30s %-25s%n", v.getId(), v.getFullName(), v.getEmail());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void showCampaignStats() {
        System.out.println("\n === THỐNG KÊ CHIẾN DỊCH ===");
        
        try {
            List<Campaign> campaigns = campaignService.getAllCampaigns();
            System.out.println(" Tổng số chiến dịch: " + campaigns.size());
            
            if (!campaigns.isEmpty()) {
                System.out.println("\nDanh sách chiến dịch:");
                System.out.printf("%-5s %-30s %-15s%n", "ID", "Tên chiến dịch", "Địa điểm");
                System.out.println("-".repeat(50));
                
                for (Campaign c : campaigns) {
                    System.out.printf("%-5s %-30s %-15s%n", c.getId(), c.getName(), c.getLocation());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void showSkillStats() {
        System.out.println("\n️ === THỐNG KÊ KỸ NĂNG ===");
        
        try {
            List<Skill> skills = skillService.getAllSkills();
            System.out.println(" Tổng số kỹ năng: " + skills.size());
            
            if (!skills.isEmpty()) {
                System.out.println("\nDanh sách kỹ năng:");
                System.out.printf("%-5s %-30s%n", "ID", "Tên kỹ năng");
                System.out.println("-".repeat(35));
                
                for (Skill s : skills) {
                    System.out.printf("%-5s %-30s%n", s.getId(), s.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void showActiveVolunteers() {
        System.out.println("\n⏰ === TÌNH NGUYỆN VIÊN ĐANG HOẠT ĐỘNG ===");
        
        try {
            List<Volunteer> activeVolunteers = volunteerService.findActiveVolunteers();
            System.out.println("📊 Số tình nguyện viên đang hoạt động: " + activeVolunteers.size());
            
            if (!activeVolunteers.isEmpty()) {
                System.out.println("\nDanh sách tình nguyện viên đang hoạt động:");
                System.out.printf("%-5s %-30s %-25s%n", "ID", "Họ tên", "Email");
                System.out.println("-".repeat(60));
                
                for (Volunteer v : activeVolunteers) {
                    System.out.printf("%-5s %-30s %-25s%n", v.getId(), v.getFullName(), v.getEmail());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void showActiveCampaigns() {
        System.out.println("\n📈 === CHIẾN DỊCH ĐANG DIỄN RA ===");
        
        try {
            List<Campaign> activeCampaigns = campaignService.findActiveCampaigns();
            System.out.println(" Số chiến dịch đang diễn ra: " + activeCampaigns.size());
            
            if (!activeCampaigns.isEmpty()) {
                System.out.println("\nDanh sách chiến dịch đang diễn ra:");
                System.out.printf("%-5s %-30s %-15s %-12s %-12s%n", 
                    "ID", "Tên chiến dịch", "Địa điểm", "Bắt đầu", "Kết thúc");
                System.out.println("-".repeat(80));
                
                for (Campaign c : activeCampaigns) {
                    System.out.printf("%-5s %-30s %-15s %-12s %-12s%n",
                        c.getId(), c.getName(), c.getLocation(), 
                        dateFormat.format(c.getStartDate()), dateFormat.format(c.getEndDate()));
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    private static void showAdvancedSkillStats() {
        System.out.println("\n === THỐNG KÊ KỸ NĂNG CAO CẤP ===");
        
        try {
            List<Skill> skills = skillService.getAllSkills();
            
            if (skills.isEmpty()) {
                System.out.println("📭 Chưa có kỹ năng nào!");
                return;
            }
            
            System.out.println("Thống kê kỹ năng cao cấp theo từng kỹ năng:");
            System.out.printf("%-5s %-20s %-15s%n", "ID", "Kỹ năng", "Số người cao cấp");
            System.out.println("-".repeat(40));
            
            for (Skill skill : skills) {
                List<SkillLevel> advancedLevels = skillService.findAdvancedVolunteersBySkill(skill.getId());
                System.out.printf("%-5s %-20s %-15s%n", 
                    skill.getId(), skill.getName(), advancedLevels.size());
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
    
    // === UTILITY METHODS ===
    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Vui lòng nhập số!");
            }
        }
    }
    
    private static Long getLongInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Vui lòng nhập số!");
            }
        }
    }
}