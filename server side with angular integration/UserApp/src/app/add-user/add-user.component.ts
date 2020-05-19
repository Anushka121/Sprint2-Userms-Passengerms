import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/userservice';
import {User} from '../model/user';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
service:UserService
  constructor(service:UserService) {
    this.service=service;
   }

  ngOnInit(): void {
  }

userType="user";
  addedUser:User=null;
  addUser(form:any){
    let details=form.value;
    let name=details.name;
    let password=details.password;
    let contact=details.contact;
    let email=details.email;

    this.addedUser=new User();
    this.addedUser.userType=this.userType
    this.addedUser.userName=name;
    this.addedUser.userPassword=password;
    this.addedUser.userPhone=contact;
    this.addedUser.email=email;
    let result=this.service.addUser(this.addedUser); // adding to the store
    result.subscribe((user:User)=>{
      this.addedUser=user;
    },
    err=>{
    console.log("err="+err);
    } );
    form.reset();
}
}
