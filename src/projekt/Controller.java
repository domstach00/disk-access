package projekt;

import projekt.Algorithms.*;

import java.util.ArrayList;

public class Controller {
    ArrayList<Order> orders = new ArrayList<>();
    ArrayList<Order> ordersSSTF = new ArrayList<>();
    ArrayList<Order> ordersFCFS = new ArrayList<>();
    ArrayList<Order> orderSCAN = new ArrayList<>();
    ArrayList<Order> ordersCSCAN = new ArrayList<>();

    public Controller() {
        createOrders(); //zadania posiadaja losowe parametry
        showOrders();

        fcfsAlg();
        sstfAlg();
        scanAlg();
        cscanAlg();
    }

    public void fcfsAlg(){
        System.out.println("\n\n\n===================================================");
        copyList(ordersFCFS);
        new FCFS(ordersFCFS);
    }
    public void sstfAlg(){
        System.out.println("\n\n\n===================================================");
        copyList(ordersSSTF);
        new SSTF(ordersSSTF);
    }
    public void scanAlg(){
        System.out.println("\n\n\n===================================================");
        copyList(orderSCAN);
        new SCAN(orderSCAN);
    }
    public void cscanAlg(){
        System.out.println("\n\n\n===================================================");
        copyList(ordersCSCAN);
        new CSCAN(ordersCSCAN);
    }



    public void createOrders(){
        int orderNumbers = (int) (Math.random()*100);
        int n = 100;

        //prawdopodobienstwo wystapienia zadania czasu rzeczywistego ustawione jest na 20%

        for (int i = 0; i < n; i++) { //mozna zmienic na losowa ilosc zlecen zamieniajac n na orderNumbers
            orders.add(new Order(i+1));
        }

    }

    public void showOrders(){
        System.out.println("Orders size: "+orders.size());

        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }

    public void copyList(ArrayList<Order> list){

        for (int i = 0; i < orders.size(); i++) {
            list.add(orders.get(i));
        }
    }

}
