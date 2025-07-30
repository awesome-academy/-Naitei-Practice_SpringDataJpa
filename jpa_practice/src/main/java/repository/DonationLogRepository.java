package repository;

import entity.DonationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationLogRepository extends JpaRepository<DonationLog, Long> { }
