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
import lk.ijse.student.buisness.custom.PaymentBO;
import lk.ijse.student.dto.PaymentDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.observer.SubjectOb;
import lk.ijse.student.reservation.ReservstionImpl;
import lk.ijse.student.service.custom.PaymentService;


/**
 *
 * @author Kavindu
 */
public class PaymentServiceImpl extends UnicastRemoteObject implements PaymentService,SubjectOb{
    
    private PaymentBO paymentBO;
    private static ArrayList<Observer> alObserver=new ArrayList<>();
    private static ReservstionImpl<PaymentService> paymentBook=new ReservstionImpl<>();
    
     public PaymentServiceImpl()throws RemoteException{
        paymentBO=(PaymentBO) BuisnessFactory.getInstance().getBO(BuisnessFactory.BOTypes.PAYMENT);
    }
     
    @Override
    public boolean addPay(PaymentDto paymentDto) throws Exception {
        boolean result = paymentBO.addPay(paymentDto);
        notifyAllObservers();
        return result;
    }

    @Override
    public boolean updatePay(PaymentDto paymentDto) throws Exception {
        boolean result = false;
        if (paymentBook.reserve(paymentDto.getPid(), this, true)) {
            result = paymentBO.updatePay(paymentDto);
            notifyAllObservers();
            if (paymentBook.isReservedInternally(paymentDto.getPid())){
                paymentBook.release(paymentDto.getPid());
            }
        }
        return result;
    }

    @Override
    public boolean removePay(String id) throws Exception {
        boolean result = false;
        if (paymentBook.reserve(id, this, true)) {
            result = paymentBO.removePay(id);
            notifyAllObservers();
            if (paymentBook.isReservedInternally(id)){
                paymentBook.release(id);
            }            
        }
        return result;
    }

    @Override
    public PaymentDto searchPay(String id) throws Exception {
        return paymentBO.searchPay(id);
    }

    @Override
    public List<PaymentDto> getAll() throws Exception {
       return paymentBO.getAll();
    }

    @Override
    public boolean reserve(Object id) throws Exception {
        return paymentBook.reserve(id, this, true);
    }

    @Override
    public boolean release(Object id) throws Exception {
       return paymentBook.release(id);
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
                    Logger.getLogger(PaymentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    @Override
    public List<PaymentDto> searchPayment(String bid, String sub, String sid)throws Exception{
       return paymentBO.searchPayment(bid, sub, sid);
    }

    @Override
    public List<PaymentDto> checkCount(String bid, String sub, String month) throws Exception {
       return paymentBO.checkCount(bid, sub, month);
    }
    
}


//select * from payment where  registration_regId='2' &&   batch_id='0' &&  subject_subId='0';