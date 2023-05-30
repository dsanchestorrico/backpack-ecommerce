package com.postgrado.ecommerce.service;
import com.postgrado.ecommerce.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role getByName(String name);
    List<Role> getAll();
}
