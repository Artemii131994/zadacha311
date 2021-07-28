package zadacha_spring_boot_security.dao;

import org.springframework.stereotype.Repository;
import zadacha_spring_boot_security.model.Role;
import zadacha_spring_boot_security.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;


@Repository
public class RoleDAOImpl implements RoleDAO {

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

    @Override
    public Set<Role> byRole(User user, String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(getByName(roles));
        }
        user.setRoles(roleSet);
        return roleSet;
    }

}
