package shop.pet.track.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.pet.track.entity.Owner;
import shop.pet.track.entity.Pet;

import java.util.List;
import java.util.Set;

@Repository
public interface PetDAO  {
    void addPet(Pet pet) ;

    Pet find(Integer id);

    void delete(Pet pet);

    void update(Pet pet) ;

    Set<Pet> getPetsByOwnerId(Integer id);
}
