package repository;

import entity.DonationLog;
import org.springframework.data.repository.CrudRepository;

public interface DonationLogRepository extends CrudRepository<DonationLog, Long> { }
