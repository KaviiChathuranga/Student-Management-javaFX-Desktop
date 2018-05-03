/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.Batch_TeacherBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.Batch_SubjectDTO;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.entity.Batch;
import lk.ijse.student.entity.Batch_Subject;
import lk.ijse.student.entity.Subject;
import lk.ijse.student.entity.Teacher;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.Batch_Teacher_Repository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 */
public class Batch_SubjectBOImpl implements Batch_TeacherBO{
    
    private Batch_Teacher_Repository batch_sub_Repository;
    
    public Batch_SubjectBOImpl(){
        batch_sub_Repository=(Batch_Teacher_Repository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.BATCH_TEACHER);
    }

    @Override
    public boolean addBatch_Teacher(BatchDto batchDTO,List<SubjectDto>subjectDtos) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            batch_sub_Repository.setSession(session);
            boolean result=false;
            
            Batch batch=new Batch(batchDTO.getBid(),batchDTO.getName(),batchDTO.getLimit(),batchDTO.getYear(),batchDTO.getFee());
            
            for (SubjectDto subjectDto : subjectDtos) {
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
                 Subject subject=new Subject(subjectDto.getSubId(),subjectDto.getName(),teacher,subjectDto.getType(),subjectDto.getDay());
                 Batch_Subject batch_Subject=new Batch_Subject(0,batch,subject);
                 result = batch_sub_Repository.save(batch_Subject);
                 
             }
//            
//            List<SubjectDto> subs=batch_SubjectDTOs.getSubject();
//            List<Subject> sublist=new ArrayList();
//             for (SubjectDto sub : subs) {
//                 TeacherDto teacherDTO=sub.getTeacherDto();
//                 Teacher teacher=new Teacher(teacherDTO.getTid(),teacherDTO.getName(),teacherDTO.getAddress(),teacherDTO.getTel(), teacherDTO.getGender(),teacherDTO.getNic(),teacherDTO.getEmail(),teacherDTO.getAge());
//                 sublist.add(new Subject(sub.getSubId(), sub.getName(), teacher,sub.getDay(),sub.getType()));
//             }
//             
//             Batch_Subject batch_Subject=new Batch_Subject(0,batch,sublist);
//
//            result = batch_sub_Repository.save(batch_Subject);

            session.getTransaction().commit();
           // session.getTransaction().rollback();
            return result;
            
        }catch(Exception ex){
            
            ex.printStackTrace();
            return false;
        }
  
    }

    @Override
    public boolean updateBatch_Teacher(Batch_SubjectDTO batch_TeacherDTO) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeBatch_Teacher(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Batch_SubjectDTO> searchBatch_subject(String id) throws Exception {
      try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
           batch_sub_Repository.setSession(session);
           
            String sql = "SELECT * FROM batch_subject WHERE batch_id ='"+id+"'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Batch_Subject.class);
            
            List<Batch_Subject> results = query.list();
            List<Batch_SubjectDTO>new_res=new ArrayList<>();
            if (results!=null) {
                for (Batch_Subject result : results) {
                    TeacherDto teacher=new TeacherDto(
                            result.getSubject().getTeacher().gettId(),
                            result.getSubject().getTeacher().getName(),
                            result.getSubject().getTeacher().getAddress(),
                            result.getSubject().getTeacher().getTel(),
                            result.getSubject().getTeacher().getGender(),
                            result.getSubject().getTeacher().getNic(),
                            result.getSubject().getTeacher().getEmail(),
                            result.getSubject().getTeacher().getAge()    
                    );
                    Batch_SubjectDTO batch_SubjectDTO=new Batch_SubjectDTO(
                            result.getId(),
                            new BatchDto(result.getBatch().getBid(),result.getBatch().getName(),result.getBatch().getsLimit(),result.getBatch().getsYear(),result.getBatch().getFee()),
                            new SubjectDto(result.getSubject().getSubId(),result.getSubject().getName(),teacher,result.getSubject().getDay(),result.getSubject().getType())
                    );
                 new_res.add(batch_SubjectDTO);   
                }
              return new_res;
          }
            return null;
           
      }
        
    }

    @Override
    public List<Batch_SubjectDTO> getAll() throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            batch_sub_Repository.setSession(session);
            session.beginTransaction();
            List<Batch_Subject> batchs = batch_sub_Repository.findAll();
            
            
                List<Batch_SubjectDTO> allList = new ArrayList<>();
                BatchDto batchDto=new BatchDto();
               // Batch_Subject bs=new Batch_Subject();
            
            for (Batch_Subject batch : batchs) {
                    batchDto.setBid(batch.getBatch().getBid());
                    batchDto.setName(batch.getBatch().getName());
                    batchDto.setLimit(batch.getBatch().getsLimit());
                    batchDto.setYear(batch.getBatch().getsYear());
                    batchDto.setYear(batch.getBatch().getsYear());
             
            }
            
            
            
//            
//            if (batchs != null) {
//
//                
//                for (Batch_Subject batch : batchs) {
//                    
//                   
//                    
//                  
//                   
//                   SubjectDto subjectDto=new SubjectDto(
//                         batch.getSubject().getSubId(),
//                         batch.getSubject().getName(),
//                         teacherDto,
//                         batch.getSubject().getType(),
//                         batch.getSubject().getDay()
//                   );
//                    
//                   Batch_SubjectDTO batch_TeacherDTO=new Batch_SubjectDTO(
//                            batch.getId(),
//                            batchDto,
//                            subjectDto
//                    );
//                    
//                   allList.add(batch_TeacherDTO);
//                }
//
//                return allList;
//
//            } else {
//
//                return null;
//            }
        }
        return null;
    }
    
}
