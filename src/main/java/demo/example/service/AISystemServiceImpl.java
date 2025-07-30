package demo.example.service;

import demo.example.model.AISystem;
import demo.example.repository.AISystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AISystemServiceImpl implements AISystemService {

    @Autowired
    private AISystemRepository aiSystemRepository;

    @Override 
    @Transactional
    public AISystem createSystem(AISystem system) {
        return aiSystemRepository.save(system);
    }
    @Override 
    @Transactional(readOnly = true)
    public List<AISystem> getAllSystems() {
    	return StreamSupport.stream(aiSystemRepository.findAll().spliterator(), false)
                .toList();
    }
    
    @Override 
    @Transactional
    public AISystem getSystemById(Long id) {
        return aiSystemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("AI System not found with id: " + id));
    }
    
    @Override 
    @Transactional
    public AISystem updateSystem(Long id, AISystem systemDetails) {
        AISystem existingSystem = getSystemById(id);
        existingSystem.setName(systemDetails.getName());
        existingSystem.setDeveloper(systemDetails.getDeveloper());
        existingSystem.setLaunchDate(systemDetails.getLaunchDate());
        existingSystem.setVersion(systemDetails.getVersion());
        existingSystem.setType(systemDetails.getType());
        existingSystem.setStatus(systemDetails.getStatus());
        return aiSystemRepository.save(existingSystem);
    }

    @Override 
    @Transactional
    public void deleteSystem(Long id) {
        aiSystemRepository.deleteById(id);
    }
}