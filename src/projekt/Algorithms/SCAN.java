package projekt.Algorithms;

import projekt.Algorithms.RealTimeAlg.FDSCAN;
import projekt.Order;

import java.util.ArrayList;

public class SCAN {
    int head = createRandom(100);
    int totalMoves = 0;
    ArrayList<Order> orders;
    ArrayList<Order> ordersRealTime = new ArrayList<>();
    ArrayList<Order> leftOrders = new ArrayList<>();
    ArrayList<Order> rightOrders = new ArrayList<>();

    public SCAN(ArrayList<Order> orders) {
        this.orders = orders;

        startAlg();
    }

    private void startAlg(){


        for (int i = 0; i < orders.size(); i++) {

            if (orders.get(i).isRealTime()) {
                ordersRealTime.add(orders.get(i));
            }
        }

        if (ordersRealTime.size() != 0) {
            new FDSCAN(ordersRealTime);
        }


        System.out.println("\n\nSCAN algorithm");
        System.out.println("Head start position: "+head);


        for (int i = 0; i < ordersRealTime.size(); i++) {
            orders.remove(ordersRealTime.get(i));
        }

        sortOrders(); // glowica zaczyna sie przemieszczac od aktualnej pozycji do lewej strony (do pozdycji 1) a nastepnie w druga strone

        for (int i = 0; i < leftOrders.size(); i++) {
            servOrder(leftOrders.get(i));
        }

        change(); // zmiana kierunku


        for (int i = 0; i < rightOrders.size(); i++) {
            servOrder(rightOrders.get(i));
        }



        System.out.println("\n\tIn total, head has made "+totalMoves+" moves");

    }

    private void change() {
        // od ostatniego zgloszenia glowica musi przemiescic sie do bloku pierwszego

        if (leftOrders.size()==0) {
            if (head != 1){
                int move = head -1;
                System.out.println("Head made "+move+" moves");
                totalMoves += move;
            }
        }
        else{
            int move = leftOrders.get(leftOrders.size()-1).getPosition();
            System.out.println("Head made "+move+" moves");
            totalMoves += move;
        }
        head = 1;
    }

    private void servOrder(Order order) {
        System.out.println("\nCurrent head position on disk is: "+head);
        System.out.println("Order to serve: "+order);
        int move = Math.abs(head - order.getPosition());
        head = order.getPosition();
        totalMoves += move;
        System.out.println("Head made "+move+" moves");

    }


    private void sortOrders(){

        for (int i = head; i >= 1 ; i--) {

            for (int j = 0; j < orders.size(); j++) {
                if (orders.get(j).getPosition() == i)
                    leftOrders.add(orders.get(j));
            }
        }

        for (int i = head+1; i <= 100 ; i++) {

            for (int j = 0; j < orders.size(); j++) {
                if (orders.get(j).getPosition() == i)
                    rightOrders.add(orders.get(j));
            }
        }
    }

    private int createRandom(int x){
        int res = (int)(Math.random()*x);
        if (res==0)
            return 1;
        else
            return res;
    }

}
