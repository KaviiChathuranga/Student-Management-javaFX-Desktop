/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Kavindu
 */
public class BatchTable {
    private SimpleStringProperty id=new SimpleStringProperty("");
    private SimpleStringProperty name=new SimpleStringProperty("");
    private SimpleStringProperty Student_limit=new SimpleStringProperty("");
    private SimpleStringProperty year=new SimpleStringProperty("");
    private SimpleStringProperty fee=new SimpleStringProperty("");

    public BatchTable() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id.set(id);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the Limit
     */
    public String getlimit() {
        return Student_limit.get();
    }

    /**
     * @param blimit the Limit to set
     */
    public void setlimit(String blimit) {
        this.Student_limit.set(blimit);
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year.get();
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year.set(year);
    }

    /**
     * @return the fee
     */
    public double getFee() {
        return Double.parseDouble(fee.get());
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(double fee) {
        this.fee.set(Double.toString(fee));
    }
    
    
}
