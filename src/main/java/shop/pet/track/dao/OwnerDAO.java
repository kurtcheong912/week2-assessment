package shop.pet.track.dao;

import shop.pet.track.entity.Owner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OwnerDAO {

    Owner findOwner(int id);
    Owner addOwner(Owner owner);
    Owner getPetsByOwnerId(int id);

    Owner getOwnerByPetId(int id);

    List<Owner> getOwnerByDate(LocalDate date);

    List<Owner>  getALlOwner();
}
