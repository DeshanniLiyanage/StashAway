package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HandleJSONFile {
    final String FILE_NAME = "dataStructure.json";
    DataStructure dataStructure;

    public void create(DataStructure plan) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = null;
        FileWriter writer;
        BufferedWriter bw;
        try {
            json = mapper.writeValueAsString(plan);
            writer = new FileWriter(FILE_NAME);
            bw = new BufferedWriter(writer);
            bw.write(json);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataStructure retrieve() {
        try {
            byte[] jsonBytes = Files.readAllBytes(Paths.get(FILE_NAME));
            String jsonContent = new String(jsonBytes);

            ObjectMapper objectMapper = new ObjectMapper();
            dataStructure = objectMapper.readValue(jsonContent, DataStructure.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataStructure;
    }
}
