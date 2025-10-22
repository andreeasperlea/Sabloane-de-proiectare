package ro.uvt.dp.test;

import ro.uvt.dp.classes.Account;
import ro.uvt.dp.classes.Bank;
import ro.uvt.dp.classes.Client;
import ro.uvt.dp.classes.CurrencyType;

public class Test {

    public static void main(String[] args) {

        Bank bcr = new Bank("Banca BCR");


        Client cl1 = new Client("Ionescu Ion", "Timisoara", CurrencyType.EUR, "EUR124", 200.9);
        bcr.addClient(cl1);
        cl1.addAccount(CurrencyType.RON, "RON1234", 400);


        Client cl2 = new Client("Marinescu Marin", "Timisoara", CurrencyType.RON, "RON126", 100);
        bcr.addClient(cl2);

        System.out.println(bcr);


        Bank cec = new Bank("Banca CEC");
        Client clientCEC = new Client("Vasilescu Vasile", "Brasov", CurrencyType.EUR, "EUR128", 700);
        cec.addClient(clientCEC);
        System.out.println(cec);

        Client cl = bcr.getClient("Marinescu Marin");
        if (cl != null) {
            cl.getAccount("RON126").depose(400);
            System.out.println(cl);
        }

        if (cl != null) {
            cl.getAccount("RON126").retrieve(67);
            System.out.println(cl);
        }
        Account c1 = cl.getAccount("RON126");
        Account c2 = bcr.getClient("Ionescu Ion").getAccount("RON1234");

        transfer(c1, c2, 40);
        System.out.println(bcr);
    }

    private static void transfer(Account from, Account to, double suma) {
        if (from.getCurrencyType() != to.getCurrencyType()) {
            throw new IllegalArgumentException("Transferul între monede diferite nu este permis!");
        }

        from.retrieve(suma);
        to.depose(suma);
    }
}
