package com.stackroute.graphqueryservice.controller;


import com.stackroute.graphqueryservice.model.ConceptTitle;
import com.stackroute.graphqueryservice.model.Content;
import com.stackroute.graphqueryservice.model.Program;
import com.stackroute.graphqueryservice.model.URL;
import com.stackroute.graphqueryservice.service.GraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("api/v1")
public class GraphController {
    GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }


    @GetMapping("/toc/{domainName}")
    public List<ConceptTitle> conceptUsingDomain(@PathVariable String domainName) {

        return graphService.getAllTheConcepts(domainName);

    }

    @GetMapping("/program/{email}")
    public List<Program> getAllPrograms(@PathVariable String email) {
        log.info(String.valueOf(graphService.getAllProgramById(email)));
        return graphService.getAllProgramById(email);

    }
    @GetMapping("/videourl/{conceptName}/{title}")
    public URL getVideoUrl(@PathVariable("conceptName") String conceptName, @PathVariable("title") String title ){
        return graphService.getUrlOfVideoTitle(conceptName,title);
    }


    @GetMapping("/video")
    public URL VideoUrl(@RequestParam("conceptName") String conceptName, @RequestParam("title") String title ){
        return graphService.getUrlOfVideoTitle(conceptName,title);
    }


    @GetMapping("/documenturl/{conceptName}/{title}")
    public URL getDocumentUrl(@PathVariable("conceptName") String conceptName, @PathVariable("title") String title ){
        return graphService.getUrlOfDocumentTitle(conceptName,title);
    }

//    @GetMapping("/test/{domainName}")
//    public List<Content> getTOC(@PathVariable("domainName") String domainName){
//        return graphService.getTOC(domainName);
//    }

    @GetMapping("/test/{domainName}/{intent}")
    public List<Content> getTOC(@PathVariable("domainName") String domainName,@PathVariable("intent") String intent){
        return graphService.getTOC(domainName,intent);
    }

    @GetMapping("/programs")
    public List<Program> getAllProgram(){
        return graphService.getAllTheProgram();
    }


    @GetMapping("/domain/{randomDomainName}")
    public List<String> conceptUsingRandomDomainName(@PathVariable String randomDomainName) {
        return graphService.getConceptOfAnyDomain(randomDomainName);
    }
}
