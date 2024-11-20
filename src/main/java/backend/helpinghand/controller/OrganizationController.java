package backend.helpinghand.controller;


import backend.helpinghand.entities.Organization;
import backend.helpinghand.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helpinghands")
@CrossOrigin("*")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @GetMapping("/orgs")
    public ResponseEntity<List<Organization>> listAllOrganizations() {
        return new ResponseEntity<>(organizationService.listAllOrganizations(), HttpStatus.OK);
    }

    @PostMapping("/orgs")
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization){
        Organization newOrganization = organizationService.addOrganization(organization);
        if(newOrganization == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(newOrganization, HttpStatus.CREATED);
    }

    @DeleteMapping("/orgs/{id}")
    public ResponseEntity<HttpStatus> deleteOrganization( @PathVariable("id") Long id){
        organizationService.deleteOrganization(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
