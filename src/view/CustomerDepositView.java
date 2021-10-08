package view;

import model.DataStructure;
import model.DepositPlan;

import java.awt.dnd.DropTarget;
import java.util.Scanner;

public class CustomerDepositView {
    private DepositPlan depositPlan;

    public void toDeposit(){
        Scanner sc = new Scanner(System.in);
        String refId;
        long deposit;

        System.out.print("Customer Reference ID : ");
        refId = sc.next();
        System.out.print("Deposit amount : ");
        deposit = sc.nextLong();

        depositPlan = new DepositPlan(refId,deposit);
    }

    public void viewDistribution(){

    }

}
