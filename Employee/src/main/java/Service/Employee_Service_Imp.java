package Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO.Employee_DAO;
import Model.Employee;

@Service
@Transactional
public class Employee_Service_Imp implements Employee_Service {
 
	@Autowired
	private Employee_DAO employeedao;
	
	private static final String FILE_DIRECTORY = "D://";
	
	@Override
	public boolean saveEmployee(Employee employee) {
		return employeedao.saveEmployee(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeedao.getEmployees();
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		return employeedao.deleteEmployee(employee);
	}

	@Override
	public List<Employee> getEmployeeByID(Employee employee) {
		return employeedao.getEmployeeByID(employee);
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		return employeedao.updateEmployee(employee);
	}
	
	public List<Employee> getEmployeeByID(int employee_id) {
		return employeedao.getEmployeeByID(employee_id);
	}

}
