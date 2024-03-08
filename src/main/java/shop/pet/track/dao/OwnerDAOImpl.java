package shop.pet.track.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.pet.track.entity.Owner;
import shop.pet.track.entity.Pet;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OwnerDAOImpl implements OwnerDAO {
    EntityManager entityManager;

    @Autowired
    public OwnerDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Owner find(Integer id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public void add(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public Owner getOwnerByPetId(Integer id) {
        TypedQuery<Pet> query = entityManager.createQuery(
                "select i from Pet i "
                        + "JOIN FETCH i.owner "
                        + "where i.id = :data"
                , Pet.class);
        query.setParameter("data", id);
        Pet pet = query.getSingleResult();
        return pet.getOwner();
    }

    @Override
    public List<Owner> getOwnerByDate(LocalDate date) {
        TypedQuery<Owner> query = entityManager.createQuery(
                "select i from Owner i "
                        + "where i.dateCreated = :data "
                , Owner.class);
        query.setParameter("data", date);
        List<Owner> owners = query.getResultList();
        ;
        return owners;
    }

    @Override
    public List<Owner> getAllOwners() {
        TypedQuery<Owner> query = entityManager.createQuery(
                "select i from Owner i "
                , Owner.class);
        List<Owner> owners = query.getResultList();
        return owners;
    }
}
