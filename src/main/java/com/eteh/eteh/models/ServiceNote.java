package com.eteh.eteh.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;
@Audited
@Entity
public class ServiceNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    @CreationTimestamp
    @CreatedDate
    private Date dataCreation;

    @UpdateTimestamp
    @CreatedDate
    private LocalDateTime dataChange;

    @Size(max = 75)
    private String briefDescription;

    @Size(max = 9000)
    private String  mainText;
    @Size(max = 9000)
    private String  commentsText;
    @ManyToOne(fetch = FetchType.LAZY)
    private User executor;
    @ManyToOne(fetch = FetchType.LAZY)
    private User controller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_author")
    @CreatedBy
    @LastModifiedBy
    private User author;

    public ServiceNote() {
    }

    public ServiceNote(Long id, Date dataCreation,
                       LocalDateTime dataChange, @Size(max = 75) String briefDescription,
                       @Size(max = 9000) String mainText, @Size(max = 9000) String commentsText,
                       User executor, User controller, User author) {
        this.id = id;
        this.dataCreation = dataCreation;
        this.dataChange = dataChange;
        this.briefDescription = briefDescription;
        this.mainText = mainText;
        this.commentsText = commentsText;
        this.executor = executor;
        this.controller = controller;
        this.author = author;
    }

    public ServiceNote(String briefDescription, User user, String mainText, String commentsText, User executor, User controller) {

        this.briefDescription=briefDescription;
        this.author = user;
        this.mainText=mainText;
        this.commentsText=commentsText;
        this.executor=executor;
        this.controller=controller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCreation() {
        return dataCreation;
    }

    public void setDataCreation(Date dataCreation) {
        this.dataCreation = dataCreation;
    }

    public LocalDateTime getDataChange() {
        return dataChange;
    }

    public void setDataChange(LocalDateTime dataChange) {
        this.dataChange = dataChange;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getCommentsText() {
        return commentsText;
    }

    public void setCommentsText(String commentsText) {
        this.commentsText = commentsText;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public User getController() {
        return controller;
    }

    public void setController(User controller) {
        this.controller = controller;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
