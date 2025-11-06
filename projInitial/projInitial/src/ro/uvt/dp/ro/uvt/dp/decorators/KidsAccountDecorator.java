package ro.uvt.dp.decorators;
import ro.uvt.dp.classes.LoggerConfig;
import ro.uvt.dp.interfaces.Operations;

public class KidsAccountDecorator extends AccountDecorator {

    private static final double INTEREST_REDUCTION_RATE = 0.5;

    public KidsAccountDecorator(Operations decoratedAccount) {
        super(decoratedAccount);
    }

    @Override
    public double getInterest() {
        double baseInterest = super.getInterest();
        double reduced = baseInterest * INTEREST_REDUCTION_RATE;
        LoggerConfig.getInstance().logInfo("Applied kids account reduced interest: " + reduced);
        return reduced;
    }

    @Override
    public String toString() {
        return decoratedAccount.toString() + " + KidsAccountDecorator";
    }
}
