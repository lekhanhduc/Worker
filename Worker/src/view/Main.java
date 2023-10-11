/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ManagerController;
import controller.Validation;
import java.util.ArrayList;
import java.util.List;
import model.History;
import model.Worker;

/**
 *
 * @author lekha
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Worker> lw = new ArrayList<>();
        ArrayList<History> lh = new ArrayList<>();
        ManagerController mng = new ManagerController(lw);     
       while(true){
           int choice = mng.menu();
           switch(choice){
               case 1: 
                   mng.createWoker(lw);
                   break;
               case 2:
                   mng.IncreaseSalary(lw);
                   break;
               case 3:
                   mng.Decrease(lw);
                   break;
               case 4: 
                   mng.showInformationEdit(lh);
                   break;
               case 5:
                   return;
           }
       }
       
    }
        
}

