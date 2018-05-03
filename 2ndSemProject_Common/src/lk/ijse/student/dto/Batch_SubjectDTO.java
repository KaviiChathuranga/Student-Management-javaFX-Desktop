/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.dto;

import java.util.List;


/**
 *
 * @author Kavindu
 */
public class Batch_SubjectDTO extends SuperDto{
    private int id;
    private BatchDto batchDto;
    private SubjectDto subject;

    public Batch_SubjectDTO() {
        
    }

    public Batch_SubjectDTO(int id, BatchDto batchDto, SubjectDto subject) {
        this.id = id;
        this.batchDto = batchDto;
        this.subject = subject;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the subject
     */
    public SubjectDto getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

   
}
