package zadacha_spring_boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zadacha_spring_boot_security.model.Role;
import zadacha_spring_boot_security.model.User;

import javax.annotation.PostConstruct;

@Service
public class InitServiceImpl implements InitService{

    private UserServiceDao userServiceDao;

    @Autowired
    public InitServiceImpl(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @PostConstruct
    @Override
    public void tableinition() {

        if (userServiceDao.getAllUser().size() == 0) {
            User user = new User();
            Role role = new Role();

            user.setId(1L);
            user.setUsername("admin");
            user.setPassword("admin");
            user.setEmail("admin@mail.ru");

            role.setName("ROLE_ADMIN");
            role.setId(Long.valueOf(1));

            user.getRoles().add(role);
            userServiceDao.update(user);

            User user1 = new User();
            Role role1 = new Role();

            user1.setId(2L);
            user1.setUsername("user");
            user1.setPassword("user");
            user1.setEmail("user@mail.ru");

            role1.setName("ROLE_USER");
            role1.setId(Long.valueOf(2));

            user1.getRoles().add(role1);
            userServiceDao.update(user1);
        }
    }
}
