package projekt.Algorithms.Comperator;

import projekt.Order;

import java.util.Comparator;

public class Comperator_DeadLine implements Comparator<Order> {


    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDeadline() - o2.getDeadline();
    }
}
