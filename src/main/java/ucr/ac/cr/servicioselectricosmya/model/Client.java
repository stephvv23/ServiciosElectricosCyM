/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.model;

import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author Stephanie
 */
public class Client {

    @CsvBindByName
    private String name;
    @CsvBindByName
    private String address;
    @CsvBindByName
    private String clientType;
    @CsvBindByName
    private String identification;
    @CsvBindByName
    private int electricMeterNumber;
    
    public static String[] TB_LABELS = {"Name", "Address", "ClientType", "Identification", "electricMeterNumber"};

    public Client() {
    }

    public Client(String name, String address, String clientType, String identification, int electricMeterNumber) {
        this.name = name;
        this.address = address;
        this.clientType = clientType;
        this.identification = identification;
        this.electricMeterNumber = electricMeterNumber;
    }

    public String getData(int column) {
        switch (column) {
            case 0:
                return String.valueOf(this.getName());
            case 1:
                return String.valueOf(this.getAddress());
            case 2:
                return String.valueOf(this.getClientType());
            case 3:
                return String.valueOf(this.getIdentification());
            case 4:
                return String.valueOf(Integer.toString(this.getElectricMeterNumber()));

        }
        return "77";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getElectricMeterNumber() {
        return electricMeterNumber;
    }

    public void setElectricMeterNumber(int electricMeterNumber) {
        this.electricMeterNumber = electricMeterNumber;
    }

    @Override
    public String toString() {
        return "Client{" + "name=" + name + ", address=" + address + ", clientType=" + clientType + ", identification=" + identification + ", electricMeterNumber=" + electricMeterNumber + '}';
    }

}
