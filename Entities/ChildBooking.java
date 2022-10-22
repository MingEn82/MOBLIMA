package Entities;



public class ChildBooking extends Booking{

    


    
    //override super class method
    public void calBookingPrice(){


        //need to first check system settings for variables such as price for cinematype etc etc.
        //afterwards, use TID(for date), cinemaType, movieType + the discount for a child price.
        //then set the price as accordingly.
        this.setPrice(5);
    }
}
