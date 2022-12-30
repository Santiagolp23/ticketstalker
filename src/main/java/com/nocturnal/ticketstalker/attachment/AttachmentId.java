package com.nocturnal.ticketstalker.attachment;

import com.nocturnal.ticketstalker.comment.CommentEntity;
import com.nocturnal.ticketstalker.ticket.TicketEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;

@Embeddable
public class AttachmentId implements Serializable {

    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

    @OneToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private CommentEntity comment;

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }
}
