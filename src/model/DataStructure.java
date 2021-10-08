package model;

import java.io.Serializable;
import java.util.List;

public class DataStructure implements Serializable {

    private List<Portfolio> plans;

    public List<Portfolio> getPlans() {
        return plans;
    }

    public void setPlans(List<Portfolio> plans) { this.plans = plans; }

    public static class Portfolio{
        private String portId;
        private String portName;
        private String portDesc;

        private List<DepositsCriterias> deposits;

        public String getPortId() {
            return portId;
        }

        public void setPortId(String portId) {
            this.portId = portId;
        }

        public String getPortName() {
            return portName;
        }

        public void setPortName(String portName) {
            this.portName = portName;
        }

        public String getPortDesc() {
            return portDesc;
        }

        public void setPortDesc(String portDesc) {
            this.portDesc = portDesc;
        }

        public List<DepositsCriterias> getDeposits() {
            return deposits;
        }

        public void setDeposits(List<DepositsCriterias> deposits) {
            this.deposits = deposits;
        }

        public static class DepositsCriterias{
            private String depId;
            private String depName;
            private double depAmount;

            public String getDepId() {
                return depId;
            }

            public void setDepId(String depId) {
                this.depId = depId;
            }

            public String getDepName() {
                return depName;
            }

            public void setDepName(String depName) {
                this.depName = depName;
            }

            public double getDepAmount() {
                return depAmount;
            }

            public void setDepAmount(double depAmount) {
                this.depAmount = depAmount;
            }
        }

    }

}
