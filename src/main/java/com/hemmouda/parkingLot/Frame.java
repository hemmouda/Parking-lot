package com.hemmouda.parkingLot;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Frame {
   static JList<String> list;
   static JTextArea display;
   private static JButton add;
   private static JButton modify;
   private static JButton delete;
   private static JButton save;
   public static Option frame;
   private static int selected_vID = -1;
   private static int selected_index = -2;
   private static final String default_display = "\n\n\t No vehicle selected.";

   public static void initialize() {
      JFrame frame = new JFrame("Parking lot");
      frame.setSize(700, 500);
      frame.setResizable(true);
      frame.setDefaultCloseOperation(3);
      frame.setLocationRelativeTo((Component)null);
      frame.setLayout(new BorderLayout());
      initializeComp(frame);
      frame.setVisible(true);
   }

   private static void initializeComp(JFrame frame) {
      list = new JList();
      list.setFont(new Font("Monospaced", 1, 14));
      list.setSelectionMode(0);
      list.addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent l) {
            Frame.select(l);
         }
      });
      frame.add(new JScrollPane(list), "West");
      JPanel holder = new JPanel();
      holder.setLayout(new FlowLayout());
      add = new JButton("Add");
      add.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Frame.add();
         }
      });
      modify = new JButton("Modify");
      modify.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Frame.modify();
         }
      });
      delete = new JButton("Delete");
      delete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Frame.delete();
         }
      });
      save = new JButton("Save");
      save.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            Frame.save();
         }
      });
      updateButtons();
      JButton exit = new JButton("Exit");
      exit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
         }
      });
      JButton open = new JButton("Open logs");
      open.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            try {
               Desktop.getDesktop().open(new File("logs"));
            } catch (IOException var3) {
               JOptionPane.showMessageDialog((Component)null, "Wasn't able to open the logs file.", "Can't proceed", 0);
            }

         }
      });
      holder.add(add);
      holder.add(modify);
      holder.add(delete);
      holder.add(save);
      holder.add(exit);
      holder.add(open);
      frame.add(holder, "South");
      display = new JTextArea();
      display.setFont(new Font("Monospaced", 1, 16));
      display.setEditable(false);
      display.setLineWrap(true);
      display.setWrapStyleWord(true);
      display.setText(Main.getDisplayedInfo() + "\n\n\t No vehicle selected.");
      frame.add(new JScrollPane(display), "Center");
      Frame.frame = new Option();
   }

   private static void save() {
      Main.save();
   }

   private static void delete() {
      if (selected_vID != -1) {
         if (JOptionPane.showConfirmDialog((Component)null, "Do you want to delete the selected Vehicle ?", "Confirmation", 0) == 0) {
            Main.deleteVehicleWithId(selected_vID);
            show();
         }
      } else {
         updateButtons();
      }

   }

   private static void modify() {
      if (selected_vID != -1) {
         enableButtons(false);
         Main.getVehicleWithId(selected_vID).callModify();
      } else {
         updateButtons();
      }

   }

   private static void add() {
      enableButtons(false);
      frame.showAdd();
   }

   public static void show() {
      list.setSelectedIndex(-1);
      selected_vID = -1;
      selected_index = -2;
      enableButtons(true);
      updateButtons();
      updateDisplay();
      String[] aux = new String[Main.list.size()];

      for(int i = 0; i < aux.length; ++i) {
         aux[i] = ((Vehicle)Main.list.get(i)).toString();
      }

      list.setListData(aux);
   }

   private static void select(ListSelectionEvent l) {
      if (list.getSelectedIndex() == selected_index) {
         String id = (String)list.getSelectedValue();
         selected_vID = Integer.valueOf(id.substring(id.indexOf(58) + 2));
         updateDisplay();
         updateButtons();
      } else {
         selected_index = list.getSelectedIndex();
      }

   }

   private static void enableButtons(boolean enable) {
      add.setEnabled(enable);
      modify.setEnabled(enable);
      save.setEnabled(enable);
      delete.setEnabled(enable);
   }

   private static void updateButtons() {
      updateModify();
      updateDelete();
      updateSave();
   }

   public static void updateDisplay() {
      if (selected_vID != -1) {
         display.setText(Main.getDisplayedInfo() + "\n\n " + Main.getVehicleWithId(selected_vID).getDescription());
      } else {
         display.setText(Main.getDisplayedInfo() + "\n\n\t No vehicle selected.");
      }

   }

   private static void updateDelete() {
      boolean enable = selected_vID != -1;
      delete.setEnabled(enable);
      if (enable) {
         delete.setToolTipText((String)null);
      } else {
         delete.setToolTipText("Please select a Vehicle to delete");
      }

   }

   private static void updateModify() {
      boolean enable = selected_vID != -1;
      modify.setEnabled(enable);
      if (enable) {
         modify.setToolTipText((String)null);
      } else {
         modify.setToolTipText("Please select a Vehicle to modify");
      }

   }

   private static void updateSave() {
      if (Main.list.size() == 0) {
         save.setEnabled(false);
         save.setToolTipText("No avaliable Vehicle to Save the log");
      } else {
         save.setEnabled(true);
         save.setToolTipText((String)null);
      }

   }
}
