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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import lk.ijse.student.controler.loginControler;
import lk.ijse.student.dto.LoginDto;
import lk.ijse.student.observer.Observer;
import org.controlsfx.control.Notifications;

/**#bf0000
 * FXML Controller class
 *
 * @author Kavindu
 */
public class LoginController implements Initializable,Observer {
    private String type="";
    
    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    
    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXButton signBtn;
    
    @FXML
    private Label headLbl;
    
    @FXML
    private JFXButton cancelBtn;

    @FXML
    private AnchorPane sidePane;

    @FXML
    private JFXHamburger hamba;
    
    @FXML
    private JFXCheckBox adminCheck;

    @FXML
    private JFXCheckBox userCheck;

    @FXML
    private JFXCheckBox teacherChek;
    
    @FXML
    private Label welcomeLbl;

    //////////////////// refernce ///////////////////
    
    private int count=0;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            UnicastRemoteObject.exportObject(this, 0);
             loginControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         
        TranslateTransition transition2=new TranslateTransition();
        transition2.setDuration(Duration.seconds(2));
        transition2.setNode(headLbl);
        transition2.setToY(+53); 
        sidePane.setStyle("-fx-border-style: solid inside;"); 
        
        transition2.play();
        if(!SpalshScreenTest.isPlashLoaded)
            
