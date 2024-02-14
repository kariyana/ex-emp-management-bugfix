package com.example.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchEmployeeForm {
    /*現在のページ */
    private Integer page;
    /*取得する件数 */
    private Integer limit;
    /*名前 */
    private String name;
    
}
