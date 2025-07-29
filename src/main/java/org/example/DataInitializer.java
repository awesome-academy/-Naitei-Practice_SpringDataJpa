package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.example.entity.Campaign;
import org.example.entity.Skill;
import org.example.entity.SkillLevel;
import org.example.entity.Volunteer;
import org.example.service.CampaignService;
import org.example.service.SkillService;
import org.example.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private SkillService skillService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void initializeData() {
        System.out.println("🚀 Bắt đầu khởi tạo dữ liệu mẫu...");

        try {
            // Khởi tạo kỹ năng
            initializeSkills();
            
            // Khởi tạo tình nguyện viên
            initializeVolunteers();
            
            // Khởi tạo chiến dịch
            initializeCampaigns();
            
            // Gán kỹ năng cho tình nguyện viên
            assignSkillsToVolunteers();
            
            // Thêm tình nguyện viên vào chiến dịch
            assignVolunteersToCampaigns();

            System.out.println("✅ Khởi tạo dữ liệu mẫu hoàn tất!");
            
        } catch (Exception e) {
            System.err.println("❌ Lỗi khởi tạo dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeSkills() {
        System.out.println("📝 Khởi tạo kỹ năng...");
        
        String[] skillNames = {
            "Lên nội dung, timeline",
            "Hoạt náo",
            "Gây quỹ",
            "Nấu ăn",
            "Chăm lo đời sống tình nguyện viên",
            "Sơ cứu",
            "Chụp ảnh",
            "Edit video",
            "Blend ảnh",
            "Viết content",
            "Ca hát",
            "Nhảy múa",
            "Chơi đàn",
            "Lãnh đạo"
        };

        for (String skillName : skillNames) {
            Skill skill = new Skill();
            skill.setName(skillName);
            skillService.saveSkill(skill);
        }
        
        System.out.println("✅ Đã tạo " + skillNames.length + " kỹ năng");
    }

    private void initializeVolunteers() {
        System.out.println("👥 Khởi tạo tình nguyện viên...");
        
        String[] volunteerNames = {
            "Nguyễn Văn An", "Trần Thị Bình", "Lê Hoàng Cường", "Phạm Thị Dung",
            "Hoàng Văn Em", "Vũ Thị Phương", "Đặng Minh Giang", "Bùi Thị Hoa",
            "Ngô Văn Inh", "Lý Thị Kim", "Đỗ Hoàng Long", "Mai Thị Lan",
            "Võ Văn Minh", "Hồ Thị Nga", "Tô Hoàng Oanh", "Dương Văn Phúc",
            "Lưu Thị Quỳnh", "Trịnh Văn Rồng", "Châu Thị Sinh", "Lâm Văn Tâm"
        };

        String[] emails = {
            "an.nguyen@email.com", "binh.tran@email.com", "cuong.le@email.com", "dung.pham@email.com",
            "em.hoang@email.com", "phuong.vu@email.com", "giang.dang@email.com", "hoa.bui@email.com",
            "inh.ngo@email.com", "kim.ly@email.com", "long.do@email.com", "lan.mai@email.com",
            "minh.vo@email.com", "nga.ho@email.com", "oanh.to@email.com", "phuc.duong@email.com",
            "quynh.luu@email.com", "rong.trinh@email.com", "sinh.chau@email.com", "tam.lam@email.com"
        };

        String[] phones = {
            "0901234567", "0901234568", "0901234569", "0901234570",
            "0901234571", "0901234572", "0901234573", "0901234574",
            "0901234575", "0901234576", "0901234577", "0901234578",
            "0901234579", "0901234580", "0901234581", "0901234582",
            "0901234583", "0901234584", "0901234585", "0901234586"
        };

        for (int i = 0; i < volunteerNames.length; i++) {
            Volunteer volunteer = new Volunteer();
            volunteer.setFullName(volunteerNames[i]);
            volunteer.setEmail(emails[i]);
            volunteer.setPhone(phones[i]);
            
            // Đặt joinDate vào năm 2023
            try {
                volunteer.setJoinDate(dateFormat.parse("15/03/2023"));
            } catch (Exception e) {
                volunteer.setJoinDate(new Date()); // Fallback
            }
            
            volunteerService.saveVolunteer(volunteer);
        }
        
        System.out.println("✅ Đã tạo " + volunteerNames.length + " tình nguyện viên");
    }

    private void initializeCampaigns() {
        System.out.println("🎯 Khởi tạo chiến dịch...");
        
        try {
            Campaign vx5 = new Campaign();
            vx5.setName("Vị Xuân 5");
            vx5.setLocation("Đồng Tháp");
            vx5.setStartDate(dateFormat.parse("15/01/2024"));
            vx5.setEndDate(dateFormat.parse("15/02/2024"));
            campaignService.saveCampaign(vx5);

            Campaign sx3 = new Campaign();
            sx3.setName("Sắc Xanh 3");
            sx3.setLocation("An Giang");
            sx3.setStartDate(dateFormat.parse("01/06/2024"));
            sx3.setEndDate(dateFormat.parse("15/06/2024"));
            campaignService.saveCampaign(sx3);

            Campaign vx6 = new Campaign();
            vx6.setName("Vị Xuân 6");
            vx6.setLocation("Thành phố Hồ Chí Minh");
            vx6.setStartDate(dateFormat.parse("05/01/2025"));
            vx6.setEndDate(dateFormat.parse("12/01/2025"));
            campaignService.saveCampaign(vx6);

            Campaign sx4 = new Campaign();
            sx4.setName("Sắc Xanh 4");
            sx4.setLocation("Bến Tre");
            sx4.setStartDate(dateFormat.parse("19/07/2025"));
            sx4.setEndDate(dateFormat.parse("29/07/2025"));
            campaignService.saveCampaign(sx4);
            
            System.out.println("✅ Đã tạo 4 chiến dịch");
            
        } catch (Exception e) {
            System.err.println("❌ Lỗi tạo chiến dịch: " + e.getMessage());
        }
    }

    private void assignSkillsToVolunteers() {
        System.out.println("🛠️ Gán kỹ năng cho tình nguyện viên...");
        
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        List<Skill> skills = skillService.getAllSkills();
        
        for (Volunteer volunteer : volunteers) {
            int numSkills = (int) (Math.random() * 2) + 2; 
            
            for (int i = 0; i < numSkills; i++) {
                Skill skill = skills.get((int) (Math.random() * skills.size()));
                SkillLevel.Level level = getRandomLevel();
                
                try {
                    volunteerService.assignSkillToVolunteer(volunteer.getId(), skill.getId(), level);
                } catch (Exception e) {
                    // Bỏ qua nếu đã có kỹ năng này
                }
            }
        }
        
        System.out.println("✅ Đã gán kỹ năng cho tất cả tình nguyện viên");
    }

    private void assignVolunteersToCampaigns() {
        System.out.println("👥 Thêm tình nguyện viên vào chiến dịch...");
        
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        
        String[] roles = {"Tình nguyện viên", "Lãnh đạo", "Thư ký", "Tài xế", "Y tế", "Đầu bếp", "MC", "Photographer"};
        
        for (Campaign campaign : campaigns) {
            for (int i = 0; i < 10; i++) {
                Volunteer volunteer = volunteers.get((int) (Math.random() * volunteers.size()));
                String role = roles[(int) (Math.random() * roles.length)];
                
                try {
                    campaignService.addVolunteerToCampaign(volunteer.getId(), campaign.getId(), role);
                } catch (Exception e) {
                }
            }
        }
        
        System.out.println("✅ Đã thêm tình nguyện viên vào các chiến dịch");
    }

    private SkillLevel.Level getRandomLevel() {
        double rand = Math.random();
        if (rand < 0.4) return SkillLevel.Level.BEGINNER;
        else if (rand < 0.8) return SkillLevel.Level.INTERMEDIATE;
        else return SkillLevel.Level.ADVANCED;
    }
} 