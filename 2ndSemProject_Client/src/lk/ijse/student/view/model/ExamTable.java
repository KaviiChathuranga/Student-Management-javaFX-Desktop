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
public class ExamTable {
    private SimpleStringProperty eid=new SimpleStringProperty("");
    private SimpleStringProperty batch=new SimpleStringProperty("");
    private SimpleStringProperty subject=new SimpleStringProperty("");
    private SimpleStringProperty date=new SimpleStringProperty("");
    private SimpleStringProperty time=new SimpleStringProperty("");
    private SimpleStringProperty hall=new SimpleStringProperty("");
    private SimpleStringProperty type=new SimpleStringProperty("");

    public ExamTable() {
    }
    
    public String getEid() {
        return eid.get();
    }
    public void setEid(String eid) {
        this.eid.set(eid);
    }
    
    public String getTime() {
        return time.get();
    }
    public void setTime(String time) {
        this.time.set(time);
    }
    
    
    public String getBatch() {
        return batch.get();
    }
    public void setBatch(String batch) {
        this.batch.set(batch);
    }
    
    public String getSubject() {
        return subject.get();
    }
    public void setSubject(String subject) {
        this.subject.set(subject);
    }
    
    public String getDate() {
        return date.get();
    }
    public void setDate(String date) {
        this.date.set(date);
    }
    
    public String getHall() {
        return hall.get();
    }
    public void setHall(String hall) {
        this.hall.set(hall);
    }
    
    public String getType() {
        return type.get();
    }
    public void setType(String type) {
        this.type.set(type);
    }
    
}
