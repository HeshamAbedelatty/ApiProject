package com.project.fawry.Controller;

import com.project.fawry.Bslogic.AdminBsl;
import com.project.fawry.Bslogic.Refund.RefundsBsl;
import com.project.fawry.Bslogic.Refund.RefundRequestTransaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    private final RefundsBsl refundsBsl;
    private final AdminBsl adminBsl;

    public AdminController(RefundsBsl refundsBsl, AdminBsl adminBsl) {
        this.refundsBsl = refundsBsl;
        this.adminBsl = adminBsl;
    }

    @GetMapping (value = "/getPaymentListByUserName/{AdminUserName}/{username}")
    public List<String> getPaymentListByUserName(@PathVariable String AdminUserName, @PathVariable String username) {
        return adminBsl.getPaymentListByUserName(AdminUserName, username);
    }

    @GetMapping (value = "/ListFundsTransactionByUserName/{AdminUserName}/{username}")
    public List<String> ListFundsTransactionByUserName(@PathVariable String AdminUserName, @PathVariable String username) {
        return adminBsl.getFundsListByUserName(AdminUserName, username);
    }
    @GetMapping(value = "/listRefundRequests/{AdminUserName}")
    public List<RefundRequestTransaction> listRefundRequests(@PathVariable String AdminUserName) {
        return refundsBsl.listRefundRequest(AdminUserName);
    }
    @GetMapping(value = "/listRefundRequestForCustomer/{AdminUserName}/{username}")
    public List<RefundRequestTransaction> listRefundRequestForCustomer(@PathVariable String AdminUserName,@PathVariable String username) {
        return refundsBsl.listRefundRequestByUserName(AdminUserName, username);
    }
    @PostMapping (value = "/acceptRefundRequest/{username}/{servicename}/{money}")// 200 ok .........
    public String acceptRefundRequest(@PathVariable String username,@PathVariable String servicename, @PathVariable String money) {
        return adminBsl.acceptRefundRequest(username, servicename, money);
    }
    @PostMapping(value = "/rejectRefundRequest/{username}/{servicename}/{money}")// 200 ok ...........
    public String rejectRefundRequest(@PathVariable String username,@PathVariable String servicename,@PathVariable String money) {
        return adminBsl.rejectRefundRequest(username, servicename, money);
    }
    @PostMapping(value = "/AddDiscounts/{dis}/{servicename}")//200 ok .........
    public String AddDiscounts(@PathVariable String dis,@PathVariable String servicename) {
        return adminBsl.AddDiscount(dis, servicename);
    }
}