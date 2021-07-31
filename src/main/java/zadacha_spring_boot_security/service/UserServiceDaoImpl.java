package zadacha_spring_boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha_spring_boot_security.dao.RoleDAO;
import zadacha_spring_boot_security.dao.UserDAO;
import zadacha_spring_boot_security.model.Role;
import zadacha_spring_boot_security.model.User;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceDaoImpl implements UserServiceDao {

    private PasswordEncoder passwordEncoder;
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    @Autowired
    public UserServiceDaoImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }


    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public Role getByName(String name) {
        return roleDAO.getByName(name);
    }


    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.add(user);
    }

    @Override
    @Transactional
    public Set<Role> byRole(User user, String[] role) {
        return roleDAO.byRole(user, role);
    }

    @Override
    @Transactional
    public User ByUserName(String s){
        return userDAO.ByUserName(s);
    }
}
