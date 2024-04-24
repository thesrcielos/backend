package c14.NoCountry.Repository;

import c14.NoCountry.Entity.Donations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DonationsRepository extends JpaRepository<Donations, Long> {
    List<Donations> findByUser_Id(Integer id);
    //Optional<Donations> findById(Long id);
}
