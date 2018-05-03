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
public class AgentTable {
    private SimpleStringProperty sid=new SimpleStringProperty("");
    private SimpleStringProperty name=new SimpleStringProperty("");
    private SimpleStringProperty address=new SimpleStringProperty("");
    private SimpleStringProperty Batch=new SimpleStringProperty("");
    private SimpleStringProperty NIC=new SimpleStringProperty("");
    private SimpleStringProperty Tel=new SimpleStringProperty("");
    

    public AgentTable() {
    }

    /**
     * @return the rid
     */
    public String getRid() {
        return sid.get();
    }

    /**
     * @param sid the sid to set
     */
    public void setRid(String sid) {
        this.sid.set(sid);
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
     * @return the address
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address.set(address);
    }
    
    
    public void setNIC(String nic){
        this.NIC.set(nic);
    }
    public String getNIC(){
        return NIC.get();
    }
    
    
    ///////////////////////////////////////
    
    public void setTEL(String tel){
        this.Tel.set(tel);
    }
    public String getTEL(){
        return Tel.get();
    }
    
    //////////////////////////////////////
    
    public void setBatch(String batch){
        this.Batch.set(batch);
    }
    public String getBatch(){
        return Batch.get();
    }

   
    
}
