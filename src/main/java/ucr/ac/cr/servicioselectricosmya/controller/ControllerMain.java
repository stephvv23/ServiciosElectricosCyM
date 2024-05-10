/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ucr.ac.cr.servicioselectricosmya.model.ArrayBill;
import ucr.ac.cr.servicioselectricosmya.model.ArrayClient;
import ucr.ac.cr.servicioselectricosmya.view.GUIMain;

/**
 *
 * @author Stephanie
 */
public class ControllerMain implements ActionListener {

    private GUIMain guiMain;
    private ArrayBill arrayBill;
    private ArrayClient arrayClient;

    public ControllerMain() {
        this.guiMain = new GUIMain();
        this.arrayBill = new ArrayBill();
        this.arrayClient = new ArrayClient();

        this.guiMain.listen(this);
        this.guiMain.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Exit":
                System.exit(0);
                break;
            case "Client":
                ControllerClient controllerClient = new ControllerClient(this.arrayClient);
                break;
            case "Bill":
                ControllerBill controllerBill = new ControllerBill(this.arrayBill, this.arrayClient);
                break;
            case "Clients":
                ControllerClient controllerClientReport = new ControllerClient(this.arrayClient);
                controllerClientReport.setVisible(false);
                controllerClientReport.getReport();

                break;
            case "Bills":
                ControllerBill controllerBillReport = new ControllerBill(this.arrayBill, this.arrayClient);
                controllerBillReport.setVisible(false);
                controllerBillReport.getReport();

                break;
        }
    }

}
