package demo.example.controller;

import demo.example.model.AISystem;
import demo.example.service.AISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ai-systems")
public class AISystemController {

    @Autowired
    private AISystemService aiSystemService;

    @PostMapping
    public AISystem createSystem(@RequestBody AISystem system) {
        return aiSystemService.createSystem(system);
    }

    @GetMapping
    public List<AISystem> getAllSystems() {
        return aiSystemService.getAllSystems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AISystem> getSystemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(aiSystemService.getSystemById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AISystem> updateSystem(@PathVariable Long id, @RequestBody AISystem systemDetails) {
        try {
            return ResponseEntity.ok(aiSystemService.updateSystem(id, systemDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystem(@PathVariable Long id) {
        aiSystemService.deleteSystem(id);
        return ResponseEntity.noContent().build();
    }
}