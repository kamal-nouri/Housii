package com.axsos.housii.housii.repositories;

import com.axsos.housii.housii.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByName(String name);
    List<Role> findAll();
}

