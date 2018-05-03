/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Kavindu
 */
@Embeddable
public class Subject_pk implements Serializable{
    private String subID;
    private String bID;

    public Subject_pk() {
    }

    public Subject_pk(String subID, String bID) {
        this.subID = subID;
        this.bID = bID;
    }
    
}
