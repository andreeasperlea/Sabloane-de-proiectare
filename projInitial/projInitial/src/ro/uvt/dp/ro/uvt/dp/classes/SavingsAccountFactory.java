package ro.uvt.dp.classes;

public class SavingsAccountFactory extends AccountFactory {

    @Override
    public Account createAccount(String accountNumber, double initialAmount, CurrencyType currencyType) {
        LoggerConfig.getInstance().logInfo("Creating savings account " + accountNumber);
        return new SavingsAccount(accountNumber, initialAmount, currencyType);
    }
}
