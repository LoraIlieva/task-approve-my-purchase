package handlers;

import common.Type;
import java.security.InvalidParameterException;


public class VicePresident extends Approver{
    @Override
    public void approve(int id, double cost, Type type) {

        if (canApprove(cost, type)) {
            System.out.println("Vice president approved purchase with id " + id + " that costs " + cost);
            return;
        }

        System.out.println("Purchase with id " + id + " needs approval from higher position than Vice president.");
        next.approve(id, cost, type);
    }

    @Override
    protected boolean canApprove(double cost, Type type) {
        if(cost <= 0) throw new InvalidParameterException();
        return switch (type) {
            case CONSUMABLES -> cost <= 700;
            case CLERICAL -> cost <= 1500;
            case GADGETS -> cost <= 2000;
            case GAMING -> cost <= 4500;
            case PC -> cost <= 6500;
        };
    }
}
