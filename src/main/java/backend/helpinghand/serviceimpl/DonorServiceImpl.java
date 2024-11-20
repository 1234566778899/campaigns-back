package backend.helpinghand.serviceimpl;

import backend.helpinghand.dtos.DTODonor;
import backend.helpinghand.entities.Donor;
import backend.helpinghand.entities.Notification;
import backend.helpinghand.exceptions.InvalidDataException;
import backend.helpinghand.exceptions.ResourceNotFoundException;
import backend.helpinghand.repositories.DonorRepository;
import backend.helpinghand.repositories.NotificationRepository;
import backend.helpinghand.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {
    @Autowired
    DonorRepository donorRepository;
    NotificationRepository notificationRepository;


    @Override
    public Donor findDonorById(Long id) throws ResourceNotFoundException {
        Donor donorFound = donorRepository.findById(id).orElse(null);
        return donorFound;
    }


    @Override
    public Donor addDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public Donor addDTODonor(DTODonor dtoDonor) {
        Donor newDonor = new Donor();
        return donorRepository.save(newDonor);
    }

    @Override
    public List<Donor> listAllDonors() {
        return donorRepository.findAll();
    }

    @Override
    public Donor updateDonor(Donor donor) {
        Donor donorFound = findDonorById(donor.getId());
        if (donorFound != null) {
            if (donor.getName() != null) {
                if(donorFound.getName().isBlank()) {
                    throw new InvalidDataException("Profile name cannot be blank");
                }
                donorFound.setName(donor.getName());
            }
            if(donor.getEmail() != null) {
                if(donor.getEmail().isBlank()) {
                    throw new InvalidDataException("Profile email cannot be blank");
                }
                donorFound.setEmail(donor.getEmail());
            }
            if(donor.getPhone() != null) {
                if(donor.getPhone().isBlank()) {
                    throw new InvalidDataException("Profile phone cannot be blank");
                }
            }
            if(donor.getAddress() != null) {
                if(donor.getAddress().isBlank()) {
                    throw new InvalidDataException("Profile address cannot be blank");
                }
            }
            if(donor.getBirthdate()!=null) {
                donorFound.setBirthdate(donor.getBirthdate());
            }

            return donorRepository.save(donorFound);
        }
        else {
            throw new ResourceNotFoundException("Profile with id: "+ donor.getId()+" not found");
        }

    }

    @Override
    public void deleteDonor(Long id) {
        Donor donorFound = findDonorById(id);
        if (donorFound == null) {
            throw new ResourceNotFoundException("Profile with id: "+id+" not found");
        }
        if(!donorFound.getComments().isEmpty()) {
            throw new InvalidDataException("Profile with id: "+id+" cannot be deleted because is used in FK Comments");
        }
        if(!donorFound.getDonations().isEmpty()) {
            throw new InvalidDataException("Profile with id:" +id+" cannot be deleted because is used in FK Donations");
        }
        donorRepository.delete(donorFound);

    }




    @Override
    public List<Donor> listByEmail(String email) {
        return donorRepository.findByEmailContains(email);
    }

    @Override
    public List<Donor> listByName(String name) {

        return donorRepository.findByNameContains(name);
    }

    @Override
    public List<Donor> listByAddress(String address) {

        return donorRepository.findByAddressContains(address);
    }

    @Override
    public List<Donor> listByPhone(String phone) {

        return donorRepository.findByPhoneContains(phone);
    }


    @Override
    public void sendNotification(Donor donor, String not_message){
        Notification notification = new Notification();
        notification.setDonor(donor);
        notification.setMessage(not_message);
        notification.setRead(false);

        notificationRepository.save(notification);
    }
}
