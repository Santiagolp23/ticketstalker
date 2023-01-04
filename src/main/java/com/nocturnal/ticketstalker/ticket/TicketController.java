package com.nocturnal.ticketstalker.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {

    @Autowired
    private TicketService service;

    @GetMapping({"/tickets", "/"})
    public String listTickets(Model model) {
        model.addAttribute("ticketDtos", service.getAllTicketsDto());
        return "tickets";
    }

}
