package zadacha_spring_boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha_spring_boot_security.dao.UserDAO;
import zadacha_spring_boot_security.model.User;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private UserDAO userDAO;


    @Autowired
    public UserDetailsImpl(UserDAO userDAO) {
        this.userDAO = userDAO;

    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.ByUserName(s);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
