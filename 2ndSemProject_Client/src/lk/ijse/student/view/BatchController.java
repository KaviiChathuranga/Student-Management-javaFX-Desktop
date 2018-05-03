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
import java.util.ArrayList;
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
import javax.swing.JOptionPane;
import lk.ijse.student.controler.BatchControler;
import lk.ijse.student.controler.Batch_Teacher_Controler;
import lk.ijse.student.controler.SubjectControler;
import lk.ijse.student.dto.BatchDto;
import lk.ijse.student.dto.Batch_SubjectDTO;
import lk.ijse.student.dto.SubjectDto;
import lk.ijse.student.observer.Observer;
import lk.ijse.student.view.model.BatchTable;

/**
 * FXML Controller class
 *
 * @author Kavindu
 */
public class BatchController implements Initializable,Observer {
private int count=0;
private final ObservableList<BatchTable> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView batchTable;
    
    @FXML
    private TableColumn idCOL;

    @FXML
    private TableColumn nameCOL;

    @FXML
    private TableColumn limitCOL;

    @FXML
    private TableColumn yearCOL;

    @FXML
    private TableColumn feeCOL;
    
    @FXML
    private JFXTextField nametxt;

    @FXML
    private JFXTextField limitTxt;

    @FXML
    private JFXTextField yearTxt;

    @FXML
    private JFXTextField feeTxt;

    @FXML
    private JFXButton regBtn;

    private boolean update = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        try {
            UnicastRemoteObject.exportObject(this, 0);
            BatchControler.registerObserver(this);
        } catch (Exception ex) {
            Logger.getLogger(BatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadBatch();
        
    } 
    
    /////////////////////////////////////////////////////////////////////
    
    @FXML
    void regBtnClick(ActionEvent event) {
        int x=0;
      
        String name=nametxt.getText();
        String limit=limitTxt.getText();
        String year=yearTxt.getText();
        double fee=Double.parseDouble(feeTxt.getText());
    try {
        List<BatchDto>list=BatchControler.getAll();
        for (BatchDto batchDto : list) {
           
             x=Integer.parseInt(batchDto.getBid()+1);
          
        }
         List<Batch_SubjectDTO>batch_teacher_DtoList=new ArrayList<>();
         
        BatchDto batchDto=new BatchDto(""+x,name,limit,year,fee);
        
        List<SubjectDto>subjectList=SubjectControler.getAll();
        
    

            boolean b=Batch_Teacher_Controler.addBatch(batchDto,subjectList);
            if (b) {
                JOptionPane.showMessageDialog(null,"A Batch Saving  Finished");
                    nametxt.setText("");
                    limitTxt.setText("");
                    yearTxt.setText("");
                    feeTxt.setText("");
                
            }else{
                JOptionPane.showMessageDialog(null,"A Batch Saving  Failed !","",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(BatchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() throws Exception {
       loadBatch();
    }

    private void loadBatch() {
         for (int i = 0; i < batchTable.getItems().size(); i++) {
            batchTable.getItems().clear();
        }

        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("Name"));
        limitCOL.setCellValueFactory(new PropertyValueFactory<>("Student_limit"));
        yearCOL.setCellValueFactory(new PropertyValueFactory<>("Year"));
        feeCOL.setCellValueFactory(new PropertyValueFactory<>("fee"));
        
        batchTable.setItems(data);
        List<BatchDto> list = null;
        try {
            list = BatchControler.getAll();
            
        } catch (Exception ex) {
            Logger.getLogger(BatchControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (BatchDto y : list) {
            
            BatchTable x = new BatchTable();
            
            x.setId(y.getBid());
            x.setName(y.getName());
            x.setlimit(y.getLimit());
            x.setYear(y.getYear());
            x.setFee(y.getFee());
            
            data.add(x);
        }
    

    }
///////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
}
