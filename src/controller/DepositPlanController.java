package controller;

import model.HandleJSONFile;
import view.CustomerDepositView;

public class DepositPlanController {
    private HandleJSONFile handleJSONFile = new HandleJSONFile();

    CustomerDepositView customerDepositView;

    public DepositPlanController(CustomerDepositView customerDepositView){
        this.customerDepositView = customerDepositView;
    }

    public void initiateDeposit(){
        customerDepositView.toDeposit();
        customerDepositView.viewDistribution();
    }
}
