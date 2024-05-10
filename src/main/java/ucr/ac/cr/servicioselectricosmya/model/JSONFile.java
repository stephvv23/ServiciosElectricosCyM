/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Stephanie
 */
public class JSONFile {
    private String fileName;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private JSONParser jsonParser;

    public JSONFile(String fileName) {
        this.fileName = fileName;
        this.jsonArray = new JSONArray();
        this.jsonObject = new JSONObject();
    }
    
    public JSONArray read(){
        this.jsonParser = new JSONParser();
       try(FileReader reader = new FileReader(fileName)){
           Object obj = this.jsonParser.parse(reader);
           this.jsonArray = (JSONArray) obj;
       }catch(IOException | ParseException e){
           System.out.print("Erro during read");
       }
       return this.jsonArray;
    }
    
    public void write(){
        this.jsonArray = read();
       try(FileWriter writer = new FileWriter(fileName)){
          writer.write(this.jsonArray.toJSONString());
          writer.flush();
          writer.close();
       }catch(IOException e){
           System.out.print("Erro during write");
       }
    }
    
    
}
