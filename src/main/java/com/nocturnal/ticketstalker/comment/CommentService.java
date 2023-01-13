package com.nocturnal.ticketstalker.comment;

import com.nocturnal.ticketstalker.ticket.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repo;

    public List<CommentEntity> findAllCommentsByTicket(TicketEntity ticket){
        return repo.findByTicketIdOrderByCreatedAtDesc(ticket);
    }
}
