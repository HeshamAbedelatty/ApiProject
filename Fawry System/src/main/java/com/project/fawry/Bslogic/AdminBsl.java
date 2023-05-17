package com.project.fawry.Bslogic;

import com.project.fawry.Repos.Authentication_Repo;
import com.project.fawry.Repos.CustomerRepo;
import com.project.fawry.Repos.RefundRequestsRepo;
import com.project.fawry.Repos.ServicesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBsl {
    private final RefundRequestsRepo requestsRepo;
    private final ServicesRepo servicesRepo;
    private final Authentication_Repo authenticationRepo;
    private final CustomerRepo customerRepo;

    public AdminBsl(RefundRequestsRepo requestsRepo, ServicesRepo servicesRepo, Authentication_Repo authenticationRepo, CustomerRepo customerRepo) {
        this.requestsRepo = requestsRepo;
        this.servicesRepo = servicesRepo;
        this.authenticationRepo = authenticationRepo;
        this.customerRepo = customerRepo;
    }

    public String acceptRefundRequest(String username, String servicename, String money) {
        int flag = requestsRepo.acceptRequest(username, servicename, money);
        if (flag == 0) {
            return "There is no Customer with this Account.";
        } else if (flag == 1) {
            return "There is no Services in this Name.";
        } else if (flag == 2) {
            return "There is no Transaction in This info.";
        } else
            return "Accepted Transaction. \nMoney returned to customer.";
    }

    public String rejectRefundRequest(String username, String servicename, String money) {
        int flag = requestsRepo.rejectRequest(username, servicename, money);
        if (flag == 0) {
            return "There is no Customer with this Account.";
        } else if (flag == 1) {
            return "There is no Services in this Name.";
        } else if (flag == 2) {
            return "There is no Transaction in This info.";
        } else
            return "Rejected Transaction.";
    }

    public String AddDiscount(String dis, String servicename) {
        int flag = servicesRepo.AddDiscount(dis, servicename);
        if (flag == 1) {
            return "There is no Discount has this Name.";
        } else if (flag == 0) {
            return "There is no Services in this Name.";
        } else
            return "Rejected Transaction.";
    }

    public List<String> getFundsListByUserName(String AdminUserName, String username) {
        boolean flag = authenticationRepo.isAdmin(AdminUserName);
        if (flag) {
            return customerRepo.getTransactionListByUserName(username);
        }
        return null;
    }
    public List<String> getPaymentListByUserName(String AdminUserName, String username) {
        boolean flag = authenticationRepo.isAdmin(AdminUserName);
        if (flag) {
            return customerRepo.getPaymentTransactionListByUserName(username);
        }
        return null;
    }
}

