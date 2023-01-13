package com.nocturnal.ticketstalker.comment;

import com.nocturnal.ticketstalker.ticket.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    public List<CommentEntity> findByTicketIdOrderByCreatedAtDesc(TicketEntity ticket);

}
