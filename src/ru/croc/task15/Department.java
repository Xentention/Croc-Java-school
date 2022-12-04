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

    /**
     * Recursive logic of calculating processing time
     *
     * @return processing time for a department and its subdepartments
     */
    public int recursiveProcessingTimeCount() {
        if (this.getSubordinatingDeps().isEmpty())
            return this.getProcessingTime();
        int processingTime = 0;
        for (Department subDep :
                this.getSubordinatingDeps()) {
            processingTime = Math.max(processingTime, subDep.recursiveProcessingTimeCount());
        }
        processingTime += this.getProcessingTime();
        return processingTime;

    }

}
