package Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import Model.Employee;
import Service.Employee_Service;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class Controller {
	
	@Autowired
	private Employee_Service employeeservice;
	
	@GetMapping
    public String App(){
        return "App is running";
    }
	
	@PostMapping("save-employee")
	public boolean saveEmployee(@RequestBody Employee employee) {
		 return employeeservice.saveEmployee(employee);
		
	}
	
	@GetMapping("employees-list")
	public List<Employee> allemployees() {
		 return employeeservice.getEmployees();
	}
	
	
	@DeleteMapping("delete-employee/{employee_id}")
	public boolean deleteEmployee(@PathVariable("employee_id") int employee_id,Employee employee) {
		employee.setEmployee_id(employee_id);
		return employeeservice.deleteEmployee(employee);
	}

	@GetMapping("employee/{employee_id}")
	public List<Employee> allemployeeByID(@PathVariable("employee_id") int employee_id,Employee employee) {
		 employee.setEmployee_id(employee_id);
		 return employeeservice.getEmployeeByID(employee);
		
	}
	
	@PostMapping("update-employee/{employee_id}")
	public boolean updateEmployee(@RequestBody Employee employee,@PathVariable("employee_id") int employee_id) {
		employee.setEmployee_id(employee_id);
		return employeeservice.updateEmployee(employee);
	}
	
	@GetMapping("download-employee")
	@ResponseStatus(HttpStatus.OK)
    public @ResponseBody void downloadEmployee(HttpServletResponse response) throws Exception {

        String filename = "employee.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
		response.setHeader("filename", filename);

        //create a csv writer
        StatefulBeanToCsv<Employee> writer = new StatefulBeanToCsvBuilder<Employee>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
         writer.write(employeeservice.getEmployees());
			
    }
	
//	@GetMapping("/api/file/{keyname}")
//	public ResponseEntity<byte[]> downloadFile(@PathVariable String keyname) {
//		ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(keyname);
//	
//		return ResponseEntity.ok()
//					.contentType(contentType(keyname))
//					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
//					.body(downloadInputStream.toByteArray());	
//	}
	
}
