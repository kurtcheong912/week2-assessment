package shop.pet.track.service;

import shop.pet.track.entity.Owner;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {

    void addOwner(Owner owner);

    Owner findOwnerOfCertainPet(Integer id);

    List<Owner> getOwnerByDate(LocalDate date);

    List<Owner> getAllOwner();


}
