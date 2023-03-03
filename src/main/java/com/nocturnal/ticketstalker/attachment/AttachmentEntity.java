package com.nocturnal.ticketstalker.attachment;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "attachments")
public class AttachmentEntity {

    @EmbeddedId
    private AttachmentId attachmentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path", nullable = false)
    private String path;

    public AttachmentEntity() {
    }

    public AttachmentEntity(AttachmentId attachmentId, String name, String path) {
        this.attachmentId = attachmentId;
        this.name = name;
        this.path = path;
    }

    public AttachmentId getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(AttachmentId attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
