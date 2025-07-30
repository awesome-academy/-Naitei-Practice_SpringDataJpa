import config.AppConfig;
import entity.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.*;
import java.util.Date;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        DonorRepository donorRepo = context.getBean(DonorRepository.class);
        DonationCampRepository campRepo = context.getBean(DonationCampRepository.class);
        DonationLogRepository logRepo = context.getBean(DonationLogRepository.class);

        Donor donor = new Donor();
        donor.setFullName("Nguyen Van A");
        donor.setEmail("a@example.com");
        donor.setPhone("0123456789");
        donor.setBirthDate(new Date());
        donor.setBloodType("O");
        donorRepo.save(donor);

        DonationCamp camp = new DonationCamp();
        camp.setName("Chiến dịch Hè 2025");
        camp.setLocation("TP.HCM");
        camp.setDate(new Date());
        campRepo.save(camp);

        DonationLog log = new DonationLog();
        log.setDonor(donor);
        log.setCamp(camp);
        log.setDonationDate(new Date());
        log.setQuantityMl(450);
        logRepo.save(log);

        System.out.println("Hoàn tất thêm donor, camp và donation log!");
        context.close();
    }

}
