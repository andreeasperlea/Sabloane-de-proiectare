package ro.uvt.dp.classes;

public class InvestmentAccount extends Account {

    private static final double INVESTMENT_RATE = 0.15;

    public InvestmentAccount(String numarCont, double suma, CurrencyType type) {
        super(numarCont, suma, type);
        LoggerConfig.getInstance().logInfo("Created new InvestmentAccount: " + numarCont + " with initial amount " + suma + " " + type);
    }

    @Override
    public double getInterest() {
        double baseInterest = super.getInterest();
        double investmentInterest = getAmount() * INVESTMENT_RATE / 100;
        double totalInterest = baseInterest + investmentInterest;

        LoggerConfig.getInstance().logInfo(
                "Calculated investment interest for account " + getAccountNumber() +
                ": base=" + baseInterest +
                ", extra=" + investmentInterest +
                ", total=" + totalInterest
        );

        return totalInterest;
    }

    @Override
    public String toString() {
        String info = "InvestmentAccount{" +
                "code='" + getAccountNumber() + '\'' +
                ", amount=" + getAmount() +
                ", currency=" + getCurrencyType() +
                ", dailyInterest=" + getInterest() +
                '}';
        LoggerConfig.getInstance().logInfo("InvestmentAccount info requested: " + info);
        return info;
    }
}
