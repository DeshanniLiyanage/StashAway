package controller;

import model.HandleDataFile;
import model.DataStructure;
import view.DataStructureView;

public class DataStructureController {
    private HandleDataFile handleDataFile = new HandleDataFile();

    private DataStructure dataStructure;
    private DataStructureView dataStructureView;

    public DataStructureController(DataStructure dataStructure, DataStructureView dataStructureView){
        this.dataStructure = dataStructure;
        this.dataStructureView = dataStructureView;
    }

    public void OnCreateStructure(){
        handleDataFile.create(dataStructure);
        dataStructureView.viewDataStructure();
    }
}
