/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taskmanager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import taskmanager.context.Context;
import taskmanager.models.TaskStatusModel;

/**
 *
 * @author Sreehari Rajan
 */
public class MarkAsVerified extends Context {

    public boolean mark(TaskStatusModel task,String task_id) throws SQLException{
        System.out.println("tyry");

        if (task.getEmpverified()!=true){
            DBConnection connect=null;
            PreparedStatement stmt=null;
            Connection con=null;
            try
            {
                                System.out.println(task_id);

                                System.out.println("tyry");

                connect = new DBConnection();
                con = connect.getConnection();
                String sql = "update task_assigned set verified=true where task_id=? and employee_id=?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, task_id);
                stmt.setString(2, task.getEmpid());
                



                stmt.executeUpdate();
                return true;

                
            }
            catch(Exception e)
            {
                       

              System.out.println(e.getLocalizedMessage());
               return false;
            }
            finally {
              connect.closeConnection(con, stmt);
            }
        }
        return false;
    }   
}
