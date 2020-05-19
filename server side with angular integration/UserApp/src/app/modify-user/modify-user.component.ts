import { Component, OnInit } from '@angular/core';
import {User} from '../model/user';
import { UserService} from '../service/userservice';


@Component({
  selector: 'app-modify-user',
  templateUrl: './modify-user.component.html',
  styleUrls: ['./modify-user.component.css']
})
export class ModifyUserComponent implements OnInit {
service:UserService
  constructor(service:UserService) {
    this.service=service;
   }

  ngOnInit(): void {
  }
  
  modifiedUser:User=null;

  modifyUser(form:any){
    let details=form.value;
    let id=details.id;
    let type=details.type;
    let name=details.name;
    let password=details.password;
    let contact=details.contact;
    let email=details.email;

    this.modifiedUser=new User();
    this.modifiedUser.userId=id;
    this.modifiedUser.userType=type;
    this.modifiedUser.userName=name;
    this.modifiedUser.userPassword=password;
    this.modifiedUser.userPhone=contact;
    this.modifiedUser.email=email;
    let result=this.service.modifyUser(this.modifiedUser,id) // adding to the store
    result.subscribe((user:User)=>{
      
    },
    err=>{
    console.log("err="+err);
    } );
    form.reset();
}
}