package com.group8.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);
    List<Host> findByEnabledTrue();
    List<Host> findByEnabledFalse();

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = FALSE WHERE a.email = ?1")
    int disableAppUser(String email);

    @Transactional
    @Modifying
    @Query("delete from AppUser a " +
            "WHERE a.email = ?1")
    void delete(String email);
}
