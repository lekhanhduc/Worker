/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DataAcess.ManagerDao;
import Repository.FuntionWorker;
import java.util.ArrayList;
import java.util.List;
import model.History;
import model.Worker;

/**
 *
 * @author lekha
 */
public class ManagerController implements FuntionWorker {

    private List<Worker> wk;
    private ArrayList<History> ht;
      @Override
    public int menu() {
        System.out.println("1. Add worker.");
        System.out.println("2. Increase salary for worker.");
        System.out.println("3. Decrease for worker");
        System.out.println("4. Show adjusted salary worker information");
        System.out.println("5. Exist");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkIntLimit(1, 5);
        return choice;
    }

    public ManagerController(List<Worker> wk) {
        this.wk = wk;
    }

    @Override
    public void createWoker(ArrayList<Worker> wk) {
        ManagerDao.Instance().createWorker(wk);
    }

    @Override
    public void IncreaseSalary(ArrayList<Worker> wk) {
        ManagerDao.Instance().changeSalary(wk, ht, 1);

    }

    @Override
    public void Decrease(ArrayList<Worker> wk) {
        ManagerDao.Instance().changeSalary(wk, ht, 0);
    }

    @Override
    public void showInformationEdit(ArrayList<History> lh) {
       ManagerDao.Instance().printListHistory(ht);
    }

    
    
}
