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
import lk.ijse.student.buisness.custom.LoginBO;
import lk.ijse.student.dto.LoginDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.LoginService;

/**
 *
 * @author Kavindu
 */
public class LoginServiceImpl extends UnicastRemoteObject implements LoginService,SubjectOb{
    
    private LoginBO loginBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<LoginService> loginBook=new ReservstionImpl<>();
    
     public LoginServiceImpl()throws RemoteException{
        loginBO=(LoginBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.LOGIN);
     }
     
    @Override
    public boolean addLogin(LoginDto loginDto) throws Exception {
         boolean result = loginBO.addLogin(loginDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateLogin(LoginDto loginDto) throws Exception {
       boolean result = false;
        if (loginBook.reserve(loginDto.getPassword(), this, true)) {
            result = loginBO.updateLogin(loginDto);
            notifyAllObservers();
            if (loginBook.isReservedInternally(loginDto.getPassword())){
                loginBook.release(loginDto.getPassword());
            }
        }
        return result;
    }

    @Override
    public boolean removeLogin(String id) throws Exception {
       boolean result = false;
        if (loginBook.reserve(id, this, true)) {
            result = loginBO.removeLogin(id);
            notifyAllObservers();
            if (loginBook.isReservedInternally(id)){
                loginBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public LoginDto searchLogin(String id) throws Exception {
       return loginBO.searchLogin(id);
    }

    @Override
    public List<LoginDto> getAll() throws Exception {
        return loginBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
        return loginBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return loginBook.release(id);
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
                    Logger.getLogger(LoginServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
}
