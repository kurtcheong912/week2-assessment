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

@Service
public class OwnerServiceImpl implements OwnerService {
    OwnerDAO ownerDAO;
    PetDAO petDAO;

    @Autowired
    public OwnerServiceImpl(OwnerDAO ownerDAO, PetDAO petDAO) {
        this.ownerDAO = ownerDAO;
        this.petDAO = petDAO;
    }

    @Override
    @Transactional
    public void addOwner(Owner owner) {
        LocalDate localDate = LocalDate.now();
        owner.setDateCreated(localDate);
        ownerDAO.add(owner);
    }

    @Override
    public Owner findOwnerOfCertainPet(Integer id) {
        Pet pet = petDAO.find(id);
        if (pet == null) {
            throw new NotFoundException("pet not found" + id);
        }
        return ownerDAO.getOwnerByPetId(id);
    }

    @Override
    public List<Owner> getOwnerByDate(LocalDate date) {
        return ownerDAO.getOwnerByDate(date);
    }

    @Override
    public List<Owner> getAllOwner() {
        return ownerDAO.getAllOwners();
    }

}
