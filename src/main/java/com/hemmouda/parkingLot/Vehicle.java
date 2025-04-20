package com.hemmouda.parkingLot;

public class Vehicle {
   public String mark;
   public int model;
   public String plate;
   public final String parked_time;
   public final int ID;
   private static int counter = 0;

   Vehicle(String mark, int model, String plate, String parked_time) {
      this.ID = counter++;
      this.mark = mark;
      this.model = model;
      this.plate = plate;
      this.parked_time = parked_time;
   }

   public String getDescription() {
      return "ID: " + this.ID + "\tParked at: " + this.parked_time + "\n\n\tParked for: " + this.getElapsedTime() + "\n\n\n\tMark: " + this.mark + "\n\n\tModel: " + this.model + "\n\n\tPlate: " + this.plate;
   }

   public void callModify() {
   }

   public void copy(Vehicle v) {
   }

   private String getElapsedTime() {
      try {
         int ch = Main.time.getHour();
         int cm = Main.time.getMinute();
         int cs = Main.time.getSecond();
         int h = Integer.valueOf(this.parked_time.substring(0, this.parked_time.indexOf("-")));
         int m = Integer.valueOf(this.parked_time.substring(this.parked_time.indexOf("-") + 1, this.parked_time.lastIndexOf("-")));
         int s = Integer.valueOf(this.parked_time.substring(this.parked_time.lastIndexOf("-") + 1, this.parked_time.length()));
         int ps = 0;
         int pm = 0;
         int ph = 0;
         if (cs - s < 0) {
            cs += 60;
            --cm;
         }

         ps = cs - s;
         if (cm - m < 0) {
            cm += 60;
            --ch;
         }

         pm = cm - m;
         ph = ch - h;
         return ph + " : " + pm + " : " + ps;
      } catch (Exception var10) {
         return "Unable to determine: " + var10.getMessage();
      }
   }
}
