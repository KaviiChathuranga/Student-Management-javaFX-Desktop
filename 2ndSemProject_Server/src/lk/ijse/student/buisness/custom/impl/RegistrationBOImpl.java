/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.RegistrationBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.dto.ResultDto;
import lk.ijse.student.dto.StudentDto;
import lk.ijse.student.entity.Batch;
import lk.ijse.student.entity.Payment;
import lk.ijse.student.entity.Registration;
import lk.ijse.student.entity.Result;
import lk.ijse.student.entity.Student;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.RegistrationRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


/**
 *
 * @author Kavindu
 */
public class RegistrationBOImpl implements RegistrationBO{
    private RegistrationRepository registrationRepository;
    public RegistrationBOImpl() {
         this.registrationRepository=(RegistrationRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.REGISTRATION);
    }

    @Override
    public boolean addRegistration(RegistrationDto registrationDto) throws Exception {
             try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            registrationRepository.setSession(session);
            
            Batch batch=new Batch(
                    registrationDto.getBatchDto().getBid(),
                    registrationDto.getBatchDto().getName(),
                    registrationDto.getBatchDto().getLimit(),
                    registrationDto.getBatchDto().getYear(),
                    registrationDto.getBatchDto().getFee()
            );
            
            Student student = new Student(
                    registrationDto.getStudentDto().getSid(),
                    registrationDto.getStudentDto().getName(),
                    registrationDto.getStudentDto().getAddress(),
                    registrationDto.getStudentDto().getAge(),
                    registrationDto.getStudentDto().getDob(),
                    registrationDto.getStudentDto().getNic(),
                    registrationDto.getStudentDto().getTel(),
                    registrationDto.getStudentDto().getEmail()
            );
            
            Registration c = new Registration(
                    registrationDto.getRegId(),
                    batch,
                    student,
                    registrationDto.getDate(),
                    registrationDto.getFee()
            );
            
            boolean result = registrationRepository.save(c);
            session.getTransaction().commit();
            return result;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRegistration(RegistrationDto registrationDto) throws Exception {
              try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            registrationRepository.setSession(session);
            
            Registration c = new Registration();
            
            registrationRepository.update(c);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeRegistration(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            registrationRepository.setSession(session);
            session.beginTransaction();
            Registration registration = registrationRepository.findById(id);
            if (registration != null) {
                registrationRepository.delete(registration);
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
    public RegistrationDto searchRegistration(String id) throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            registrationRepository.setSession(session);
            session.beginTransaction();
            Registration t = registrationRepository.findById(id);
            if (t != null) {
               
                session.getTransaction().commit();
                
                BatchDto batch=new BatchDto(t.getBatch().getBid(),t.getBatch().getName(),t.getBatch().getsLimit(),t.getBatch().getsYear(),t.getBatch().getFee());
                StudentDto student=new StudentDto(t.getStudent().getSid(),t.getStudent().getName(),t.getStudent().getAddress(),t.getStudent().getAge(),t.getStudent().getDob(),t.getStudent().getNic(),t.getStudent().getTel(),t.getStudent().getEmail());
               
                RegistrationDto c = new RegistrationDto(t.getRegId(),batch,student,t.getDate(),t.getFee());
                
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
    public List<RegistrationDto> getAll() throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            registrationRepository.setSession(session);
            session.beginTransaction();
            List<Registration> registrations = registrationRepository.findAll();
            session.getTransaction().commit();
            if (registrations != null) {

                List<RegistrationDto> allList = new ArrayList<>();

                for (Registration registration : registrations) {
                    
                     BatchDto batchDto=new BatchDto(
                    registration.getBatch().getBid(),
                    registration.getBatch().getName(),
                    registration.getBatch().getsLimit(),
                    registration.getBatch().getsYear(),
                    registration.getBatch().getFee()
            );
            
                    StudentDto studentDto = new StudentDto(
                    registration.getStudent().getSid(),
                    registration.getStudent().getName(),
                    registration.getStudent().getAddress(),
                    registration.getStudent().getAge(),
                    registration.getStudent().getDob(),
                    registration.getStudent().getNic(),
                    registration.getStudent().getTel(),
                    registration.getStudent().getEmail()
            );
            
            RegistrationDto c = new RegistrationDto(
                    registration.getRegId(),
                    batchDto,
                    studentDto,
                    registration.getDate(),
                    registration.getFee()
                    
            );

                    allList.add(c);
                }

                return allList;

            } else {

                return null;
            }
        }
    }

    @Override
    public List<RegistrationDto> getBatchStudent(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            registrationRepository.setSession(session);
            session.beginTransaction();
            
            String sql = "SELECT * FROM Registration WHERE batch_id ='"+id+"'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Registration.class);
            
            List<Registration> registrations = query.list();
            if (registrations != null) {

                List<RegistrationDto> allList = new ArrayList<>();

                for (Registration registration : registrations) {
                    
                     BatchDto batchDto=new BatchDto(
                    registration.getBatch().getBid(),
                    registration.getBatch().getName(),
                    registration.getBatch().getsLimit(),
                    registration.getBatch().getsYear(),
                    registration.getBatch().getFee()
            );
            
                    StudentDto studentDto = new StudentDto(
                    registration.getStudent().getSid(),
                    registration.getStudent().getName(),
                    registration.getStudent().getAddress(),
                    registration.getStudent().getAge(),
                    registration.getStudent().getDob(),
                    registration.getStudent().getNic(),
                    registration.getStudent().getTel(),
                    registration.getStudent().getEmail()
            );
            
            RegistrationDto c = new RegistrationDto(
                    registration.getRegId(),
                    batchDto,
                    studentDto,
                    registration.getDate(),
                    registration.getFee()
                    
            );

                    allList.add(c);
                }

                return allList;

            } else {

                return null;
            }
        }
    }
    
}
