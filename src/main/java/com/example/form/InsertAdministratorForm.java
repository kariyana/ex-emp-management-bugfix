package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** メールアドレス */
	@Email
	@NotBlank(message = "メールアドレスを入力してください")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "パスワードを入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9.?/-]{8,24}$" ,message = "８文字以上２４文字以内で入力してください。")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "パスワードを入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9.?/-]{8,24}$" ,message = "８文字以上２４文字以内で入力してください。")
	private String confirmPassword;

}
