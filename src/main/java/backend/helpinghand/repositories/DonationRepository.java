package backend.helpinghand.repositories;

import backend.helpinghand.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    public List<Donation> findByDonor_NameContains(String name);
    public List<Donation> findByCampaign_CampNameContains(String name);
    public List<Donation> findByMoneyAmountGreaterThan(Float money);
}
