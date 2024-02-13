package com.example.controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.exception.EmployeeNotFoundException;
import com.example.form.SearchEmployeeForm;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

import jakarta.websocket.server.PathParam;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@GetMapping({"","/"})
	public String showList(Model model
							,SearchEmployeeForm form) {
		List<Employee> employeeList = employeeService.showList(form);
		if (employeeList.isEmpty()) {
			form.setName("");
			employeeList = employeeService.showList(form);
			model.addAttribute("errorNotEmployee", "１件もありませんでした");
		}
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id    リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
   @GetMapping("/showDetail/{id}")
    public String showDetail(@PathVariable("id") String id,
                            Model model,
                            UpdateEmployeeForm form
                            ) {
        
        Employee employee = employeeService.showDetail(Integer.parseInt(id)).orElseThrow(EmployeeNotFoundException::new);
        model.addAttribute("employee", employee);
        BeanUtils.copyProperties(employee, form);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(employee.getHireDate());
        form.setDependentsCount(employee.getDependentsCount().toString());
        form.setHireDate(date);
        return "employee/detail";
    }

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") String id,
						@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showDetail(id,model,form);
		}
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(id));
		employeeService.update(employee);
		return "redirect:/employees";
	}
}
