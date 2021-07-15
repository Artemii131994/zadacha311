package zadacha_spring_boot_security.dao;

import org.springframework.stereotype.Repository;
import zadacha_spring_boot_security.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Repository
public class RoleDAOImpl implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role getByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT role FROM Role role WHERE role.name = :role", Role.class);
        return query
                .setParameter("role", name)
                .getSingleResult();
    }

}
