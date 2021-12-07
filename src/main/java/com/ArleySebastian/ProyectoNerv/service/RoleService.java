package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;


import com.ArleySebastian.ProyectoNerv.entity.Role;

public interface RoleService {
     List<Role> listarRoles();
     int guardar(Role r);
     public Role save(Role Role);
}
