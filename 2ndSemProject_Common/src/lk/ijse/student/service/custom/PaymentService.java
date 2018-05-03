/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.custom;

import java.util.List;
import lk.ijse.student.dto.PaymentDto;
import lk.ijse.student.service.SuperService;

/**
 *
 * @author Kavindu
 */
public interface PaymentService extends SuperService{
    public boolean addPay(PaymentDto paymentDto)throws Exception;
    public boolean updatePay(PaymentDto paymentDto)throws Exception;
    public boolean removePay(String id)throws Exception;
    public PaymentDto searchPay(String id)throws Exception;
    public List<PaymentDto>getAll()throws Exception;

    public List<PaymentDto> searchPayment(String bid, String sub, String sid)throws Exception;
    public List<PaymentDto> checkCount(String bid, String sub, String month)throws Exception;
}
