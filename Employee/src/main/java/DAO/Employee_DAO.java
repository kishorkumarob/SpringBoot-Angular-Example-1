package DAO;

import java.util.List;

import Model.Employee;

public interface Employee_DAO {

	public boolean saveEmployee(Employee employee);
	public List<Employee> getEmployees();
	public boolean deleteEmployee(Employee employee);
	public List<Employee> getEmployeeByID(Employee employee);
	public boolean updateEmployee(Employee employee);
	public List<Employee> getEmployeeByID(int employee_id);
}
