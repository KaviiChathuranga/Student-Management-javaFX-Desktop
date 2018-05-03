/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.service.custom;

import java.util.List;
import lk.ijse.student.dto.ResultDto;
import lk.ijse.student.service.SuperService;

/**
 *
 * @author Kavindu
 */
public interface ResultService extends SuperService{
    public boolean addResult(ResultDto resultDto)throws Exception;
    public boolean updateResult(ResultDto resultDto)throws Exception;
    public boolean removeResult(String id)throws Exception;
    public ResultDto searchResult(String id)throws Exception;
    public List<ResultDto>getAll()throws Exception;

    public ResultDto searchResult(String text, String sid)throws Exception;
}
