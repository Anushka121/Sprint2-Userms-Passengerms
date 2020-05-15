import { Injectable } from '@angular/core';
import { Observable, observable } from 'rxjs';
import {Passenger} from '../model/passenger';
import {HttpClient} from '@angular/common/http';
@Injectable()
export class PassengerService{
    client:HttpClient ;
  constructor(client:HttpClient ){
  this.client=client;
  }
  basePassengerUrl="http://localhost:8086/passengers";

  addPassenger(passenger:Passenger[]): Observable<Passenger[]>{
    let url=this.basePassengerUrl+"/add";
    let result:Observable<Passenger[]>= this.client.post<Passenger[]>(url,passenger)
    return result;
    }

  fetchAllpassengers():Observable<Passenger[]>
  {
    let url=this.basePassengerUrl;
    let observable:Observable<Passenger[]> =this.client.get<Passenger[]>(url);
    return observable;
  }

  findPassengerByUin(uin:number):Observable<Passenger>{
    let url=this.basePassengerUrl+'/get/'+uin;
    let observable:Observable<Passenger> =this.client.get<Passenger>(url);
    return observable;  
  }


  deletePassenger(uin:number){
    let url=this.basePassengerUrl+"/delete/"+uin;
    let result:Observable<boolean>=this.client.delete<boolean>(url);
    return result;
  }

}