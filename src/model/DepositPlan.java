package model;

import java.util.*;

public class DepositPlan {
    private final String ONE_TIME_ID = "D01";
    private final String MONTHLY_ID = "D02";

    private DataStructure dataStructure;

    private String refId;
    private long amount;
    private long safetyDepositAmount;
    private HashMap<String, Long> allocatedPlans;

    public DepositPlan(String refId, long amount) {
        this.refId = refId;
        this.amount = amount;

        fundDeviation();
    }

    public DepositPlan() {

    }

    public void fundDeviation(){
        HandleDataFile handleJSONFile = new HandleDataFile();

        long tempDepositAmount = amount;

        dataStructure = handleJSONFile.retrieve();
        allocatedPlans = new HashMap<>();

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

        /*selecting from one-time plans*/
        tempDepositAmount = findDepositPlans(allocatedPlans, onetime, tempDepositAmount);

        /*selecting from monthly plans*/
        tempDepositAmount = findDepositPlans(allocatedPlans, monthly, tempDepositAmount);

        /*when deposit excesses the current plans*/
        if(tempDepositAmount > 0){
            setSafetyDepositAmount(tempDepositAmount);
        }

    }

    private long findDepositPlans(HashMap<String, Long> allocatedPlans, HashMap<String, DataStructure.Portfolio.DepositsCriterias> monthly, long remainingAmount) {
        for (Map.Entry<String, DataStructure.Portfolio.DepositsCriterias> mElement : monthly.entrySet()) {
            DataStructure.Portfolio.DepositsCriterias mValue = mElement.getValue();
            if(mValue.getDepAmount() <= remainingAmount ){
                long deposit;
                if(allocatedPlans.containsKey(mElement.getKey())) {
                    deposit = allocatedPlans.get(mElement.getKey()) + (long) mValue.getDepAmount();
                    allocatedPlans.put(mElement.getKey(),deposit);
                }else {
                    allocatedPlans.put(mElement.getKey(), (long) mValue.getDepAmount());
                }
                remainingAmount = (long) (remainingAmount - mValue.getDepAmount());
            }
        }
        this.allocatedPlans = allocatedPlans;

        return remainingAmount;
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

    public long getSafetyDepositAmount() {
        return safetyDepositAmount;
    }

    public void setSafetyDepositAmount(long safetyDepositAmount) {
        this.safetyDepositAmount = safetyDepositAmount;
    }

    public HashMap<String, Long> getAllocatedPlans() {
        return allocatedPlans;
    }

    public void setAllocatedPlans(HashMap<String, Long> allocatedPlans) {
        this.allocatedPlans = allocatedPlans;
    }

}
