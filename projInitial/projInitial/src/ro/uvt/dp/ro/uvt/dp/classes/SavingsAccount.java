package ro.uvt.dp.classes;

import ro.uvt.dp.interfaces.InterestCalculator;

public class SavingsAccount extends Account {

    public static final double SAVINGS_BONUS_RATE = 0.05;

    public SavingsAccount(String numarCont, double suma, InterestCalculator interestCalculator) {
        super(numarCont, suma, interestCalculator);
        LoggerConfig.getInstance().logInfo("Created new SavingsAccount: " + numarCont + " with initial amount " + suma);
    }

    @Override
    public double getInterest() {
        double baseInterest = super.getInterest();
        double bonusInterest = getAmount() * SAVINGS_BONUS_RATE / 100;
        double totalInterest = baseInterest + bonusInterest;

        LoggerConfig.getInstance().logInfo(
                "Calculated savings interest for account " + getAccountNumber() +
                        ": base=" + baseInterest +
                        ", bonus=" + bonusInterest +
                        ", total=" + totalInterest
        );

        return totalInterest;
    }

    @Override
    public String toString() {
        String info = "SavingsAccount{" +
                "code='" + getAccountNumber() + '\'' +
                ", amount=" + getAmount() +
                ", interest=" + getInterest() +
                ", calculator=" + getInterestCalculator().getClass().getSimpleName() +
                '}';
        LoggerConfig.getInstance().logInfo("SavingsAccount info requested: " + info);
        return info;
    }
}
