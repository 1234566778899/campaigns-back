package backend.helpinghand.services;

import backend.helpinghand.dtos.DTODonor;
import backend.helpinghand.entities.Donor;

import java.util.List;

public interface DonorService {
    public Donor addDonor(Donor donor);
    public Donor addDTODonor(DTODonor dtoDonor);
    public List<Donor> listAllDonors();
    public Donor updateDonor(Donor donor);
    public Donor findDonorById(Long id);
    public void deleteDonor(Long id);
    public List<Donor> listByEmail(String email);
    public List<Donor> listByName(String name);
    public List<Donor> listByAddress(String address);
    public List<Donor> listByPhone(String phone);
    public void sendNotification(Donor donor, String not_message);
}
