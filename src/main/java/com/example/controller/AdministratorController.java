package com.example.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 管理者情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private HttpSession session;

	private final PasswordEncoder passwordEncoder;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：管理者を登録する
	/////////////////////////////////////////////////////
	/**
	 * 管理者登録画面を出力します.
	 * 
	 * @return 管理者登録画面
	 */
	@GetMapping("/toInsert")
	public String toInsert(InsertAdministratorForm form) {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param form 管理者情報用フォーム
	 * @return ログイン画面へリダイレクト
	 */
	@PostMapping("/insert")
	public String insert(Model model,
						@Validated InsertAdministratorForm form,
						BindingResult result) {
		//バリデーション以外のエラーがあるかどうか
		boolean isError = false;
		//メールアドレスの重複を確認する。
		Administrator sameEmailAdministrator = administratorService.findByMailAddress(form.getMailAddress()).orElse(null);
		if (sameEmailAdministrator!=null) {
			model.addAttribute("errorMessageSameEmail", "そのメールアドレスは既に利用されております");
			isError = true;
		}
		//メールアドレスと確認用メールアドレスが一致しているかどうか
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			model.addAttribute("errorMessageNotMatchPassword", "パスワードと確認用パスワードが一致しません");
			isError = true;
		}

		//バリデーションエラーを確認する。
		if (result.hasErrors() || isError) {
			return toInsert(form);
		}
		Administrator administrator = new Administrator();
		// フォームからドメインにプロパティ値をコピー
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/login";
	}

	/////////////////////////////////////////////////////
	// ユースケース：ログインをする
	/////////////////////////////////////////////////////
	/**
	 * ログイン画面を出力します.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/login")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * ログインします.
	 * 
	 * @param form 管理者情報用フォーム
	 * @return ログイン後の従業員一覧画面
	 */
	@PostMapping("/login")
	public String login(LoginForm form, RedirectAttributes redirectAttributes) {
		Administrator administrator = administratorService.findByMailAddress(form.getMailAddress()).orElse(null);
		if (!passwordEncoder.matches(form.getPassword(), administrator.getPassword())) {
			redirectAttributes.addFlashAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
			return "redirect:/login";
		}
		//ログインしているユーザーの情報をsessionに渡す
		session.setAttribute("administratorName", administrator.getName());
		return "redirect:/employees";
	}

	/////////////////////////////////////////////////////
	// ユースケース：ログアウトをする
	/////////////////////////////////////////////////////
	/**
	 * ログアウトをします. (SpringSecurityに任せるためコメントアウトしました)
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login";
	}

}
