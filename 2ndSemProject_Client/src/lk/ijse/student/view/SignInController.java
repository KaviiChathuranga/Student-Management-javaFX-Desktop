/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import lk.ijse.student.controler.loginControler;
import lk.ijse.student.dto.LoginDto;
import lk.ijse.student.observer.Observer;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class SignInController implements Initializable,Observer {
private int count=0;
private String type="";
    /**
     * Initializes the controller class.
     */
      @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXButton signBtn;

    @FXML
    private JFXPasswordField reTxt;

    @FXML
    private JFXButton backBtn;
        @FXML
    private JFXHamburger hamba;
            @FXML
    private AnchorPane sidePane;
            @FXML
    private JFXCheckBox adminCheck;

    @FXML
    private JFXCheckBox userCheck;

    @FXML
    private JFXCheckBox teacherChek;

    @FXML
    void backBtnClick(ActionEvent event) {
          AnchorPane box;
        try {
            box = FXMLLoader.load(getClass().getResource("/lk/ijse/student/view/Login.fxml"));
              FadeTransition fade=new FadeTransition(Duration.millis(2000), box);
                fade.setFromValue(0);
                fade.setToValue(0.95);
                fade.setCycleCount(1);
                fade.play();
                
                    SpalshScreenTest screenTest=new SpalshScreenTest();
                    Stage stage=new Stage(StageStyle.UNDECORATED); 
                    screenTest.start(stage);        
                    ((Stage)(((JFXButton)event.getSource()).getScene().getWindow())).hide();
                    
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @FXML
    void signBtnClick(ActionEvent event) {
        String name=nameTxt.getText();
        String pw=passwordTxt.getText();
        
    try {
      LoginDto dto=new LoginDto(pw,name,type);
      LoginDto loginDto=loginControler.searchLog(pw);
        if (loginDto!=null && loginDto.getType().equals(type)) {
            JOptionPane.showMessageDialog(null,"This Is Duplicate Entry!","",JOptionPane.WARNING_MESSAGE);
        }else{
        if (dto!=null) {
             boolean b=loginControler.addLog(dto);
             if (b) {
                JOptionPane.showMessageDialog(null,"Your Sign In Finished");
            }else{
                 JOptionPane.showMessageDialog(null,"Your Sign In Not Finish !","",JOptionPane.WARNING_MESSAGE);
             }
        }
      }
    } catch (Exception ex) {
        Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
      @FXML
    void checkPassword(KeyEvent event) {
       
    }
    
    /////////////////////////////////////////////////////////////////////////////
        @FXML
    void hambaClick(MouseEvent event) {
              
//        Stage stage=new Stage();
//                        stage.initStyle(StageStyle.TRANSPARENT);
//                        sidePane.setBackground(Background.EMPTY);
//                        Scene scene=new Scene(sidePane);
//                        scene.setFill(Color.TRANSPARENT);
//                        stage.setScene(scene);
//                      stage.show();
//                        
                        
        count++;
        if (count==1) {
//             FadeTransition fadeIn=new FadeTransition(Duration.millis(3000),sidePane);
//            fadeIn.setFromValue(0);
//            fadeIn.setToValue(1);
//            fadeIn.setCycleCount(1);
//            fadeIn.play();
//        sidePane.opacityProperty().setValue(20);
//        sidePane.setMaxHeight(65);
        
        TranslateTransition transition2=new TranslateTransition();
        transition2.setDuration(Duration.seconds(1.5));
        transition2.setNode(sidePane);
        transition2.setToY(+95); 
     
    
        transition2.play();
         
        
        }else{
            count=0;
        TranslateTransition transition2=new TranslateTransition();
        transition2.setDuration(Duration.seconds(2));
        transition2.setNode(sidePane);
        transition2.setToY(-95); 
        
         FadeTransition fadeIn=new FadeTransition(Duration.millis(3000));
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            FadeTransition fadeIn1=new FadeTransition(Duration.millis(3000));
            fadeIn1.setFromValue(0);
            fadeIn1.setToValue(1);
            fadeIn1.setCycleCount(1);
            fadeIn1.play();
       
        
        transition2.play();
        
        count=0;
        }
    }
    /////////////////////////////////////////////////////////////////////////////
    
        @FXML
    void adminCheckClick(MouseEvent event) {
         if (adminCheck.isSelected()) {
             type="admin";
          userCheck.setSelected(false);  
          teacherChek.setSelected(false);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////
      @FXML
    void teacherCheckClick(MouseEvent event) {
          if (teacherChek.isSelected()) {
              type="teacher";
          userCheck.setSelected(false);  
          adminCheck.setSelected(false);
        }
    }
    //////////////////////////////////////////////////////////////////////////////
    @FXML
    void userChekClick(MouseEvent event) {
        if (userCheck.isSelected()) {
            type="user";
          adminCheck.setSelected(false);  
          teacherChek.setSelected(false);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////
        @FXML
    void sideEnterd(MouseEvent event) {

    }
    /////////////////////////////////////////////////////////////////////////////////
    @FXML
    void sideExited(MouseEvent event) {

    }
    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
             loginControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public void update() throws Exception {
        
    }

    
}
