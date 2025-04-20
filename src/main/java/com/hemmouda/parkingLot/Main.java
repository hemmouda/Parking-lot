package com.hemmouda.parkingLot;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Main {
   public static ArrayList<Vehicle> list;
   public static LocalDateTime time;

   public static void main(String[] args) {
      time = LocalDateTime.now();
      list = new ArrayList();
      File file = new File("logs");
      if (!file.exists()) {
         file.mkdir();
      }

      Frame.initialize();
      Timer timer = new Timer(25, new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Main.time = LocalDateTime.now();
            Frame.updateDisplay();
            Frame.frame.updatePlate();
         }
      });
      timer.start();
   }

   public static String getDisplayedInfo() {
      return "\n " + time.getHour() + " : " + time.getMinute() + " : " + time.getSecond() + "\t ||\tVehicle Parked: " + list.size() + "\n\n    --------------------------------";
   }

   public static String getTime() {
      return time.getHour() + "-" + time.getMinute() + "-" + time.getSecond();
   }

   public static Vehicle getVehicleWithId(int id) {
      for(int i = 0; i < list.size(); ++i) {
         if (((Vehicle)list.get(i)).ID == id) {
            return (Vehicle)list.get(i);
         }
      }

      return null;
   }

   public static void deleteVehicleWithId(int id) {
      for(int i = 0; i < list.size(); ++i) {
         if (((Vehicle)list.get(i)).ID == id) {
            list.remove(i);
         }
      }

   }

   public static void save() {
      String t = time.getHour() + "-" + time.getMinute() + "-" + time.getSecond();
      File file = new File("logs/Log " + t + ".txt");

      try {
         file.createNewFile();
         FileWriter fw = new FileWriter(file);
         fw.write("The log from " + t + " (*): \n\n");

         for(int i = 0; i < list.size(); ++i) {
            fw.write(((Vehicle)list.get(i)).getDescription() + "\n\n");
         }

         fw.write("\n(*): the Parked time is counted up to the time of save.");
         fw.close();
         JOptionPane.showMessageDialog((Component)null, " The Log has been saved under the path: \n " + file.getAbsolutePath() + "\n\t\t(Or click the 'Open logs' button to directly acces it..)", "Saved", 1);
      } catch (Exception var4) {
         JOptionPane.showMessageDialog((Component)null, " " + var4.getMessage() + "\n An Error occured, couldn't Save.", "Can't proceed", 2);
      }

   }
}
