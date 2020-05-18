import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import { Injectable } from '@angular/core';
import { Observable, observable } from 'rxjs'
@Injectable()
export class UserService{
  client:  HttpClient
  constructor(client:HttpClient)
  {
      this.client=client;
  }

  baseUserUrl="http://localhost:8089/users";



  addUser(user:User):Observable<User>{
    let url=this.baseUserUrl+"/add";
    let result:Observable<User>= this.client.post<User>(url,user);
    return result;
    }
    
    fetchAllUsers():Observable<User[]>
    {
      let url=this.baseUserUrl;
      let observable:Observable<User[]> =this.client.get<User[]>(url);
      return observable;
    }

    findUserByUserId(userId:number):Observable<User>{
      let url=this.baseUserUrl+'/get/'+userId
      let observable:Observable<User> =this.client.get<User>(url);
      return observable;  
    }
  
  
    deleteUser(userId:number){
      let url=this.baseUserUrl+"/delete/"+userId;
      let result:Observable<boolean>=this.client.delete<boolean>(url);
      return result;
    }

    modifyUser(user:User,userId:number):Observable<User>{
      let url=this.baseUserUrl+"/modify/"+userId;
      let result:Observable<User>= this.client.put<User>(url,user);
      return result;
      }
    

}