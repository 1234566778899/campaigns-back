package backend.helpinghand.serviceimpl;

import backend.helpinghand.dtos.DTODonation;
import backend.helpinghand.entities.Campaign;
import backend.helpinghand.entities.Donation;
import backend.helpinghand.entities.DonationStatus;
import backend.helpinghand.entities.Donor;
import backend.helpinghand.repositories.DonationRepository;
import backend.helpinghand.repositories.DonationStatusRepository;
import backend.helpinghand.services.CampaignService;
import backend.helpinghand.services.DonationService;
import backend.helpinghand.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    DonationRepository donationRepository;
    DonationStatusRepository donationStatusRepository;
    DonorService donorService;
    CampaignService campaignService;

    @Override
    public List<Donation> listAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public Donation addDonation(DTODonation dtoDonation) {
        Donation newDonation = new Donation();

        newDonation.setMoneyAmount(dtoDonation.getMoneyAmount());
        newDonation.setDonationDate(LocalDate.now().toString());

        newDonation.setDonationStatus(dtoDonation.getDonationStatus());

        newDonation.setDonor(dtoDonation.getDonor());

        newDonation.setCampaign(dtoDonation.getCampaign());

        return donationRepository.save(newDonation);
    }

    @Override
    public List<Donation> findByDonor_NameContains(String profileName) {
        return donationRepository.findByDonor_NameContains(profileName);
    }

    @Override
    public List<Donation> findByCampaign_CampNameContains(String campaignName) {
        return donationRepository.findByCampaign_CampNameContains(campaignName);
    }

    @Override
    public List<Donation> findByMoneyAmountGreaterThan(Float money) {
        return donationRepository.findByMoneyAmountGreaterThan(money);
    }

    @Override
    public void completeDonation(Long donationId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found with id: " + donationId));

        DonationStatus completedStatus = donationStatusRepository.findByStatus("Completed");
        donation.setDonationStatus(completedStatus);
        donationRepository.save(donation);

        Donor donor = donation.getDonor();
        String not_message = "Gracias por tu donación de S/." + donation.getMoneyAmount() + ". ¡Tu apoyo es invaluable!";
        donorService.sendNotification(donor, not_message);
    }
}
