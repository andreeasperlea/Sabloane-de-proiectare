package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.InterestCalculator;
import ro.uvt.dp.classes.LoggerConfig;

public class RONInterestCalculator implements InterestCalculator {
    @Override
    public double calculateInterest(double amount) {
        double interest = (amount < 500) ? 0.3 : 0.8;
        LoggerConfig.getInstance().logInfo("RON interest calculated: " + interest);
        return interest;
    }
}
