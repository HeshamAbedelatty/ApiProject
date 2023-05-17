package com.project.fawry.Bslogic.Refund;

public class RefundRequestTransaction {
    private Refunds refund ;
    private String username;
    public RefundRequestTransaction(String username ,Refunds refund)
    {
        this.username = username;
        this.refund = refund;
    }

    public String getUsername() {
        return username;
    }

    public Refunds getRefund() {
        return refund;
    }

    public String getInfo()
    {
        return username+" "+"make refund request for service "+refund.getServiceName()
                       +" and pay "+refund.getMoneyThatpaied();
    }
}
