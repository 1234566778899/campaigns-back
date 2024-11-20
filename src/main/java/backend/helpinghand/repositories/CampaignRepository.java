package backend.helpinghand.repositories;

import backend.helpinghand.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    public List<Campaign> findByCampNameContains(String campaignName);
    public List<Campaign> findByOrganization_Id(Long organizationId);
    public List<Campaign> findByCategory_Id(Long categoryId);
    public List<Campaign> findByMoneyGoalGreaterThan(Double moneyGoal);
    public List<Campaign> findByCampName(String campaignName);
}

