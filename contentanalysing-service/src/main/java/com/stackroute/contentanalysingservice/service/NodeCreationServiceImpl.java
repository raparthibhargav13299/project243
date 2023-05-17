package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.Intent;
import com.stackroute.contentanalysingservice.model.Terms;
import com.stackroute.contentanalysingservice.repository.TermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NodeCreationServiceImpl implements NodeCreationService {

    @Autowired
    TermsRepository termsRepository;

    // Added all the keywords with which we will be able to identify the category of the page
    final String[] beginner = new String[]{"easy", "dummies", "simple", "starter"};
    final String[] intermediate = new String[]{"average", "moderate", "mediocre", "standard"};
    final String[] expert = new String[]{"Qualified", "Experience", "Professional", "Trained"};
    final Terms[] terms = new Terms[4];

    @Override
    public List<Terms> getAllKeywords() {
        return termsRepository.findAll();
    }

    @Override
    public void createAndRelateNodes(Intent intent) {

        if (termsRepository.parentNodePresent(intent.getName()) == null) {
            termsRepository.createParentNode(intent.getName());
            if ((intent.getName()).equals("beginner")) {

                for (int i = 0; i < 4; i++) {
                    terms[i] = new Terms(beginner[i], "beginner");
                    extracted(terms[i]);
                }
            } else if ((intent.getName()).equals("expert")) {

                for (int i = 0; i < 4; i++) {
                    terms[i] = new Terms(expert[i], "expert");
                    extracted(terms[i]);
                }
            }
            if (intent.getName().equals("intermediate")) {
                for (int i = 0; i < 4; i++) {
                    terms[i] = new Terms(intermediate[i], "intermediate");
                    extracted(terms[i]);
                }
            }
        }
    }

    private void extracted(Terms terms) {
        termsRepository.createKeywordNode(terms.getName(), terms.getParentName());
        termsRepository.createRelation(terms.getName(), terms.getParentName());
    }
}
