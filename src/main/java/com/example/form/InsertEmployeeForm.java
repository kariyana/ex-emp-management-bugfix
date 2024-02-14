package com.example.form;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertEmployeeForm {
    
    /*id */
    private String id;
    /*名前 */
    @NotBlank
    private String name;
    /*画像 */
    private MultipartFile image;
    /*性別 */
    @NotBlank
    private String gender;
    /*入社日 */
    @NotBlank
    @Pattern(regexp="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",message = "正しい日付型で入力してください(yyyy-MM-dd)")
    private String hireDate;
    /*メールアドレス */
    @NotBlank
    @Email
    private String mailAddress;
    /*郵便番号 */
    @NotBlank
    private String zipCode;
    /*住所 */
    @NotBlank
    private String address;
    /*電話番号 */
    @NotBlank
    private String telephone;
    /*給料 */
    @NotBlank
    @Pattern(regexp = "^[0-9]*" ,message="数値を入力してください")
    private String salary;
    /*特性 */
    @NotBlank
    private String characteristics;
    /*扶養人数 */
    @NotBlank
    @Pattern(regexp = "^[0-9]*" ,message="数値を入力してください")
    private String dependentsCount;

}
