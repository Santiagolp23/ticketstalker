package com.nocturnal.ticketstalker.ticket;

import com.nocturnal.ticketstalker.comment.CommentEntity;
import com.nocturnal.ticketstalker.comment.CommentService;
import com.nocturnal.ticketstalker.priority.PriorityEntity;
import com.nocturnal.ticketstalker.priority.PriorityService;
import com.nocturnal.ticketstalker.project.ProjectEntity;
import com.nocturnal.ticketstalker.project.ProjectService;
import com.nocturnal.ticketstalker.status.StatusEntity;
import com.nocturnal.ticketstalker.status.StatusService;
import com.nocturnal.ticketstalker.user.UserEntity;
import com.nocturnal.ticketstalker.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repo;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private CommentService commentService;

    public List<TicketEntity> listAllTickets() {
        return repo.findAll();
    }

    public TicketEntity findTicketById(Long id) {
        return repo.findById(id).get();
    }


    public TicketEntity saveTicket(TicketEntity ticket) {
        return repo.save(ticket);
    }

    public void updateTicket(TicketEntity ticket) {
        repo.save(ticket);
    }

    public void deleteTicket(Long id) {
        repo.deleteById(id);
    }


    public List<TicketProjectDTO> getAllTicketsDtoAndSortByIdAsc() {
        return repo.getTicketDtosAndSort(Sort.by("id"));
    }

    public List<ProjectEntity> listAllProjects() {
        return projectService.listAllProjects();
    }

    public List<PriorityEntity> listAllPriorities() {
        return priorityService.listAllPriorities();
    }

    public List<UserEntity> listAllusers() {
        return userService.listAllUsers();
    }

    public StatusEntity findStatusById(Integer id) {
        return statusService.findById(id).get();
    }

    public List<StatusEntity> listAllStatuses() {
        return statusService.listAllStatuses();
    }

    public UserEntity findUserById(Long id) {
        return userService.findById(id).get();
    }


    public TicketEntity changeTicketProjectPriorityAndUserWithId(TicketEntity ticket, Long projectId, Integer priotityId,
                                                                 Long userId) {
        ticket.setProject(projectService.findById(projectId).orElseThrow());
        ticket.setPriority(priorityService.findById(priotityId).orElseThrow());
        ticket.setUser(userService.findById(userId).orElseThrow());
        return ticket;
    }

    public List<CommentEntity> findAllCommentsByTicket(TicketEntity ticket){
        return commentService.findAllCommentsByTicket(ticket);
    }

}
