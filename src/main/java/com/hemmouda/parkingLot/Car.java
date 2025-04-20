package com.hemmouda.parkingLot;

public class Car extends Vehicle {
   public int doors_number;

   Car(String mark, int model, String plate, int doors_number, String parked_time) {
      super(mark, model, plate, parked_time);
      this.doors_number = doors_number;
   }

   public void copy(Vehicle v) {
      Car c = (Car)v;
      this.mark = c.mark;
      this.model = c.model;
      this.plate = c.plate;
      this.doors_number = c.doors_number;
   }

   public String toString() {
      return "Car ID°: " + super.ID;
   }

   public String getDescription() {
      return "Car " + super.getDescription() + "\n\n\tNumber of Doors: " + this.doors_number;
   }

   public void callModify() {
      Frame.frame.showModify(this);
   }
}
