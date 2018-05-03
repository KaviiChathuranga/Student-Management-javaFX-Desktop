/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.custom.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.student.buisness.BuisnessFactory;
import lk.ijse.student.buisness.custom.RegistrationBO;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.RegistrationService;

/**
 *
 * @author Kavindu
 */
public class RegistrationServiceImpl extends UnicastRemoteObject implements RegistrationService,SubjectOb{
    
    private RegistrationBO registrationBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<RegistrationService> regBook=new ReservstionImpl<>();
    
     public RegistrationServiceImpl()throws RemoteException{
        registrationBO=(RegistrationBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.REGISTRATION);
    }
    
    @Override
    public boolean addRegistration(RegistrationDto registrationDto) throws Exception {
        boolean result = registrationBO.addRegistration(registrationDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateRegistration(RegistrationDto registrationDto) throws Exception {
               boolean result = false;
        if (regBook.reserve(registrationDto.getRegId(), this, true)) {
            result = registrationBO.updateRegistration(registrationDto);
            notifyAllObservers();
            if (regBook.isReservedInternally(registrationDto.getRegId())){
                regBook.release(registrationDto.getRegId());
            }
        }
        return result;
    }

    @Override
    public boolean removeRegistration(String id) throws Exception {
         boolean result = false;
        if (regBook.reserve(id, this, true)) {
            result = registrationBO.removeRegistration(id);
            notifyAllObservers();
            if (regBook.isReservedInternally(id)){
                regBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public RegistrationDto searchRegistration(String id) throws Exception {
        return registrationBO.searchRegistration(id);
    }

    @Override
    public List<RegistrationDto> getAll() throws Exception {
        return registrationBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
       return regBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return regBook.release(id);
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        alObserver.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) throws Exception {
       alObserver.remove(observer);
    }

    @Override
    public void notifyAllObservers() throws Exception {
        new Thread(() -> {
            for (Observer observer : alObserver) {
                try {
                    observer.update();
                } catch (Exception ex) {
                    Logger.getLogger(RegistrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    @Override
    public List<RegistrationDto> getBatchStudent(String id) throws Exception {
        return registrationBO.getBatchStudent(id); 
    }
    
}
