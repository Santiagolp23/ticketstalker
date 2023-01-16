package com.nocturnal.ticketstalker.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        ticket.setStatus(service.findStatusById(1));
        service.saveTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/update/{id}")
    public String showEditTicketForm(@PathVariable Long id, Model model){
        model.addAttribute("ticket",service.findTicketById(id));
        model.addAttribute("projects", service.listAllProjects());
        model.addAttribute("priorities", service.listAllPriorities());
        model.addAttribute("users", service.listAllusers());

        return "edit_ticket";
    }

    @PostMapping("/tickets/{id}")
    public String updateTicket(@PathVariable Long id, @ModelAttribute("ticket") TicketEntity ticket){
        TicketEntity existentTicket = service.findTicketById(id);

        existentTicket.setId(id);
        existentTicket.setTitle(ticket.getTitle());
        existentTicket.setDescription(ticket.getDescription());
        existentTicket.setProject(ticket.getProject());
        existentTicket.setPriority(ticket.getPriority());
        existentTicket.setUser(ticket.getUser());

        service.updateTicket(existentTicket);

        return "redirect:/tickets";

    }

    @GetMapping("/tickets/{id}")
    public String deleteTicket(@PathVariable Long id){
        service.deleteTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/details/{id}")
    public String showTicketDetails(@PathVariable Long id, Model model){
        model.addAttribute("ticket", service.findTicketById(id));
        model.addAttribute("comments", service.findAllCommentsByTicket(service.findTicketById(id)));
        model.addAttribute("projects", service.listAllProjects());
        model.addAttribute("priorities", service.listAllPriorities());
        model.addAttribute("users", service.listAllusers());

        return "ticket_details";
    }

}
