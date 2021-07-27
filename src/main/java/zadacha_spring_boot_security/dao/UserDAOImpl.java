package zadacha_spring_boot_security.dao;


import org.springframework.stereotype.Repository;
import zadacha_spring_boot_security.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        List<User> allUser = entityManager.createQuery("from User", User.class)
                .getResultList();

        return allUser;
    }



    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("delete from User where id =:userId")
                .setParameter("userId", id)
                .executeUpdate();
    }

    @Override
    public User ByUserName(String s) {
        return (User) entityManager.createQuery("from User where username = :username", User.class)
                .setParameter("username", s)
                .getSingleResult();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
}
