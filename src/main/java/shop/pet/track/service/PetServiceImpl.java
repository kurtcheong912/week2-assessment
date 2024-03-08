package shop.pet.track.service;

import jakarta.transaction.Transactional;
import org.hibernate.tool.schema.spi.SqlScriptException;
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
public class PetServiceImpl implements PetService{
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
        try {
            Owner owner = ownerDAO.find(id);
            LocalDate localDate = LocalDate.now();
            pet.setOwner(owner);
            pet.setDateCreated(localDate);
            petDAO.addPet(pet);
        } catch (Exception ex) {
            throw new NotFoundException("owner not found id :" + id);
        }

    }

    @Override
    @Transactional
    public void deletePet(Integer id) {
        try {
            Pet pet = petDAO.find(id);
            pet.setOwner(null);
            petDAO.delete(pet);
        } catch (Exception ex) {
            throw new NotFoundException("Pet not found" + id);
        }
    }

    @Override
    @Transactional
    public void updatePet(Integer id, String name, String breed) {
        try {
            LocalDate localDate = LocalDate.now();
            Pet pet = petDAO.find(id);
            pet.setDateModified(localDate);
            pet.setBreed(breed);
            pet.setName(name);
            petDAO.update(pet);
        } catch (Exception ex) {
            throw new NotFoundException("Pet not found" + id);
        }
    }

    @Override
    public Set<Pet> getPetsByOwnerId(Integer id) {
        try {
            Set<Pet> pets  = petDAO.getPetsByOwnerId(id);
            return pets;
        } catch (SqlScriptException ex) {
            throw new NotFoundException("Owner not found" + id);
        }
    }
}
