/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.student.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import lk.ijse.student.controler.AttnendanceControler;
import lk.ijse.student.controler.BatchControler;
import lk.ijse.student.controler.Batch_Teacher_Controler;
import lk.ijse.student.controler.ExamControler;
import lk.ijse.student.controler.SubjectControler;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.Batch_SubjectDTO;
import lk.ijse.student.dto.ExamDto;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.view.model.ExamTable;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class ExamController implements Initializable,Observer {
//private String eid="";
    private final ObservableList<ExamTable> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
     @FXML
    private Label exCountLbl;

    @FXML
    private JFXTextField examIdTxt;

    @FXML
    private JFXComboBox batchBox;

    @FXML
    private JFXComboBox subjectBox;

    @FXML
    private JFXDatePicker dateTxt;

    @FXML
    private JFXButton examBtn;

    @FXML
    private JFXTimePicker timeTxt;

    @FXML
    private JFXComboBox hallBox;

    @FXML
    private TableView examTable;

    @FXML
    private TableColumn eidCOL;

    @FXML
    private TableColumn batchCOL;

    @FXML
    private TableColumn subCOL;

    @FXML
    private TableColumn dateCOL;

    @FXML
    private TableColumn timeCOL;

    @FXML
    private TableColumn hallCOL;

    @FXML
    private TableColumn typeCOL;

    @FXML
    private JFXComboBox typeCMB;
    
    ////////////////////////////////////////////////////////////////////////
    
    @FXML
    void examBtnClick(ActionEvent event) {
        int x=0;
        String eid=examIdTxt.getText();
        String batch=(String) batchBox.getValue();
        String sub=(String) subjectBox.getValue();
        String hall=(String) hallBox.getValue();
        String type=(String) typeCMB.getValue();
        String date=dateTxt.getValue().toString();
        String time=timeTxt.getValue().toString();
        
         try {
             BatchDto batchDto=BatchControler.searchNameOfBatch(batch);
             SubjectDto subjectDto=SubjectControler.searchSubjectName(sub);
             
             ExamDto examDto=new ExamDto(eid,batchDto,subjectDto,hall,type,date,time);
             boolean b=ExamControler.addExam(examDto);
             if (b) {
                 JOptionPane.showMessageDialog(null,"This Exam Saving Finished");
                 examIdTxt.setText("");
                 
                  for (int i = 0; i < examTable.getItems().size(); i++) {
                        examTable.getItems().clear();
                  }
                  loadExam();
        
           
             }else{
                 JOptionPane.showMessageDialog(null,"Sorry ! this exam can't save now","Try Again",JOptionPane.WARNING_MESSAGE);
             }
         } catch (Exception ex) {
             Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }

    
    ////////////////////////////////////////////////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
            UnicastRemoteObject.exportObject(this, 0);
            ExamControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      loadBatch();
      loadExam();
      loadHall();
      loadType();
    }  
    
    ///////////////////////////////////////////////////////////////////////

    @Override
    public void update() throws Exception {
        loadExam();
    }

    private void loadExam() {
        
         for (int i = 0; i < examTable.getItems().size(); i++) {
            examTable.getItems().clear();
        }
        
       eidCOL.setCellValueFactory(new PropertyValueFactory<>("eid"));
       hallCOL.setCellValueFactory(new PropertyValueFactory<>("hall"));
       timeCOL.setCellValueFactory(new PropertyValueFactory<>("time"));
       batchCOL.setCellValueFactory(new PropertyValueFactory<>("batch"));
       subCOL.setCellValueFactory(new PropertyValueFactory<>("subject"));
       dateCOL.setCellValueFactory(new PropertyValueFactory<>("date"));
       typeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
       
        examTable.setItems(data);
        List<ExamDto>list=null;
        
        
        try {
            list=ExamControler.getAll();
            for (ExamDto examDto : list) {
                ExamTable examTable=new ExamTable();
                
                examTable.setEid(examDto.getEid());
                examTable.setBatch(examDto.getBatchDto().getName());
                examTable.setSubject(examDto.getSubjectDto().getName());
                examTable.setDate(examDto.getDate());
                examTable.setHall(examDto.getHall());
                examTable.setTime(examDto.getTime());
                examTable.setType(examDto.getType()); 
                data.add(examTable);
            }
        } catch (Exception ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadBatch() {
         try {
            List<BatchDto>list=BatchControler.getAll();
            for (BatchDto batchDto : list) {
                
                batchBox.getItems().addAll(batchDto.getName());
                                        
            }
        } catch (Exception ex) {
            Logger.getLogger(TeacherRegController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
    
     @FXML
    void batchAction(ActionEvent event) {
         String batch=""+batchBox.getValue();
        try {
            BatchDto batchDto=BatchControler.searchNameOfBatch(batch);
            List<Batch_SubjectDTO>list=Batch_Teacher_Controler.search(batchDto.getBid());
            
            
            if (list!=null) {
                
            for (Batch_SubjectDTO batch_SubjectDTO : list) {
                
                subjectBox.getItems().addAll(batch_SubjectDTO.getSubject().getName());
                                        
            }
            }
        } catch (Exception ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadHall() {
        hallBox.getItems().addAll("1");
        hallBox.getItems().addAll("2");
        hallBox.getItems().addAll("3");
        hallBox.getItems().addAll("4");
        hallBox.getItems().addAll("5");
    }

    private void loadType() {
        typeCMB.getItems().addAll("Open Book Test");
        typeCMB.getItems().addAll("Practical");
        typeCMB.getItems().addAll("Theory");
        typeCMB.getItems().addAll("Revision");
        typeCMB.getItems().addAll("Not Import");
    }

    
}

//
// for (int i = 0; i < myTable.getItems().size(); i++) {
//            myTable.getItems().clear();
//        }
//        