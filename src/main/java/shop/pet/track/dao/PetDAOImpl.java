package shop.pet.track.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.pet.track.entity.Owner;
import shop.pet.track.entity.Pet;

import java.util.List;
import java.util.Set;

@Repository
public class PetDAOImpl implements PetDAO  {
    EntityManager entityManager;

    @Autowired
    public PetDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addPet(Pet pet) {
        entityManager.persist(pet);
    }
    @Override
    public Pet find(Integer id) {
        return entityManager.find(Pet.class, id);
    }
    @Override
    public void delete(Pet pet) {

        entityManager.remove(pet);
    }

    @Override
    public void update(Pet pet) {
        entityManager.merge(pet);
    }

    @Override
    public Set<Pet> getPetsByOwnerId(Integer id) {
        TypedQuery<Owner> query = entityManager.createQuery(
                "select i from Owner i "
                        + "JOIN FETCH i.pets "
                        + "where i.id = :data"
                , Owner.class);
        query.setParameter("data", id);
        Owner owner = query.getSingleResult();
        return owner.getPets();
    }
}
