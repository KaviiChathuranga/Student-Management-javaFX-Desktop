/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.StudentBO;
import lk.ijse.student.dto.StudentDto;
import lk.ijse.student.entity.Student;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.StudentRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 */
public class StudentBOImpl implements StudentBO{

    private StudentRepository studentRepository;

    public StudentBOImpl() {
        this.studentRepository=(StudentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.STUDENT);
    }
    
    @Override
    public boolean addStudent(StudentDto studentDto) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            studentRepository.setSession(session);
            
            Student c = new Student(
                    studentDto.getSid(),
                    studentDto.getName(),
                    studentDto.getAddress(),
                    studentDto.getAge(),
                    studentDto.getDob(),
                    studentDto.getNic(),
                    studentDto.getTel(),
                    studentDto.getEmail()
            );
            
            boolean result = studentRepository.save(c);
            session.getTransaction().commit();
            return result;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            session.beginTransaction();
            
            Student c = new Student(
            studentDto.getSid(),
                    studentDto.getName(),
                    studentDto.getAddress(),
                    studentDto.getAge(),
                    studentDto.getDob(),
                    studentDto.getNic(),
                    studentDto.getTel(),
                    studentDto.getEmail()
            );
            
            studentRepository.update(c);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeStudent(String id) throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            session.beginTransaction();
            Student student = studentRepository.findById(id);
            if (student != null) {
                studentRepository.delete(student);
                session.getTransaction().commit();
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public StudentDto searchStudent(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            session.beginTransaction();
            Student student = studentRepository.findById(id);
            if (student != null) {
                session.getTransaction().commit();
                
                StudentDto c = new StudentDto(
                    student.getSid(),
                    student.getName(),
                    student.getAddress(),
                    student.getAge(),
                    student.getDob(),
                    student.getNic(),
                    student.getTel(),
                    student.getEmail()
                );
                
                return c;
            }else{
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StudentDto> getAll() throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            studentRepository.setSession(session);
            session.beginTransaction();
            List<Student> students = studentRepository.findAll();
            session.getTransaction().commit();
            if (students != null) {

                List<StudentDto> alStudent = new ArrayList<>();

                for (Student student : students) {
                    
                    StudentDto dto = new StudentDto(
                    student.getSid(),
                    student.getName(),
                    student.getAddress(),
                    student.getAge(),
                    student.getDob(),
                    student.getNic(),
                    student.getTel(),
                    student.getEmail()
                    );
                    
                    alStudent.add(dto);
                }

                return alStudent;

            } else {

                return null;
            }
        }
    }
    
}
