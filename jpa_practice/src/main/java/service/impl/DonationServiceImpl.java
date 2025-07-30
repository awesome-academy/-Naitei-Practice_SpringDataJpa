package service.impl;

import entity.DonationCamp;
import entity.DonationLog;
import entity.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DonationCampRepository;
import repository.DonationLogRepository;
import repository.DonorRepository;
import service.DonationService;

import java.util.Date;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonorRepository donorRepo;
    private final DonationCampRepository campRepo;
    private final DonationLogRepository logRepo;

    @Autowired
    public DonationServiceImpl(DonorRepository donorRepo, DonationCampRepository campRepo, DonationLogRepository logRepo) {
        this.donorRepo = donorRepo;
        this.campRepo = campRepo;
        this.logRepo = logRepo;
    }

    @Override
    public void performDonation() {
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

        System.out.println("Hoàn tất thêm donor, camp và donation log thông qua Service!");
    }
}