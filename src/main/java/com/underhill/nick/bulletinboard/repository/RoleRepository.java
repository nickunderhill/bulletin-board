package com.underhill.nick.bulletinboard.repository;

import com.underhill.nick.bulletinboard.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByName(String name);

}
