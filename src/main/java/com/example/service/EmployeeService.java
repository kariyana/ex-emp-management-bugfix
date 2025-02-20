package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.form.SearchEmployeeForm;
import com.example.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を全件取得します.
	 * 
	 * @return 従業員情報一覧
	 */
	public List<Employee> showList(SearchEmployeeForm form) {
		List<Employee> employeeList = employeeRepository.findAll(form);
		return employeeList;
	}
	/**
	 * 従業員情報を全件取得します.(ページング機能)
	 * @param limit
	 * @param ofset
	 * @return 従業員情報一覧
	 */
	public List<Employee> getEmployeeWithPaginate(SearchEmployeeForm form,int limit, int ofset) {
		List<Employee> employeeList = employeeRepository.findAllWithPaginate(form,limit,ofset);
		return employeeList;
	}
	/**
	 * 従業員名のみ取得します.（オートコンプリート）
	 * @param limit
	 * @return 従業員名前一覧
	 */
	public List<Employee> findEmployeeNameByName(SearchEmployeeForm form,int limit, int ofset) {
		List<Employee> employeeNames = employeeRepository.findEmployeeNameByName(form,limit);
		return employeeNames;
	}

	/**
	 * 従業員情報を取得します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 * @throws org.springframework.dao.DataAccessException 検索されない場合は例外が発生します
	 */
	public Optional<Employee> showDetail(Integer id) {
		Employee employee = employeeRepository.findById(id);
		return Optional.ofNullable(employee);
	}

	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee 更新した従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}

	/**
	 * 従業員を新規追加する
	 * @param employee 追加した従業員情報
	 */
	@Synchronized
	public void insert(Employee employee){
		//新規追加するIDを取得する
		int employeeId = employeeRepository.getMaxId()+1;
		employee.setId(employeeId);

		employeeRepository.insert(employee);
	}

	   /**
     * 従業員の総数を取得する
     * @return numberOfEmployees int
     * 
     */
    public int getNumberOfEmployees(){
        return employeeRepository.getNumberOfEmployees();
    }
}
