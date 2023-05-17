package com.stackroute.graphcommandservice.service;

import com.stackroute.graphcommandservice.model.*;

import java.util.List;

public interface ProgramService {

    public Program createConceptGraph(Program program, List<ExcelSheetData> excelArray);
    public void createVideoNode(Response response);

}
