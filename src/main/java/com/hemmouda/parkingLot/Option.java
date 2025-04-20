package com.hemmouda.parkingLot;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Option extends JFrame implements ActionListener {
   private JRadioButton car_radioButton;
   private JRadioButton truck_radioButton;
   private JRadioButton bike_radioButton;
   private char type_selected;
   private JPanel type_panel;
   private JTextField mark;
   private JTextField model;
   private JTextField plate_1;
   private JTextField plate_2;
   private JTextField plate_3;
   private JPanel plate;
   private JTextField option;
   private JLabel option_label;
   private JButton finish;
   private boolean showingAdd;
   private static Vehicle v_toModify;
   private static final Font F0 = new Font("Helvetica", 0, 12);
   private static final Font F1 = new Font("Helvetica", 0, 14);
   private static final Font F2 = new Font("Helvetica", 0, 16);
   private static final Border RB;
   private static final Border WB;
   private static final Border GB;

   static {
      RB = BorderFactory.createLineBorder(Color.RED, 4);
      WB = BorderFactory.createLineBorder(new Color(238, 238, 238), 4);
      GB = BorderFactory.createLineBorder(Color.GREEN, 4);
   }

   Option() {
      this.setSize(400, 400);
      this.setLocationRelativeTo((Component)null);
      this.setLayout(new GridLayout(6, 2, 5, 5));
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent arg0) {
            Option.this.back();
         }
      });
      JLabel l0 = new JLabel("Type: ", 0);
      l0.setFont(F2);
      this.add(l0);
      ButtonGroup type_group = new ButtonGroup();
      this.car_radioButton = new JRadioButton("Car");
      this.truck_radioButton = new JRadioButton("Truck");
      this.bike_radioButton = new JRadioButton("Bike");
      this.car_radioButton.addActionListener(this);
      this.car_radioButton.setFont(F0);
      this.truck_radioButton.addActionListener(this);
      this.truck_radioButton.setFont(F0);
      this.bike_radioButton.addActionListener(this);
      this.bike_radioButton.setFont(F0);
      type_group.add(this.car_radioButton);
      type_group.add(this.truck_radioButton);
      type_group.add(this.bike_radioButton);
      this.type_panel = new JPanel();
      this.type_panel.setLayout(new FlowLayout());
      this.type_panel.add(this.car_radioButton);
      this.type_panel.add(this.truck_radioButton);
      this.type_panel.add(this.bike_radioButton);
      this.add(this.type_panel);
      JLabel l1 = new JLabel("Mark: ", 0);
      l1.setFont(F2);
      this.add(l1);
      this.mark = new JTextField();
      this.mark.setFont(F1);
      this.add(this.mark);
      JLabel l2 = new JLabel("Model: ", 0);
      l2.setFont(F2);
      this.add(l2);
      this.model = new JTextField();
      this.model.setFont(F1);
      this.add(this.model);
      JLabel l3 = new JLabel("Plate: ", 0);
      l3.setFont(F2);
      this.add(l3);
      this.plate = new JPanel();
      this.plate.setLayout(new FlowLayout());
      this.plate_1 = new JTextField(3);
      this.plate_1.setFont(F0);
      this.plate_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Option.this.writingP1();
         }
      });
      this.plate_2 = new JTextField(2);
      this.plate_2.setFont(F0);
      this.plate_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Option.this.writingP2();
         }
      });
      this.plate_3 = new JTextField(2);
      this.plate_3.setFont(F0);
      this.plate_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Option.this.writingP3();
         }
      });
      this.plate.add(this.plate_1);
      this.plate.add(new JLabel("-", 0));
      this.plate.add(this.plate_2);
      this.plate.add(new JLabel("-", 0));
      this.plate.add(this.plate_3);
      this.add(this.plate);
      this.option_label = new JLabel("Please select a type..", 2);
      this.option_label.setFont(F1);
      this.add(this.option_label);
      this.option = new JTextField();
      this.add(this.option);
      this.finish = new JButton();
      this.finish.setFont(F2);
      this.finish.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Option.this.finish();
         }
      });
      this.add(this.finish);
      JButton back = new JButton("Return");
      back.setFont(F2);
      back.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Option.this.back();
         }
      });
      this.add(back);
   }

   public void showModify(Car c) {
      this.setTitle("Modify the Car with ID°:" + c.ID);
      this.type_selected = 'c';
      this.setOption();
      this.option.setText(String.valueOf(c.doors_number));
      this.genericShowModify(c);
   }

   public void showModify(Truck t) {
      this.setTitle("Modify the Truck with ID°:" + t.ID);
      this.type_selected = 't';
      this.setOption();
      this.option.setText(t.getValue());
      this.genericShowModify(t);
   }

   public void showModify(Bike b) {
      this.setTitle("Modify the Bike with ID°: " + b.ID);
      this.type_selected = 'b';
      this.setOption();
      this.option.setText(String.valueOf(b.max_speed));
      this.genericShowModify(b);
   }

   private void genericShowModify(Vehicle v) {
      v_toModify = v;
      this.showingAdd = false;
      this.finish.setText("Modify");
      this.enableButton(false);
      this.mark.setText(v.mark);
      this.writePLate(v.plate);
      this.model.setText(String.valueOf(v.model));
      this.setBorders(GB);
      this.setVisible(true);
   }

   public void showAdd() {
      this.showingAdd = true;
      this.setTitle("Park a Vehicle");
      this.finish.setText("Park");
      this.enableButton(true);
      this.setOption();
      this.model.setText("");
      this.clearPLate();
      this.mark.setText("");
      this.option.setText("");
      this.setBorders(WB);
      this.setVisible(true);
   }

   public void setBorders(Border b) {
      this.mark.setBorder(b);
      this.model.setBorder(b);
      this.plate.setBorder(b);
      this.option.setBorder(b);
      this.type_panel.setBorder(b);
   }

   private void finish() {
      Vehicle v = null;
      boolean mark_ok = false;
      boolean model_ok = false;
      boolean plate_ok = false;
      boolean option_ok = false;
      boolean excep = false;

      try {
         String mark_s = "";

         try {
            mark_s = valueOf(this.mark.getText());
            mark_ok = true;
         } catch (Exception var15) {
            excep = true;
         }

         int model_i = 0;

         try {
            model_i = Integer.valueOf(this.model.getText());
            model_ok = true;
         } catch (Exception var14) {
            excep = true;
         }

         String plate_s = "";

         try {
            plate_s = this.valueOfPlate();
            plate_ok = true;
         } catch (Exception var13) {
            excep = true;
         }

         int doors_number = 0;
         boolean hasTrailer = false;
         float max_speed = 0.0F;
         switch(this.type_selected) {
         case 'b':
            max_speed = Float.valueOf(this.option.getText());
            option_ok = true;
            break;
         case 'c':
            doors_number = Integer.valueOf(this.option.getText());
            option_ok = true;
            break;
         case 't':
            hasTrailer = valueOfBool(this.option.getText());
            option_ok = true;
            break;
         default:
            throw new Exception();
         }

         if (excep) {
            throw new Exception();
         }

         switch(this.type_selected) {
         case 'b':
            v = new Bike(mark_s, model_i, plate_s, max_speed, Main.getTime());
            break;
         case 'c':
            v = new Car(mark_s, model_i, plate_s, doors_number, Main.getTime());
            break;
         case 't':
            v = new Truck(mark_s, model_i, plate_s, hasTrailer, Main.getTime());
         }
      } catch (Exception var16) {
         if (mark_ok) {
            this.mark.setBorder(GB);
         } else if (!mark_ok) {
            this.mark.setBorder(RB);
         }

         if (model_ok) {
            this.model.setBorder(GB);
         } else if (!model_ok) {
            this.model.setBorder(RB);
         }

         if (plate_ok) {
            this.plate.setBorder(GB);
         } else if (!plate_ok) {
            this.plate.setBorder(RB);
         }

         if (option_ok) {
            this.option.setBorder(GB);
         } else if (!option_ok) {
            if (this.showingAdd && this.type_selected != 'c' && this.type_selected != 't' && this.type_selected != 'b') {
               this.type_panel.setBorder(RB);
            } else {
               this.option.setBorder(RB);
               this.type_panel.setBorder(GB);
            }
         }

         JOptionPane.showMessageDialog((Component)null, " Please fill out correct informations.", "Can't proceed", 2);
      }

      if (v != null) {
         if (this.showingAdd) {
            Main.list.add(v);
         } else {
            v_toModify.copy((Vehicle)v);
         }

         this.back();
      }

   }

   private void back() {
      Frame.show();
      this.setVisible(false);
   }

   private void enableButton(boolean enable) {
      this.car_radioButton.setEnabled(enable);
      this.truck_radioButton.setEnabled(enable);
      this.bike_radioButton.setEnabled(enable);
   }

   public void actionPerformed(ActionEvent a) {
      if (a.getSource() == this.car_radioButton) {
         this.type_selected = 'c';
      } else if (a.getSource() == this.truck_radioButton) {
         this.type_selected = 't';
      } else if (a.getSource() == this.bike_radioButton) {
         this.type_selected = 'b';
      }

      this.setOption();
   }

   private void setOption() {
      switch(this.type_selected) {
      case 'b':
         this.option_label.setText(" Maximum speed? (80,125,..)");
         this.bike_radioButton.setSelected(true);
         break;
      case 'c':
         this.option_label.setText(" Number of doors? (2,4,..)");
         this.car_radioButton.setSelected(true);
         break;
      case 't':
         this.option_label.setText(" Has a trailer? (Y/N,..)");
         this.truck_radioButton.setSelected(true);
         break;
      default:
         this.option_label.setText(" Please select a type..");
      }

   }

   private static String valueOf(String s) throws Exception {
      if (s.length() == 0) {
         throw new Exception();
      } else {
         return s;
      }
   }

   private static boolean valueOfBool(String s) throws Exception {
      s = s.toLowerCase();
      if (!s.equals("true") && !s.equals("yes") && !s.equals("1") && !s.equals("y")) {
         if (!s.equals("false") && !s.equals("no") && !s.equals("0") && !s.equals("n")) {
            throw new Exception();
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   private void writePLate(String s) {
      if (s.length() == 10) {
         this.plate_1.setText(s.substring(0, 4));
         this.plate_2.setText(s.substring(5, 7));
         this.plate_3.setText(s.substring(8, 10));
      } else {
         JOptionPane.showMessageDialog((Component)null, "An Error occured with the Plate..", "Error", 0);
      }

   }

   private String valueOfPlate() throws Exception {
      String p1 = this.plate_1.getText();
      String p2 = this.plate_2.getText();
      String p3 = this.plate_3.getText();
      if (p1.length() != 4) {
         throw new Exception();
      } else if (p2.length() != 2) {
         throw new Exception();
      } else if (p3.length() != 2) {
         throw new Exception();
      } else {
         return p1 + "-" + p2 + "-" + p3;
      }
   }

   private void writingP1() {
      String s = this.plate_1.getText();
      if (s.length() > 4) {
         this.plate_1.setText(s.substring(0, 4));
         this.plate_2.setText(String.valueOf(s.charAt(4)));
         this.plate_2.requestFocus();
      }

   }

   private void writingP2() {
      String s = this.plate_2.getText();
      if (s.length() > 2) {
         this.plate_2.setText(s.substring(0, 2));
         this.plate_3.setText(String.valueOf(s.charAt(2)));
         this.plate_3.requestFocus();
      }

   }

   private void writingP3() {
      String s = this.plate_3.getText();
      if (s.length() > 2) {
         this.plate_3.setText(s.substring(0, 2));
         this.option.requestFocus();
      }

   }

   private void clearPLate() {
      this.plate_1.setText("");
      this.plate_2.setText("");
      this.plate_3.setText("");
   }

   public void updatePlate() {
      this.writingP1();
      this.writingP2();
      this.writingP3();
   }
}
