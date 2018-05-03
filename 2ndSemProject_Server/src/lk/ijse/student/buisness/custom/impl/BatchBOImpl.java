/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.BatchBO;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.entity.Batch;


import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.BatchRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 */
public class BatchBOImpl implements BatchBO{
    private BatchRepository batchRepository;
    public BatchBOImpl() {
         this.batchRepository=(BatchRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.BATCH);
    }

        
    @Override
    public boolean addBatch(BatchDto batchDto) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            batchRepository.setSession(session);
            
            Batch c = new Batch(
                    batchDto.getBid(),
                    batchDto.getName(),
                    batchDto.getLimit(),
                    batchDto.getYear(),
                    batchDto.getFee()
            );
            
            boolean result = batchRepository.save(c);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean updateBatch(BatchDto batchDto) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            batchRepository.setSession(session);
            
            Batch c = new Batch();
            
             batchRepository.update(c);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeBatch(String id) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            batchRepository.setSession(session);
            session.beginTransaction();
            Batch batch = batchRepository.findById(id);
            if (batch != null) {
                batchRepository.delete(batch);
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
    public BatchDto searchBatch(String id) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            batchRepository.setSession(session);
            session.beginTransaction();
            Batch t = batchRepository.findById(id);
            if (t != null) {
                session.getTransaction().commit();
                
                BatchDto c = new BatchDto();
                
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
    public List<BatchDto> getAll() throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            batchRepository.setSession(session);
            session.beginTransaction();
            List<Batch> batchs = batchRepository.findAll();
            session.getTransaction().commit();
            if (batchs != null) {

                List<BatchDto> allList = new ArrayList<>();

                for (Batch batch : batchs) {
                   BatchDto c = new BatchDto(
                    batch.getBid(),
                    batch.getName(),
                    batch.getsLimit(),
                    batch.getsYear(),
                    batch.getFee()
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
    public BatchDto searchNameOfBatch(String name) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            batchRepository.setSession(session);
            session.beginTransaction();
            
            String sql = "SELECT * FROM Batch WHERE name ='"+name+"'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Batch.class);
            
            List<Batch> results = query.list();
            session.getTransaction().commit();
            
            BatchDto x=new BatchDto();
            if (results != null) {
                
                 
                for (Batch t : results) {
                   // System.out.println("batabata at "+t.getBid());
                    x.setBid(t.getBid());
                    x.setName(t.getName());
                    x.setLimit(t.getsLimit());
                    x.setYear(t.getsYear());
                    x.setFee(t.getFee());
                   // System.out.println("batch "+t.getBid());
                return x;
                }

                
            }else{
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        return null;
        
    }
    
}
