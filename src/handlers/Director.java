package handlers;

import common.Type;
import java.security.InvalidParameterException;

public class Director extends Approver {

    @Override
    public void approve(int id, double cost, Type type) {
        if (canApprove(cost, type)) {
            System.out.println("Director approved purchase with id " + id + " that costs " + cost);
            return;
        }

        System.out.println("Purchase with id " + id + " needs approval from higher position than Director.");
        next.approve(id, cost, type);
    }

    @Override
    protected boolean canApprove(double cost, Type type) {
        if(cost <= 0) throw new InvalidParameterException();
        return switch (type) {
            case CONSUMABLES -> cost <= 500;
            case CLERICAL -> cost <= 1000;
            case GADGETS -> cost <= 1500;
            case GAMING -> cost <= 3500;
            case PC -> cost <= 6000;
        };
    }
}
