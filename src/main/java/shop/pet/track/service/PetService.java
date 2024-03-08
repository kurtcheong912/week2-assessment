package shop.pet.track.service;

import shop.pet.track.entity.Pet;

import java.util.Set;

public interface PetService {
    void addPetsToOwner(Integer id, Pet pet);

    void deletePet(Integer id);

    void updatePet(Integer id,Pet pet);

    Set<Pet> getPetsByOwnerId(Integer id);
}
