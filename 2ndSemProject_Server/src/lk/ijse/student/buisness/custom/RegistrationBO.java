/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.RegistrationDto;


/**
 *
 * @author Kavindu
 */
public interface RegistrationBO extends SuperBO{
    public boolean addRegistration(RegistrationDto registrationDto)throws Exception;
    public boolean updateRegistration(RegistrationDto registrationDto)throws Exception;
    public boolean removeRegistration(String id)throws Exception;
    public RegistrationDto searchRegistration(String id)throws Exception;
    public List<RegistrationDto>getAll()throws Exception;
    
    public List<RegistrationDto>getBatchStudent(String id)throws Exception;
}
