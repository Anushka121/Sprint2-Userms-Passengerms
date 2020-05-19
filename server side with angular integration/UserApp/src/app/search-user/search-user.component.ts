import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/userservice';
import { Observable} from 'rxjs';
import { User} from '../model/user';

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent implements OnInit {
service:UserService
  constructor(service:UserService) {
    this.service=service
   }

  ngOnInit(): void {
  }

foundUser=null;
foundStatus=null;

  findUser(form:any):void
  {
    let details=form.value;
    let userId=details.userId;
    let fetched:Observable<User> =this.service.findUserByUserId(userId)
   fetched.subscribe(
    user=>{
    this.foundUser=user;
    this.foundStatus="found";
     },
    err=>{
      this.foundStatus="notfound";
     console.log("err while fetching ="+err);  
     }
   );    

    }
   
}
