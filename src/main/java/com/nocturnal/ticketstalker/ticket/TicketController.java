package com.nocturnal.ticketstalker.ticket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    private final Log LOGGER = LogFactory.getLog(TicketController.class);

    @Autowired
    private TicketService service;

    @GetMapping({"/tickets", "/"})
    public String listTickets(Model model) {
        model.addAttribute("ticketDtos", service.getAllTicketsDtoAndSortByIdAsc());
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
    public String showEditTicketForm(@PathVariable Long id, Model model) {
        model.addAttribute("ticket", service.findTicketById(id));
        model.addAttribute("projects", service.listAllProjects());
        model.addAttribute("priorities", service.listAllPriorities());
        model.addAttribute("users", service.listAllusers());

        return "edit_ticket";
    }

    @PostMapping("/tickets/{id}")
    public String updateTicket(@PathVariable Long id, @ModelAttribute("ticket") TicketEntity ticket) {
        TicketEntity existentTicket = service.findTicketById(id);

        existentTicket.setTitle(ticket.getTitle());
        existentTicket.setProject(ticket.getProject());
        existentTicket.setDescription(ticket.getDescription());
        existentTicket.setPriority(ticket.getPriority());
        existentTicket.setUser(ticket.getUser());

        service.updateTicket(existentTicket);


        return "redirect:/tickets";

    }


    @GetMapping("/tickets/{id}")
    public String deleteTicket(@PathVariable Long id) {
        service.deleteTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets/details/{id}")
    public String showTicketDetails(@PathVariable Long id, Model model) {
        model.addAttribute("ticket", service.findTicketById(id));
        model.addAttribute("comments", service.findAllCommentsByTicket(service.findTicketById(id)));
        model.addAttribute("projects", service.listAllProjects());
        model.addAttribute("statuses", service.listAllStatuses());
        model.addAttribute("priorities", service.listAllPriorities());
        model.addAttribute("users", service.listAllusers());

        return "ticket_details";
    }

    @PostMapping("/tickets/details/{id}")
    @ResponseBody
    public String updateTicketFromDetailsForm(@PathVariable Long id, @RequestParam Integer statusId, @RequestParam Integer priorityId) {
        TicketEntity existentTicket = service.findTicketById(id);

        existentTicket.setStatus(service.findStatusById(statusId));
        existentTicket.setPriority(service.findPriorityById(priorityId));

        LOGGER.warn(existentTicket.getStatus().getName());
        LOGGER.warn(existentTicket.getPriority().getName());

        service.updateTicket(existentTicket);

        return "Success";

    }

}
