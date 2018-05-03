/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.repository.custom.impl;

import lk.ijse.student.entity.Student;
import lk.ijse.student.repository.SuperREpositoryImpl;
import lk.ijse.student.repository.custom.StudentRepository;

/**
 *
 * @author Kavindu
 */
public class StudentRepositoryImpl extends SuperREpositoryImpl<Student, String> implements StudentRepository{

    public StudentRepositoryImpl() {
        System.out.println("StudentRepository impl : " + this);
    }
    
}
