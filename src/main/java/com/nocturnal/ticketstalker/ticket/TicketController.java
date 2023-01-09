package com.nocturnal.ticketstalker.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {

    @Autowired
    private TicketService service;

    @GetMapping({"/tickets", "/"})
    public String listTickets(Model model) {
        model.addAttribute("ticketDtos", service.getAllTicketsDto());
        return "tickets";
    }

    @GetMapping("/tickets/new")
    public String createNewStudentForm(Model model) {
        TicketEntity ticket = new TicketEntity();
        model.addAttribute("ticket", ticket);
        model.addAttribute("projects", service.listAllProjects());
        model.addAttribute("priorities", service.listAllPriorities());
        model.addAttribute("users", service.listAllusers());
        return "create_ticket";
    }

    @PostMapping("/tickets")
    public String saveTicket(@ModelAttribute("ticket") TicketEntity ticket, @RequestParam("project") Long projectId,
                             @RequestParam("priority") Integer priorityId, @RequestParam("user") Long userId) {
        ticket = service.changeTicketProjectPriorityAndUserWithId(ticket, projectId, priorityId, userId);
        ticket.setStatus(service.findStatusById(1).get());
        service.saveTicket(ticket);
        return "redirect:/tickets";
    }

}
