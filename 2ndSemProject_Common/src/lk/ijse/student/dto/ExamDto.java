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
public class ExamDto extends SuperDto{
    private String eid;
    
    private BatchDto batchDto;
    private SubjectDto subjectDto;
    private String hall;
    private String type;
    private String date;
    private String time;
    

    public ExamDto() {
    }

    public ExamDto(String eid, BatchDto batchDto, SubjectDto subjectDto, String hall, String type, String date, String time) {
        this.eid = eid;
        this.batchDto = batchDto;
        this.subjectDto = subjectDto;
        this.hall = hall;
        this.type = type;
        this.date = date;
        this.time = time;
    }
    

    /**
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
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
     * @return the hall
     */
    public String getHall() {
        return hall;
    }

    /**
     * @param hall the hall to set
     */
    public void setHall(String hall) {
        this.hall = hall;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
