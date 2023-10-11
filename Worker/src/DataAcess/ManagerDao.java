/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAcess;

import java.util.ArrayList;
import model.Worker;
import controller.Validation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import model.History;

/**
 *
 * @author lekha
 */
public class ManagerDao {

    private static ManagerDao instance = null;

    public static ManagerDao Instance() {
        if (instance == null) {
            synchronized (ManagerDao.class) {
                if (instance == null) {
                    instance = new ManagerDao();
                }
            }
        }
        return instance;
    }
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

    //allow user add worker
    public  void createWorker(ArrayList<Worker> lw) {
        System.out.print("Enter code: ");
        String id = Validation.checkInputString();
//        if (!Validate.checkIdExist(lw, id)) {
//            System.err.println("Code(id) must be existed in DB.");
//            return;
//        }
        System.out.print("Enter name: ");
        String name = Validation.checkInputString();
        System.out.print("Enter age: ");
        int age = Validation.checkIntLimit(18, 50);
        System.out.print("Enter salary: ");
        int salary = Validation.checkInputSalary();
        System.out.print("Enter work location: ");
        String workLocation = Validation.checkInputString();
        if (!Validation.checkWorkerExist(lw, id, name, age, salary, workLocation)) {
            System.err.println("Duplicate.");
        } else {
            lw.add(new Worker(id, name, age, salary, workLocation));
            System.err.println("Add success.");
        }
    }

    //allow user increase salary for user
    public  void changeSalary(ArrayList<Worker> lw, ArrayList<History> lh, int status) {
        if (lw.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.print("Enter code: ");
        String id = Validation.checkInputString();
        Worker worker = getWorkerByCode(lw, id);
        if (worker == null) {
            System.err.println("Not exist worker.");
        } else {
            double salaryCurrent = worker.getSalary();
            int salaryUpdate;
            //check user want to update salary
            if (status == 1) {
                System.out.print("Enter salary: ");
                //loop until user input salary update > salary current
                while (true) {     
                    salaryUpdate = Validation.checkInputSalary();
                    //check user input salary update > salary current
                    if (salaryUpdate <= salaryCurrent) {
                        System.err.println("Must be greater than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                lh.add(new History("UP", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            } else {
                System.out.print("Enter salary: ");
                //loop until user input salary update < salary current
                while (true) {
                    salaryUpdate = Validation.checkInputSalary();
                    //check user input salary update < salary current
                    if (salaryUpdate >= salaryCurrent) {
                        System.err.println("Must be smaller than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                lh.add(new History("DOWN", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            }
            worker.setSalary(salaryUpdate);
            System.err.println("Update success");
        }
    }

    //allow user print history
    public  void printListHistory(ArrayList<History> lh) {
        if (lh.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.printf("%-5s%-15s%-5s%-10s%-10s%-20s\n", "Code", "Name", "Age",
                "Salary", "Status", "Date");
        Collections.sort(lh);
        //print history from first to last list
        for (History history : lh) {
            printHistory(history);
        }
    }

    //get worker by code
    public  Worker getWorkerByCode(ArrayList<Worker> lw, String id) {
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return worker;
            }
        }
        return null;
    }

    //get current date 
    public  String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    //print history
    public  void printHistory(History history) {
        System.out.printf("%-5s%-15s%-5d%-10d%-10s%-20s\n", history.getId(),
                history.getName(), history.getAge(), history.getSalary(),
                history.getStatus(), history.getDate());
    }
}