package com.example.controller;

import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Employee;
import com.example.exception.EmployeeNotFoundException;
import com.example.form.InsertEmployeeForm;
import com.example.form.SearchEmployeeForm;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import com.example.service.UploadImageService;

import java.time.format.DateTimeFormatter;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	private final UploadImageService uploadImageService;

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
	/**
	 * 従業員を追加画面を表示します
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 画面を出力
	 */
	@GetMapping("/insert")
	public String showInsert(Model model
							,InsertEmployeeForm form) {
		System.out.println("form"+form);
		return "/employee/insert";
	}
	/**
	 * 従業員を追加いたします。
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@PostMapping({"/",""})
	public String insert(@Validated InsertEmployeeForm form
						,BindingResult result
						,Model model) {
		if (result.hasErrors()) {
			return showInsert(model,form);
		}
		
		
		
		// フォームに渡されたアップロードファイルを取得
		MultipartFile multipartFile = form.getImage();
		String fileName = "";
		if (!multipartFile.isEmpty()) {
			fileName = uploadImageService.getUploadImageName(multipartFile);
		}
		if (fileName.equals("")) {
			model.addAttribute("errorUploadImage", "正しい画像ファイルを選択してください");
			return showInsert(model,form);
		}
		
		//従業員情報にセットする
		Employee employee = new Employee();
		BeanUtils.copyProperties(form, employee);
		employee.setImage(fileName);
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employee.setSalary(Integer.parseInt(form.getSalary()));
		//文字列型からtimestamp型へ変換
		try{        
			Timestamp hireDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(form.getHireDate()).getTime());
			employee.setHireDate(hireDate);
		} catch (ParseException e){       
				e.printStackTrace();
		}
		employeeService.insert(employee);
		System.out.println(employee);
		System.out.println(form);
		return "redirect:/employees/insert";
	}
}
