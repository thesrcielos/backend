package c14.NoCountry.Repository;

import c14.NoCountry.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import c14.NoCountry.Entity.Users;

import java.util.Arrays;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    boolean existsByEmail(String email);
<<<<<<< HEAD
    @Query(value = "SELECT * FROM users WHERE email LIKE %?1%", nativeQuery = true)
    List<Users> searchProjectByEmail(String searchTerm);
=======


>>>>>>> 3956438f74385c239b6560ed215e15c3b2e8e195
}