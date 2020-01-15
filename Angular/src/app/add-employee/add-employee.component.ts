import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import {FormControl,FormGroup,Validators} from '@angular/forms';
import { Employee } from '../employee';
@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  constructor(private employeeservice:EmployeeService) { }

  employee : Employee=new Employee();
  submitted = false;

  ngOnInit() {
    this.submitted=false;
  }

  employeesaveform=new FormGroup({
    employee_name:new FormControl('' , [Validators.required , Validators.minLength(5) ] ),
    employee_designation:new FormControl('',[Validators.required]),
    employee_department:new FormControl()
  });

  saveEmployee(saveEmployee){
    this.employee=new Employee();   
    this.employee.employee_name=this.EmployeeName.value;
    this.employee.employee_designation=this.EmployeeDesignation.value;
    this.employee.employee_department=this.EmployeeDepartment.value;
    this.submitted = true;
    this.save();
  }

  

  save() {
    this.employeeservice.createEmployee(this.employee)
      .subscribe(data => console.log(data), error => console.log(error));
    this.employee = new Employee();
  }

  get EmployeeName(){
    return this.employeesaveform.get('employee_name');
  }

  get EmployeeDesignation(){
    return this.employeesaveform.get('employee_designation');
  }

  get EmployeeDepartment(){
    return this.employeesaveform.get('employee_department');
  }

  addEmployeeForm(){
    this.submitted=false;
    this.employeesaveform.reset();
  }
}
