package model;

import java.util.*;

public class DepositPlan {
    private final String ONE_TIME_ID = "D01";
    private final String MONTHLY_ID = "D02";

    private DataStructure dataStructure;

    private String refId;
    private long amount;

    public DepositPlan(String refId, long amount) {
        this.refId = refId;
        this.amount = amount;

        fundDeviation();
    }

    public void fundDeviation(){
        HandleJSONFile handleJSONFile = new HandleJSONFile();
        dataStructure = handleJSONFile.retrieve();

        HashMap<String,DataStructure.Portfolio.DepositsCriterias> monthly = new HashMap<>();
        HashMap<String,DataStructure.Portfolio.DepositsCriterias> onetime = new HashMap<>();

        /*for-each(split portfolios into hashmaps based on deposit plans)*/
        for (DataStructure.Portfolio p : dataStructure.getPlans()) {
            for (DataStructure.Portfolio.DepositsCriterias d : p.getDeposits()) {
                if(d.getDepId().equals(ONE_TIME_ID) && d.getDepAmount() > 0)
                    onetime.put(p.getPortId(),d);
                else if(d.getDepId().equals(MONTHLY_ID) && d.getDepAmount() > 0)
                    monthly.put(p.getPortId(),d);
            }
        }

        /*monthly plans has been prioritized to first choice*/
        Iterator mIterator = monthly.entrySet().iterator();
        while (mIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)mIterator.next();
            DataStructure.Portfolio.DepositsCriterias value = (DataStructure.Portfolio.DepositsCriterias) mapElement.getValue();
            if(value.getDepAmount() <= amount )
                System.out.println("dd");
        }
        List<DataStructure.Portfolio> plans = dataStructure.getPlans();
        List<DataStructure.Portfolio.DepositsCriterias> deposits;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
