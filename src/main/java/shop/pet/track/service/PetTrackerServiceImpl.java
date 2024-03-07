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
import java.util.List;
import java.util.Set;

@Service
public class PetTrackerServiceImpl implements PetTrackerService {
    OwnerDAO ownerDAO;
    PetDAO petDAO;

    @Autowired
    public PetTrackerServiceImpl(OwnerDAO ownerDAO, PetDAO petDAO) {
        this.ownerDAO = ownerDAO;
        this.petDAO = petDAO;
    }

    @Override
    @Transactional
    public Owner addOwner(Owner owner) {
        LocalDate localDate = LocalDate.now();
        owner.setDateCreated(localDate);
        return ownerDAO.addOwner(owner);
    }

    @Override
    @Transactional
    public Pet addPetsToOwner(int id, Pet pet) {
        try {
            Owner owner = ownerDAO.findOwner(id);
            LocalDate localDate = LocalDate.now();
            pet.setOwner(owner);
            pet.setDateCreated(localDate);
        } catch (Exception ex) {
            throw new NotFoundException("owner not found id :" + id);
        }
        return petDAO.addPetsToOwner(pet);
    }

    @Override
    @Transactional
    public void deletePet(int id) {
        try {
            petDAO.deletePet(id);
        } catch (Exception ex) {
            throw new NotFoundException("Pet not found" + id);
        }

    }

    @Override
    @Transactional
    public void updatePet(int id, String name, String breed) {
        try {
            LocalDate localDate = LocalDate.now();
            Pet pet = petDAO.findPet(id);
            pet.setDateModified(localDate);
            pet.setBreed(breed);
            pet.setName(name);
            petDAO.updatePet(pet);
        } catch (Exception ex) {
            throw new NotFoundException("Pet not found" + id);
        }

    }

    @Override
    public Owner findOwnerOfCertainPet(int id) {
        try {
            return ownerDAO.getOwnerByPetId(id);
        } catch (Exception ex) {
            throw new NotFoundException("Pet not found" + id);
        }

    }

    @Override
    public Set<Pet> getPetsByOwnerId(int id) {
        try {
            Owner owner = ownerDAO.getPetsByOwnerId(id);
            Set<Pet> pets = owner.getPets();
            return pets;
        } catch (Exception ex) {
            throw new NotFoundException("Owner not found" + id);
        }

    }

    @Override
    public List<Owner> getOwnerByDate(LocalDate date) {
        return ownerDAO.getOwnerByDate(date);
    }

    @Override
    public List<Owner> getAllOwner() {
        return ownerDAO.getALlOwner();
    }


}
