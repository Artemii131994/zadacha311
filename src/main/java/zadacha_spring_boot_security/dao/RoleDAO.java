package zadacha_spring_boot_security.dao;


import zadacha_spring_boot_security.model.Role;
import zadacha_spring_boot_security.model.User;

import java.util.Set;

public interface RoleDAO {

    public Role getByName(String name);

    public Set<Role> byRole(User user, String[] role);
}

