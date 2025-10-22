package ro.uvt.dp.classes;

public class InvestmentAccountFactory extends AccountFactory {

    @Override
    public Account createAccount(String accountNumber, double initialAmount, CurrencyType currencyType) {
        LoggerConfig.getInstance().logInfo("Creating investment account " + accountNumber);
        return new InvestmentAccount(accountNumber, initialAmount, currencyType);
    }
}
