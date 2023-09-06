package au.edu.rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.superprice.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // TODO: Add service methods
}
