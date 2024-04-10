package c14.NoCountry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import c14.NoCountry.Entity.Users;

import java.util.Arrays;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    void deleteById(Integer id);
}