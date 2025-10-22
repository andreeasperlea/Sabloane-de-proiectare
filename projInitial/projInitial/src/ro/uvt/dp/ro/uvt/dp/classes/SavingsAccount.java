package ro.uvt.dp.classes;

public class SavingsAccount extends Account {

    public static final double SAVINGS_BONUS_RATE = 0.05;

    public SavingsAccount(String numarCont, double suma, CurrencyType type) {
        super(numarCont, suma, type);
        LoggerConfig.logInfo("Created new SavingsAccount: " + numarCont + " with initial amount " + suma + " " + type);
    }

    @Override
    public double getInterest() {
        double baseInterest = super.getInterest();
        double bonusInterest = getAmount() * SAVINGS_BONUS_RATE / 100;
        double totalInterest = baseInterest + bonusInterest;

        LoggerConfig.logInfo(
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
                ", currency=" + getCurrencyType() +
                ", dailyInterest=" + getInterest() +
                '}';
        LoggerConfig.logInfo("SavingsAccount info requested: " + info);
        return info;
    }
}
