package backend.helpinghand.services;

import backend.helpinghand.entities.Organization;

import java.util.List;

public interface OrganizationService {
    public Organization addOrganization(Organization organization);
    public Organization findOrgById(Long id);
    public List<Organization> listAllOrganizations();
    public void deleteOrganization(Long id);
}
