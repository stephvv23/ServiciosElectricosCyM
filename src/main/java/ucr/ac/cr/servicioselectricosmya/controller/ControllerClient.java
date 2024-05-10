/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ucr.ac.cr.servicioselectricosmya.model.ArrayClient;
import ucr.ac.cr.servicioselectricosmya.model.Client;
import ucr.ac.cr.servicioselectricosmya.view.DataPanelClient;
import ucr.ac.cr.servicioselectricosmya.view.GUIClient;
import ucr.ac.cr.servicioselectricosmya.view.GUIReport;
import ucr.ac.cr.servicioselectricosmya.view.PanelButtons;

/**
 *
 * @author Stephanie
 */
public class ControllerClient implements ActionListener, MouseListener {

    private GUIClient guiClient;
    private ArrayClient arrayClient;
    private DataPanelClient dataPanelClient;
    private PanelButtons panelButtons;
    private GUIReport guiReport;

    public ControllerClient(ArrayClient arrayClient) {
        this.guiClient = new GUIClient();

        this.guiReport = new GUIReport();
        this.guiReport.listenMouse(this);

        this.arrayClient = arrayClient;
        this.panelButtons = this.guiClient.getPanelButtons1();
        this.panelButtons.listen(this);

        this.dataPanelClient = this.guiClient.getDataPanelClient1();
        this.dataPanelClient.setCbClientType();

        this.guiClient.setVisible(true);
    }

    public boolean validateClient(Client clientValidate) {
        if (clientValidate.getAddress().isBlank()) {
            GUIClient.setMessage("Address is empty");
            return false;

        } else if (clientValidate.getName().isBlank()) {
            GUIClient.setMessage("Name is empty");
            return false;

        } else if (clientValidate.getIdentification().isBlank()) {
            GUIClient.setMessage("Identification is empty");
            return false;

        } else if (Integer.toString(clientValidate.getElectricMeterNumber()).isBlank()) {
            GUIClient.setMessage("Electric meter number is empty");
            return false;
        }
        return true;
    }

    public void getReport() {
        this.guiReport.setDataTblReport(arrayClient.getMatrixClients(), Client.TB_LABELS);
        this.guiReport.setVisible(true);
    }

    public void setVisible(boolean b) {
        this.guiClient.setVisible(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Add":
                Client client = this.dataPanelClient.getClient();
                if (this.validateClient(client)) {
                    GUIClient.setMessage(this.arrayClient.add(client));
                    this.dataPanelClient.clear();

                }
                break;
            case "Edit":
                Client clientEdit = this.dataPanelClient.getClient();
                if (this.validateClient(clientEdit)) {
                    GUIClient.setMessage(this.arrayClient.edit(clientEdit));
                    this.dataPanelClient.clear();
                }
                break;
            case "Delete":
                int electricNumber = this.dataPanelClient.getElectricMeterNumber();
                if (this.arrayClient.search(electricNumber) != null) {
                    GUIClient.setMessage(this.arrayClient.delete(electricNumber));
                    this.dataPanelClient.clear();
                }
                break;
            case "Report":
                this.getReport();
                break;
            case "Clear":
                this.dataPanelClient.clear();
                break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().toString().equals(this.guiReport.getTblReport())) {

            this.guiClient.setVisible(true);
            this.dataPanelClient.setClient(new Client(this.guiReport.getDataRow()[0],
                    this.guiReport.getDataRow()[1],
                    this.guiReport.getDataRow()[2],
                    this.guiReport.getDataRow()[3],
                    Integer.parseInt(this.guiReport.getDataRow()[4])));

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
