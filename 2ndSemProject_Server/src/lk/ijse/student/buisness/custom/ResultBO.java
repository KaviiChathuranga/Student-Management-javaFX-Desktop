/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom;

import java.util.List;
import lk.ijse.student.buisness.SuperBO;
import lk.ijse.student.dto.ResultDto;


/**
 *
 * @author Kavindu
 */
public interface ResultBO extends SuperBO{
    public boolean addResult(ResultDto resultDto)throws Exception;
    public boolean updateResult(ResultDto resultDto)throws Exception;
    public boolean removeResult(String id)throws Exception;
    public ResultDto searchResult(String id)throws Exception;
    public List<ResultDto>getAll()throws Exception;

    public ResultDto searchResult(String text, String sid)throws Exception;
}
