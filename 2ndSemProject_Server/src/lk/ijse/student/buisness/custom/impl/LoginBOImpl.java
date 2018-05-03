/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.buisness.custom.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.student.buisness.custom.LoginBO;
import lk.ijse.student.dto.LoginDto;
import lk.ijse.student.entity.Login;
import lk.ijse.student.repository.RepositoryFactory;
import lk.ijse.student.repository.custom.LoginRepository;
import lk.ijse.student.resouce.NewHibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Kavindu
 */
public class LoginBOImpl implements LoginBO{
    private LoginRepository loginRepository;
    public LoginBOImpl() {
         this.loginRepository=(LoginRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.LOGIN);
    }

    
    @Override
    public boolean addLogin(LoginDto loginDto) throws Exception {
         try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            loginRepository.setSession(session);
            
            Login c = new Login(
                    loginDto.getPassword(),
                    loginDto.getName(),
                    loginDto.getType()
            );
            
            boolean result = loginRepository.save(c);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean updateLogin(LoginDto loginDto) throws Exception {
            try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            loginRepository.setSession(session);
            
            Login c = new Login();
            
            loginRepository.update(c);
            session.getTransaction().commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeLogin(String id) throws Exception {
        try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            loginRepository.setSession(session);
            session.beginTransaction();
            Login login = loginRepository.findById(id);
            if (login != null) {
                loginRepository.delete(login);
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
    public LoginDto searchLogin(String id) throws Exception {
       try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            loginRepository.setSession(session);
            session.beginTransaction();
            Login t = loginRepository.findById(id);
            if (t != null) {
                session.getTransaction().commit();
                
                LoginDto dto = new LoginDto(
                    t.getPassword(),
                    t.getName(),
                    t.getType()
                    );
                
                return dto;
            }else{
                return null;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LoginDto> getAll() throws Exception {
      try (Session session = NewHibernateUtil.getSessionFactory().openSession()) {
            loginRepository.setSession(session);
            session.beginTransaction();
            List<Login> logins = loginRepository.findAll();
            session.getTransaction().commit();
            if (logins != null) {

                List<LoginDto> allList = new ArrayList<>();

                for (Login login : logins) {
                    
                    LoginDto dto = new LoginDto(
                    login.getPassword(),
                    login.getName(),
                    login.getType()
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
