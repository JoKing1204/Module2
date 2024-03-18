package com.example.demo.creator;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/creators"})
public class CreatorController {
    @Autowired
    CreatorRepository creatorRepository;

    public CreatorController() {
    }

    @GetMapping
    List<Creator> getCreators() {
        return this.creatorRepository.findAll();
    }

    @PostMapping
    Creator createCreator(@RequestBody Creator creator) {
        return (Creator)this.creatorRepository.save(creator);
    }

    @DeleteMapping({"/{creatorId}"})
    ResponseEntity<?> deleteGenre(@PathVariable Long creatorId) {
        if (!this.creatorRepository.existsById(creatorId)) {
            return ResponseEntity.notFound().build();
        } else {
            this.creatorRepository.deleteById(creatorId);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping({"/{creatorId}"})
    ResponseEntity<Creator> changeCreator(@PathVariable Long creatorId, @RequestBody Creator updatedCreator) {
        if (!this.creatorRepository.existsById(creatorId)) {
            return ResponseEntity.notFound().build();
        } else {
            Creator existingCreator = (Creator)this.creatorRepository.findById(creatorId).orElse((Creator)null);
            if (existingCreator == null) {
                return ResponseEntity.notFound().build();
            } else {
                existingCreator.setName(updatedCreator.getName());
                Creator savedCreator = (Creator)this.creatorRepository.save(existingCreator);
                return ResponseEntity.ok(savedCreator);
            }
        }
    }
}
