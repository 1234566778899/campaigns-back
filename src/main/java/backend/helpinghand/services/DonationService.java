package backend.helpinghand.services;

import backend.helpinghand.dtos.DTODonation;
import backend.helpinghand.entities.Donation;

import java.util.List;

public interface DonationService {
    public List<Donation> listAllDonations();
    public Donation addDonation(DTODonation dtoDonation);
    public List<Donation> findByDonor_NameContains(String name);
    public List<Donation> findByCampaign_CampNameContains(String name);
    public List<Donation> findByMoneyAmountGreaterThan(Float money);
    public void completeDonation(Long donationId);
}
