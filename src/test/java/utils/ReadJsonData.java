package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadJsonData {

    JSONParser jsonParser = new JSONParser();
    FileReader fileReader;
    Object object;
    JSONObject jsonObject;

    public ReadJsonData(String jsonData) {
        try {
            this.fileReader = new FileReader(jsonData, StandardCharsets.UTF_8);
            this.object = jsonParser.parse(fileReader);
            this.jsonObject = (JSONObject) object;
        } catch (IOException e) {
            LoggerLoad.error("An error occurred while reading the JSON file: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            LoggerLoad.error("An error occurred while parsing the JSON data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
