package ru.croc.task15;


import java.util.ArrayList;
import java.util.List;

public class Department {
    private final String depCode;
    private final int processingTime;

    private final List<Department> subordinatingDeps;

    public Department(String depCode,
                      int processingTime){
        this.depCode = depCode;
        this.processingTime = processingTime;
        subordinatingDeps = new ArrayList<>();
    }

    public List<Department> getSubordinatingDeps(){
        return subordinatingDeps;
    }

    public void addSubordinate(Department subDep){
        subordinatingDeps.add(subDep);
    }

    public int getProcessingTime() {
        return processingTime;
    }

}
