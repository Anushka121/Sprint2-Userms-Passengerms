import { Component, OnInit } from '@angular/core';
import {Passenger} from '../model/passenger'
@Component({
  selector: 'app-add-passenger',
  templateUrl: './add-passenger.component.html',
  styleUrls: ['./add-passenger.component.css']
})
export class AddPassengerComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  addedPassenger: Passenger=null;

  addPassenger(passengerForm:any){
    let details = passengerForm.value;
    let uin  = details.uin;
    let pnr=details.pnr;
    let pname = details.pname;
    let passenger_age = details.passenger_age;
    let luggage  = details.luggage;
    let gender= details.gender;
    
    let passenger = new Passenger(uin,pnr,pname,passenger_age,luggage,gender);
    this.addedPassenger=passenger;
    passengerForm.reset();
  }

}

