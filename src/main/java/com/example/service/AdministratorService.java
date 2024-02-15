package com.example.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

import lombok.RequiredArgsConstructor;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AdministratorService {

	private final AdministratorRepository administratorRepository;

	private final PasswordEncoder passwordEncoder;

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		System.out.println(administrator);
		administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
		administratorRepository.insert(administrator);
	}

	/**
	 * ログインをします.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	// public Administrator login(String mailAddress, String password) {
	// 	Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
	// 	if (passwordEncoder.matches(password, administrator.getPassword())) {
	// 		return administrator;
	// 	}
	// 	return Optional.ofNullable();
	// }

	/**
	 * 引数で渡したメールアドレスと一致する管理者情報を取得
	 * 
	 * @param mailAddress
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	public Optional<Administrator> findByMailAddress(String mailAddress){
		return administratorRepository.findByMailAddress(mailAddress);
	
	}
}
