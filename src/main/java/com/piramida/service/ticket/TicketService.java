package com.piramida.service.ticket;

import java.util.List;

public interface TicketService {

    List<String> getAllTicketNames();

    void deleteTicket(String ticketName);

    boolean checkTicketByName(String ticketName);
}
