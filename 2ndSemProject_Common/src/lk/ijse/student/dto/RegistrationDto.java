/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.dto;

/**
 *
 * @author Kavindu
 */
public class RegistrationDto extends SuperDto{
    private String regId;
    
    private BatchDto batchDto;
    
    private StudentDto studentDto;
    
    private String date;
    private double fee;

    public RegistrationDto() {
    }

    public RegistrationDto(String regId, BatchDto batchDto, StudentDto studentDto, String date, double fee) {
        this.regId = regId;
        this.batchDto = batchDto;
        this.studentDto = studentDto;
        this.date = date;
        this.fee = fee;
    }
    

    /**
     * @return the regId
     */
    public String getRegId() {
        return regId;
    }

    /**
     * @param regId the regId to set
     */
    public void setRegId(String regId) {
        this.regId = regId;
    }

    /**
     * @return the batchDto
     */
    public BatchDto getBatchDto() {
        return batchDto;
    }

    /**
     * @param batchDto the batchDto to set
     */
    public void setBatchDto(BatchDto batchDto) {
        this.batchDto = batchDto;
    }

    /**
     * @return the studentDto
     */
    public StudentDto getStudentDto() {
        return studentDto;
    }

    /**
     * @param studentDto the studentDto to set
     */
    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the fee
     */
    public double getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(double fee) {
        this.fee = fee;
    }
    
    
}
