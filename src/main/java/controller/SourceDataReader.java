package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.CompentencyLevelYaml;
import model.CompentencyYaml;
import model.Plan;
import model.TopicYaml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SourceDataReader {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    ArrayList<TopicYaml> yamlTopics = new ArrayList<>();
    ArrayList<CompentencyYaml> yamlCompetencies = new ArrayList<>();
    ArrayList<CompentencyLevelYaml> yamlCompetencyLevels = new ArrayList<>();

    public HashMap<String, ArrayList> readAndPrepareAllSourceDataYamls() throws IOException {

        HashMap<String, ArrayList> mapYamlObjects = new HashMap<>();

        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        readAllSourceData(new File("src/main/resources/data1"));

        mapYamlObjects.put("topics", yamlTopics);
        mapYamlObjects.put("competencies", yamlCompetencies);
        mapYamlObjects.put("levels", yamlCompetencyLevels);

        return mapYamlObjects;
    }


    public void readAllSourceData(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                readAllSourceData(fileEntry);
            } else {
                if(fileEntry.getName().startsWith("topic")){
                    yamlTopics.add(mapper.readValue(fileEntry, TopicYaml.class));
                }else if(fileEntry.getName().startsWith("competency")){
                    yamlCompetencies.add(mapper.readValue(fileEntry, CompentencyYaml.class));
                }else if(fileEntry.getName().startsWith("level")){
                    yamlCompetencyLevels.add(mapper.readValue(fileEntry, CompentencyLevelYaml.class));
                }else{
                    //TODO
                    System.out.println("Abhandeln: "+fileEntry.getName());
                }
            }
        }


    }


    public void createMasterYaml(Plan plan) throws IOException {
        mapper.writeValue(new File("src/main/resources/testMaster.yaml"), plan);
    }
}
