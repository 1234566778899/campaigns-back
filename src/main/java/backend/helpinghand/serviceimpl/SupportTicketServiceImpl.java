package backend.helpinghand.serviceimpl;

import backend.helpinghand.entities.SupportTicket;
import backend.helpinghand.repositories.SupportTicketRepository;
import backend.helpinghand.services.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportTicketServiceImpl implements SupportTicketService {
    @Autowired
    SupportTicketRepository supportTicketRepository;


    @Override
    public SupportTicket addTicket(SupportTicket supportTicket) {
        return supportTicketRepository.save(supportTicket);
    }

    @Override
    public List<SupportTicket> listAllSupportTickets() {
        List<SupportTicket> listTicket = supportTicketRepository.findAll();
        return listTicket;
    }
}
