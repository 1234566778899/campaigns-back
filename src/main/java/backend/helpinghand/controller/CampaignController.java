package backend.helpinghand.controller;


import backend.helpinghand.dtos.DTOCampaignSummary;
import backend.helpinghand.entities.Campaign;
import backend.helpinghand.repositories.CampaignRepository;
import backend.helpinghand.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/helpinghands")
@CrossOrigin(origins = "http://localhost:4200")

public class CampaignController {
    @Autowired
    CampaignService campaignService;

    @GetMapping("/campaigns")
    public ResponseEntity<List<Campaign>> listAllCampaigns() {
        return new ResponseEntity<>(campaignService.listAllCampaigns(), HttpStatus.OK);
    }

    @GetMapping("/campaigns/summary")
    public ResponseEntity<List<DTOCampaignSummary>> listAllDTOCampaigns() {
        return new ResponseEntity<>(campaignService.listAllDTOCampaigns(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Campaign newCampaign = campaignService.addCampaign(campaign);
        return new ResponseEntity<>(newCampaign, HttpStatus.OK);
    }

    @DeleteMapping("/campaigns/{id}")
    public ResponseEntity<HttpStatus> deleteCampaign( @PathVariable("id") Long id){
        campaignService.deleteCampaign(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/campaigns/{name}")
    public ResponseEntity<List<Campaign>> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(campaignService.getCampaignsByName(name), HttpStatus.OK);
    }

    @GetMapping("/campaigns/organizations/{organizationId}")
    public List<Campaign> getCampaignsByOrganization(@PathVariable ("organizationId")Long organizationId) {
        return campaignService.getCampaignsByOrganization(organizationId);
    }

    @GetMapping("/campaigns/category/{categoryId}")
    public List<Campaign> getCampaignsByCategory(@PathVariable("categoryId") Long categoryId) {
        return campaignService.getCampaignsByCategory(categoryId);
    }

    @GetMapping("campaigns/moneygoal/{moneygoal}")
    public List<Campaign> getCampaignsByMoneyGoalGreaterThan(@PathVariable("moneygoal") Double moneyGoal) {
        return campaignService.getCampaignsByMoneyGoalGreaterThan(moneyGoal);
    }


}

