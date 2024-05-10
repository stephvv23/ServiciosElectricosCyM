/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Stephanie
 */
public class ArrayBill {

    private ArrayList<Bill> listBills;

    public ArrayBill() {

        File filejson = new File("bills.json");
        if (!filejson.exists()) {
            try {
                filejson.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        listBills = leerJSON("bills.json");
    }

    public int getNumberBill() {
        if (!listBills.isEmpty()) {
            return this.listBills.get(listBills.size() - 1).getBillNumber() + 1;
        }
        return 1;
    }

    public String[][] getMatrixBills() {
        if (!listBills.isEmpty()) {
            String[][] matrix = new String[this.listBills.size()][Bill.TB_LABELS.length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = this.listBills.get(i).getData(j);
                }
            }
            return matrix;
        }
        return new String[1][5];
    }

    public double getTotalPay(int electricConsumption) {
        double totalPay = 0;
        if (electricConsumption > 0 && electricConsumption <= 100) {
            totalPay = electricConsumption * 900;
        }
        if (electricConsumption > 100 && electricConsumption <= 200) {
            totalPay = 100 * 900 + (electricConsumption - 100) * 1250;
        }
        if (electricConsumption > 200 && electricConsumption <= 350) {
            totalPay = 100 * 900 + 100 * 1250 + (electricConsumption - 200) * 1500;
        }
        if (electricConsumption > 350) {
            totalPay = 100 * 900 + 100 * 1250 + 150 * 1500 + (electricConsumption - 350) * 1750;
        }
        return totalPay;
    }

    public Bill search(int billNumber) {
        for (Bill bill : listBills) {
            if (bill.getBillNumber() == billNumber) {
                return bill;
            }
        }
        return null;
    }

    public String add(Bill bill) {
        if (search(bill.getBillNumber()) == null) {
            this.listBills.add(bill);
            writeJSON("bills.json");
            return "Added succesfully";
        }
        return "Bill number already exist";
    }

    public String edit(Bill billEdit) {
        if (search(billEdit.getBillNumber()) != null) {
            for (int i = 0; i < this.listBills.size(); i++) {
                if (this.listBills.get(i).getBillNumber() == billEdit.getBillNumber()) {
                    this.listBills.set(i, billEdit);
                    writeJSON("bills.json");
                    return "Edit succesfully";
                }
            }
        }
        return "Bill number not found";
    }

    public String delete(int billNumber) {
        if (search(billNumber) != null) {
            for (int i = 0; i < this.listBills.size(); i++) {
                if (this.listBills.get(i).getBillNumber() == billNumber) {
                    this.listBills.remove(i);
                    writeJSON("bills.json");
                    return "Delete succesfully";
                }
                
            }
        }
        return "Bill number not found";
    }

    public ArrayList<Bill> leerJSON(String fileName) {
        JSONParser parser = new JSONParser();
        ArrayList<Bill> list = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName)) {

            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object object : jsonArray) {

                JSONObject jsonObject = (JSONObject) object;
                Bill bill = new Bill();

                bill.setBillNumber(Integer.parseInt(String.valueOf(jsonObject.get("bill number"))));
                bill.setDate(String.valueOf(jsonObject.get("date")));
                bill.setTotalPay(Double.parseDouble(String.valueOf(jsonObject.get("total pay"))));
                bill.setElectricMeterNumber(Integer.parseInt(String.valueOf(jsonObject.get("electric meter number"))));
                bill.setEnergyConsumption(Integer.parseInt(String.valueOf(jsonObject.get("energy comsumption"))));

                list.add(bill);

            }

        } catch (IOException | ParseException e) {
//            e.printStackTrace();
        }
        return list;
    }

    public void writeJSON(String filename) {
        JSONArray jsonArray = new JSONArray();

        for (Bill bill : listBills) {

            JSONObject jsonObjectAdd = new JSONObject();

            jsonObjectAdd.put("bill number", bill.getBillNumber());
            jsonObjectAdd.put("date", bill.getDate());
            jsonObjectAdd.put("total pay", bill.getTotalPay());
            jsonObjectAdd.put("electric meter number", bill.getElectricMeterNumber());
            jsonObjectAdd.put("energy comsumption", bill.getEnergyConsumption());

            jsonArray.add(jsonObjectAdd);

        }

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonArray.toJSONString());
            writer.flush();
            writer.close();

        } catch (IOException e) {
//            e.printStackTrace();

        }
    }
}
