/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.reservation;

import java.rmi.Remote;

/**
 *
 * @author Kavindu
 */
public interface Reservation extends Remote{
    public boolean reserve(Object id) throws Exception;
    public boolean release(Object id) throws Exception;
}
