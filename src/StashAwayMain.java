import controller.DataStructureController;
import controller.DepositPlanController;
import model.DataStructure;
import view.CustomerDepositView;
import view.DataStructureView;

import java.util.ArrayList;
import java.util.List;

public class StashAwayMain {

    public static void main(String args[]){
        /*create the data structure*/
        DataStructure dataStructure = retrieveDepositPlanDetails();
        DataStructureView dataStructureView = new DataStructureView();

        DataStructureController structureController = new DataStructureController(dataStructure, dataStructureView);
        structureController.OnCreateStructure();

        /*handle customer fund deposit*/
        CustomerDepositView customerDepositView = new CustomerDepositView();

        DepositPlanController depositPlanController = new DepositPlanController(customerDepositView);
        depositPlanController.initiateDeposit();

    }

    public static DataStructure retrieveDepositPlanDetails(){
        DataStructure structure = new DataStructure();

        List<DataStructure.Portfolio> plans;
        List<DataStructure.Portfolio.DepositsCriterias> deposits;
        DataStructure.Portfolio p;
        DataStructure.Portfolio.DepositsCriterias dc;

        plans = new ArrayList<DataStructure.Portfolio>();
        deposits = new ArrayList<DataStructure.Portfolio.DepositsCriterias>();

        p = new DataStructure.Portfolio();
        p.setPortId("P01");
        p.setPortName("High risk");
        p.setPortDesc("High risk description");

        dc = new DataStructure.Portfolio.DepositsCriterias();
        dc.setDepId("D01");
        dc.setDepName("One time");
        dc.setDepAmount(1000);
        deposits.add(dc);

        dc = new DataStructure.Portfolio.DepositsCriterias();
        dc.setDepId("D02");
        dc.setDepName("Monthly");
        dc.setDepAmount(0);
        deposits.add(dc);

        p.setDeposits(deposits);
        plans.add(p);

        deposits = new ArrayList<DataStructure.Portfolio.DepositsCriterias>();

        p = new DataStructure.Portfolio();
        p.setPortId("P02");
        p.setPortName("Retirement");
        p.setPortDesc("Retirement description");

        dc = new DataStructure.Portfolio.DepositsCriterias();
        dc.setDepId("D01");
        dc.setDepName("One time");
        dc.setDepAmount(500);
        deposits.add(dc);

        dc = new DataStructure.Portfolio.DepositsCriterias();
        dc.setDepId("D02");
        dc.setDepName("Monthly");
        dc.setDepAmount(100);
        deposits.add(dc);

        p.setDeposits(deposits);
        plans.add(p);

        structure.setPlans(plans);

        return structure;
    }
}
