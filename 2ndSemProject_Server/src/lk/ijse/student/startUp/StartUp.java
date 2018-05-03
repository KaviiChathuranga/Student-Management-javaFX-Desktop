/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.startUp;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.student.service.impl.ServiceFactoryImpl;

/**
 *
 * @author Kavindu
 */
public class StartUp {
    public static void main(String[] args) {
            Registry registry;
        try {
            registry = LocateRegistry.createRegistry(5050);
            registry.rebind("student", ServiceFactoryImpl.getInstance());
            System.out.println("Server has been started ");
        } catch (RemoteException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
