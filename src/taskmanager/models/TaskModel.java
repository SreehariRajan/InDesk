/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taskmanager.models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Sreehari Rajan
 */
public class TaskModel {
    private SimpleStringProperty id;
            private SimpleStringProperty name;
            private SimpleStringProperty desc;
            
            public TaskModel(String id, String name,String desc) {
                this.id = new SimpleStringProperty(id);
                this.name = new SimpleStringProperty(name);
                this.desc = new SimpleStringProperty(desc);
            }
            
            public String getId() {
                return id.get();
            }

            public void setId(String id) {
                this.id = new SimpleStringProperty(id);
            }

            public String getName() {
                return name.get();
            }

            public void setName(String name) {
                this.name = new SimpleStringProperty(name);
            }
             public String getDesc() {
                return desc.get();
            }

            public void setDesc(String desc) {
                this.desc = new SimpleStringProperty(desc);
            }
}
