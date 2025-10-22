package ro.uvt.dp.tests;

import ro.uvt.dp.classes.*;

public class Test {

    public static void main(String[] args) {

        Bank bcr = new Bank("Banca BCR");

        Client cl1 = new Client.Builder("Ionescu Ion", "Timisoara")
                .addAccount(new BasicAccountFactory(), CurrencyType.EUR, "EUR124", 200.9)
                .addAccount(new SavingsAccountFactory(), CurrencyType.RON, "RON1234", 400)
                .build();
        bcr.addClient(cl1);

        Client cl2 = new Client.Builder("Marinescu Marin", "Timisoara")
                .addAccount(new BasicAccountFactory(), CurrencyType.RON, "RON126", 100)
                .build();
        bcr.addClient(cl2);

        System.out.println("\n===== Bank BCR Initial State =====");
        System.out.println(bcr);

        Bank cec = new Bank("Banca CEC");
        Client clientCEC = new Client.Builder("Vasilescu Vasile", "Brasov")
                .addAccount(new InvestmentAccountFactory(), CurrencyType.EUR, "EUR128", 700)
                .build();
        cec.addClient(clientCEC);

        System.out.println("\n===== Bank CEC Initial State =====");
        System.out.println(cec);

        Client cl = bcr.getClient("Marinescu Marin");
        if (cl != null) {
            cl.getAccount("RON126").depose(400);
            System.out.println("\nAfter deposit:");
            System.out.println(cl);
        }

        if (cl != null) {
            cl.getAccount("RON126").retrieve(67);
            System.out.println("\nAfter withdrawal:");
            System.out.println(cl);
        }

        Account c1 = cl.getAccount("RON126");
        Account c2 = bcr.getClient("Ionescu Ion").getAccount("RON1234");

        System.out.println("\n===== Performing Transfer =====");
        transfer(c1, c2, 40);

        System.out.println("\n===== Final State of BCR =====");
        System.out.println(bcr);
    }

    private static void transfer(Account from, Account to, double suma) {
        if (from.getCurrencyType() != to.getCurrencyType()) {
            throw new IllegalArgumentException("Transferul între monede diferite nu este permis!");
        }

        from.retrieve(suma);
        to.depose(suma);
        LoggerConfig.getInstance().logInfo("Transfer completed: " + suma + " " + from.getCurrencyType() +
                " from " + from.getAccountNumber() + " to " + to.getAccountNumber());
    }
}
