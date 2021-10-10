package controller;

import model.DepositPlan;
import model.HandleDataFile;
import view.CustomerDepositView;

public class DepositPlanController {
    private HandleDataFile handleDataFile = new HandleDataFile();

    CustomerDepositView customerDepositView;
    DepositPlan depositPlan;

    public DepositPlanController(CustomerDepositView customerDepositView){
        this.customerDepositView = customerDepositView;
    }

    public void initiateDeposit(){
        this.depositPlan = customerDepositView.toDeposit();
        customerDepositView.viewDistribution(handleDataFile.retrieve(),depositPlan.getAllocatedPlans(),depositPlan.getSafetyDepositAmount());
    }
}
