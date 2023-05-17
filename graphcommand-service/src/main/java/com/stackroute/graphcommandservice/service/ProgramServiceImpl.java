package com.stackroute.graphcommandservice.service;

import com.stackroute.graphcommandservice.model.*;
import com.stackroute.graphcommandservice.repository.ConceptRepository;
import com.stackroute.graphcommandservice.repository.DocumentRepository;
import com.stackroute.graphcommandservice.repository.DomainRepository;
import com.stackroute.graphcommandservice.repository.VideoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    DomainRepository domainRepository;
    DocumentRepository documentRepository;
    ConceptRepository conceptRepository;
    VideoRepository videoRepository;

    @Autowired
    public ProgramServiceImpl(DomainRepository domainRepository, ConceptRepository conceptRepository, DocumentRepository documentRepository, VideoRepository videoRepository) {
        this.domainRepository = domainRepository;
        this.conceptRepository = conceptRepository;
        this.documentRepository = documentRepository;
        this.videoRepository = videoRepository;
    }

    List<Response> consumerArray = new ArrayList<>();

    @RabbitListener(queues = "${spring.rabbitmq.queue2}")
    public void consumerFunction(Response response) {
        if (response.getType().equals("page")) {
            createDocumentNode(response);
        }
        else {
            createVideoNode(response);
        }
    }


    @Override
    public Program createConceptGraph(Program program, List<ExcelSheetData> excelArray) {
        Optional<Domain> domainExist = domainRepository.findById(program.getDomainName());
        if (!domainExist.isPresent()) {
            Domain domain = new Domain(program.getDomainName(), program.getCreatedBy(), program.getProgramTitle(), program.getDescription(), program.getDuration(), program.getImageName(),program.getImageType(), program.getPicByte());
            domainRepository.save(domain);
            for (ExcelSheetData data : excelArray) {
                Optional<Concept> conceptExist = conceptRepository.findById(data.getParent());

                if (conceptExist.isPresent()) {
                    Concept concept = new Concept(data.getConceptName());
                    conceptRepository.save(concept);
                    conceptRepository.parentConceptRelationship(data.getParent(), data.getConceptName());
                } else {
                    Optional<Domain> domainObject = domainRepository.findById(data.getParent());
                    if (domainObject.isPresent()) {
                        Concept concept = new Concept(data.getConceptName());
                        conceptRepository.save(concept);
                        conceptRepository.domainConceptRelationship(data.getParent(), data.getConceptName());
                    } else {
                        Concept concept = new Concept(data.getParent());
                        conceptRepository.save(concept);
                        conceptRepository.parentConceptRelationship(data.getParent(), data.getConceptName());
                    }
                }
            }
        }
        return program;
    }
//
//    public void addDetailsToTheNode(){
//        for(Response response:consumerArray){
//            if(conceptRepository.existsById(response.getConceptName())){
//                conceptRepository.addPropertyToConcept(response.getConceptName(),response.getUrl(), response.getReadingTime(), response.getIntent() );
//            }
//        }
//    }

    public void createDocumentNode(Response response) {
        Document document = new Document(response.getTitle(),response.getUrl(), response.getDuration(), response.getNoOfVideos(), response.getNoOfImages());
        documentRepository.save(document);
        documentRepository.relationBetweenDocumentAndComponent(response.getConceptName(), document.getTitle(), response.getIntent(), response.getConfidenceScore());
    }


    public void createVideoNode(Response response) {
        Video video = new Video(response.getUrl(), response.getTitle(), response.getDuration(), response.getNoOfVideos(), response.getNoOfImages());
        videoRepository.save(video);
        videoRepository.relationBetweenVideoAndConcept(response.getConceptName(), response.getUrl(), response.getIntent(), response.getConfidenceScore());
    }

//    public void addDetailsToTheNode() {
//        if (conceptRepository.existsById(response.getConceptName())) {
//            conceptRepository.addPropertyToConcept(response.getConceptName(), response.getUrl(), response.getReadingTime(), response.getIntent());
//        }
//    }
}
