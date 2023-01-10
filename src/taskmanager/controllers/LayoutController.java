/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package taskmanager.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author Sreehari Rajan
 */
public class LayoutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private ScrollPane content_div;

    @FXML
    private void handleChangeView(ActionEvent event) {
        try {
            String menuItemID = ((Button) event.getSource()).getId();
            System.out.println(getClass().getResource("../resources/views/"+menuItemID + ".fxml"));
            
          
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/views/"+menuItemID + ".fxml"));
//            loader.setController(this);

            content_div.setCenterShape(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
}
