package com.project.fawry.Repos;

import com.project.fawry.Models.BaseUser;
import com.project.fawry.Bslogic.Refund.RefundRequestTransaction;
import com.project.fawry.Bslogic.Refund.Refunds;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RefundRequestsRepo {
    private List<RefundRequestTransaction> transactionList;
    private List<RefundRequestTransaction> AcceptedList ;
    public final Authentication_Repo authenticationRepo;
    public final ServicesRepo servicesRepo;

    public RefundRequestsRepo(Authentication_Repo authenticationRepo, ServicesRepo servicesRepo) {
        this.authenticationRepo = authenticationRepo;
        this.servicesRepo = servicesRepo;
        transactionList = new ArrayList<>();
        AcceptedList=new ArrayList<>();
    }

    public int makeRequest(String username, Refunds refund) {
        BaseUser baseUser = new BaseUser();
        baseUser = authenticationRepo.search(username);
        if (baseUser != null) {
            if (servicesRepo.searchServices(refund.getServiceName()).equals("0")) {
                return 1;
            }
            RefundRequestTransaction transaction = new RefundRequestTransaction(username, refund);
            for (RefundRequestTransaction refundRequestTransaction : transactionList) {
                if (refundRequestTransaction.getUsername().equals(baseUser.getUsername())
                        && refundRequestTransaction.getRefund().getServiceName().equals(refund.getServiceName())
                        && refundRequestTransaction.getRefund().getMoneyThatpaied().equals(refund.getMoneyThatpaied())) {
                    return 3;
                }
            }
            transactionList.add(transaction);
            return 2;
        }
        return 0;
    }

    public List<RefundRequestTransaction> getTransactionList() {
        return transactionList;
    }

    public List<RefundRequestTransaction> getTransactionListByUserName(String username) {
        BaseUser baseUser = authenticationRepo.searchForacceptOrReject(username);
        if (baseUser != null) {
            List<RefundRequestTransaction> d = new ArrayList<>();
            for (RefundRequestTransaction refundRequestTransaction : transactionList) {
                if (refundRequestTransaction.getUsername().equals(baseUser.getUsername())) {
                    d.add(refundRequestTransaction);
                    return d;
                }
            }
        }
        return null;
    }

    public int acceptRequest(String username, String servicename, String money) {
        BaseUser baseUser = authenticationRepo.searchForacceptOrReject(username);
        if (baseUser != null) {
            if (servicesRepo.searchServices(servicename).equals("0")) {
                return 1;
            }
            for (int i = 0; i < transactionList.size(); i++) {
                if (transactionList.get(i).getUsername().equals(baseUser.getUsername())
                        && transactionList.get(i).getRefund().getServiceName().equals(servicename)
                        && transactionList.get(i).getRefund().getMoneyThatpaied().equals(money)) {
                    AcceptedList.add(transactionList.get(i));
                    transactionList.remove(transactionList.get(i));
                    double amount;
                    amount = Double.parseDouble(money);
                    baseUser.increaseWallet(amount);
                    return 3;
                }
            }
            return 2;
        }
        return 0;
    }

    public int rejectRequest(String username, String servicename, String money) {
        BaseUser baseUser = authenticationRepo.searchForacceptOrReject(username);
        if (baseUser != null) {
            if (servicesRepo.searchServices(servicename).equals("0")) {
                return 1;
            }
            for (int i = 0; i < transactionList.size(); i++) {
                if (transactionList.get(i).getUsername().equals(baseUser.getUsername())
                        && transactionList.get(i).getRefund().getServiceName().equals(servicename)
                        && transactionList.get(i).getRefund().getMoneyThatpaied().equals(money)) {
                    transactionList.remove(transactionList.get(i));
                    return 3;
                }
            }
            return 2;
        }
        return 0;
    }
}

