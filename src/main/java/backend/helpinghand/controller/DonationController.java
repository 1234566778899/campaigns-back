package backend.helpinghand.controller;

import backend.helpinghand.dtos.DTODonation;
import backend.helpinghand.entities.Donation;
import backend.helpinghand.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helpinghands")
@CrossOrigin("*")
public class DonationController {

    @Autowired
    DonationService donationService;

    @GetMapping("/donations")
    public ResponseEntity<List<Donation>> listAllDonations() {
        return new ResponseEntity<>(donationService.listAllDonations(), HttpStatus.OK);
    }


    @PostMapping("/donations")
    public ResponseEntity<Donation>  addDonation(@RequestBody DTODonation dtoDonation) {
        Donation newDonation = donationService.addDonation(dtoDonation);
        return new ResponseEntity<>(newDonation, HttpStatus.OK);
    }

    @PutMapping("/donations/{donationId}/complete")
    public ResponseEntity<String> completeDonation(@PathVariable Long donationId) {
        donationService.completeDonation(donationId);
        return new ResponseEntity<>("Donation completed successfully!", HttpStatus.OK);
    }

    @GetMapping("/donations/money/{amount}")
    public List<Donation> listDonationsMoneyGreaterThan(@PathVariable("amount") Float moneyAmount) {
        return donationService.findByMoneyAmountGreaterThan(moneyAmount);
    }

    @GetMapping("/donations/profiles/{profile_name}")
    public ResponseEntity<List<Donation>> listDonationsByProfileName(@PathVariable ("profile_name") String profileName) {
        return new ResponseEntity<>(donationService.findByDonor_NameContains(profileName),HttpStatus.OK);
    }

    @GetMapping("/comments/campaigns/{campaign_name}")
    public ResponseEntity<List<Donation>> listDonationsByCampaignName(@PathVariable ("campaign_name")  String campaignName){
        return new ResponseEntity<>(donationService.findByCampaign_CampNameContains(campaignName),HttpStatus.OK);
    }


}
