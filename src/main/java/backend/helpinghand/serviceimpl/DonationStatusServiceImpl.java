package backend.helpinghand.serviceimpl;

import backend.helpinghand.entities.DonationStatus;
import backend.helpinghand.repositories.DonationStatusRepository;
import backend.helpinghand.services.DonationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationStatusServiceImpl implements DonationStatusService {

    @Autowired
    DonationStatusRepository donationStatusRepository;

    @Override
    public DonationStatus addDonationStatus(DonationStatus donationStatus) {
        return donationStatusRepository.save(donationStatus);
    }
}
