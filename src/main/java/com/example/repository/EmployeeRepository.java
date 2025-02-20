package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;
import com.example.form.SearchEmployeeForm;

import lombok.RequiredArgsConstructor;

/**
 * employeesテーブルを操作するリポジトリ.
 * 
 * @author igamasayuki
 * 
 */
@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

	/**
	 * Employeeオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	private final NamedParameterJdbcTemplate template;

	/**
	 * 従業員一覧情報を入社日順で取得します.
	 * 
	 * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します
	 */
	public List<Employee> findAll(SearchEmployeeForm form) {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT ");
		sqlBuilder.append(" e.id ");
		sqlBuilder.append(" ,e.name ");
		sqlBuilder.append(" ,e.image ");
		sqlBuilder.append(" ,e.gender ");
		sqlBuilder.append(" ,e.hire_date ");
		sqlBuilder.append(" ,e.mail_address ");
		sqlBuilder.append(" ,e.zip_code ");
		sqlBuilder.append(" ,e.address ");
		sqlBuilder.append(" ,e.telephone ");
		sqlBuilder.append(" ,e.salary ");
		sqlBuilder.append(" ,e.characteristics ");
		sqlBuilder.append(" ,e.dependents_count  ");
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(" employees e ");
		sqlBuilder.append(" WHERE 1 = 1 ");

		String nameKeyWord = form.getName();
		if (nameKeyWord!=null && !nameKeyWord.equals("")) {
			sqlBuilder.append(" AND ");
			sqlBuilder.append(" e.name LIKE '%");
			sqlBuilder.append(nameKeyWord);
			sqlBuilder.append("%' ");
		}

		sqlBuilder.append(" ORDER BY ");
		sqlBuilder.append(" e.hire_date desc ;");
		
		String findAllSql = sqlBuilder.toString();
		List<Employee> developmentList = template.query(findAllSql, EMPLOYEE_ROW_MAPPER);

		return developmentList;
	}
	/**
	 * 従業員一覧情報を入社日順で取得します.(ページング機能付き)
	 * @param ofset
	 * @param limit
	 * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します
	 */
	public List<Employee> findAllWithPaginate(SearchEmployeeForm form,int limit,int ofset) {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT ");
		sqlBuilder.append(" e.id ");
		sqlBuilder.append(" ,e.name ");
		sqlBuilder.append(" ,e.image ");
		sqlBuilder.append(" ,e.gender ");
		sqlBuilder.append(" ,e.hire_date ");
		sqlBuilder.append(" ,e.mail_address ");
		sqlBuilder.append(" ,e.zip_code ");
		sqlBuilder.append(" ,e.address ");
		sqlBuilder.append(" ,e.telephone ");
		sqlBuilder.append(" ,e.salary ");
		sqlBuilder.append(" ,e.characteristics ");
		sqlBuilder.append(" ,e.dependents_count  ");
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(" employees e ");
		sqlBuilder.append(" WHERE 1 = 1 ");

		String nameKeyWord = form.getName();
		if (nameKeyWord!=null && !nameKeyWord.equals("")) {
			sqlBuilder.append(" AND ");
			sqlBuilder.append(" e.name LIKE '%");
			sqlBuilder.append(nameKeyWord);
			sqlBuilder.append("%' ");
		}

		sqlBuilder.append(" ORDER BY ");
		sqlBuilder.append(" e.hire_date desc ");
		sqlBuilder.append(" LIMIT ");
		sqlBuilder.append(limit);
		sqlBuilder.append(" OFFSET ");
		sqlBuilder.append(ofset);

		sqlBuilder.append(";");
		
		String findAllSql = sqlBuilder.toString();
		List<Employee> developmentList = template.query(findAllSql, EMPLOYEE_ROW_MAPPER);

		return developmentList;
	}
	/**
	 * 従業員一覧情報を入社日順で取得します.(ページング機能付き)
	 * @param ofset
	 * @param limit
	 * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します
	 */
	public List<Employee> findEmployeeNameByName(SearchEmployeeForm form,int limit) {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT ");
		sqlBuilder.append(" e.id ");
		sqlBuilder.append(" ,e.name ");
		sqlBuilder.append(" ,e.image ");
		sqlBuilder.append(" ,e.gender ");
		sqlBuilder.append(" ,e.hire_date ");
		sqlBuilder.append(" ,e.mail_address ");
		sqlBuilder.append(" ,e.zip_code ");
		sqlBuilder.append(" ,e.address ");
		sqlBuilder.append(" ,e.telephone ");
		sqlBuilder.append(" ,e.salary ");
		sqlBuilder.append(" ,e.characteristics ");
		sqlBuilder.append(" ,e.dependents_count  ");
		sqlBuilder.append(" FROM ");
		sqlBuilder.append(" employees e ");
		sqlBuilder.append(" WHERE 1 = 1 ");

		String nameKeyWord = form.getName();
		if (nameKeyWord!=null && !nameKeyWord.equals("")) {
			sqlBuilder.append(" AND ");
			sqlBuilder.append(" e.name LIKE '%");
			sqlBuilder.append(nameKeyWord);
			sqlBuilder.append("%' ");
		}

		sqlBuilder.append(" ORDER BY ");
		sqlBuilder.append(" e.hire_date desc ");
		sqlBuilder.append(" LIMIT ");
		sqlBuilder.append(limit);

		sqlBuilder.append(";");
		
		String findAllSql = sqlBuilder.toString();
		List<Employee> developmentList = template.query(findAllSql, EMPLOYEE_ROW_MAPPER);

		return developmentList;
	}

	/**
	 * 主キーから従業員情報を取得します.
	 * 
	 * @param id 検索したい従業員ID
	 * @return 検索された従業員情報
	 * @exception org.springframework.dao.DataAccessException 従業員が存在しない場合は例外を発生します
	 */
	public Employee findById(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee development = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

		return development;
	}

	/**
	 * 従業員情報を変更します.
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		String updateSql = "UPDATE employees SET dependents_count=:dependentsCount WHERE id=:id";
		template.update(updateSql, param);
	}

	/**
	 * 従業員を新規追加
	 * @param Employee
	 */
	public void insert(Employee employee){
		String insertSql = """
				INSERT INTO 
				  employees
				  (id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count)
				VALUES
				  (:id,:name,:image,:gender,:hireDate,:mailAddress,:zipCode,:address,:telephone,:salary,:characteristics,:dependentsCount)
				""";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(insertSql, param);
	}

	/**
	 * 従業員のIDの最大値を取得
	 */
	public int getMaxId(){
		String getMaxIdSql = """
				SELECT
				  max(id)
				FROM 
				  employees
				""";
		SqlParameterSource param = new MapSqlParameterSource();
		return template.queryForObject(getMaxIdSql, param,Integer.class);
	}
	/**
	 * 従業員の件数を取得する
	 */
	public int getNumberOfEmployees(){
		String getMaxIdSql = """
				SELECT
				  count(id)
				FROM 
				  employees
				""";
		SqlParameterSource param = new MapSqlParameterSource();
		return template.queryForObject(getMaxIdSql, param,Integer.class);
	}
}
