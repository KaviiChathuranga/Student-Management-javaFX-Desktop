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
public class Login {
    @Id
    private String password;
    
    private String name;
    
    private String type;

    
    public Login() {
    }

    public Login(String password,String name, String type) {
        
        this.name = name;
        this.type = type;
        this.password=password;
    }

    /**
     * @return the id
     */

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getPassword(){
        return password;
    }
    
    @Override
    public String toString() {
        return "Login{ name=" + name + ", type=" + type + '}';
    }
    
    
    
}
