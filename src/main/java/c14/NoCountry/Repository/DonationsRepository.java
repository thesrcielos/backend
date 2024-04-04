package c14.NoCountry.Repository;

import c14.NoCountry.Entity.donations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationsRepository extends JpaRepository<donations, Long> {
}
