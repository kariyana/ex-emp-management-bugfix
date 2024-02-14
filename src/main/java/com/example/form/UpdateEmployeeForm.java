package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeForm {
    
    /*id */
    private String id;
     /*名前 */
     private String name;
     /*画像 */
     private String image;
     /*性別 */
     private String gender;
     /*入社日 */
     @NotBlank
     @Pattern(regexp="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",message = "正しい日付型で入力してください(yyyy-MM-dd)")
     private String hireDate;
     /*メールアドレス */
     private String mailAddress;
     /*郵便番号 */
     private String zipCode;
     /*住所 */
     private String address;
     /*電話番号 */
     private String telephone;
     /*給料 */
     private Integer salary;
     /*特性 */
     private String characteristics;
    /*扶養人数 */
    @NotBlank
    @Pattern(regexp = "^[0-9]*" ,message="数値を入力してください")
    private String dependentsCount;
}
