package backend.helpinghand.services;

import backend.helpinghand.entities.SupportTicket;

import java.util.List;

public interface SupportTicketService {
    public SupportTicket addTicket(SupportTicket supportTicket);
    public List<SupportTicket> listAllSupportTickets();

}
