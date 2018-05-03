/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Kavindu
 */
@Entity
public class Batch {
    @Id
    private String id;
    
    private String name;
    private String bLimit;
    private String year;
    private double fee;

    
    public Batch() {
    }

    public Batch(String bid, String name, String sLimit, String sYear, double fee) {
        this.id = bid;
        this.name = name;
        this.bLimit = sLimit;
        this.year = sYear;
        this.fee = fee;
    }
    public Batch(String name, String sLimit, String sYear, double fee) {
        
        this.name = name;
        this.bLimit = sLimit;
        this.year = sYear;
        this.fee = fee;
    }

    /**
     * @return the bid
     */
    public String getBid() {
        return id;
    }

    /**
     * @param bid the bid to set
     */
    public void setBid(String bid) {
        this.id = bid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sLimit
     */
    public String getsLimit() {
        return bLimit;
    }

    /**
     * @param sLimit the sLimit to set
     */
    public void setsLimit(String sLimit) {
        this.bLimit = sLimit;
    }

    /**
     * @return the sYear
     */
    public String getsYear() {
        return year;
    }

    /**
     * @param sYear the sYear to set
     */
    public void setsYear(String sYear) {
        this.year = sYear;
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

    @Override
    public String toString() {
        return "Batch{" + "id=" + id + ", name=" + name + ", blimit=" + bLimit + ", year=" + year + ", fee=" + fee + '}';
    }
    
    
    
        
}
