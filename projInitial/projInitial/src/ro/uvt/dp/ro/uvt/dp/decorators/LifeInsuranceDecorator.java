package ro.uvt.dp.decorators;
import ro.uvt.dp.classes.LoggerConfig;
import ro.uvt.dp.interfaces.Operations;

public class LifeInsuranceDecorator extends AccountDecorator {

    private static final double INSURANCE_BONUS_RATE = 0.01;

    public LifeInsuranceDecorator(Operations decoratedAccount) {
        super(decoratedAccount);
    }

    @Override
    public double getTotalAmount() {
        double base = super.getTotalAmount();
        double bonus = base * INSURANCE_BONUS_RATE;
        LoggerConfig.getInstance().logInfo("Applied life insurance bonus: " + bonus);
        return base + bonus;
    }

    @Override
    public String toString() {
        return decoratedAccount.toString() + " + LifeInsuranceDecorator";
    }
}
