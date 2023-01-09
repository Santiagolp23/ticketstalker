package com.nocturnal.ticketstalker.ticket;


import org.springframework.beans.factory.annotation.Autowired;

public class TicketProjectDTO {

    @Autowired
    private TicketEntity ticketEntity;

    private String projectName;

    private String priorityName;

    private String statusName;

    private String userName;

    public TicketProjectDTO(TicketEntity ticketEntity, String projectName, String priorityName, String statusName, String userName) {
        this.ticketEntity = ticketEntity;
        this.projectName = projectName;
        this.priorityName = priorityName;
        this.statusName = statusName;
        this.userName = userName;
    }

    public TicketEntity getTicketEntity() {
        return ticketEntity;
    }

    public void setTicketEntity(TicketEntity ticketEntity) {
        this.ticketEntity = ticketEntity;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
