package com.project.fawry.Bslogic.Refund;

import com.project.fawry.Repos.Authentication_Repo;
import com.project.fawry.Repos.RefundRequestsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundsBsl {
    private final RefundRequestsRepo requestsRepo;
    private final Authentication_Repo authenticationRepo;

    public RefundsBsl(RefundRequestsRepo requestsRepo, Authentication_Repo authenticationRepo) {
        this.requestsRepo = requestsRepo;
        this.authenticationRepo = authenticationRepo;
    }

    public String makeRefundRequest(String username, Refunds refund) {
        int flag = requestsRepo.makeRequest(username, refund);
        if (flag == 1) {
            return "There is no service with this name";
        } else if (flag == 2) {
            return "Successfully Added.";
        } else if (flag == 3) {
            return "This Transaction is made before.";
        } else
            return "Please log in first.";
    }

    public List<RefundRequestTransaction> listRefundRequest(String AdminUserName) {
        boolean flag = authenticationRepo.isAdmin(AdminUserName);
        if (flag) {
            return requestsRepo.getTransactionList();
        }
        return null;
    }

    public List<RefundRequestTransaction> listRefundRequestByUserName(String AdminUserName, String username) {
        boolean flag = authenticationRepo.isAdmin(AdminUserName);
        if (flag) {
            return requestsRepo.getTransactionListByUserName(username);
        }
        return null;
    }


}
