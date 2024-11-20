package backend.helpinghand.serviceimpl;

import backend.helpinghand.entities.Organization;
import backend.helpinghand.exceptions.InvalidDataException;
import backend.helpinghand.exceptions.KeyRepeatedDataException;
import backend.helpinghand.exceptions.ResourceNotFoundException;
import backend.helpinghand.services.OrganizationService;
import backend.helpinghand.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public boolean isOrgNameRegistered(String orgName) {
        return !organizationRepository.findByOrgName(orgName).isEmpty();
    }

    @Override
    public Organization addOrganization(Organization organization) {
        if(organization.getOrgName()==null || organization.getOrgName().isBlank()){
            throw new InvalidDataException("Organization name cannot be blank");
        }
        if(isOrgNameRegistered(organization.getOrgName())){
            throw new KeyRepeatedDataException("Organization name: "+organization.getOrgName()+ " already exists");
        }
        if(organization.getOrgDescription()==null || organization.getOrgDescription().isBlank()){
            throw new InvalidDataException("Organization description cannot be blank");
        }
        if(organization.getOrgEmail()==null || organization.getOrgEmail().isBlank()){
            throw new InvalidDataException("Organization email cannot be blank");
        }
        if(organization.getOrgPhone()==null || organization.getOrgPhone().isBlank()){
            throw new InvalidDataException("Organization phone cannot be blank");
        }

        return organizationRepository.save(organization);
    }

    @Override
    public Organization findOrgById(Long id){
        Organization organizationFound = organizationRepository.findById(id).orElse(null);
        return organizationFound;
    }


    @Override
    public List<Organization> listAllOrganizations() {
        List<Organization> listOrg = organizationRepository.findAll();
        return listOrg;
    }

    @Override
    public void deleteOrganization(Long id) {
        Organization orgFound = findOrgById(id);
        if (orgFound == null) {
            throw new ResourceNotFoundException("Organization with id: "+id+" not found");
        }
        if(!orgFound.getCampaigns().isEmpty()) {
            throw new InvalidDataException("Profile with id: "+id+" cannot be deleted because is used in FK Campaigns");
        }
        organizationRepository.delete(orgFound);
    }
}
