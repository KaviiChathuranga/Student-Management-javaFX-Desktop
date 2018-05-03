/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.student.controler.TeacherControler;
import lk.ijse.student.observer.Observer;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class TeacherController implements Initializable ,Observer{

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton regBtn;

    @FXML
    private JFXButton modifyBtn;

    @FXML
    private JFXButton batchBtn;

    @FXML
    private JFXTextField messageTxt;

    @FXML
    private JFXSlider slider3;

    @FXML
    private Label headLabl;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private Label timeLbl;

    @FXML
    private Label dateLbl;
    
        @FXML
    private AnchorPane middleAnchorPane;
      
        @FXML
    private ImageView sideImg;
        
        
        Node s;

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            UnicastRemoteObject.exportObject(this, 0);
             TeacherControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(StudentPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadImages();
        loadTime();
        imagePlay();
    }   
    
     @FXML
    void BatchBtnClick(ActionEvent event) {
        if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);

             }
             try {
                 s=FXMLLoader.load(getClass().getResource("Batch.fxml"));
                 //JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(BatchController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    @FXML
    void RegBtnClick(ActionEvent event) {
        if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);

             }
             try {
                 s=FXMLLoader.load(getClass().getResource("TeacherReg.fxml"));
                 //JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(BatchController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void logOutBtnClick(ActionEvent event) {
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
    ////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    void modifyBtnClick(ActionEvent event) {
         if (s!=null) {
                 middleAnchorPane.getChildren().remove(s);

             }
             try {
                 s=FXMLLoader.load(getClass().getResource("TeacherUpdate.fxml"));
                 //JFXFillTransition fXFillTransition=new JFXFillTransition();

                 middleAnchorPane.getChildren().add(s);
             } catch (IOException ex) {
                 Logger.getLogger(BatchController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    
    private void loadImages() {
        Image logOutImage=new Image("lk/ijse/student/assets/Logout Rounded Up_30px.png");
        logoutBtn.setGraphic(new ImageView(logOutImage));
        
        Image regImage=new Image("lk/ijse/student/assets/Add User Male_40px.png");
        regBtn.setGraphic(new ImageView(regImage));
        
        Image modiImage=new Image("lk/ijse/student/assets/Edit Property_40px.png");
        modifyBtn.setGraphic(new ImageView(modiImage));
        
        Image attImage=new Image("lk/ijse/student/assets/Thumb Up_40px.png");
        batchBtn.setGraphic(new ImageView(attImage));
 
    }

    private void loadTime() {
        Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
                        Calendar time = Calendar.getInstance();
                        
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        
                        timeLbl.setText(simpleDateFormat.format(time.getTime()));
         }),
                    new KeyFrame(Duration.seconds(1))
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            
            Date d=new Date();
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("YYY/MM/dd");
            dateLbl.setText(simpleDateFormat1.format(d));
    }

    private void imagePlay() {
        
        TranslateTransition transition2=new TranslateTransition();
        transition2.setDuration(Duration.seconds(2.2));
        transition2.setNode(sideImg);
        transition2.setToX(+245.5); 
        
        transition2.play();
    }

    @Override
    public void update() throws Exception {
       //load
    }
    
}
