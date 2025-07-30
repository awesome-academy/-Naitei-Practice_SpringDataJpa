package demo.example.repository;

import demo.example.model.AISystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AISystemRepository extends CrudRepository<AISystem, Long>{
	//Crud Spring JPA
}
