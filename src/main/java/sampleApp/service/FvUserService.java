package sampleApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sampleApp.model.FvUser;
import sampleApp.repository.FvUserRepository;

@Service
public class FvUserService {

	@Autowired
	FvUserRepository fvUserRepository;

	public void create(FvUser fvUser) {
		fvUserRepository.save(fvUser);
	}
}
