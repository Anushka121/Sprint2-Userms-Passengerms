import { Component, OnInit } from '@angular/core';
import {Passenger} from '../model/passenger'
import { generate } from 'rxjs';
import { PassengerService } from '../services/passengerservices';

@Component({
  selector: 'app-add-passenger',
  templateUrl: './add-passenger.component.html',
  styleUrls: ['./add-passenger.component.css']
})
export class AddPassengerComponent implements OnInit {

  service:PassengerService;
  
  constructor(service:PassengerService) {
    this.service=service;
   }
  passengers:Passenger[]=[];
    pnr=10000000;
   generate()
   {
     this.pnr++
 
   }
  ngOnInit(): void {
  }
  addedPassenger: Passenger=null;

  addPassenger(passengerForm:any){

    let details = passengerForm.value;
    let uin  = details.uin;
    let pnr=this.generate();
    let pname = details.pname;
    let passenger_age = details.passenger_age;
    let luggage  = details.luggage;
    let gender= details.gender;
    this.addedPassenger=new Passenger();
    this.addedPassenger.uin=uin;
    this.addedPassenger.pnr=this.pnr;
    this.addedPassenger.passengerName=pname;
    this.addedPassenger.passengerAge=passenger_age;
    this.addedPassenger.luggage=luggage;
    this.addedPassenger.gender=gender;
    this.passengers.push(this.addedPassenger);

    let result=this.service.addPassenger(this.passengers);
    
    result.subscribe((passenger:Passenger[])=>{
      this.passengers=passenger
    },
    err=>{
      console.log("err="+err);
      } );
      passengerForm.reset();
  }

}

