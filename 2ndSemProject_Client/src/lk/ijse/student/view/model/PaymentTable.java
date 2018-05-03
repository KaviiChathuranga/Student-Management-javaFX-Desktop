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
public class PaymentTable {
    private SimpleStringProperty Date=new SimpleStringProperty("");
    private SimpleStringProperty Month=new SimpleStringProperty("");
    private SimpleStringProperty RID=new SimpleStringProperty("");
    private SimpleStringProperty Student=new SimpleStringProperty("");
    private SimpleStringProperty Batch=new SimpleStringProperty("");
    private SimpleStringProperty Subject=new SimpleStringProperty("");
    private SimpleStringProperty Fee=new SimpleStringProperty("");
    
    public String getDate() {
        return Date.get();
    }
    public void setDate(String eid) {
        this.Date.set(eid);
    }
    //////////////////////////////////////////////////////
        public String getMonth() {
        return Month.get();
    }
    public void setMonth(String eid) {
        this.Month.set(eid);
    }
    ////////////////////////////////////////////////////////
        public String getRID() {
        return RID.get();
    }
    public void setRID(String eid) {
        this.RID.set(eid);
    }
    ////////////////////////////////////////////////////////
        public String getStudent() {
        return Student.get();
    }
    public void setStudent(String eid) {
        this.Student.set(eid);
    }
    /////////////////////////////////////////////////////////
        public String getBatch() {
        return Batch.get();
    }
    public void setBatch(String eid) {
        this.Batch.set(eid);
    }
    /////////////////////////////////////////////////////////
        public String getSubject() {
        return Subject.get();
    }
    public void setSubject(String eid) {
        this.Subject.set(eid);
    }
    ////////////////////////////////////////////////////////
        public String getFee() {
        return Fee.get();
    }
    public void setfee(String eid) {
        this.Fee.set(eid);
    }
}
