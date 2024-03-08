package shop.pet.track.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.pet.track.entity.Owner;
import shop.pet.track.exception.NotFoundException;
import shop.pet.track.service.OwnerService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OwnerController {
    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/owner")
    public ResponseEntity<String> addOwner(@RequestBody Owner owner) {
        if (owner.getFirstName() == null || owner.getLastName() == null) {
            return new ResponseEntity<>("Last name or first name is empty", HttpStatus.BAD_REQUEST);
        } else {
            ownerService.addOwner(owner);
            return new ResponseEntity<>("added new owner", HttpStatus.OK);
        }
    }

    @GetMapping("/owner/pet/{petId}")
    public ResponseEntity<Owner> getOwnerByPetId(@PathVariable Integer petId) {

        return new ResponseEntity<>(ownerService.findOwnerOfCertainPet(petId), HttpStatus.OK);

    }

    @GetMapping("/owner/date/{myDate}")
    public ResponseEntity<List<Owner>> getPetsBYOwnerId(@RequestParam(value = "myDate", required = true)
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate myDate) {
        return new ResponseEntity<>(ownerService.getOwnerByDate(myDate), HttpStatus.OK);

    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getPetsBYOwnerId() {
        return new ResponseEntity<>(ownerService.getAllOwner(), HttpStatus.OK);

    }
}
