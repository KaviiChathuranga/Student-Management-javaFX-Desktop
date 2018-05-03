/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.custom;

import java.util.List;
import lk.ijse.student.dto.LoginDto;
import lk.ijse.student.service.SuperService;

/**
 *
 * @author Kavindu
 */
public interface LoginService extends SuperService{
    public boolean addLogin(LoginDto loginDto)throws Exception;
    public boolean updateLogin(LoginDto loginDto)throws Exception;
    public boolean removeLogin(String id)throws Exception;
    public LoginDto searchLogin(String id)throws Exception;
    public List<LoginDto>getAll()throws Exception;
}
