package com.postgrado.ecommerce.repository;

import com.postgrado.ecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

  /*
  @Query(value="SELECT * FROM roles WHERE name = ?1", nativeQuery = true)
  Role findRoleByName(String name);

  @Query("SELECT r FROM Role r WHERE r.name = ?1")
  Role getRoleByName(String name);
  */
    Optional<Role> findByName(String name);
}

