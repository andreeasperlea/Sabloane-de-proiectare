package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.InterestCalculator;
import ro.uvt.dp.classes.LoggerConfig;

public class EURInterestCalculator implements InterestCalculator {
    @Override
    public double calculateInterest(double amount) {
        double interest = 0.1;
        LoggerConfig.getInstance().logInfo("EUR interest calculated: " + interest);
        return interest;
    }
}
