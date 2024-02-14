package com.example.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchEmployeeAutoCompleteForm {
    
    /*取得する件数 */
    private int limit;
    /*名前 */
    private String name;
}
