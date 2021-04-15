package projekt.Algorithms;

import projekt.Algorithms.RealTimeAlg.FDSCAN;
import projekt.Order;

import java.util.ArrayList;

public class CSCAN {
    int head = createRandom(100);
    int totalMoves = 0;
    ArrayList<Order> orders;
    ArrayList<Order> ordersRealTime = new ArrayList<>();
    ArrayList<Order> leftOrders = new ArrayList<>();
    ArrayList<Order> rightOrders = new ArrayList<>();

    public CSCAN(ArrayList<Order> orders) {
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


//        System.out.println("\n\nC-SCAN algorithm");
//        System.out.println("Head start position: "+head);


        for (int i = 0; i < ordersRealTime.size(); i++) {
            orders.remove(ordersRealTime.get(i));
        }

        sortOrders(); // glowica zaczyna sie przemieszczac od akutalnej pozycji do lewej strony (do pozdycji 1) a nastepnie od prawej strony

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
        // glowica przechodzi przechodzi na jeden skraj dysku a nastepnie na drugi skraj

        if (rightOrders != null){

//            System.out.println("\nHead moves to start and moves to the end");
            if (leftOrders.size() == 0) //przejscie glowicy na pierwszy skraj dysku
                if (head != 1){
                    int move = head -1;
//                    System.out.println("Head made "+move+" moves");
                    totalMoves += move;
                }
                else;
            else{
                int move = leftOrders.get(leftOrders.size()-1).getPosition() -1;
//                System.out.println("Head made "+move+" moves");
                totalMoves += move;
            }


            totalMoves += 99; //przejscie na drugi skraj dysku
            head = 100;
        }

    }

    private void servOrder(Order order) {
//        System.out.println("\nCurrent head position on disk is: "+head);
//        System.out.println("Order to serve: "+order);
        int move = Math.abs(head - order.getPosition());
        head = order.getPosition();
        totalMoves += move;
//        System.out.println("Head made "+move+" moves");

    }


    private void sortOrders(){

        for (int i = head; i >= 1 ; i--) {

            for (int j = 0; j < orders.size(); j++) {
                if (orders.get(j).getPosition() == i)
                    leftOrders.add(orders.get(j));
            }
        }

        for (int i = 100; i >= head+1 ; i--) {

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
