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
public class Payment {
 @Id
    private String pid; 
    
    @OneToOne(cascade = CascadeType.ALL)
    private Registration registration;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Batch batch;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Subject subject;
    
    private String date;
    private double fee;
    private String month;

    public Payment() {
    }

    public Payment(String pid, Registration registration, Batch batch, Subject subject, String date, double fee, String month) {
        this.pid = pid;
        this.registration = registration;
        this.batch = batch;
        this.subject = subject;
        this.date = date;
        this.fee = fee;
        this.month = month;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
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
     * @return the fee
     */
    public double getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

   
 
 
}
