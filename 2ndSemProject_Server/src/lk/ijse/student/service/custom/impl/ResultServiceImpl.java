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
import lk.ijse.student.buisness.custom.ResultBO;
import lk.ijse.student.dto.ResultDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.ResultService;

/**
 *
 * @author Kavindu
 */
public class ResultServiceImpl extends UnicastRemoteObject implements ResultService,SubjectOb{
    
    private ResultBO resultBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<ResultService> resultBook=new ReservstionImpl<>();
    
     public ResultServiceImpl()throws RemoteException{
        resultBO=(ResultBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.RESULT);
    }
    
    @Override
    public boolean addResult(ResultDto resultDto) throws Exception {
          boolean result = resultBO.addResult(resultDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updateResult(ResultDto resultDto) throws Exception {
                      boolean result = false;
        if (resultBook.reserve(resultDto.getResId(), this, true)) {
            result = resultBO.updateResult(resultDto);
            notifyAllObservers();
            if (resultBook.isReservedInternally(resultDto.getResId())){
                resultBook.release(resultDto.getResId());
            }
        }
        return result;
    }

    @Override
    public boolean removeResult(String id) throws Exception {
               boolean result = false;
        if (resultBook.reserve(id, this, true)) {
            result = resultBO.removeResult(id);
            notifyAllObservers();
            if (resultBook.isReservedInternally(id)){
                resultBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public ResultDto searchResult(String id) throws Exception {
        return resultBO.searchResult(id);
    }

    @Override
    public List<ResultDto> getAll() throws Exception {
       return resultBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
       return resultBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
        return resultBook.release(id);
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
                    Logger.getLogger(ResultServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    @Override
    public ResultDto searchResult(String text, String sid) throws Exception {
        return resultBO.searchResult(text,sid);
    }
    
}
