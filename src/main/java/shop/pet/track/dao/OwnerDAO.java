package shop.pet.track.dao;

import shop.pet.track.entity.Owner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OwnerDAO {
    Owner find(Integer id);

    void add(Owner owner);

    Owner getOwnerByPetId(Integer id);

    List<Owner> getOwnerByDate(LocalDate date);

    List<Owner>  getAllOwners();
}
