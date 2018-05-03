/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.SubjectBO;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.entity.Subject;
import lk.ijse.student.entity.Teacher;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.SubjectRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 */
public class SubjectBOImpl implements SubjectBO{
    private SubjectRepository subjectRepository;
    public SubjectBOImpl() {
         this.subjectRepository=(SubjectRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.SUBJECT);
    }

    @Override
    public boolean addSub(SubjectDto subjectDto) throws Exception {
          try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            subjectRepository.setSession(session);
            
            Teacher teacher=new Teacher(
                    subjectDto.getTeacherDto().getTid(),
                    subjectDto.getTeacherDto().getName(),
                    subjectDto.getTeacherDto().getAddress(),
                    subjectDto.getTeacherDto().getTel(),
                    subjectDto.getTeacherDto().getGender(),
                    subjectDto.getTeacherDto().getNic(),
                    subjectDto.getTeacherDto().getEmail(),
                    subjectDto.getTeacherDto().getAge()
            );
            
            Subject c = new Subject(
            subjectDto.getSubId(),
            subjectDto.getName(),
            teacher,
            subjectDto.getType(),
            subjectDto.getDay()
            );
            
            boolean result = subjectRepository.save(c);
            session.getTransaction().commit();
            return result;
          }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSub(SubjectDto subjectDto) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            subjectRepository.setSession(session);
            
            Subject c = new Subject();
            
            subjectRepository.update(c);
            session.getTransaction().commit();
            return true;
          }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeSub(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            subjectRepository.setSession(session);
            session.beginTransaction();
            Subject subject = subjectRepository.findById(id);
            if (subject != null) {
                subjectRepository.delete(subject);
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
    public SubjectDto searchSubject(String id) throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            subjectRepository.setSession(session);
            session.beginTransaction();
            Subject s = subjectRepository.findById(id);
            if (s != null) {
                session.getTransaction().commit();
                
                  TeacherDto teacherDto=new TeacherDto(
                    s.getTeacher().gettId(),
                    s.getTeacher().getName(),
                    s.getTeacher().getAddress(),
                    s.getTeacher().getTel(),
                    s.getTeacher().getGender(),
                    s.getTeacher().getNic(),
                    s.getTeacher().getEmail(),
                    s.getTeacher().getAge()
            );
                
                SubjectDto c = new SubjectDto(
                    s.getSubId(),
                    s.getName(),
                    teacherDto,
                    s.getType(),
                    s.getDay()
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
    public List<SubjectDto> getAll() throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            subjectRepository.setSession(session);
            session.beginTransaction();
            List<Subject> subjects = subjectRepository.findAll();
            session.getTransaction().commit();
            if (subjects != null) {

                List<SubjectDto> alSubjectDtos = new ArrayList<>();

                for (Subject s : subjects) {
                    
                    TeacherDto teacherDto=new TeacherDto(
                    s.getTeacher().gettId(),
                    s.getTeacher().getName(),
                    s.getTeacher().getAddress(),
                    s.getTeacher().getTel(),
                    s.getTeacher().getGender(),
                    s.getTeacher().getNic(),
                    s.getTeacher().getEmail(),
                    s.getTeacher().getAge()
            );
                    
                    SubjectDto dto = new SubjectDto(
                        s.getSubId(),
                        s.getName(),
                        teacherDto,
                        s.getType(),
                        s.getDay()
                );
                    
                    alSubjectDtos.add(dto);
                }

                return alSubjectDtos;

            } else {

                return null;
            }
        }
    }

    @Override
    public List<SubjectDto> searchAll(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            subjectRepository.setSession(session);
            session.beginTransaction();
           
            
            String sql = "SELECT * FROM subject WHERE subid ='"+id+"'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Subject.class);
            
            List<Subject> results = query.list();
            
            session.getTransaction().commit();
            if (results != null) {

                List<SubjectDto> alSubjectDtos = new ArrayList<>();

                for (Subject s : results) {
                
                     TeacherDto teacherDto=new TeacherDto(
                        s.getTeacher().gettId(),
                        s.getTeacher().getName(),
                        s.getTeacher().getAddress(),
                        s.getTeacher().getTel(),
                        s.getTeacher().getGender(),
                        s.getTeacher().getNic(),
                        s.getTeacher().getEmail(),
                        s.getTeacher().getAge()
            );
                    
                    SubjectDto dto = new SubjectDto(
                        s.getSubId(),
                        s.getName(),
                        teacherDto,
                        s.getType(),
                        s.getDay()
                );
                    
                    alSubjectDtos.add(dto);
                }

                return alSubjectDtos;

            } else {
                System.out.println("nullllll");
                return null;
            }
        }
    }

    @Override
    public SubjectDto searchSubjectName(String name) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            subjectRepository.setSession(session);
            session.beginTransaction();
           
            
            String sql = "SELECT * FROM subject WHERE name ='"+name+"'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Subject.class);
            
            List<Subject> results = query.list();
            
            session.getTransaction().commit();
            if (results != null) {

                List<SubjectDto> alSubjectDtos = new ArrayList<>();

                for (Subject s : results) {
                
                     TeacherDto teacherDto=new TeacherDto(
                        s.getTeacher().gettId(),
                        s.getTeacher().getName(),
                        s.getTeacher().getAddress(),
                        s.getTeacher().getTel(),
                        s.getTeacher().getGender(),
                        s.getTeacher().getNic(),
                        s.getTeacher().getEmail(),
                        s.getTeacher().getAge()
            );
                    
                    SubjectDto dto = new SubjectDto(
                        s.getSubId(),
                        s.getName(),
                        teacherDto,
                        s.getType(),
                        s.getDay()
                );
                return dto;
                }
                
            }
            return null;
        }
    }
    
}
