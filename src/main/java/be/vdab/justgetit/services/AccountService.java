package be.vdab.justgetit.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@Transactional(readOnly = false, isolation = READ_COMMITTED)
public class AccountService {

}
