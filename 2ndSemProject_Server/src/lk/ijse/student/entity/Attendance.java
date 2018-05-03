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
public class Attendance {
    @Id
    private String aid;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Registration registration;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Batch batch;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Subject subject;
    private String date;
    
    private String time;

    
    public Attendance() {
    }

    public Attendance(String aid, Registration registration, Batch batch, Subject subject, String date, String time) {
        this.aid = aid;
        this.registration = registration;
        this.batch = batch;
        this.subject = subject;
        this.date = date;
        this.time = time;
    }

    /**
     * @return the aid
     */
    public String getAid() {
        return aid;
    }

    /**
     * @param aid the aid to set
     */
    public void setAid(String aid) {
        this.aid = aid;
    }

    /**
     * @return the registration
     */
    public Registration getRegistration() {
        return registration;
    }

    /**
     * @param registration the registration to set
     */
    public void setRegistration(Registration registration) {
        this.registration = registration;
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
