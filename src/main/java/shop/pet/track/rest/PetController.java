package shop.pet.track.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.pet.track.entity.Pet;
import shop.pet.track.service.PetService;

import java.util.Set;

@RestController
public class PetController {
    private PetService petService;
    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("pet/{ownerId}")
    public ResponseEntity<Void> addPet(@PathVariable Integer ownerId, @RequestBody Pet pet) {
        petService.addPetsToOwner(ownerId, pet);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("pet/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer petId) {
        petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("pet/{petId}/{name}/{breed}")
    public ResponseEntity<Void> updatePet(@PathVariable Integer petId, @PathVariable String name, @PathVariable String breed) {
        petService.updatePet(petId, name, breed);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pet/owner/{ownerId}")
    public ResponseEntity<Set<Pet>> getPetsBYOwnerId(@PathVariable Integer ownerId) {
        return new ResponseEntity<>(petService.getPetsByOwnerId(ownerId), HttpStatus.OK);
    }
}
