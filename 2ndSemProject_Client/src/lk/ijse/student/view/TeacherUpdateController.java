/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import lk.ijse.student.controler.AttnendanceControler;
import lk.ijse.student.observer.Observer;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class TeacherUpdateController implements Initializable,Observer {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            UnicastRemoteObject.exportObject(this, 0);
            AttnendanceControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(TeacherUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void update() throws Exception {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
