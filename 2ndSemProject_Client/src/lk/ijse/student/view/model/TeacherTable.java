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
public class TeacherTable {
    private SimpleStringProperty Tid=new SimpleStringProperty("");
    private SimpleStringProperty Teacher=new SimpleStringProperty("");
    private SimpleStringProperty Tel=new SimpleStringProperty("");
    private SimpleStringProperty NIC=new SimpleStringProperty("");
    private SimpleStringProperty Address=new SimpleStringProperty("");
    private SimpleStringProperty SubID=new SimpleStringProperty("");
    private SimpleStringProperty Subject=new SimpleStringProperty("");

    public TeacherTable() {
    }
    
    
    public String getTId() {
        return Tid.get();
    }
    public void setTId(String tid) {
        this.Tid.set(tid);
    }
    ///////////////////////////////////////////////////////////////////////////
        public String getTeacher() {
        return Teacher.get();
    }
    public void setTeacher(String tid) {
        this.Teacher.set(tid);
    }
    ////////////////////////////////////////////////////////////////////////////
         public String getTel() {
        return Tel.get();
    }
    public void setTel(String tid) {
        this.Tel.set(tid);
    }
    ////////////////////////////////////////////////////////////////////////////
         public String getNic() {
        return NIC.get();
    }
    public void setNic(String tid) {
        this.NIC.set(tid);
    }
    /////////////////////////////////////////////////////////////////////////
         public String getAddress() {
        return Address.get();
    }
    public void setAddress(String tid) {
        this.Address.set(tid);
    }
    //////////////////////////////////////////////////////////////////////////////
         public String getSubID() {
        return SubID.get();
    }
    public void setSubId(String tid) {
        this.SubID.set(tid);
    }
    ////////////////////////////////////////////////////////////////////////////
            public String getSubject() {
        return Subject.get();
    }
    public void setSubject(String tid) {
        this.Subject.set(tid);
    }
}
