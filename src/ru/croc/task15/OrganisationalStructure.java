package ru.croc.task15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class OrganisationalStructure {
    private final Map<String, Department> organisation = new TreeMap<>();
    private Department headDepartment;

    /**
     * Gets a new departments' hierarchy from a file
     *
     * @param path path to the file
     * @return new organisational structure
     */
    public static OrganisationalStructure getFromFile(String path) {
        OrganisationalStructure organisation = new OrganisationalStructure();

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                String[] dep = currentLine.split(",");
                organisation.add(dep[0], dep[1], Integer.parseInt(dep[2]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return organisation;
    }

    /**
     * Adds a new department to the hierarchy
     *
     * @param depCode        department's code
     * @param superCode      department's superior's code
     * @param processingTime how long it takes for the department
     *                       to process an application
     */
    public void add(String depCode,
                    String superCode,
                    int processingTime) {
        Department newDep = new Department(depCode, processingTime);
        if (superCode.equals("-"))
            headDepartment = newDep;
        else
            organisation.get(superCode).addSubordinate(newDep);
        organisation.put(depCode, newDep);
    }

    /**
     * @return how long it takes to process
     * an application
     */
    public int getProcessingTime() {
        return headDepartment.recursiveProcessingTimeCount();
    }

}