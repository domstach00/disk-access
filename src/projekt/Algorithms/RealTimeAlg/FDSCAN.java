package projekt.Algorithms.RealTimeAlg;


import projekt.Order;

import java.util.ArrayList;

public class FDSCAN {
    int head = createRandom(100);
    int totalMoves = 0;
    ArrayList<Order> ordersRealTime;
    ArrayList<Order> leftOrders = new ArrayList<>();
    ArrayList<Order> rightOrders = new ArrayList<>();



    public FDSCAN(ArrayList<Order> ordersRealTime) {
        this.ordersRealTime = ordersRealTime;

        startAlg();
    }



    private void startAlg(){

        System.out.println("\n\nFDSCAN algorithm");
        System.out.println("Starting algorithm for real time tasks");
        System.out.println("Head start position: "+head);

        sortOrders(); // glowica zaczyna sie przemieszczac w kierunku najblizszego zlecenia  a kolenie do skraju dysku, a nastepnie w druga strone


        System.out.println("\n\tIn total, head has made "+totalMoves+" moves");


    }



    private void sortOrders(){

        for (int i = head; i >= 1 ; i--) { // podzial na prawy i lewy kierunek glowicy

            for (int j = 0; j < ordersRealTime.size(); j++) {
                if (ordersRealTime.get(j).getPosition() == i)
                    leftOrders.add(ordersRealTime.get(j));
            }
        }

        for (int i = head+1; i <= 100 ; i++) {

            for (int j = 0; j < ordersRealTime.size(); j++) {
                if (ordersRealTime.get(j).getPosition() == i)
                    rightOrders.add(ordersRealTime.get(j));
            }
        }

        // sprawdzanie z ktorej strony glowicy jest najmniejszy deadLine
        if (leftOrders.size() == 0)
            prepereServe(rightOrders, leftOrders);
        else if (rightOrders.size() == 0)
            prepereServe(leftOrders, rightOrders);
        else if (findMin(leftOrders).getDeadline() - findMin(rightOrders).getDeadline()  > 0) // sprawdzamy z ktorej strony jest najnizszy deadline
            prepereServe(rightOrders, leftOrders);
        else
            prepereServe(leftOrders, rightOrders);


    }



    private Order findMin(ArrayList<Order> list){
        Order min = list.get(0);


        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getDeadline() < min.getDeadline())
                min = list.get(i);

        }
        return min;
    }



    private void prepereServe(ArrayList<Order> firstWay, ArrayList<Order> secondWay){

        for (int i = 0; i < firstWay.size(); i++) {
            servOrder(firstWay.get(i));
        }

        if (secondWay.size() != 0)
            change(firstWay);

        for (int i = 0; i < secondWay.size(); i++) {
            servOrder(secondWay.get(i));
        }

    }



    private void change(ArrayList<Order> firstWay) {
        // od ostatniego zgloszenia glowica musi przemiescic sie do bloku pierwszego
        System.out.println("\nChange of direction of movement");


        if (firstWay.size()==0) {
            if (firstWay == leftOrders && head != 1) {
                System.out.println("Head made "+head+" moves");
                totalMoves += head;
                head = 1;
            }
            else {
                System.out.println("Head made "+(100-head)+" moves");
                totalMoves += (100 - head);
                head = 100;
            }
        }
        else {

            if (firstWay == leftOrders) {
                int move = firstWay.get(firstWay.size()-1).getPosition() -1;
                System.out.println("Head made "+move+" moves");
                totalMoves += move;
                head =1;
            }
            else {
                int move = (100 - firstWay.get(firstWay.size()-1).getPosition());
                System.out.println("Head made "+move+" moves");
                totalMoves +=move;
                head =100;

            }

        }

    }



    private void servOrder(Order order) {

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
