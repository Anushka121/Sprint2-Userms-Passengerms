export class Passenger{
    
   
    uin:Number;
    pnr:Number;
    passengerName:String;
    passengerAge:Number;
    luggage:Number;
    gender:String;

    constructor(pnr:Number,uin:Number,passengerName:String,passengerAge:Number,luggage:Number,gender:String){
        this.pnr=pnr;
        this.uin=uin;
        this.passengerName=passengerName;
        this.passengerAge=passengerAge;
        this.gender=gender;
    }
}