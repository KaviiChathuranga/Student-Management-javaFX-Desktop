/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.ExamBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.ExamDto;
import lk.ijse.student.dto.LoginDto;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.entity.Batch;
import lk.ijse.student.entity.Exam;
import lk.ijse.student.entity.Login;
import lk.ijse.student.entity.Subject;
import lk.ijse.student.entity.Teacher;

import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.ExamRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.Session;


/**
 *
 * @author Kavindu
 */
public class ExamBOImpl implements ExamBO{
    
    private ExamRepository examRepository;
    
    public ExamBOImpl() {
       this.examRepository=(ExamRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.EXAM);
    }

    
    @Override
    public boolean addExam(ExamDto examDto) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            examRepository.setSession(session);
            
             Batch batch=new Batch(
                        examDto.getBatchDto().getBid(),
                        examDto.getBatchDto().getName(),
                        examDto.getBatchDto().getLimit(),
                        examDto.getBatchDto().getYear(),
                        examDto.getBatchDto().getFee()
             );
             
              Teacher teacher =new Teacher(
                         
                         examDto.getSubjectDto().getTeacherDto().getTid(),
                         examDto.getSubjectDto().getTeacherDto().getName(),
                         examDto.getSubjectDto().getTeacherDto().getAddress(),
                         examDto.getSubjectDto().getTeacherDto().getTel(),
                         examDto.getSubjectDto().getTeacherDto().getGender(),
                         examDto.getSubjectDto().getTeacherDto().getNic(),
                         examDto.getSubjectDto().getTeacherDto().getEmail(),
                         examDto.getSubjectDto().getTeacherDto().getAge()
                         
                 );
              
             Subject subject=new Subject(
                         examDto.getSubjectDto().getSubId(),
                         examDto.getSubjectDto().getName(),
                         teacher,
                         examDto.getSubjectDto().getType(),
                         examDto.getSubjectDto().getDay()
                 );
             
            Exam c = new Exam(
                        examDto.getEid(),
                        batch,
                        subject,
                        examDto.getHall(),
                        examDto.getType(),
                        examDto.getDate(),
                        examDto.getTime()                    
            );
            
            boolean result = examRepository.save(c);
            session.getTransaction().commit();
            return result;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateExam(ExamDto examDto) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            examRepository.setSession(session);
            
            Exam c = new Exam();
            
            examRepository.update(c);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeExam(String id) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            examRepository.setSession(session);
            session.beginTransaction();
            Exam exam = examRepository.findById(id);
            if (exam != null) {
                examRepository.delete(exam);
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
    public ExamDto searchExam(String id) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            examRepository.setSession(session);
            session.beginTransaction();
            Exam exam = examRepository.findById(id);
            if (exam != null) {
                session.getTransaction().commit();
                
                 BatchDto batch=new BatchDto(
                        exam.getBatch().getBid(),
                        exam.getBatch().getName(),
                        exam.getBatch().getsLimit(),
                        exam.getBatch().getsYear(),
                        exam.getBatch().getFee()
             );
             
                    TeacherDto teacher =new TeacherDto(
                         
                         exam.getSubject().getTeacher().gettId(),
                         exam.getSubject().getTeacher().getName(),
                         exam.getSubject().getTeacher().getAddress(),
                         exam.getSubject().getTeacher().getTel(),
                         exam.getSubject().getTeacher().getGender(),
                         exam.getSubject().getTeacher().getNic(),
                         exam.getSubject().getTeacher().getEmail(),
                         exam.getSubject().getTeacher().getAge()
                         
                 );
              
                    SubjectDto subject=new SubjectDto(
                         exam.getSubject().getSubId(),
                         exam.getSubject().getName(),
                         teacher,
                         exam.getSubject().getType(),
                         exam.getSubject().getDay()
                 );
                    
                    ExamDto c = new ExamDto(
                            exam.getEid(),
                            batch,
                            subject,
                            exam.getHall(),
                            exam.getType(),
                            exam.getDate(),
                            exam.getTime()
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
    public List<ExamDto> getAll() throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            examRepository.setSession(session);
            session.beginTransaction();
            List<Exam> exams = examRepository.findAll();
            session.getTransaction().commit();
            if (exams != null) {

                List<ExamDto> allList = new ArrayList<>();

                for (Exam exam : exams) {
                    
                    BatchDto batch=new BatchDto(
                        exam.getBatch().getBid(),
                        exam.getBatch().getName(),
                        exam.getBatch().getsLimit(),
                        exam.getBatch().getsYear(),
                        exam.getBatch().getFee()
             );
             
                    TeacherDto teacher =new TeacherDto(
                         
                         exam.getSubject().getTeacher().gettId(),
                         exam.getSubject().getTeacher().getName(),
                         exam.getSubject().getTeacher().getAddress(),
                         exam.getSubject().getTeacher().getTel(),
                         exam.getSubject().getTeacher().getGender(),
                         exam.getSubject().getTeacher().getNic(),
                         exam.getSubject().getTeacher().getEmail(),
                         exam.getSubject().getTeacher().getAge()
                         
                 );
              
                    SubjectDto subject=new SubjectDto(
                         exam.getSubject().getSubId(),
                         exam.getSubject().getName(),
                         teacher,
                         exam.getSubject().getType(),
                         exam.getSubject().getDay()
                 );
                    
                    ExamDto dto = new ExamDto(
                            exam.getEid(),
                            batch,
                            subject,
                            exam.getHall(),
                            exam.getType(),
                            exam.getDate(),
                            exam.getTime()
                    );
                    
                    allList.add(dto);
                }

                return allList;

            } else {

                return null;
            }
        }
    }
    
}
