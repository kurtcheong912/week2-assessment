package shop.pet.track.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.pet.track.entity.Pet;
@Repository
public class PetDAOImpl implements PetDAO  {
    EntityManager entityManager;

    @Autowired
    public PetDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Pet addPetsToOwner(Pet pet) {
        return entityManager.merge(pet);
    }
    @Override
    public Pet findPet(int id) {

        return entityManager.find(Pet.class, id);
    }
    @Override
    public void deletePet(int id) {
        Pet pet = entityManager.find(Pet.class, id);
        pet.setOwner(null);
        entityManager.remove(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        entityManager.merge(pet);
    }
}
