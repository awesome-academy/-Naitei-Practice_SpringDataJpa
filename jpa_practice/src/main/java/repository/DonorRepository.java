package repository;

import entity.Donor;
import org.springframework.data.repository.CrudRepository;

public interface DonorRepository extends CrudRepository<Donor, Long> { }