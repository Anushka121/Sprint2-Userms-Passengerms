import { Component, OnInit } from '@angular/core';
import {Passenger} from '../model/passenger';
import { PassengerService } from '../services/passengerservices';
import { Observable } from 'rxjs';
@Component({
  
  selector: 'app-list-passenger',
  templateUrl: './list-passenger.component.html',
  styleUrls: ['./list-passenger.component.css']
})
export class ListPassengerComponent implements OnInit {

passengers:Passenger[]=[];


  service:PassengerService
  constructor(service:PassengerService) {
    this.service=service;
    let observable:Observable<Passenger[]>=this.service.fetchAllpassengers();
    observable.subscribe(
      pass=>{
        this.passengers=pass;
       console.log("inside success callback ="+this.passengers.length);
      },
      err=>console.log(err)
      );
    }

  
  foundPassenger:Passenger=null;
  foundStatus=null;

  
  ngOnInit(): void {
  }
  
  findPassenger(form:any):void{
    let details=form.value;
    let uin=details.uin;
    let fetched:Observable<Passenger> =this.service.findPassengerByUin(uin)
   fetched.subscribe(
    Passenger=>{
    this.foundPassenger=Passenger; 
    this.foundStatus="found";
     },
    err=>{
      this.foundStatus="notfound";
     console.log("err while fetching ="+err);  
     }
   );    
  
  }

  removePassenger(uin:number){
    let result:Observable<boolean>=this.service.deletePassenger(uin);
    result.subscribe(pass=>{
        this.removeLocalEmployee(uin);
    },err=>{
     console.log("err in deleting ="+err);
    })
      }

 
removeLocalEmployee(uin:number){
  let foundIndex=-1;
  for(let i=0;i<this.passengers.length;i++){
    let pass=this.passengers[i];
    if(pass.uin===uin){
      foundIndex=i;
      break;
    }
  }
  if(foundIndex<0){
    return;
  }
  this.passengers.splice(foundIndex,1);
}

  
      }
      



  

