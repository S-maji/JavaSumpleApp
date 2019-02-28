package sampleApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sampleApp.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

		public Account findByName(String name);

		public Account findById(int id);

}
