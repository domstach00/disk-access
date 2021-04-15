package projekt.Algorithms.RealTimeAlg;

import projekt.Algorithms.Comperator.Comperator_DeadLine;
import projekt.Order;

import java.util.ArrayList;
import java.util.Collections;

public class EDF {
    int head = createRandom(100);
    int totalMoves = 0;
    ArrayList<Order> ordersRealTime;

    public EDF(ArrayList<Order> ordersRealTime) {
        System.out.println("\n\nEDF Algorithm");
        System.out.println("Starting algorithm for real time tasks");
        this.ordersRealTime = ordersRealTime;


        startAlg();
    }


    private void startAlg(){
        System.out.println("Sorting orders...");
        Collections.sort(ordersRealTime, new Comperator_DeadLine());

        System.out.println("Head start position: "+head);


        for (int i = 0; i < ordersRealTime.size(); i++) {
            servOrder(ordersRealTime.get(i));
        }

        System.out.println("\n\tIn total, head has made "+totalMoves+" moves");
    }

    private void servOrder(Order order){
        System.out.println("\nCurrent head position on disk is: "+head);
        System.out.println("Order to serve: "+order);
        int move = Math.abs(head - order.getPosition());
        head = order.getPosition();
        totalMoves += move;
        System.out.println("Head made "+move+" moves");

    }

    private int createRandom(int x){
        int res = (int)(Math.random()*x);
        if (res==0)
            return 1;
        else
            return res;
    }
}
