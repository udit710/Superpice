package rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmit.sept.superprice.service.TransactionItemService;

@RestController
@RequestMapping("/transactionitem")
public class TransactionItemController {

    @Autowired
    private TransactionItemService transactionitemService;

    // TODO: Add controller methods
}
