/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.dto;

/**
 *
 * @author Kavindu
 */
public class BatchDto extends SuperDto{
    private String bid;
    private String name;
    private String limit;
    private String year;
    private double fee;
    

    public BatchDto() {
    }

    public BatchDto(String bid, String name, String limit, String year, double fee) {
        this.bid = bid;
        this.name = name;
        this.limit = limit;
        this.year = year;
        this.fee = fee;
    }
     public BatchDto(String name, String limit, String year, double fee) {
        this.name = name;
        this.limit = limit;
        this.year = year;
        this.fee = fee;
    }
    

    /**
     * @return the bid
     */
    public String getBid() {
        return bid;
    }

    /**
     * @param bid the bid to set
     */
    public void setBid(String bid) {
        this.bid = bid;
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
     * @return the limit
     */
    public String getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(String limit) {
        this.limit = limit;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
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
        return "BatchDto{" + "bid=" + bid + ", name=" + name + ", limit=" + limit + ", year=" + year + ", fee=" + fee + '}';
    }
    
    
    
    
}
