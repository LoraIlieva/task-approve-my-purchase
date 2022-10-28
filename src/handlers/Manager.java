package handlers;

import common.Type;
import java.security.InvalidParameterException;


public class Manager extends Approver {
    @Override
    public void approve(int id, double cost, Type type) {
        if (canApprove(cost, type)) {
            System.out.println("Manager approved purchase with id " + id + " that costs " + cost);
            return;
        }

        System.out.println("Purchase with id " + id + " needs approval from higher position than Manager.");
        next.approve(id, cost, type);
    }

    @Override
    protected boolean canApprove(double cost, Type type) {
        if(cost <= 0) throw new InvalidParameterException();

        return switch (type) {
            case CONSUMABLES -> cost <= 300;
            case CLERICAL -> cost <= 500;
            case GADGETS -> cost <= 1000;
            case GAMING -> cost <= 3000;
            case PC -> cost <= 5000;
        };
    }
}
