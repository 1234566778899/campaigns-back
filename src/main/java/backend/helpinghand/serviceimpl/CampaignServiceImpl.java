package backend.helpinghand.serviceimpl;

import backend.helpinghand.dtos.DTOCampaignSummary;
import backend.helpinghand.entities.Campaign;
import backend.helpinghand.entities.Donation;
import backend.helpinghand.exceptions.InvalidDataException;
import backend.helpinghand.exceptions.KeyRepeatedDataException;
import backend.helpinghand.exceptions.ResourceNotFoundException;
import backend.helpinghand.repositories.CampaignRepository;
import backend.helpinghand.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    CampaignRepository campaignRepository;



    public boolean isCampaignNameRegistered(String campaignName) {
        return !campaignRepository.findByCampName(campaignName).isEmpty();
    }

    @Override
    public Campaign findCampById(Long id)  {
        Campaign campaignFound = campaignRepository.findById(id).orElse(null);
        return campaignFound;
    }

    @Override
    public List<Campaign> listAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Campaign addCampaign(Campaign campaign) {
        if(campaign.getCampName()==null || campaign.getCampName().isBlank()){
            throw new InvalidDataException("Campaign name cannot be blank");
        }
        if(isCampaignNameRegistered(campaign.getCampName())){
            throw new KeyRepeatedDataException("Campaign name: "+campaign.getCampName()+ " already exists");
        }
        if(campaign.getDescription()==null || campaign.getDescription().isBlank()){
            throw new InvalidDataException("Campaign description cannot be blank");
        }
        if(campaign.getMoneyGoal()==null || campaign.getMoneyGoal().isNaN()){
            throw new InvalidDataException("Campaign money Goal cannot be blank");
        }
        if(campaign.getStartDate()==null || campaign.getStartDate().isBlank()){
            throw new InvalidDataException("Campaign start date cannot be blank");
        }
        if(campaign.getEndDate()==null || campaign.getEndDate().isBlank()){
            throw new InvalidDataException("Campaign end date cannot be blank");
        }

        return campaignRepository.save(campaign);
    }

    @Override
    public List<DTOCampaignSummary> listAllDTOCampaigns() {
        List<DTOCampaignSummary> dtoCampaignSummaries = new ArrayList<>();

        List<Campaign> campaigns = listAllCampaigns();
        for (Campaign campaign : campaigns) {
            Long campaignId = campaign.getId();
            String campaignName = campaign.getCampName();
            Double moneyGoal = campaign.getMoneyGoal();
            Double moneyRaised = 0.0;
            String startDate = campaign.getStartDate();
            String endDate = campaign.getEndDate();

            for(Donation donation: campaign.getDonations()){
                moneyRaised = moneyRaised + donation.getMoneyAmount().doubleValue();
            }
            Double percentage = (moneyRaised/moneyGoal)*100.0;
            DTOCampaignSummary dtoCampaignSummary = new DTOCampaignSummary(campaignId,campaignName,moneyGoal,moneyRaised,percentage,startDate,endDate);
            dtoCampaignSummaries.add(dtoCampaignSummary);
        }
        return dtoCampaignSummaries;
    }

    @Override
    public void deleteCampaign(Long id) {
        Campaign campaignFound = findCampById(id);
        if (campaignFound == null) {
            throw new ResourceNotFoundException("Campaign with id: "+id+" not found");
        }
        campaignRepository.delete(campaignFound);
    }

    @Override
    public List<Campaign> getCampaignsByName(String campaignName) {
        if(!isCampaignNameRegistered(campaignName)){
            throw new ResourceNotFoundException("Campaign name: "+campaignName+" not found");
        }
        return campaignRepository.findByCampNameContains(campaignName);
    }

    @Override
    public List<Campaign> getCampaignsByOrganization(Long organizationId) {
        return campaignRepository.findByOrganization_Id(organizationId);
    }


    @Override
    public List<Campaign> getCampaignsByCategory(Long categoryId) {
        return campaignRepository.findByCategory_Id(categoryId);
    }

    @Override
    public List<Campaign> getCampaignsByMoneyGoalGreaterThan(Double moneyGoal) {
        return campaignRepository.findByMoneyGoalGreaterThan(moneyGoal);
    }


}
