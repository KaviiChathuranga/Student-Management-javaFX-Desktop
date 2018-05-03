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
public class SubjectDto extends SuperDto{
    private String subId;
    private String name;
    private TeacherDto teacherDto;
    private String type;
    private String day;

    public SubjectDto() {
    }

    public SubjectDto(String subId, String name, TeacherDto teacherDto, String type, String day) {
        this.subId = subId;
        this.name = name;
        this.teacherDto = teacherDto;
        this.type = type;
        this.day = day;
    }
    

    /**
     * @return the subId
     */
    public String getSubId() {
        return subId;
    }

    /**
     * @param subId the subId to set
     */
    public void setSubId(String subId) {
        this.subId = subId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the batchDto
     */
    public TeacherDto getTeacherDto() {
        return teacherDto;
    }

    /**
     * @param teacherDto  the teacherDto to set
     */
    public void setTeacherDto(TeacherDto teacherDto) {
        this.teacherDto = teacherDto;
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
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }
    
    
    
}
