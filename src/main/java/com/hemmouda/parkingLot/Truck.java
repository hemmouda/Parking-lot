package com.hemmouda.parkingLot;

public class Truck extends Vehicle {
   public boolean hasTrailer;

   Truck(String mark, int model, String plate, boolean hasTrailer, String parked_time) {
      super(mark, model, plate, parked_time);
      this.hasTrailer = hasTrailer;
   }

   public void copy(Vehicle v) {
      Truck t = (Truck)v;
      this.mark = t.mark;
      this.model = t.model;
      this.plate = t.plate;
      this.hasTrailer = t.hasTrailer;
   }

   public String getDescription() {
      return "Truck " + super.getDescription() + "\n\n\tHas a Trailer: " + this.getValue();
   }

   public String getValue() {
      return this.hasTrailer ? "Yes" : "No";
   }

   public String toString() {
      return "Truck ID°: " + super.ID;
   }

   public void callModify() {
      Frame.frame.showModify(this);
   }
}
