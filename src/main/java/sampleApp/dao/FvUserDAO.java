package sampleApp.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sampleApp.model.FvUser;

@Repository
public class FvUserDAO {

	@Autowired
	EntityManager em;

	public FvUser findUser(int uid, int fid) {
		String query = "";
		query += "SELECT * ";
		query += "FROM fvusers ";
		query += "WHERE user_id = :uid AND fvuser_id = :fid "; //setParameterで引数の値を代入できるようにNamedParameterを利用

		return (FvUser)em.createNativeQuery(query, FvUser.class).setParameter("uid", uid).setParameter("fid", fid)
				.getSingleResult();
	}

}
