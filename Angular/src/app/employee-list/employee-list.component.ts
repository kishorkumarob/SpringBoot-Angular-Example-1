import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Observable,Subject } from "rxjs";

import {FormControl,FormGroup,Validators} from '@angular/forms';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

 constructor(private employeeservice:EmployeeService) { }

  employeesArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any>= new Subject();


  employees: Observable<Employee[]>;
  employee : Employee=new Employee();
  deleteMessage=false;
  employeelist:any;
  isupdated = false;    
 

  ngOnInit() {
    this.isupdated=false;
    this.dtOptions = {
      pageLength: 6,
      stateSave:true,
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],
      processing: true
    };   
    this.employeeservice.getEmployeeList().subscribe(data =>{
    this.employees =data;
    this.dtTrigger.next();
    })
  }
  
  deleteEmployee(id: number) {
    this.employeeservice.deleteEmployee(id)
      .subscribe(
        data => {
          console.log(data);
          this.deleteMessage=true;
          this.employeeservice.getEmployeeList().subscribe(data =>{
            this.employees =data
            })
        },
        error => console.log(error));
  }


  updateEmployee(id: number){
    this.employeeservice.getEmployee(id)
      .subscribe(
        data => {
          this.employeelist=data           
        },
        error => console.log(error));
  }

  downloadEmployee(id: number){
    this.employeeservice.downloadEmployee(id)
      .subscribe(response => {
        const filename = response.headers.get('filename')
            });
        error => console.log(error);
        
  }

  employeeupdateform=new FormGroup({
    employee_id:new FormControl(),
    employee_name:new FormControl(),
    employee_designation:new FormControl(),
    employee_department:new FormControl()
  });

  updateEmp(updemp){
    this.employee=new Employee(); 
   this.employee.employee_id=this.EmployeeId.value;
   this.employee.employee_name=this.EmployeeName.value;
   this.employee.employee_designation=this.EmployeeDesignation.value;
   this.employee.employee_department=this.EmployeeDepartment.value;
   console.log(this.EmployeeDepartment.value);
   

   this.employeeservice.updateEmployee(this.employee.employee_id,this.employee).subscribe(
    data => {     
      this.isupdated=true;
      this.employeeservice.getEmployeeList().subscribe(data =>{
        this.employees =data
        })
    },
    error => console.log(error));
  }

  get EmployeeName(){
    return this.employeeupdateform.get('employee_name');
  }

  get EmployeeDesignation(){
    return this.employeeupdateform.get('employee_designation');
  }

  get EmployeeDepartment(){
    return this.employeeupdateform.get('employee_department');
  }

  get EmployeeId(){
    return this.employeeupdateform.get('employee_id');
  }

  changeisUpdate(){
    this.isupdated=false;
  }
}
