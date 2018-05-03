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
public class PaymentDto extends SuperDto{
    private String pid;
    
    private RegistrationDto registrationDto;
    private BatchDto batchDto;
    private SubjectDto subjectDto;
    
    private String date;
    private double fee;
    private String month;
    

    public PaymentDto() {
    }

    public PaymentDto(String pid, RegistrationDto registrationDto, BatchDto batchDto, SubjectDto subjectDto, String date, double fee, String month) {
        this.pid = pid;
        this.registrationDto = registrationDto;
        this.batchDto = batchDto;
        this.subjectDto = subjectDto;
        this.date = date;
        this.fee = fee;
        this.month = month;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the registrationDto
     */
    public RegistrationDto getRegistrationDto() {
        return registrationDto;
    }

    /**
     * @param registrationDto the registrationDto to set
     */
    public void setRegistrationDto(RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
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
     * @return the subjectDto
     */
    public SubjectDto getSubjectDto() {
        return subjectDto;
    }

    /**
     * @param subjectDto the subjectDto to set
     */
    public void setSubjectDto(SubjectDto subjectDto) {
        this.subjectDto = subjectDto;
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

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    
    
    
    
}
