package shop.pet.track.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.pet.track.dao.OwnerDAO;
import shop.pet.track.dao.PetDAO;
import shop.pet.track.entity.Owner;
import shop.pet.track.entity.Pet;
import shop.pet.track.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Set;

@Service
public class PetServiceImpl implements PetService {
    OwnerDAO ownerDAO;
    PetDAO petDAO;

    @Autowired
    public PetServiceImpl(OwnerDAO ownerDAO, PetDAO petDAO) {
        this.ownerDAO = ownerDAO;
        this.petDAO = petDAO;
    }

    @Override
    @Transactional
    public void addPetsToOwner(Integer id, Pet pet) {
        Owner owner = ownerDAO.find(id);
        if (owner == null) {
            throw new NotFoundException("owner not found id :" + id);
        } else {
            LocalDate localDate = LocalDate.now();
            pet.setOwner(owner);
            pet.setDateCreated(localDate);
            petDAO.addPet(pet);
        }
    }

    @Override
    @Transactional
    public void deletePet(Integer id) {
        Pet pet = petDAO.find(id);
        if (pet != null) {
            petDAO.delete(pet);
        } else {
            throw new NotFoundException("Pet not found id " + id);
        }
    }

    @Override
    @Transactional
    public void updatePet(Integer id, Pet pet) {
        Pet myPet = petDAO.find(id);
        if (myPet != null) {
            if (!pet.getBreed().isEmpty()) {
                myPet.setBreed(pet.getBreed());
            }
            if (!pet.getName().isEmpty()) {
                myPet.setName(pet.getName());
            }
            LocalDate localDate = LocalDate.now();
            myPet.setDateModified(localDate);
            petDAO.update(myPet);
        } else {
            throw new NotFoundException("Pet not found id " + id);
        }
    }

    @Override
    public Set<Pet> getPetsByOwnerId(Integer id) {
        Owner owner = ownerDAO.find(id);
        if (owner != null) {
            Set<Pet> pets = petDAO.getPetsByOwnerId(id);
            if (pets.isEmpty()) {
                throw new NotFoundException("Owner not found" + id);
            }
            return pets;
        } else {
            throw new NotFoundException("Owner not found id " + id);
        }
    }
}
