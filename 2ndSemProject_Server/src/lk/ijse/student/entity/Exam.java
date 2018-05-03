/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


/**
 *
 * @author Kavindu
 */
@Entity
public class Exam {
    @Id
    private String eid;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Batch batch;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Subject subject;
    
    private String hall;
    private String type;
    private String date;
    private String time;

    
    public Exam() {
    }

    public Exam(String eid, Batch batch, Subject subject, String hall, String type, String date, String time) {
        this.eid = eid;
        this.batch = batch;
        this.subject = subject;
        this.hall = hall;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    /**
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

    /**
     * @return the batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * @return the hall
     */
    public String getHall() {
        return hall;
    }

    /**
     * @param hall the hall to set
     */
    public void setHall(String hall) {
        this.hall = hall;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

  
    
}
