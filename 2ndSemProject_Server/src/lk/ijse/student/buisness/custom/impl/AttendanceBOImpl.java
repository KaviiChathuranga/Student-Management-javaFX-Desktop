/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.AttendanceBO;
import lk.ijse.student.dto.AttendanceDto;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.dto.StudentDto;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.entity.Attendance;
import lk.ijse.student.entity.Batch;
import lk.ijse.student.entity.Registration;
import lk.ijse.student.entity.Student;
import lk.ijse.student.entity.Subject;
import lk.ijse.student.entity.Teacher;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.AttendanceRepository;


import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 */
public class AttendanceBOImpl implements AttendanceBO{
    
    private AttendanceRepository attendanceRepository;

    public AttendanceBOImpl() {
         this.attendanceRepository=(AttendanceRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ATTENDANCE);
    }
    
    @Override
    public boolean addAttendance(AttendanceDto attendanceDto) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            attendanceRepository.setSession(session);
            
             Batch batch=new Batch(
                attendanceDto.getRegistrationDto().getBatchDto().getBid(),
                attendanceDto.getRegistrationDto().getBatchDto().getName(),
                attendanceDto.getRegistrationDto().getBatchDto().getLimit(),
                attendanceDto.getRegistrationDto().getBatchDto().getYear(),
                attendanceDto.getRegistrationDto().getBatchDto().getFee()
             );
             System.out.println("boo "+batch.getBid());
             
             Student student=new Student(
                attendanceDto.getRegistrationDto().getStudentDto().getSid(),
                attendanceDto.getRegistrationDto().getStudentDto().getName(),
                attendanceDto.getRegistrationDto().getStudentDto().getAddress(),
                attendanceDto.getRegistrationDto().getStudentDto().getAge(),
                attendanceDto.getRegistrationDto().getStudentDto().getDob(),
                attendanceDto.getRegistrationDto().getStudentDto().getNic(),
                attendanceDto.getRegistrationDto().getStudentDto().getTel(),
                attendanceDto.getRegistrationDto().getStudentDto().getEmail()     
             );
             
             Registration registration=new Registration(
                attendanceDto.getRegistrationDto().getRegId(),
                batch,
                student,
                attendanceDto.getRegistrationDto().getDate(),
                attendanceDto.getRegistrationDto().getFee()
             );
             Teacher teacher=new Teacher(
                attendanceDto.getSubjectDto().getTeacherDto().getTid(),
                attendanceDto.getSubjectDto().getTeacherDto().getName(),
                attendanceDto.getSubjectDto().getTeacherDto().getAddress(),
                attendanceDto.getSubjectDto().getTeacherDto().getTel(),
                attendanceDto.getSubjectDto().getTeacherDto().getGender(),
                attendanceDto.getSubjectDto().getTeacherDto().getNic(),
                attendanceDto.getSubjectDto().getTeacherDto().getEmail(),
                attendanceDto.getSubjectDto().getTeacherDto().getAge()
             );
             
             Subject subject=new Subject(
                attendanceDto.getSubjectDto().getSubId(),
                attendanceDto.getSubjectDto().getName(),
                teacher,
                attendanceDto.getSubjectDto().getType(),
                attendanceDto.getSubjectDto().getDay()
             );
            
            
            Attendance c = new Attendance(
                attendanceDto.getAid(),
                registration,
                batch,
                subject,
                attendanceDto.getDate(),
                attendanceDto.getTime()
            );
            
            boolean result = attendanceRepository.save(c);
            session.getTransaction().commit();
            return result;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAttendance(AttendanceDto attendanceDto) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            attendanceRepository.setSession(session);
            
            Attendance c = new Attendance();
            
            attendanceRepository.update(c);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeAttendance(String id) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            attendanceRepository.setSession(session);
            session.beginTransaction();
            Attendance attendance = attendanceRepository.findById(id);
            if (attendance != null) {
                attendanceRepository.delete(attendance);
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
    public AttendanceDto searchAttendance(String id) throws Exception {
           try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            attendanceRepository.setSession(session);
            session.beginTransaction();
            Attendance t = attendanceRepository.findById(id);
            if (t != null) {
                session.getTransaction().commit();
                
                AttendanceDto c = new AttendanceDto();
                
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
    public List<AttendanceDto> getAll() throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            attendanceRepository.setSession(session);
            session.beginTransaction();
            List<Attendance> attendances = attendanceRepository.findAll();
            session.getTransaction().commit();
            if (attendances != null) {

                List<AttendanceDto> allList = new ArrayList<>();

                for (Attendance attendance : attendances) {
                    
                    BatchDto batchDto=new BatchDto(
                attendance.getRegistration().getBatch().getBid(),
                attendance.getRegistration().getBatch().getName(),
                attendance.getRegistration().getBatch().getsLimit(),
                attendance.getRegistration().getBatch().getsYear(),
                attendance.getRegistration().getBatch().getFee()
                     );
                    
                     StudentDto studentDto=new StudentDto(
                attendance.getRegistration().getStudent().getSid(),
                attendance.getRegistration().getStudent().getName(),
                attendance.getRegistration().getStudent().getAddress(),
                attendance.getRegistration().getStudent().getAge(),
                attendance.getRegistration().getStudent().getDob(),
                attendance.getRegistration().getStudent().getNic(),
                attendance.getRegistration().getStudent().getTel(),
                attendance.getRegistration().getStudent().getEmail()     
                     );
                     
                     RegistrationDto registrationDto=new RegistrationDto(
                attendance.getRegistration().getRegId(),
                batchDto,
                studentDto,
                attendance.getRegistration().getDate(),
                attendance.getRegistration().getFee()
                    );
                     
                     TeacherDto teacherDto=new TeacherDto(
                attendance.getSubject().getTeacher().gettId(),
                attendance.getSubject().getTeacher().getName(),
                attendance.getSubject().getTeacher().getAddress(),
                attendance.getSubject().getTeacher().getTel(),
                attendance.getSubject().getTeacher().getGender(),
                attendance.getSubject().getTeacher().getNic(),
                attendance.getSubject().getTeacher().getEmail(),
                attendance.getSubject().getTeacher().getAge()
             );
                    
                    SubjectDto subjectDto=new SubjectDto(
                attendance.getSubject().getSubId(),
                attendance.getSubject().getName(),
                teacherDto,
                attendance.getSubject().getType(),
                attendance.getSubject().getDay()
             );
            
            
            AttendanceDto c = new AttendanceDto(
                attendance.getAid(),
                registrationDto,
                batchDto,
                subjectDto,
                attendance.getDate(),
                attendance.getTime()
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
