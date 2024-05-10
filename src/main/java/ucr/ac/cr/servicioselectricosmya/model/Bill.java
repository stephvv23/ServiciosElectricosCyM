/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.model;

/**
 *
 * @author Stephanie
 */
public class Bill {

    private int billNumber;
    private int electricMeterNumber;
    private String date;
    private int energyConsumption; // en kilovatios hora
    private double TotalPay;

    public static String[] TB_LABELS = {"Bill number", "Electric meter number", "Date", "Energy comsumption", "Total Pay"};

    public Bill() {
    }

    public Bill(int billNumber, int electricMeterNumber, String date, int energyConsumption, double TotalPay) {
        this.billNumber = billNumber;
        this.electricMeterNumber = electricMeterNumber;
        this.date = date;
        this.energyConsumption = energyConsumption;
        this.TotalPay = TotalPay;
    }

    public String getData(int column) {
        switch (column) {
            case 0:
                return String.valueOf(this.getBillNumber());
            case 1:

                return String.valueOf(this.getElectricMeterNumber());
            case 2:

                return String.valueOf(this.getDate());
            case 3:

                return String.valueOf(this.getEnergyConsumption());
            case 4:

                return String.valueOf(this.getTotalPay());

        }
        return "";
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public int getElectricMeterNumber() {
        return electricMeterNumber;
    }

    public void setElectricMeterNumber(int electricMeterNumber) {
        this.electricMeterNumber = electricMeterNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public double getTotalPay() {
        return TotalPay;
    }

    public void setTotalPay(double TotalPay) {
        this.TotalPay = TotalPay;
    }

    @Override
    public String toString() {
        return "Bill{" + "billNumber=" + billNumber + ", electricMeterNumber=" + electricMeterNumber + ", Date=" + date + ", EnergyConsumption=" + energyConsumption + ", TotalPay=" + TotalPay + '}';
    }

}
