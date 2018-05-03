/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.ResultBO;
import lk.ijse.student.dto.ResultDto;
import lk.ijse.student.dto.TeacherDto;
import lk.ijse.student.entity.Registration;
import lk.ijse.student.entity.Result;
import lk.ijse.student.entity.Subject;
import lk.ijse.student.entity.Teacher;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.ResultRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


/**
 *
 * @author Kavindu
 */
public class ResultBOImpl implements ResultBO{
    private ResultRepository resultRepository;
    public ResultBOImpl() {
         this.resultRepository=(ResultRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.RESULT);
    }

    @Override
    public boolean addResult(ResultDto r) throws Exception {
            try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            resultRepository.setSession(session);
            
            Result c = new Result(
                    r.getResId(),
                    r.getEid(),
                    r.getBatch(),
                    r.getDate(),
                    r.getSubject(),
                    r.getSid(),
                    r.getName(),
                    r.getNic(),
                    r.getMarks()
            );
            
            boolean result = resultRepository.save(c);
            session.getTransaction().commit();
            return result;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateResult(ResultDto r) throws Exception {
             try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            resultRepository.setSession(session);
            
             Result c = new Result(
                    r.getResId(),
                    r.getEid(),
                    r.getBatch(),
                    r.getDate(),
                    r.getSubject(),
                    r.getSid(),
                    r.getName(),
                    r.getNic(),
                    r.getMarks()
            );
            
            resultRepository.update(c);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeResult(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            resultRepository.setSession(session);
            session.beginTransaction();
            Result result = resultRepository.findById(id);
            if (result != null) {
                resultRepository.delete(result);
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
    public ResultDto searchResult(String id) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            resultRepository.setSession(session);
            session.beginTransaction();
            Result t = resultRepository.findById(id);
            if (t != null) {
                session.getTransaction().commit();
                
                ResultDto c = new ResultDto();
                
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
    public List<ResultDto> getAll() throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            resultRepository.setSession(session);
            session.beginTransaction();
            List<Result> results = resultRepository.findAll();
            session.getTransaction().commit();
            if (results != null) {

                List<ResultDto> allList = new ArrayList<>();

                for (Result r : results) {
                    
                    ResultDto c = new ResultDto(
                    r.getResId(),
                    r.getEid(),
                    r.getBatch(),
                    r.getDate(),
                    r.getSubject(),
                    r.getSid(),
                    r.getName(),
                    r.getNic(),
                    r.getMarks()
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
    public ResultDto searchResult(String text, String sid) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            resultRepository.setSession(session);
            session.beginTransaction();
            
            String sql = "SELECT * FROM Result WHERE resId ='"+text+"' && sid='"+sid+"'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Result.class);
            
            List<Result> t = query.list();
            
            
            if (t != null) {
                session.getTransaction().commit();
                for (Result r : t) {
                  ResultDto c = new ResultDto(
                    r.getResId(),
                    r.getEid(),
                    r.getBatch(),
                    r.getDate(),
                    r.getSubject(),
                    r.getSid(),
                    r.getName(),
                    r.getNic(),
                    r.getMarks()
                );
                  return c;
                }
               return null;
                
                
            }else{
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
}
