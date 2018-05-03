/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.TeacherBO;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.entity.Student;
import lk.ijse.student.entity.Subject;
import lk.ijse.student.entity.Teacher;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.TeacherRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 */
public class TeacherBOImpl implements TeacherBO{
    private TeacherRepository teacherRepository;
    public TeacherBOImpl() {
         this.teacherRepository=(TeacherRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.TEACHER);
    }

    @Override
    public boolean addTeacher(TeacherDto teacherDto) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            teacherRepository.setSession(session);
            
            Teacher teacher=new Teacher(
                    teacherDto.getTid(),
                    teacherDto.getName(),
                    teacherDto.getAddress(),
                    teacherDto.getTel(),
                    teacherDto.getGender(),
                    teacherDto.getNic(),
                    teacherDto.getEmail(),
                    teacherDto.getAge()
            );
            
            boolean result = teacherRepository.save(teacher);
            session.getTransaction().commit();
            return result;
          }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTeacher(TeacherDto teacherDto) throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            
            Teacher teacher=new Teacher(
                    teacherDto.getTid(),
                    teacherDto.getName(),
                    teacherDto.getAddress(),
                    teacherDto.getTel(),
                    teacherDto.getGender(),
                    teacherDto.getNic(),
                    teacherDto.getEmail(),
                    teacherDto.getAge()
            );
            
            teacherRepository.update(teacher);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeTeacher(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            Teacher teacher = teacherRepository.findById(id);
            if (teacher != null) {
                teacherRepository.delete(teacher);
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
    public TeacherDto searchTeahcer(String id) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            Teacher s = teacherRepository.findById(id);
            if (s != null) {
                session.getTransaction().commit();
                
                  TeacherDto teacherDto=new TeacherDto(
                    s.gettId(),
                    s.getName(),
                    s.getAddress(),
                    s.getTel(),
                    s.getGender(),
                    s.getNic(),
                    s.getEmail(),
                    s.getAge()
            );
                
                return teacherDto;
            }else{
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TeacherDto> getAll() throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            teacherRepository.setSession(session);
            session.beginTransaction();
            List<Teacher> teachers = teacherRepository.findAll();
            session.getTransaction().commit();
            if (teachers != null) {

                List<TeacherDto> allList = new ArrayList<>();

                for (Teacher s : teachers) {
                    
                    TeacherDto teacherDto=new TeacherDto(
                    s.gettId(),
                    s.getName(),
                    s.getAddress(),
                    s.getTel(),
                    s.getGender(),
                    s.getNic(),
                    s.getEmail(),
                    s.getAge()
            );
                    
                    allList.add(teacherDto);
                }

                return allList;

            } else {

                return null;
            }
        }
    }
    
}
