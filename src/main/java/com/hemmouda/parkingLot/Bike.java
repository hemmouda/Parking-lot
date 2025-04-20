package com.hemmouda.parkingLot;

public class Bike extends Vehicle {
   public float max_speed;

   Bike(String mark, int model, String plate, float max_speed, String parked_time) {
      super(mark, model, plate, parked_time);
      this.max_speed = max_speed;
   }

   public void copy(Vehicle v) {
      Bike b = (Bike)v;
      this.mark = b.mark;
      this.model = b.model;
      this.plate = b.plate;
      this.max_speed = b.max_speed;
   }

   public String getDescription() {
      return "Bike " + super.getDescription() + "\n\n\tMaximum speed: " + this.max_speed;
   }

   public String toString() {
      return "Bike ID°: " + super.ID;
   }

   public void callModify() {
      Frame.frame.showModify(this);
   }
}
