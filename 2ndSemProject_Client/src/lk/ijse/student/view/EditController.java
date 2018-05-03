/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class EditController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Circle circle;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXDialogLayout dianlogLayout;


    void mangeBtnClick(ActionEvent event) {

    }

    void profileBtnClick(ActionEvent event) {

    }

    void userProfileClick(ActionEvent event) {

    }
   
       
    public static void main(String[] args) {
        launch(args);
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        //Image imageIcon=new Image("lk/ijse/student/assets/IMG_1040.jpg");
        // circle.setFill(new ImagePattern(imageIcon));
    }  
    
    
    
}
