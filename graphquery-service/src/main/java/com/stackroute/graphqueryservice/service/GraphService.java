package com.stackroute.graphqueryservice.service;

import com.stackroute.graphqueryservice.model.ConceptTitle;
import com.stackroute.graphqueryservice.model.Content;
import com.stackroute.graphqueryservice.model.Program;
import com.stackroute.graphqueryservice.model.URL;

import java.util.List;

public interface GraphService {
    public List<ConceptTitle> getAllTheConcepts(String domainName);
    public List<String> getConceptOfAnyDomain(String domainName);


    public List<Program> getAllProgramById(String email);

//    public List<Content> getTOC(String domainName);

    public List<Content> getTOC(String domainName,String intent);

    public URL getUrlOfVideoTitle(String conceptName, String videoTitle);
    public URL getUrlOfDocumentTitle(String conceptName,String documentTitle);
    public List<Program> getAllTheProgram();
}
