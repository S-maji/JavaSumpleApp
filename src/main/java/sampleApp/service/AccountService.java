package sampleApp.service;

import sampleApp.repository.AccountRepository;
import sampleApp.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public Account create(Account account, String rawPassword) {
		String encodedPassword = passwordEncoder.encode(rawPassword);
		account.setPassword(encodedPassword);
		return accountRepository.save(account);
	}



}
