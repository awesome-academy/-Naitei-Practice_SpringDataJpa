package demo.example.service;

import demo.example.model.AISystem;
import java.util.List;

public interface AISystemService {
    AISystem createSystem(AISystem system);
    List<AISystem> getAllSystems();
    AISystem getSystemById(Long id);
    AISystem updateSystem(Long id, AISystem systemDetails);
    void deleteSystem(Long id);
}