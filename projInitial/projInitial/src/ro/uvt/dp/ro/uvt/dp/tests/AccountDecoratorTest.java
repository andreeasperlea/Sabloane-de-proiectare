package ro.uvt.dp.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ro.uvt.dp.classes.Account;
import ro.uvt.dp.decorators.LifeInsuranceDecorator;
import ro.uvt.dp.decorators.KidsAccountDecorator;
import ro.uvt.dp.interfaces.Operations;
import ro.uvt.dp.classes.RONInterestCalculator;

public class AccountDecoratorTest {

    @Test
    public void testLifeInsuranceDecoratorAddsBonus() {
        Operations baseAccount = new Account("ACC100", 1000, new RONInterestCalculator());

        Operations insuredAccount = new LifeInsuranceDecorator(baseAccount);

        double baseTotal = baseAccount.getTotalAmount();
        double insuredTotal = insuredAccount.getTotalAmount();

        assertEquals(baseTotal * 1.01, insuredTotal, 0.001,
                "LifeInsuranceDecorator should increase total amount by 1%");
    }

    @Test
    public void testKidsAccountDecoratorReducesInterest() {
        Operations baseAccount = new Account("ACC200", 2000, new RONInterestCalculator());
        Operations kidsAccount = new KidsAccountDecorator(baseAccount);

        double baseInterest = baseAccount.getInterest();
        double kidsInterest = kidsAccount.getInterest();

        // Kids account gives half the normal interest
        assertEquals(baseInterest * 0.5, kidsInterest, 0.001,
                "KidsAccountDecorator should reduce interest by 50%");
    }

    @Test
    public void testCombinedDecoratorsWorkTogether() {
        Operations baseAccount = new Account("ACC300", 3000, new RONInterestCalculator());
        Operations combined = new KidsAccountDecorator(new LifeInsuranceDecorator(baseAccount));

        double baseTotal = baseAccount.getTotalAmount();
        double combinedTotal = combined.getTotalAmount();

        assertTrue(combinedTotal > baseTotal,
                "Combined decorators should not reduce total below base account total");
    }
}
