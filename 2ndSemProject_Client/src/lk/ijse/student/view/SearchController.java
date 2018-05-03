/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXTextField;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import lk.ijse.student.controler.AttnendanceControler;
import lk.ijse.student.controler.RegistrationControler;
import lk.ijse.student.controler.StudentControler;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.observer.Observer;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class SearchController implements Initializable ,Observer{

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField sidTxt;

    @FXML
    private JFXTextField ageTxt;
    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextField addressTxt;

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField talTxt;

    @FXML
    private JFXTextField nicTxt;

    @FXML
    private JFXTextField bdytxt;

    @FXML
    private JFXTextField bidTxt;
    
    private String sid="";

    ////////////////////////////////////////////////////////////////////////
    
    @FXML
    void idAction(ActionEvent event) {
        
        String sid=sidTxt.getText();
        try {
            RegistrationDto registrationDto=RegistrationControler.searcherAll(sid);
            
            if(!this.sid.equalsIgnoreCase(sid)){
                RegistrationControler.release(this.sid);
                    this.sid=sid;
            }
            if (registrationDto != null) {
                boolean isReserved = StudentControler.reserveStudent(sid);
                if (isReserved) {
                        nameTxt.setText(registrationDto.getStudentDto().getName());
                        ageTxt.setText(""+registrationDto.getStudentDto().getAge());
                        talTxt.setText(registrationDto.getStudentDto().getTel());
                        addressTxt.setText(registrationDto.getStudentDto().getAddress());
                        bidTxt.setText(registrationDto.getBatchDto().getName());
                        emailTxt.setText(registrationDto.getStudentDto().getEmail());
                        nicTxt.setText(registrationDto.getStudentDto().getNic());
                        bdytxt.setText(registrationDto.getStudentDto().getDob());
                } else {
                    nameTxt.setText("");
                    ageTxt.setText("");
                    talTxt.setText("");
                    addressTxt.setText("");
                    bidTxt.setText("");
                    emailTxt.setText("");
                    nicTxt.setText("");
                    bdytxt.setText("");
                    
                    JOptionPane.showMessageDialog(null, "Reserved");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! No Student found");
                nameTxt.setText("");
                    ageTxt.setText("");
                    talTxt.setText("");
                    addressTxt.setText("");
                    bidTxt.setText("");
                    emailTxt.setText("");
                    nicTxt.setText("");
                    bdytxt.setText("");
                nameTxt.setText("");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }  catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            AttnendanceControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void update() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
