package com.jpql.Repository.role;

import java.util.Optional;

import com.jpql.usermodel.ERole;
import com.jpql.usermodel.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);
    
}
