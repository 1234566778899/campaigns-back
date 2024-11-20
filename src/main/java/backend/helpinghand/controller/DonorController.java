package backend.helpinghand.controller;


import backend.helpinghand.entities.Donor;
import backend.helpinghand.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helpinghands")
@CrossOrigin("*")
public class DonorController {
    @Autowired
    DonorService donorService;

    @GetMapping("/donors")
    public ResponseEntity<List<Donor>> listAllDonors() {
        return new ResponseEntity<>(donorService.listAllDonors(), HttpStatus.OK);
    }


    @PostMapping("/donors")
    public ResponseEntity<Donor>  addDonor(@RequestBody Donor donor) {
        Donor newDonor = donorService.addDonor(donor);
        return new ResponseEntity<>(newDonor, HttpStatus.OK);
    }

    @PutMapping("/donors/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable("id") Long id, @RequestBody Donor donor){
        donor.setId(id);
        Donor updateDonor = donorService.updateDonor(donor);
        return new ResponseEntity<>(updateDonor, HttpStatus.OK);
    }

    @DeleteMapping("/donors/{id}")
    public ResponseEntity<HttpStatus> deleteDonor( @PathVariable("id") Long id){
        donorService.deleteDonor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/donors/email/{email}")
    public ResponseEntity<List<Donor>> getDonorsByEmail(@PathVariable("email") String email) {
        List<Donor> donors = donorService.listByEmail(email);
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }

    @GetMapping("/donors/name/{name}")
    public ResponseEntity<List<Donor>> getDonorsByName(@PathVariable("name") String name) {
        List<Donor> donors = donorService.listByName(name);
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }

    @GetMapping("/donors/address/{address}")
    public ResponseEntity<List<Donor>> getDonorsByAddress(@PathVariable("address") String address) {
        List<Donor> donors = donorService.listByAddress(address);
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }

    @GetMapping("/donors/phone/{phone}")
    public ResponseEntity<List<Donor>> getDonorsByPhone(@PathVariable("phone") String phone) {
        List<Donor> donors = donorService.listByPhone(phone);
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }

}
