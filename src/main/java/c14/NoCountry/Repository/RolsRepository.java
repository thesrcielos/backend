package c14.NoCountry.Repository;

import c14.NoCountry.Entity.comments;
import c14.NoCountry.Entity.rols;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolsRepository extends JpaRepository<rols, Long> {
}
