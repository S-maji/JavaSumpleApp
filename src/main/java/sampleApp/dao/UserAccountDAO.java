package sampleApp.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sampleApp.model.Account;

@Repository
public class UserAccountDAO {

	@Autowired
	EntityManager em;

	/**
	 * フォームの入力値から該当するユーザを検索 合致するものが無い場合Nullが返される
	 * @param userName
	 * @return 一致するユーザが存在するとき:UserEntity、存在しないとき:Null
	 */
	public Account findUser(String userName) {
		String query = "";
		query += "SELECT * ";
		query += "FROM users ";
		query += "WHERE name = :userName "; //setParameterで引数の値を代入できるようにNamedParameterを利用

		//EntityManagerで取得された結果はオブジェクトとなるので、LoginUser型へキャストが必要となる
		return (Account)em.createNativeQuery(query, Account.class).setParameter("userName", userName)
				.getSingleResult();
	}

}