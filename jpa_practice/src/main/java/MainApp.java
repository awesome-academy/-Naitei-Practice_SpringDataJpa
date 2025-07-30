import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.DonationService;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        DonationService donationService = context.getBean(DonationService.class);

        donationService.performDonation();

        context.close();
    }
}