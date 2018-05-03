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
public class AttendanceDto extends SuperDto{
    private String aid;
    
    private RegistrationDto registrationDto;
    private BatchDto batchDto;
    private SubjectDto subjectDto;
    
    private String date;
    private String time;
        

    public AttendanceDto() {
    }

    public AttendanceDto(String aid, RegistrationDto registrationDto, BatchDto batchDto, SubjectDto subjectDto, String date, String time) {
        this.aid = aid;
        this.registrationDto = registrationDto;
        this.batchDto = batchDto;
        this.subjectDto = subjectDto;
        this.date = date;
        this.time = time;
    }
    

    /**
     * @return the aid
     */
    public String getAid() {
        return aid;
    }

    /**
     * @param aid the aid to set
     */
    public void setAid(String aid) {
        this.aid = aid;
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
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
    
    
}
