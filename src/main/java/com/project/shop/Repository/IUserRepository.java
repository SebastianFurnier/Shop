package com.project.shop.Repository;

import com.project.shop.Model.Role;
import com.project.shop.Model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserSec, Long> {
    Optional<UserSec> findUserEntityByUsername(String username);
    UserSec searchUserById(Long id);
    Optional<UserSec> searchByUsername(String username);
    Optional<UserSec> searchByEmail(String email);
    @Query("SELECT p.id FROM UserSec p WHERE p.username = :username")
    Long getIdByUsername(String username);
}
