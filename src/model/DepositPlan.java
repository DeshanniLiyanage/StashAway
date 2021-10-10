package model;

import java.util.*;

public class DepositPlan {
    private final String ONE_TIME_ID = "D01";
    private final String MONTHLY_ID = "D02";

    private DataStructure dataStructure;

    private String refId;
    private float amount;
    private float safetyDepositAmount;
    private HashMap<String, Float> allocatedPlans;

    public DepositPlan(String refId, float amount) {
        this.refId = refId;
        this.amount = amount;

        fundDeviation();
    }

    public void fundDeviation(){
        HandleDataFile handleJSONFile = new HandleDataFile();

        float tempDepositAmount = amount;

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

    private float findDepositPlans(HashMap<String, Float> allocatedPlans, HashMap<String, DataStructure.Portfolio.DepositsCriterias> monthly, float remainingAmount) {
        for (Map.Entry<String, DataStructure.Portfolio.DepositsCriterias> mElement : monthly.entrySet()) {
            DataStructure.Portfolio.DepositsCriterias mValue = mElement.getValue();
            if(mValue.getDepAmount() <= remainingAmount ){ /*check - remaining balance is enough to complete a deposit*/
                float deposit;
                if(allocatedPlans.containsKey(mElement.getKey())) { /*when in same portfolio*/
                    deposit = allocatedPlans.get(mElement.getKey()) + (float) mValue.getDepAmount();
                    allocatedPlans.put(mElement.getKey(),deposit);
                }else { /*when in different portfolios*/
                    allocatedPlans.put(mElement.getKey(), (float) mValue.getDepAmount());
                }
                remainingAmount = (float) (remainingAmount - mValue.getDepAmount());
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getSafetyDepositAmount() {
        return safetyDepositAmount;
    }

    public void setSafetyDepositAmount(float safetyDepositAmount) {
        this.safetyDepositAmount = safetyDepositAmount;
    }

    public HashMap<String, Float> getAllocatedPlans() {
        return allocatedPlans;
    }

    public void setAllocatedPlans(HashMap<String, Float> allocatedPlans) {
        this.allocatedPlans = allocatedPlans;
    }

}
