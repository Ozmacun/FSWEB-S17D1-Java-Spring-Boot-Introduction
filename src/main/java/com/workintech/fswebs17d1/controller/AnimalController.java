package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    private final Map<Integer, Animal> animals = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return new ResponseEntity<>(new ArrayList<>(animals.values()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Integer id) {
        if (animals.containsKey(id)) {
            return new ResponseEntity<>(animals.get(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addAnimal(@RequestBody Animal newAnimal) {
        if (newAnimal.getId() == null || newAnimal.getName() == null) {
            return new ResponseEntity<>("ID and Name cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (animals.containsKey(newAnimal.getId())) {
            return new ResponseEntity<>("Animal with this ID already exists", HttpStatus.CONFLICT);
        }

        animals.put(newAnimal.getId(), newAnimal);
        return new ResponseEntity<>("Animal added successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnimal(@PathVariable Integer id, @RequestBody Animal updatedAnimal) {
        if (!animals.containsKey(id)) {
            return new ResponseEntity<>("Animal not found", HttpStatus.NOT_FOUND);
        }

        animals.put(id, updatedAnimal);
        return new ResponseEntity<>("Animal updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Integer id) {
        if (animals.containsKey(id)) {
            animals.remove(id);
            return new ResponseEntity<>("Animal deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Animal not found", HttpStatus.NOT_FOUND);
    }
}
