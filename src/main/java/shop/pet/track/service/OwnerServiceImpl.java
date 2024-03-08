package shop.pet.track.service;

import jakarta.transaction.Transactional;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.pet.track.dao.OwnerDAO;
import shop.pet.track.dao.PetDAO;
import shop.pet.track.entity.Owner;
import shop.pet.track.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
@Service
public class OwnerServiceImpl implements OwnerService{
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
        try {
            return ownerDAO.getOwnerByPetId(id);
        } catch (SqlScriptException ex) {
            throw new NotFoundException("Pet not found" + id);
        }
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
