import { Component, OnInit } from '@angular/core';
import { User} from '../model/user';
import { UserService } from '../service/userservice';
import {Observable} from 'rxjs'
import{ Router} from '@angular/router';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users:User[]=[];
  ngOnInit(): void {
  }


service:UserService;
constructor(service:UserService,private router:Router) {

  this.service=service;
  let observable:Observable<User[]>=this.service.fetchAllUsers();
  observable.subscribe(
    user=>{
      this.users=user;
      console.log("inside success callback ="+this.users.length);
    },
    err=>console.log(err)
    );
  }


  
  
  
   
   //deleting
    removeUser(userId:number)
     {
    let result:Observable<boolean>=this.service.deleteUser(userId);
    result.subscribe(user=>{
        this.removeLocalUser(userId);
    },err=>{
     console.log("err in deleting ="+err);
    })   
  }


 
removeLocalUser(userId:number)
{
  let foundIndex=-1;
  for(let i=0;i<this.users.length;i++){
    let user=this.users[i];
    if(user.userId===userId){
      foundIndex=i;
      break;
    }
  }
  if(foundIndex<0){
    return;
  }
  this.users.splice(foundIndex,1);
}


//updating

update()
{
  this.router.navigate(['../modify-user'])
}



  }
