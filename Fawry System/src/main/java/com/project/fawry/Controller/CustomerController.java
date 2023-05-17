package com.project.fawry.Controller;

import com.project.fawry.Bslogic.CustomerBsl;
import com.project.fawry.Bslogic.Refund.RefundsBsl;
import com.project.fawry.Bslogic.Refund.Refunds;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@RestController
public class CustomerController {
    private final CustomerBsl customerBsl;
    private final RefundsBsl refundsBsl;

    public CustomerController(CustomerBsl customerBsl, RefundsBsl refundsBsl) {
        this.customerBsl = customerBsl;
        this.refundsBsl = refundsBsl;
    }
    @PostMapping(value = "/PayforService/{UserName}/{Provider}/{service}/{way}/{amount}/{creditCard}")//200 ok
    public String PayforService(@PathVariable String UserName, @PathVariable String Provider,@PathVariable String service,@PathVariable String way,@PathVariable double amount
            ,@PathVariable String creditCard) {
        return customerBsl.payForService(UserName, Provider, service, way, amount, creditCard);
    }
    @GetMapping(value = "/getServices")// 200 ok
    public List<String> getServices() {
        return customerBsl.getServices();
    }
    @PostMapping(value = "/addValueToWallet/{money}/{username}") // 200 ok
    public String addValueToWallet(@PathVariable double money,@PathVariable String username) {
        return customerBsl.CheckAddToWallet(money, username);
    }
    @GetMapping(value = "/searchForServices/{name}")//200 ok
    public String searchForServices(@PathVariable String name) {
        return customerBsl.searchServiceName(name);
    }
    @GetMapping(value = "/printAllDiscounts")
    public Vector<Vector<String>> printAllDiscounts() {

        return customerBsl.printAllDiscounts();
    }
    @GetMapping(value = "/searchForDiscountByname/{s}")
    public String searchForDiscountByname(@PathVariable String s) {
        return customerBsl.searchForDiscountByname(s);
    }
    @PostMapping(value = "/makeRefundRequest/{username}")// 200 ok
    public String makeRefundRequest(@PathVariable String username, @RequestBody Refunds refund) {
        return refundsBsl.makeRefundRequest(username, refund);
    }
    @GetMapping(value = "/getCustomerInfo/{username}")// 200 ok
    public String getCustomerInfo( @PathVariable String username) {
        return customerBsl.getCustomerInfo(username);
    }
    @PostMapping(value = "/MakeService/{UserName}/{Provider}/{service}")//200 ok
    public String MakeService(@PathVariable String UserName,@PathVariable String Provider,@PathVariable String service) {
        return customerBsl.MakeServiceByCustomer(UserName, Provider, service);
    }
}