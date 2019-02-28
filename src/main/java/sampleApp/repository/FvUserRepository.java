package sampleApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sampleApp.model.FvUser;

public interface FvUserRepository extends JpaRepository<FvUser, Integer> {

}
