package controller;

import model.HandleJSONFile;
import model.DataStructure;
import view.DataStructureView;

public class DataStructureController {
    private HandleJSONFile handleJSONFile = new HandleJSONFile();

    private DataStructure dataStructure;
    private DataStructureView dataStructureView;

    public DataStructureController(DataStructure dataStructure, DataStructureView dataStructureView){
        this.dataStructure = dataStructure;
        this.dataStructureView = dataStructureView;
    }

    public void OnCreateStructure(){
        handleJSONFile.create(dataStructure);
        dataStructureView.viewDataStructure();
    }
}