        Splash();
        
        
    }   
    private void Splash(){
        
        try {
            SpalshScreenTest.isPlashLoaded=true;
            
            AnchorPane splash=FXMLLoader.load(getClass().getResource("Splashh.fxml"));
            root.getChildren().setAll(splash);
            
            FadeTransition fadeIn=new FadeTransition(Duration.millis(3000),splash);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            
            FadeTransition fadeOut=new FadeTransition(Duration.millis(3000),splash);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            
            fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
                @Override 
                public void handle(ActionEvent event) { 
                   fadeOut.play();
                }
            });
            fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                  
                    try {
                      AnchorPane mainForm = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        root.getChildren().setAll(mainForm);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                }
            });
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @FXML
    void loginBtnAction(ActionEvent event) {
          
        if (userCheck.isSelected()) {
            String name=nameTxt.getText();
          String pw=passwordTxt.getText();
            
             Mainn main=new Mainn();
        Stage stage=new Stage(StageStyle.UNDECORATED); 
        
        try {
          
            LoginDto loginDto=loginControler.searchLog(pw);
            if (loginDto==null) {
                  showAlert();
                   JOptionPane.showMessageDialog(null,"NO User Registerd !","",JOptionPane.WARNING_MESSAGE);
            }else{
            
               
                if (loginDto.getPassword().equals(pw) && loginDto.getName().equals(name) && type.equals(loginDto.getType())) {
                    main.start(stage);
                     ((Stage)(((JFXButton)event.getSource()).getScene().getWindow())).hide();
                     showGoodAlert();
                }else{
                    showAlert();
                   // JOptionPane.showMessageDialog(null,"The Password Or User Name is incorrect !","",JOptionPane.WARNING_MESSAGE);
                }
            
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        if (adminCheck.isSelected()) {
              String name=nameTxt.getText();
          String pw=passwordTxt.getText();
                 AdminMain main=new AdminMain();
        Stage stage=new Stage(StageStyle.UNDECORATED); 
        
        try {
             LoginDto loginDto=loginControler.searchLog(pw);
            if (loginDto==null) {
                showAlert();
                   JOptionPane.showMessageDialog(null,"NO User Registerd !","",JOptionPane.WARNING_MESSAGE);
                   
            }else{
            
               
                if (loginDto.getPassword().equals(pw) && loginDto.getName().equals(name) && type.equals(loginDto.getType())) {
                    main.start(stage);
                     ((Stage)(((JFXButton)event.getSource()).getScene().getWindow())).hide();
                     showGoodAlert();
                }else{
                    showAlert();
                }
            
            }
           
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        if (teacherChek.isSelected()) {
                String name=nameTxt.getText();
          String pw=passwordTxt.getText();
                 TeacherMain main=new TeacherMain();
        Stage stage=new Stage(StageStyle.UNDECORATED); 
        
        try {
             LoginDto loginDto=loginControler.searchLog(pw);
            if (loginDto==null) {
                   JOptionPane.showMessageDialog(null,"NO User Registerd !","",JOptionPane.WARNING_MESSAGE);
            }else{
            
               
                if (loginDto.getPassword().equals(pw) && loginDto.getName().equals(name) && type.equals(loginDto.getType())) {
                    main.start(stage);
                     ((Stage)(((JFXButton)event.getSource()).getScene().getWindow())).hide();
                     showGoodAlert();
                }else{
                    showAlert();
                }
            
            }
          
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
 
        
        
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////
        @FXML
    void signClick(ActionEvent event) {
            SignMain main=new SignMain();
            Stage stage=new Stage(StageStyle.UNDECORATED); 
        try {
            main.start(stage);
            ((Stage)(((JFXButton)event.getSource()).getScene().getWindow())).hide();
        } catch (Exception ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    
    
    @FXML
    void cancelBtnClick(ActionEvent event) {
        ((Stage)(((JFXButton)event.getSource()).getScene().getWindow())).hide();
        
        
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void sideEnterd(MouseEvent event) {
        
        
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void sideExited(MouseEvent event) {
      
        
    }
    ///////////////////////////////////////////////////////////////////////////////////////
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
     
        
        headLbl.setVisible(false);
        welcomeLbl.setVisible(false);
        
            
        transition2.play();
         
        
        }else{
            count=0;
        TranslateTransition transition2=new TranslateTransition();
        transition2.setDuration(Duration.seconds(2));
        transition2.setNode(sidePane);
        transition2.setToY(-95); 
        
         FadeTransition fadeIn=new FadeTransition(Duration.millis(3000),headLbl);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            
            FadeTransition fadeIn1=new FadeTransition(Duration.millis(3000),welcomeLbl);
            fadeIn1.setFromValue(0);
            fadeIn1.setToValue(1);
            fadeIn1.setCycleCount(1);
            fadeIn1.play();
            
        headLbl.setVisible(true);
        welcomeLbl.setVisible(true);
        
        transition2.play();
        
        count=0;
        }
       
    }
    ////////////////////////////////////////////////////////////////////////////////////
    
    
    @FXML
    void adminCheckClick(MouseEvent event) {
        if (adminCheck.isSelected()) {
            type="admin";
          userCheck.setSelected(false);  
          teacherChek.setSelected(false);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////
   
       @FXML
    void userChekClick(MouseEvent event) {
        if (userCheck.isSelected()) {
            type="user";
          adminCheck.setSelected(false);  
          teacherChek.setSelected(false);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////
        @FXML
    void teacherCheckClick(MouseEvent event) {
        if (teacherChek.isSelected()) {
            type="teacher";
          userCheck.setSelected(false);  
          adminCheck.setSelected(false);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////

    @Override
    public void update() throws Exception {
        
    }

    private void showAlert() {
       Image img = new Image("/lk/ijse/student/assets/27394172_735719689960818_214562347_n.png");
                Notifications notification = Notifications.create()
                        .title("OOPss ! Login Fail !")
                        .text("Checking is not Successfully.")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("clicked notification");
                            }
                        });
                notification.darkStyle();
                notification.show();
    }

    private void showGoodAlert() {
       Image img = new Image("/lk/ijse/student/assets/27292700_735719656627488_946698439_n.png");
                Notifications notification = Notifications.create()
                        .title("Congrats ! Login Correctly !")
                        .text("Checking is Successfully.")
                        .graphic(new ImageView(img))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("clicked notification");
                            }
                        });
                notification.darkStyle();
                notification.show();
    }
    
}
