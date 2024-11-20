package backend.helpinghand.services;

import backend.helpinghand.dtos.DTOCampaignSummary;
import backend.helpinghand.entities.Campaign;

import java.util.List;

public interface CampaignService {
    public Campaign findCampById(Long id);
    public List<Campaign> listAllCampaigns();
    public Campaign addCampaign(Campaign Campaign);
    public List<DTOCampaignSummary> listAllDTOCampaigns();
    public void deleteCampaign(Long id);
    public List<Campaign>getCampaignsByName(String campaignName);
    public List<Campaign>getCampaignsByOrganization(Long organizationId);
    public List<Campaign> getCampaignsByCategory(Long categoryId);
    public List<Campaign> getCampaignsByMoneyGoalGreaterThan(Double moneyGoal);




}
