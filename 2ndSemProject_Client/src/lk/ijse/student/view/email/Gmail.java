/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view.email;

/**
 *
 * @author Kavindu
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;

public class Gmail  /*implements EventHandler<ActionEvent>*/ {

    public void setMail(String recipientEmail,String message){
        try {
            GoogleMail.Send("97crazycute", "30981310k0", recipientEmail, "Orient Registration", message);
        } catch (MessagingException ex) {
            Logger.getLogger(Gmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

} // end Send Gmail program //
