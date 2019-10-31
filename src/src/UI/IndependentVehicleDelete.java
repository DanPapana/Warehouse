package src.UI;

import src.logic.Transport.Truck;
import src.logic.Transport.Van;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;

class IndependentVehicleDelete implements ActionListener {

    private JRadioButton vanButton;
    private ArrayList<Van> vans;
    private ArrayList<Truck> trucks;
    private JComboBox vanList;
    private JComboBox truckList;
    private JTextPane descriptionPane;

    IndependentVehicleDelete() {
    }

    void setVehicles(ArrayList<Van> vans, ArrayList<Truck> trucks) {
        this.vans = vans;
        this.trucks = trucks;
    }

    IndependentVehicleDelete(JRadioButton vanButton, JComboBox vanList, JComboBox truckList, JTextPane descriptionPane) {
        this.vanButton = vanButton;
        this.vanList = vanList;
        this.truckList = truckList;
        this.descriptionPane = descriptionPane;
    }

    public void actionPerformed(ActionEvent event) {
        try {
            if (vanButton.isSelected()) {
                vans.remove(vanList.getSelectedIndex());

                FileWriter fw = new FileWriter("logFile.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(LocalTime.now() + " [REMARK] Van " +
                        vanList.getItemAt(vanList.getSelectedIndex()) + " is deleted");
                pw.close();

                vanList.removeItemAt(vanList.getSelectedIndex());
                if (vanList.getSelectedIndex() > vanList.getItemCount()) {
                    vanList.setSelectedIndex(vanList.getSelectedIndex());
                }
                if (vanList.getItemCount() < 1) {
                    vanList.removeAllItems();
                }

            } else {
                trucks.remove(truckList.getSelectedIndex());

                    FileWriter fw = new FileWriter("logFile.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(LocalTime.now() + " [REMARK] Truck " +
                            truckList.getItemAt(truckList.getSelectedIndex()) + " is deleted");
                    pw.close();

                truckList.removeItemAt(truckList.getSelectedIndex());
                if (truckList.getSelectedIndex() > truckList.getItemCount()) {
                    truckList.setSelectedIndex(truckList.getSelectedIndex());
                }
                if (truckList.getItemCount() < 1) {
                    truckList.removeAllItems();
                }
            }
        } catch (Exception e) {
            descriptionPane.setText(" 🔪🔪🔪  YOU STOP THAT RIGHT THERE YOU CRIMINAL SCUM  🔪🔪🔪");

            try {
                FileWriter fw = new FileWriter("logFile.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(LocalTime.now() + " [WARNING] Some of the fields are empty");
                pw.close();
            } catch (IOException ignored) {
            }
        }
    }
}