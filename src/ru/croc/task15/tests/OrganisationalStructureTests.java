package ru.croc.task15.tests;

import org.testng.annotations.Test;
import ru.croc.task15.OrganisationalStructure;

import static org.testng.AssertJUnit.assertEquals;

public class OrganisationalStructureTests {
    private final String filePath = "src/ru/croc/task15/tests/testInput.txt";

    @Test
    private void getProcessingTimeTest(){
        OrganisationalStructure organisation = OrganisationalStructure.getFromFile(filePath);
        int trueTime = 6;

        assertEquals(trueTime, organisation.getProcessingTime());
    }
}
