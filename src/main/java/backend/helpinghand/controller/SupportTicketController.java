package backend.helpinghand.controller;

import backend.helpinghand.entities.SupportTicket;
import backend.helpinghand.services.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helpinghands")
@CrossOrigin("*")
public class SupportTicketController {
    @Autowired
    SupportTicketService supportTicketService;

    @GetMapping("/support-tickets")
    public ResponseEntity<List<SupportTicket>> listAllSupportTickets() {
        List<SupportTicket> tickets = supportTicketService.listAllSupportTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping("/support-tickets")
    public ResponseEntity<SupportTicket> addSupportTicket(@RequestBody SupportTicket ticket) {
        SupportTicket newTicket = supportTicketService.addTicket(ticket);
        if(newTicket == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }


}
