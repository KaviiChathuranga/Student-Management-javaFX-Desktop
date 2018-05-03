/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import lk.ijse.student.controler.AttnendanceControler;
import lk.ijse.student.controler.BatchControler;
import lk.ijse.student.controler.ExamControler;
import lk.ijse.student.controler.RegistrationControler;
import lk.ijse.student.controler.ResultControler;
import lk.ijse.student.controler.SubjectControler;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.ExamDto;
import lk.ijse.student.dto.RegistrationDto;
import lk.ijse.student.dto.ResultDto;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.view.model.ResultTable;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class ResultController implements Initializable,Observer {
private String rid="";
private final ObservableList<ResultTable> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private JFXTextField examIdTxt;

    @FXML
    private JFXTextField batchtxt;

    @FXML
    private JFXTextField subjectTxt;

    @FXML
    private JFXTextField dateTxt;

    @FXML
    private JFXTextField sidTxt;

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private JFXTextField NicTxt;

    @FXML
    private JFXTextField resultTxt;

    @FXML
    private JFXButton inputBtn;

    @FXML
    private TableView resultTable;

    @FXML
    private TableColumn eidCOL;

    @FXML
    private TableColumn dateCOL;

    @FXML
    private TableColumn ridCOL;

    @FXML
    private TableColumn nameCOL;

    @FXML
    private TableColumn nicCOL;

    @FXML
    private TableColumn marksCOL;

    @FXML
    private TableColumn gradeCOL;
    
    
    @FXML
    private JFXButton updateBtn;
    
    /////////////////////////////////////////////////////////////
    @FXML
    void inputBtnClick(ActionEvent event) { 
        
        try {
            
            int id=0;
            
            List<ResultDto>res=ResultControler.getAll();
            for (ResultDto re : res) {
                id=(Integer.parseInt(re.getResId())+1);
            }
            
            
                        String eid=examIdTxt.getText();
                        String sid=sidTxt.getText();
                        String date=dateTxt.getText();
            try {
                BatchDto batchDto=BatchControler.searchNameOfBatch(batchtxt.getText());
                int result=Integer.parseInt(resultTxt.getText());
                SubjectDto subjectDto=SubjectControler.searchSubjectName(subjectTxt.getText());
                
                List<ResultDto>rds=ResultControler.getAll();
                for (ResultDto rd : rds) {
                    id=Integer.parseInt(rd.getResId())+1;
                }
                
                ResultDto resultDto=new ResultDto(""+id,eid,batchDto.getName(),date,subjectDto.getName(),sid,nameTxt.getText(),NicTxt.getText(),result);
                boolean b=ResultControler.addResult(resultDto);
                if (b) {
                    JOptionPane.showMessageDialog(null,"Student Result Input Success");
                        examIdTxt.setText("");
                        sidTxt.setText("");
                        dateTxt.setText("");
                        batchtxt.setText("");
                        subjectTxt.setText("");
                        resultTxt.setText("");
                        nameTxt.setText("");
                        NicTxt.setText("");
                        
                for (int i = 0; i < resultTable.getItems().size(); i++) {
                        resultTable.getItems().clear();
                }
                loadResult();
                
                }else{
                    JOptionPane.showMessageDialog(null,"Sorry ! This Result can't input this time","try again",JOptionPane.WARNING_MESSAGE);
                }
                
            } catch (Exception ex) {
                Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            
        } catch (Exception ex) {
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }
    /////////////////////////////////////////////////////////////
    
    @FXML
    void sidAction(ActionEvent event) {
        try {
            String sid=sidTxt.getText();
            
            ResultDto resultDto=ResultControler.searchResult(examIdTxt.getText(),sid);
            
            if (resultDto!=null) {
                rid=resultDto.getResId();
                resultTxt.setText(""+resultDto.getMarks()); 
                nameTxt.setText(""+resultDto.getName()); 
                NicTxt.setText(""+resultDto.getNic());  
            }else{
                 rid="";   
            try {
                RegistrationDto r=RegistrationControler.searcherAll(sid);
                if (r==null) {
                    JOptionPane.showMessageDialog(null,"Sorry ! This Student Id Not Registerd Yet");
                    nameTxt.setText("");
                    NicTxt.setText("");
                    resultTxt.setText(""); 
                }else{
                    nameTxt.setText(r.getStudentDto().getName());
                    NicTxt.setText(r.getStudentDto().getNic());
                    resultTxt.setText(""); 
                }
            } catch (Exception ex) {
                Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        } catch (Exception ex) {
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /////////////////////////////////////////////////////////////
    
    @FXML
    void eidAction(ActionEvent event) {
        String eid=examIdTxt.getText();
        try {
            ExamDto examDto=ExamControler.searchExam(eid);
           
            if (examDto==null) {
                JOptionPane.showMessageDialog(null,"Sorry ! this is a Invalid Exam Id");
                 batchtxt.setText("");
                 subjectTxt.setText("");
                 dateTxt.setText("");
            }else{
                batchtxt.setText(examDto.getBatchDto().getName());
                subjectTxt.setText(examDto.getSubjectDto().getName());
                dateTxt.setText(examDto.getDate());
                
            }
        } catch (Exception ex) {
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /////////////////////////////////////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            ResultControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadResult();
        loadImg();
    }    

    @Override
    public void update() throws Exception {
       loadResult();
    }

    private void loadResult() {
        
         for (int i = 0; i < resultTable.getItems().size(); i++) {
                        resultTable.getItems().clear();
                }
         
       eidCOL.setCellValueFactory(new PropertyValueFactory<>("EID"));
       dateCOL.setCellValueFactory(new PropertyValueFactory<>("Date"));
       ridCOL.setCellValueFactory(new PropertyValueFactory<>("RID"));
       nameCOL.setCellValueFactory(new PropertyValueFactory<>("Student"));
       nicCOL.setCellValueFactory(new PropertyValueFactory<>("Nic"));
       marksCOL.setCellValueFactory(new PropertyValueFactory<>("Marks"));
       
       resultTable.setItems(data);
       List<ResultDto>list=null;
       
    try {
        List<ResultDto>resultDtos=ResultControler.getAll();
        for (ResultDto resultDto : resultDtos) {
              ResultTable examTable=new ResultTable();
                
                examTable.setEid(resultDto.getEid());
                examTable.setDate(resultDto.getDate());
                examTable.setRID(resultDto.getSid());
                examTable.setStudent(resultDto.getName());
                examTable.setNIC(resultDto.getNic());
                examTable.setMarks(""+resultDto.getMarks());
                
                data.add(examTable);
        }
        
    } catch (Exception ex) {
        Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }

    private void loadImg() {
        Image regImage=new Image("lk/ijse/student/assets/Add User Male_40px.png");
       // pane.setGraphic(new ImageView(regImage));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
      @FXML
    void updateBtnClick(ActionEvent event) {
               
                String sid=sidTxt.getText();
                String name=(nameTxt.getText()); 
                int result= Integer.parseInt(resultTxt.getText()); 
                String nic=NicTxt.getText(); 
                String eid=examIdTxt.getText();
                String batch=batchtxt.getText();
                String date=dateTxt.getText();
                String sub=subjectTxt.getText();
                
                ResultDto resultDto=new ResultDto(""+0,rid, batch, date, sub, sid, name, nic, result);
    try {
        boolean b=ResultControler.updateResult(resultDto);
        if (b) {
            JOptionPane.showMessageDialog(null,"the new result updated");
                        examIdTxt.setText("");
                        sidTxt.setText("");
                        dateTxt.setText("");
                        batchtxt.setText("");
                        subjectTxt.setText("");
                        resultTxt.setText("");
                        nameTxt.setText("");
                        NicTxt.setText("");
        }else{
            JOptionPane.showMessageDialog(null,"Sorry ! the result could not be update ","try again",JOptionPane.WARNING_MESSAGE);
        }
    } catch (Exception ex) {
        Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
}
