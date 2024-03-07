package shop.pet.track.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.pet.track.entity.Pet;
@Repository
public interface PetDAO  {



   Pet addPetsToOwner(Pet pet) ;

     Pet findPet(int id);

     void deletePet(int id);


     void updatePet(Pet pet) ;
}
