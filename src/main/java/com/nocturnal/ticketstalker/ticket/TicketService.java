package com.nocturnal.ticketstalker.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repo;

    public List<TicketEntity> listAllTickets() {
        return repo.findAll();
    }

    public List<TicketDTO> getAllTicketsDto() {
        return repo.getTicketDtos();
    }

}
