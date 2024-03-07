package shop.pet.track.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.pet.track.entity.Owner;
import shop.pet.track.entity.Pet;
import shop.pet.track.service.PetTrackerService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class restController {
    private PetTrackerService petTrackerService;

    @Autowired
    public restController(PetTrackerService petTrackerService) {
        this.petTrackerService = petTrackerService;
    }

    @PostMapping("/owner")
    public Owner addOwner(@RequestBody Owner owner) {

        return petTrackerService.addOwner(owner);
    }

    @PostMapping("/pet/{ownerId}")
    public Pet addPet(@PathVariable int ownerId, @RequestBody Pet pet) {

        return petTrackerService.addPetsToOwner(ownerId, pet);
    }

    @DeleteMapping("pet/{petId}")
    public void deletePet(@PathVariable int petId) {

        petTrackerService.deletePet(petId);
    }

    @PutMapping("pet/{petId}/{name}/{breed}")
    public void updatePet(@PathVariable int petId, @PathVariable String name, @PathVariable String breed) {

        petTrackerService.updatePet(petId, name, breed);
    }
    @GetMapping("/petOwner/{petId}")
    public Owner getOwnerByPetId(@PathVariable int petId) {

        return petTrackerService.findOwnerOfCertainPet(petId);
    }

    @GetMapping("/ownerPet/{ownerId}")
    public Set<Pet> getPetsBYOwnerId(@PathVariable int ownerId) {

        return petTrackerService.getPetsByOwnerId(ownerId);
    }

    @GetMapping("/dateOwner/{myDate}")
    public List<Owner> getPetsBYOwnerId(@PathVariable
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate myDate) {
        return petTrackerService.getOwnerByDate(myDate);
    }

    @GetMapping("/owners")
    public List<Owner> getPetsBYOwnerId(){
        return petTrackerService.getAllOwner();
    }

}
