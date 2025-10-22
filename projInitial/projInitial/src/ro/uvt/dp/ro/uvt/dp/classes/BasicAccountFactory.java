package ro.uvt.dp.classes;

public class BasicAccountFactory extends AccountFactory {

    @Override
    public Account createAccount(String accountNumber, double initialAmount, CurrencyType currencyType) {
        LoggerConfig.getInstance().logInfo("Creating basic account " + accountNumber);
        return new Account(accountNumber, initialAmount, currencyType);
    }
}
