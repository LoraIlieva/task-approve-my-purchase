package handlers;

import common.Type;
import java.security.InvalidParameterException;


public class President extends Approver{
    @Override
    public void approve(int id, double cost, Type type) {

        if (canApprove(cost, type)) {
            System.out.println("President approved purchase with id " + id + " that costs " + cost);
            return;
        }

        System.out.println("Purchase with id " + id + " needs approval from higher position than President.");
        next.approve(id, cost, type);
    }

    @Override
    protected boolean canApprove(double cost, Type type) {
        if(cost <= 0) throw new InvalidParameterException();
        return switch (type) {
            case CONSUMABLES -> cost <= 1000;
            case CLERICAL -> cost <= 2000;
            case GADGETS -> cost <= 3000;
            case GAMING -> cost <= 5000;
            case PC -> cost <= 8000;
        };
    }
}
