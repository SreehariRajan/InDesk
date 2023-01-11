/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taskmanager.context;

/**
 *
 * @author Sreehari Rajan
 */
public class Context {
     public static String logged_in_user_name=null;
    public static String logged_in_user_id=null;
    public static String logged_in_user_password=null;
    public static Integer logged_in_user_level=null;
    
    
    
    public static String current_task_id=null;
    public static String current_task_name=null;
    public static String current_task_date=null;
    public static String current_task_desc=null;
    
    public static void handleClearContext(){
         logged_in_user_name=null;
     logged_in_user_id=null;
     logged_in_user_password=null;
     logged_in_user_level=null;
    
    
    
     current_task_id=null;
     current_task_name=null;
     current_task_date=null;
     current_task_desc=null;
    }

}
