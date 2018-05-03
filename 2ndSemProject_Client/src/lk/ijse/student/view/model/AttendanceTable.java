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
public class AttendanceTable {
    private SimpleStringProperty rid=new SimpleStringProperty("");
    private SimpleStringProperty student=new SimpleStringProperty("");
    private SimpleStringProperty time=new SimpleStringProperty("");
    private SimpleStringProperty batch=new SimpleStringProperty("");
    private SimpleStringProperty subject=new SimpleStringProperty("");
    private SimpleStringProperty date=new SimpleStringProperty("");

    public AttendanceTable() {
    }
    
    public String getRid() {
        return rid.get();
    }
    public void setRid(String rid) {
        this.rid.set(rid);
    }
    
    public String getStudent() {
        return student.get();
    }
    public void setStudent(String student) {
        this.student.set(student);
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
    
}
