/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Stephanie
 */
public class ArrayClient {
    
    private ArrayList<Client> listClients;
    
    public ArrayClient() {
        try {
            File file = new File("clients.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            
            listClients = leerCSV("clients.csv");
        } catch (IOException e) {
            
        }
    }
    
    public Client search(int electricMeterNumber) {
        if (!listClients.isEmpty() && electricMeterNumber != -1) {
            for (Client client : listClients) {
                if (client.getElectricMeterNumber() == electricMeterNumber) {
                    return client;
                }
            }
        }
        return null;
    }
    
    public String add(Client client) {
        if (search(client.getElectricMeterNumber()) == null) {
            try {
                this.listClients.add(client);
                
                escribirCSV(listClients, "clients.csv");
               System.out.print(this.imprimirLista());
                return "Client succesfully added";
            } catch (IOException e) {
                
            }
            return "Error during write";
        }
        return "Electric meter number already registered";
    }
    
    public String edit(Client clientEdit) {
        if (search(clientEdit.getElectricMeterNumber()) != null) {
            for (int i = 0; i < this.listClients.size(); i++) {
                if (clientEdit.getElectricMeterNumber() == this.listClients.get(i).getElectricMeterNumber()) {
                    try {
                        this.listClients.set(i, clientEdit);
                        
                        escribirCSV(listClients, "clients.csv");
                        
                        return "Client succesfully edited";
                    } catch (IOException e) {
                        
                    }
                }
            }
            
            return "Error during write";
        }
        return "Electric meter number not found";
    }
    
    public String imprimirLista(){
        String mensaje = "";
        for (Client listClient : listClients) {
            mensaje += listClient.getAddress()+"   ";
        }
        return mensaje;
    }
    
    public String delete(int electricMeterNumber) {
        if (search(electricMeterNumber) != null) {
            for (int i = 0; i < this.listClients.size(); i++) {
                if (electricMeterNumber == this.listClients.get(i).getElectricMeterNumber()) {
                    try {
                        this.listClients.remove(i);
                        
                        escribirCSV(listClients, "clients.csv");
                        
                        return "Client succesfully deleted";
                    } catch (IOException e) {
                        
                    }
                }
            }
            
            return "Error during write";
        }
        return "Electric meter number not found";
    }
    
    public String[][] getMatrixClients() {
        if (!listClients.isEmpty()) {
            String[][] matrixClients = new String[this.listClients.size()][Client.TB_LABELS.length];
            for (int i = 0; i < matrixClients.length; i++) {
                for (int j = 0; j < matrixClients[0].length; j++) {
                    matrixClients[i][j] = this.listClients.get(i).getData(j);
                }
            }
            return matrixClients;
        }
        return new String[1][5];
    }
    
    public String[] getIDCombo() {
        String[] idList = new String[this.listClients.size()];
        for (int i = 0; i < this.listClients.size(); i++) {
            idList[i] = Integer.toString(this.listClients.get(i).getElectricMeterNumber());
            System.out.print(this.listClients.get(i).getName());
        }
        
        return idList;
    }
    
    public static ArrayList<Client> leerCSV(String rutaArchivo) throws IOException {
        
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            // Configurar el lector CSV
            CsvToBean<Client> csvToBean = new CsvToBeanBuilder<Client>(reader)
                    .withType(Client.class)
                    .build();

            // Leer las personas del archivo CSV
            return (ArrayList<Client>) csvToBean.parse();
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo " + rutaArchivo + " no existe.");
            FileReader fileReader = new FileReader(rutaArchivo);
            return new ArrayList<>();  // Devuelve una lista vacía
        }
    }

    // Método para escribir una lista de objetos Person en un archivo CSV
    public static void escribirCSV(ArrayList<Client> personas, String rutaArchivo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo))) {
            // Configurar el escritor CSV
            StatefulBeanToCsv<Client> beanToCsv = new StatefulBeanToCsvBuilder<Client>(writer).build();
            
            try {
                // Escribir la lista de personas en el archivo CSV
                beanToCsv.write(personas);
            } catch (CsvDataTypeMismatchException ex) {
                
            } catch (CsvRequiredFieldEmptyException ex) {
                
            }
        }
    }
    
}
