package zadacha_spring_boot_security.dao;


import zadacha_spring_boot_security.model.Role;

public interface RoleDAO {

  public Role getByName(String name);
}

