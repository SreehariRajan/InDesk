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
import taskmanager.models.EmployeeModel;

/**
 *
 * @author Sreehari Rajan
 */
public class AddEmployeeToTask extends Context {

    public boolean add(EmployeeModel employee,String task_id) throws SQLException{
        System.out.println("tyry");

        if (employee.getAdded()!=true){
            DBConnection connect=null;
            PreparedStatement stmt=null;
            Connection con=null;
            try
            {
                                System.out.println(task_id);

                                System.out.println("tyry");

                connect = new DBConnection();
                con = connect.getConnection();
                String sql = "insert into task_assigned values(?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, employee.getId());
                stmt.setString(2, task_id);

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
