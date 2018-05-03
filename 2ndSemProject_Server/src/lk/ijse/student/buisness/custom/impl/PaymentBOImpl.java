/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.PaymentBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.PaymentDto;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.dto.StudentDto;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.entity.Batch;
import lk.ijse.student.entity.Payment;
import lk.ijse.student.entity.Registration;
import lk.ijse.student.entity.Student;
import lk.ijse.student.entity.Subject;
import lk.ijse.student.entity.Teacher;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.PaymentRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


/**
 *
 * @author Kavindu
 */
public class PaymentBOImpl implements PaymentBO{
    private PaymentRepository paymentRepository;
    public PaymentBOImpl() {
         this.paymentRepository=(PaymentRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.PAYMENT);
    }

    @Override
    public boolean addPay(PaymentDto paymentDto) throws Exception {
          try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            paymentRepository.setSession(session);
              //System.out.println("cvbnm,kjhgffg  "+paymentDto.getRegistrationDto().getBatchDto().getBid());
             Batch batch=new Batch(
                paymentDto.getRegistrationDto().getBatchDto().getBid(),
                paymentDto.getRegistrationDto().getBatchDto().getName(),
                paymentDto.getRegistrationDto().getBatchDto().getLimit(),
                paymentDto.getRegistrationDto().getBatchDto().getYear(),
                paymentDto.getRegistrationDto().getBatchDto().getFee()
             );
             
             Student student=new Student(
                paymentDto.getRegistrationDto().getStudentDto().getSid(),
                paymentDto.getRegistrationDto().getStudentDto().getName(),
                paymentDto.getRegistrationDto().getStudentDto().getAddress(),
                paymentDto.getRegistrationDto().getStudentDto().getAge(),
                paymentDto.getRegistrationDto().getStudentDto().getDob(),
                paymentDto.getRegistrationDto().getStudentDto().getNic(),
                paymentDto.getRegistrationDto().getStudentDto().getTel(),
                paymentDto.getRegistrationDto().getStudentDto().getEmail()     
             );
             
             Registration registration=new Registration(
                paymentDto.getRegistrationDto().getRegId(),
                batch,
                student,
                paymentDto.getRegistrationDto().getDate(),
                paymentDto.getRegistrationDto().getFee()
             );
            
             Teacher teacher =new Teacher(
                         
                         paymentDto.getSubjectDto().getTeacherDto().getTid(),
                         paymentDto.getSubjectDto().getTeacherDto().getName(),
                         paymentDto.getSubjectDto().getTeacherDto().getAddress(),
                         paymentDto.getSubjectDto().getTeacherDto().getTel(),
                         paymentDto.getSubjectDto().getTeacherDto().getGender(),
                         paymentDto.getSubjectDto().getTeacherDto().getNic(),
                         paymentDto.getSubjectDto().getTeacherDto().getEmail(),
                         paymentDto.getSubjectDto().getTeacherDto().getAge()
                         
                 );
             
             Subject subject=new Subject(
                     paymentDto.getSubjectDto().getSubId(),
                     paymentDto.getSubjectDto().getName(),
                     teacher,
                     paymentDto.getSubjectDto().getType(),
                     paymentDto.getSubjectDto().getDay()
             );
            
            Payment c = new Payment(
                    paymentDto.getPid(),
                    registration,
                    batch,
                    subject,
                    paymentDto.getDate(),
                    paymentDto.getFee(),
                    paymentDto.getMonth()
            );
            
            boolean result = paymentRepository.save(c);
            session.getTransaction().commit();
            return result;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePay(PaymentDto paymentDto) throws Exception {
                 try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            paymentRepository.setSession(session);
            
            Payment c = new Payment();
            
             paymentRepository.update(c);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removePay(String id) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            paymentRepository.setSession(session);
            session.beginTransaction();
            Payment payment = paymentRepository.findById(id);
            if (payment != null) {
                paymentRepository.delete(payment);
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
    public PaymentDto searchPay(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            paymentRepository.setSession(session);
            session.beginTransaction();
            Payment t = paymentRepository.findById(id);
            if (t != null) {
                session.getTransaction().commit();
                
                PaymentDto c = new PaymentDto();
                
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
    public List<PaymentDto> getAll() throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            paymentRepository.setSession(session);
            session.beginTransaction();
            List<Payment> payments = paymentRepository.findAll();
            session.getTransaction().commit();
            if (payments != null) {
                
                List<PaymentDto> allList = new ArrayList<>();

                for (Payment payment : payments) {
                    
                    
                  BatchDto batchDto=new BatchDto(
                payment.getRegistration().getBatch().getBid(),
                payment.getRegistration().getBatch().getName(),
                payment.getRegistration().getBatch().getsLimit(),
                payment.getRegistration().getBatch().getsYear(),
                payment.getRegistration().getBatch().getFee()
             );
             
                    StudentDto studentDto=new StudentDto(
                payment.getRegistration().getStudent().getSid(),
                payment.getRegistration().getStudent().getName(),
                payment.getRegistration().getStudent().getAddress(),
                payment.getRegistration().getStudent().getAge(),
                payment.getRegistration().getStudent().getDob(),
                payment.getRegistration().getStudent().getNic(),
                payment.getRegistration().getStudent().getTel(),
                payment.getRegistration().getStudent().getEmail()     
             );
             
                    RegistrationDto registrationDto=new RegistrationDto(
                payment.getRegistration().getRegId(),
                batchDto,
                studentDto,
                payment.getRegistration().getDate(),
                payment.getRegistration().getFee()
             );
            
                    TeacherDto teacherDto =new TeacherDto(
                         
                         payment.getSubject().getTeacher().gettId(),
                         payment.getSubject().getTeacher().getName(),
                         payment.getSubject().getTeacher().getAddress(),
                         payment.getSubject().getTeacher().getTel(),
                         payment.getSubject().getTeacher().getGender(),
                         payment.getSubject().getTeacher().getNic(),
                         payment.getSubject().getTeacher().getEmail(),
                         payment.getSubject().getTeacher().getAge()
                         
                 );
             
                    SubjectDto subjectDto=new SubjectDto(
                     payment.getSubject().getSubId(),
                     payment.getSubject().getName(),
                     teacherDto,
                     payment.getSubject().getType(),
                     payment.getSubject().getDay()
             );
            
            PaymentDto c = new PaymentDto(
                    payment.getPid(),
                    registrationDto,
                    batchDto,
                    subjectDto,
                    payment.getDate(),
                    payment.getFee(),
                    payment.getMonth()
            );
                    allList.add(c);
                }

                return allList;

            } else {
                    System.out.println("getAll BOIMPL");
                return null;
            }
        }
    }

    @Override
    public List<PaymentDto> searchPayment(String bid, String sub, String sid) throws Exception {
       
        
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            paymentRepository.setSession(session);
            session.beginTransaction();
            
            String sql = "select * from payment where  registration_regId='"+sid+"' && batch_id='"+bid+"' &&  subject_subId='"+sub+"' ";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Payment.class);
            
            List<Payment> payments = query.list();
            session.getTransaction().commit();
            List<PaymentDto> allList = new ArrayList<>();
            if (payments != null) {
                

                for (Payment payment : payments) {
                    
                    
                  BatchDto batchDto=new BatchDto(
                payment.getRegistration().getBatch().getBid(),
                payment.getRegistration().getBatch().getName(),
                payment.getRegistration().getBatch().getsLimit(),
                payment.getRegistration().getBatch().getsYear(),
                payment.getRegistration().getBatch().getFee()
             );
             
                    StudentDto studentDto=new StudentDto(
                payment.getRegistration().getStudent().getSid(),
                payment.getRegistration().getStudent().getName(),
                payment.getRegistration().getStudent().getAddress(),
                payment.getRegistration().getStudent().getAge(),
                payment.getRegistration().getStudent().getDob(),
                payment.getRegistration().getStudent().getNic(),
                payment.getRegistration().getStudent().getTel(),
                payment.getRegistration().getStudent().getEmail()     
             );
             
                    RegistrationDto registrationDto=new RegistrationDto(
                payment.getRegistration().getRegId(),
                batchDto,
                studentDto,
                payment.getRegistration().getDate(),
                payment.getRegistration().getFee()
             );
            
                    TeacherDto teacherDto =new TeacherDto(
                         
                         payment.getSubject().getTeacher().gettId(),
                         payment.getSubject().getTeacher().getName(),
                         payment.getSubject().getTeacher().getAddress(),
                         payment.getSubject().getTeacher().getTel(),
                         payment.getSubject().getTeacher().getGender(),
                         payment.getSubject().getTeacher().getNic(),
                         payment.getSubject().getTeacher().getEmail(),
                         payment.getSubject().getTeacher().getAge()
                         
                 );
             
                    SubjectDto subjectDto=new SubjectDto(
                     payment.getSubject().getSubId(),
                     payment.getSubject().getName(),
                     teacherDto,
                     payment.getSubject().getType(),
                     payment.getSubject().getDay()
             );
            
            PaymentDto c = new PaymentDto(
                    payment.getPid(),
                    registrationDto,
                    batchDto,
                    subjectDto,
                    payment.getDate(),
                    payment.getFee(),
                    payment.getMonth()
            );
                    System.out.println("payment "+c.getMonth());
                    allList.add(c);
                    
                }
                
                

                return allList;

            } else {
         System.out.println("search payment BOIMPL");
                return null;
            }
        }
    }

    @Override
    public List<PaymentDto> checkCount(String bid, String sub, String month) throws Exception {
        
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            paymentRepository.setSession(session);
            session.beginTransaction();
            
            String sql = "select * from payment where  month='"+month+"' && batch_id='"+bid+"' &&  subject_subId='"+sub+"' ";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Payment.class);
            
            List<Payment> payments = query.list();
            session.getTransaction().commit();
            List<PaymentDto> allList = new ArrayList<>();
            if (payments != null) {
                

                for (Payment payment : payments) {
                    
                    
                  BatchDto batchDto=new BatchDto(
                payment.getRegistration().getBatch().getBid(),
                payment.getRegistration().getBatch().getName(),
                payment.getRegistration().getBatch().getsLimit(),
                payment.getRegistration().getBatch().getsYear(),
                payment.getRegistration().getBatch().getFee()
             );
             
                    StudentDto studentDto=new StudentDto(
                payment.getRegistration().getStudent().getSid(),
                payment.getRegistration().getStudent().getName(),
                payment.getRegistration().getStudent().getAddress(),
                payment.getRegistration().getStudent().getAge(),
                payment.getRegistration().getStudent().getDob(),
                payment.getRegistration().getStudent().getNic(),
                payment.getRegistration().getStudent().getTel(),
                payment.getRegistration().getStudent().getEmail()     
             );
             
                    RegistrationDto registrationDto=new RegistrationDto(
                payment.getRegistration().getRegId(),
                batchDto,
                studentDto,
                payment.getRegistration().getDate(),
                payment.getRegistration().getFee()
             );
            
                    TeacherDto teacherDto =new TeacherDto(
                         
                         payment.getSubject().getTeacher().gettId(),
                         payment.getSubject().getTeacher().getName(),
                         payment.getSubject().getTeacher().getAddress(),
                         payment.getSubject().getTeacher().getTel(),
                         payment.getSubject().getTeacher().getGender(),
                         payment.getSubject().getTeacher().getNic(),
                         payment.getSubject().getTeacher().getEmail(),
                         payment.getSubject().getTeacher().getAge()
                         
                 );
             
                    SubjectDto subjectDto=new SubjectDto(
                     payment.getSubject().getSubId(),
                     payment.getSubject().getName(),
                     teacherDto,
                     payment.getSubject().getType(),
                     payment.getSubject().getDay()
             );
            
            PaymentDto c = new PaymentDto(
                    payment.getPid(),
                    registrationDto,
                    batchDto,
                    subjectDto,
                    payment.getDate(),
                    payment.getFee(),
                    payment.getMonth()
            );
                    System.out.println("payment "+c.getMonth());
                    allList.add(c);
                    
                }
                
                

                return allList;

            } else {
         System.out.println("search payment count BOIMPL");
                return null;
            }
        }
    }
    
}



          