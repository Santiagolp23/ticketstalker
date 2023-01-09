package com.nocturnal.ticketstalker.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    @Query("SELECT new com.nocturnal.ticketstalker.ticket.TicketProjectDTO(t,p.name,prio.name,s.name,CONCAT(u.firstName,' ',u.lastName)) " +
            "FROM TicketEntity t " +
            "INNER JOIN t.project p " +
            "INNER JOIN t.priority prio " +
            "INNER JOIN t.status s " +
            "INNER JOIN t.user u")
    public List<TicketProjectDTO> getTicketDtos();
}
