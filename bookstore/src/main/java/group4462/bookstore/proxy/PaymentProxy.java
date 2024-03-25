package group4462.bookstore.proxy;

import group4462.bookstore.model.TransferObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "accounts",url = "http://localhost:8081")
public interface PaymentProxy {

    @PostMapping("/accounts/payment")
    boolean paymentBook(@RequestBody TransferObject dataObject);
}
