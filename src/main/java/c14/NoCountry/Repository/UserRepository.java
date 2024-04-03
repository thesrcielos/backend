package c14.NoCountry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import c14.NoCountry.Entity.users;

@Repository
public interface UserRepository extends JpaRepository<users, Long> {
    users findByEmail(String email);
}