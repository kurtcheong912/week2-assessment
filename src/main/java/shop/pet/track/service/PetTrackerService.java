package shop.pet.track.service;

import shop.pet.track.entity.Owner;
import shop.pet.track.entity.Pet;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PetTrackerService {
    Owner addOwner(Owner owner);

    Pet addPetsToOwner(int id,Pet pet);

    void deletePet(int id);

    void updatePet(int id,String name, String breed);

    Owner findOwnerOfCertainPet(int id);

    Set<Pet> getPetsByOwnerId(int id);

    List<Owner> getOwnerByDate(LocalDate date);


    List<Owner> getAllOwner();
}
