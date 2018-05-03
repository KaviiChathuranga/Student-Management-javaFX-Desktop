/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.JFXFillTransition;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton paymentBtn;
    @FXML
    private JFXButton manageBtn;

    @FXML
    private JFXButton userBtn;

    @FXML
    private JFXButton viewBtn;

    @FXML
    private JFXButton sendBtn;

    @FXML
    private ImageView image;

    @FXML
    private Circle faceImage;

    @FXML
    private JFXSlider slider3;

    @FXML
    private Label headLabl1;

    @FXML
    private Label timeLbl;

    @FXML
    private Label dateLbl;

    @FXML
    private Label headLabl;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private AnchorPane middleAnchorPane;

    Node s;
    @FXML
    private StackPane stackPane;
    @FXML
    private Label timeLbl1;
    @FXML
    private Label dateLbl1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagePlay();
        setimages();
        loadTime();
    }

    private void imagePlay() {
        TranslateTransition transition2 = new TranslateTransition();
        transition2.setDuration(Duration.seconds(2.2));
        transition2.setNode(image);
        transition2.setToX(+247.5);

        transition2.play();

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(2.2));
        transition.setNode(faceImage);
        transition.setToX(+250.5);

        transition.play();
    }

    //////////////////////////////////////////////////////////////////////
    @FXML
    void logOutBtnClick(ActionEvent event) {
        AnchorPane box;
        try {
            box = FXMLLoader.load(getClass().getResource("/lk/ijse/student/view/Login.fxml"));
            FadeTransition fade = new FadeTransition(Duration.millis(2000), box);
            fade.setFromValue(0);
            fade.setToValue(0.95);
            fade.setCycleCount(1);
            fade.play();

            SpalshScreenTest screenTest = new SpalshScreenTest();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            screenTest.start(stage);
            ((Stage) (((JFXButton) event.getSource()).getScene().getWindow())).hide();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //////////////////////////////////////////////////////////////////////

    private void setimages() {
        Image logImage = new Image("lk/ijse/student/assets/Logout Rounded Up_30px.png");
        logoutBtn.setGraphic(new ImageView(logImage));

        Image payImage = new Image("lk/ijse/student/assets/Coins_40px_1.png");
        paymentBtn.setGraphic(new ImageView(payImage));

        Image userImage = new Image("lk/ijse/student/assets/User Group Man Man_40px.png");
        userBtn.setGraphic(new ImageView(userImage));

        Image viewImage = new Image("lk/ijse/student/assets/Reply All Arrow_40px.png");
        viewBtn.setGraphic(new ImageView(viewImage));
    }

    ///////////////////////////////////////////////////////////////////////
    @FXML
    void mangeUseClick(ActionEvent event) {

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

        Date d = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("YYY/MM/dd");
        dateLbl.setText(simpleDateFormat1.format(d));
    }

    //////////////////////////////////////////////////////////////////////////////////
    @FXML
    void paymentBtnClick(ActionEvent event) {
        System.out.println("iscb");
    }

    @FXML
    void viewBtnClick(ActionEvent event) {

    }

    @FXML
    void manageBtnClick(ActionEvent event) {

//        
//        try {
//            FXMLLoader fXMLLoader=new FXMLLoader(getClass().getResource("Edit.fxml"));
//           Parent root = fXMLLoader.load();
//            Stage stage =new Stage();   
//            stage.setScene(new Scene(root));    
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            
           //dialog Pane
           JFXDialogLayout dialogLayout=new JFXDialogLayout();
           dialogLayout.setHeading(new Text("kavi"+"\n"));
           Label lblUName=new Label("User Name :");
           JFXTextField userName=new JFXTextField();
           Label lblPsswrd=new Label("Password :");
           JFXTextField pswrd=new JFXTextField();
           Label lbel=new Label("Re-Password :");
           JFXTextField rePswrd=new JFXTextField();
           
          // dialogLayout.setBody(lblUName + "\n" );
//           dialogLayout.setBody(userName);
//           dialogLayout.setBody(lblPsswrd);
//           dialogLayout.setBody(pswrd);
           
           JFXDialog dialog=new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
           
           JFXButton button=new JFXButton("ok");
           
           //dialogLayout.setBody(button);
           dialog.show();
         
    }

}
