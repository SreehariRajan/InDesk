/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taskmanager.controllers;

/**
 *
 * @author Sreehari Rajan
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import taskmanager.utils.DBConnection;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import taskmanager.context.Context;
/**
 *
 * @author Sreehari Rajan
 */
public class LoginController extends Context {
        @FXML
        private TextField password;
        @FXML
        private TextField user_id;
        @FXML
        private Label message_label;
    
       private Stage stage;
       private Scene scene;
       private Parent root;
       
       
       
    /**
     *
     * @param event
     */
    public void login(ActionEvent event) throws SQLException {
            DBConnection connect=null;
            PreparedStatement stmt=null;
            Connection con=null;
            boolean found=true;
            try
            {
                connect = new DBConnection();
                con = connect.getConnection();
                String sql = "select * from employees where id=? ";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, user_id.getText());
                ResultSet rs = stmt.executeQuery();
                if (rs.next())
                {
                  if (password.getText().equals(rs.getString(4))){
                        logged_in_user_id=rs.getString(1);
                        logged_in_user_name=rs.getString(2);
                        logged_in_user_level=rs.getString(3);
                        logged_in_user_password=rs.getString(4);

                        message_label.setText("");
                        Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_1.fxml")));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                  }else{
                        message_label.setText("Invalid password");
                  }
                }
                else
                {
                  message_label.setText("User with the ID not found");
                }
            }
            catch(Exception e)
            {
              System.out.println(e.getLocalizedMessage());
            }
            finally {
              connect.closeConnection(con, stmt);
            }

       }
       
       
}

