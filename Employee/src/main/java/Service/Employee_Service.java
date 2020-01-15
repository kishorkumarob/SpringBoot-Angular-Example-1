package Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;

import Model.Employee;

public interface Employee_Service {

	
	public boolean saveEmployee(Employee employee);
	public List<Employee> getEmployees();
	public boolean deleteEmployee(Employee employee);
	public List<Employee> getEmployeeByID(Employee employee);
	public boolean updateEmployee(Employee employee);
	public List<Employee> getEmployeeByID(int employee_id);

}
