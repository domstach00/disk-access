package projekt.Algorithms;
import projekt.Algorithms.RealTimeAlg.EDF;
import projekt.Order;

import java.util.ArrayList;

public class FCFS {
    int head = createRandom(100);
    int totalMoves = 0;
    ArrayList<Order> orders;
    ArrayList<Order> ordersRealTime = new ArrayList<>();

    public FCFS(ArrayList<Order> orders){
        this.orders = orders;

        startAlg();
    }


    private void startAlg(){

        for (int i = 0; i < orders.size(); i++) {

            if (orders.get(i).isRealTime())
                ordersRealTime.add(orders.get(i));
        }

        if (ordersRealTime.size() != 0)
            new EDF(ordersRealTime);


        System.out.println("\n\nFCFS algorithm");
        System.out.println("Head start position: "+ head);


        for (int i = 0; i < ordersRealTime.size(); i++) {
            orders.remove(ordersRealTime.get(i));
        }


        for (int i = 0; i < orders.size(); i++) {
            servOrder(orders.get(i));
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
