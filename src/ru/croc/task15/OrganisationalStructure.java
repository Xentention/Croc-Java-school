package ru.croc.task15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class OrganisationalStructure {
    Map<String, Department> organisation = new TreeMap<>();
    Department headDepartment;

    /**
     * Gets a new departments' hierarchy from a file
     * @param path  path to the file
     * @return  new organisational structure
     */
    public static OrganisationalStructure getFromFile(String path){
        OrganisationalStructure organisation =  new OrganisationalStructure();
        ArrayList<ArrayList<String>> depsInfo = parseFile(path);
        for (ArrayList<String> info:
             depsInfo) {
            organisation.add(info.get(0), info.get(1), Integer.parseInt(info.get(2)));
        }
        return organisation;
    }

    /**
     * Adds a new department to the hierarchy
     * @param depCode   department's code
     * @param superCode department's superior's code
     * @param processingTime    how long it takes for the department
     *                          to process an application
     */
    public void add(String depCode,
                    String superCode,
                    int processingTime){
        Department newDep = new Department(depCode, processingTime);
        if(superCode.equals("-"))
            headDepartment = newDep;
        else
            organisation.get(superCode).addSubordinate(newDep);
        organisation.put(depCode, newDep);
    }

    /**
     * @return  how long it takes to process
     *          an application
     */
    public int getProcessingTime(){
        return recursiveProcessingTimeCount(headDepartment);
    }

    /**
     * Recursive logic of calculating processing time
     * @param current   current root of a structure's subtree
     * @return  processing time for a department anf its subdepartments
     */
    private int recursiveProcessingTimeCount(Department current){
        if(current.getSubordinatingDeps().isEmpty())
            return current.getProcessingTime();
        int processingTime = 0;
        for (Department subDep:
             current.getSubordinatingDeps()) {
            processingTime = Math.max(processingTime, recursiveProcessingTimeCount(subDep));
        }
        processingTime += current.getProcessingTime();
        return processingTime;

    }

    private static ArrayList<ArrayList<String>> parseFile(String path){
        ArrayList<ArrayList<String>> depsInfo = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                depsInfo.add(new ArrayList<>());
                String[] dep = currentLine.split(",");
                for (String attribute:
                        dep) {
                    depsInfo.get(depsInfo.size() - 1).add(attribute);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return depsInfo;
    }


}
