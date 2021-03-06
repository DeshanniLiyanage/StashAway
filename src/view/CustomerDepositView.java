package view;

import model.DataStructure;
import model.DepositPlan;

import java.awt.dnd.DropTarget;
import java.text.NumberFormat;
import java.util.*;

public class CustomerDepositView {
    private DepositPlan depositPlan;
    Locale locale;
    NumberFormat currencyFormatter;

    public DepositPlan toDeposit(){
        Scanner sc = new Scanner(System.in);
        String refId;
        float deposit;

        System.out.print("Customer Reference ID : ");
        refId = sc.next();

        do{
            System.out.print("Deposit amount (SGD) : ");
            while(!sc.hasNextFloat()) {
                System.out.println("     Message : Input doesn't match specifications. Try again.");
                System.out.print("Deposit amount (SGD) : ");
                sc.next();
            }

            deposit = sc.nextFloat();

        } while (deposit < 0);

        sc.close();

        return new DepositPlan(refId,deposit);
    }

    public void viewDistribution(DataStructure dataStructure, HashMap<String, Float> allocatedPlans, float safeDepAmount){

        System.out.println();
        System.out.println("Welcome to StashAway  - Your personal wealth manager");
        System.out.println("  \nPlease take a look at following Portfolios of yours\n");

        List<DataStructure.Portfolio> plans = dataStructure.getPlans();

        locale = new Locale("en", "US");
        currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        for (DataStructure.Portfolio p:plans) {
            System.out.println(" - "+p.getPortName()+"  ");
            for (DataStructure.Portfolio.DepositsCriterias d:p.getDeposits()) {
                System.out.print("    "+d.getDepName()+" [" + currencyFormatter.format(d.getDepAmount()) + "]");
            }
            System.out.println();
        }

        System.out.println("\nSoon your current deposit will be arranged accordingly\n");

        if(allocatedPlans != null)
            for (Map.Entry<String, Float> dElement : allocatedPlans.entrySet()) {
                float depAmount = dElement.getValue();
                String portfolio = getPortfolioName(plans,dElement.getKey());

                System.out.println(portfolio + " : " + currencyFormatter.format(depAmount));
            }

        System.out.println("Your deposit has been arranged successfully.\n");

        if(safeDepAmount > 0) {/* when excess balance is there*/
            System.out.println("Your have " + currencyFormatter.format(safeDepAmount) + "(SGD) balance.");
            System.out.println("We'll be happily safe keeping it until your next transfer");
            System.out.println("     Message : Please contact us for a revision");
        }
    }

    public String getPortfolioName(List<DataStructure.Portfolio> plans,String portId){
        for (DataStructure.Portfolio p:plans) {
            if(p.getPortId().equals(portId))
                return p.getPortName();
        }
        return null;
    }

}
