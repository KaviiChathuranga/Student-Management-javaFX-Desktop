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
public class ResultTable {
    private SimpleStringProperty EID=new SimpleStringProperty("");
    private SimpleStringProperty Date=new SimpleStringProperty("");
    private SimpleStringProperty RID=new SimpleStringProperty("");
    private SimpleStringProperty Student=new SimpleStringProperty("");
    private SimpleStringProperty Nic=new SimpleStringProperty("");
    private SimpleStringProperty Marks=new SimpleStringProperty("");
    
    
    
    public String getEid() {
        return EID.get();
    }
    public void setEid(String eid) {
        this.EID.set(eid);
    }
    /////////////////////////////////////////////////////////////////////////////////
    public String getDate() {
        return Date.get();
    }
    public void setDate(String eid) {
        this.Date.set(eid);
    }
    /////////////////////////////////////////////////////////////////////////////////
    public String getRID() {
        return RID.get();
    }
    public void setRID(String eid) {
        this.RID.set(eid);
    }
    //////////////////////////////////////////////////////////////////////////////////
     public String getStudent() {
        return Student.get();
    }
    public void setStudent(String eid) {
        this.Student.set(eid);
    }
    //////////////////////////////////////////////////////////////////////////////////
    public String getNIC() {
        return Nic.get();
    }
    public void setNIC(String eid) {
        this.Nic.set(eid);
    }
    //////////////////////////////////////////////////////////////////////////////////
     public String getMarks() {
        return Marks.get();
    }
    public void setMarks(String eid) {
        this.Marks.set(eid);
    }
    ///////////////////////////////////////////////////////////////////////////////////
}
