/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ucr.ac.cr.servicioselectricosmya.model.ArrayBill;
import ucr.ac.cr.servicioselectricosmya.model.ArrayClient;
import ucr.ac.cr.servicioselectricosmya.model.Bill;
import ucr.ac.cr.servicioselectricosmya.view.DataPanelBill;
import ucr.ac.cr.servicioselectricosmya.view.GUIBill;
import ucr.ac.cr.servicioselectricosmya.view.GUIClient;
import ucr.ac.cr.servicioselectricosmya.view.GUIReport;
import ucr.ac.cr.servicioselectricosmya.view.PanelButtons;

/**
 *
 * @author Stephanie
 */
public class ControllerBill implements ActionListener, MouseListener {

    private GUIBill guiBill;
    private GUIReport guiReport;
    private DataPanelBill dataPanelBill;
    private PanelButtons dataButtons;
    private ArrayBill arrayBill;
    private ArrayClient arrayClient;

    public ControllerBill(ArrayBill arrayBill, ArrayClient arrayClient) {
        this.guiBill = new GUIBill();
        this.guiReport = new GUIReport();
        this.arrayBill = arrayBill;
        this.arrayClient = arrayClient;

        this.dataButtons = this.guiBill.getPanelButtons1();
        this.dataButtons.listen(this);

        this.dataPanelBill = this.guiBill.getDataPanelBill1();
        this.dataPanelBill.setNumberBill(Integer.toString(arrayBill.getNumberBill()));
        this.dataPanelBill.setCbMeterNumbers(arrayClient.getIDCombo());
        this.dataPanelBill.listen(this);

        this.guiBill.setVisible(true);
    }

    public void getReport() {
        this.guiReport.listenMouse(this);
        this.guiReport.setDataTblReport(arrayBill.getMatrixBills(), Bill.TB_LABELS);
        this.guiReport.setVisible(true);
    }

    public boolean validateBill(Bill bill) {
        if (bill.getDate().isBlank()) {
            GUIClient.setMessage("The date is empty");
            return false;
        } else if (Integer.toString(bill.getElectricMeterNumber()).equals("Selected option")) {
            GUIClient.setMessage("Select a electric meter number");
            return false;
        }
        return true;
    }

    public void setVisible(boolean b) {
        this.guiBill.setVisible(b);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                this.dataPanelBill.setLbTotalPay(arrayBill.getTotalPay(this.dataPanelBill.getEnergyComsumption()));
                Bill bill = this.dataPanelBill.getBill();

                if (this.validateBill(bill)) {
                    GUIClient.setMessage(this.arrayBill.add(bill));
                    this.dataPanelBill.setNumberBill(Integer.toString(arrayBill.getNumberBill()));
                    this.dataPanelBill.clear();
                }

                break;
            case "Edit":
                Bill billEdit = this.dataPanelBill.getBill();

                if (this.validateBill(billEdit)) {
                    GUIClient.setMessage(this.arrayBill.edit(billEdit));
                    this.dataPanelBill.setNumberBill(Integer.toString(arrayBill.getNumberBill()));
                }
                break;
            case "Delete":

                GUIClient.setMessage(this.arrayBill.delete(this.dataPanelBill.getLbNumberBill()));
                this.dataPanelBill.setNumberBill(Integer.toString(arrayBill.getNumberBill()));

                break;
            case "Report":
                this.getReport();
                break;
            case "Clear":
                this.dataPanelBill.clear();
                break;
            case "Calculate":
                this.dataPanelBill.setLbTotalPay(arrayBill.getTotalPay(this.dataPanelBill.getEnergyComsumption()));
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().toString().equals(this.guiReport.getTblReport())) {
            this.guiBill.setVisible(true);
            
            // billNumber, int electricMeterNumber, String date, int energyConsumption, double TotalPay)
            this.dataPanelBill.setBill(new Bill(Integer.parseInt(this.guiReport.getDataRow()[0]),
                    Integer.parseInt(this.guiReport.getDataRow()[1]),
                    this.guiReport.getDataRow()[2],
                    Integer.parseInt(this.guiReport.getDataRow()[3]),
                    Double.parseDouble(this.guiReport.getDataRow()[4])));

            this.guiReport.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
