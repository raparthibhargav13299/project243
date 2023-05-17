package com.stackroute.graphcommandservice.repository;

import com.stackroute.graphcommandservice.model.Concept;
import com.stackroute.graphcommandservice.model.Domain;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends Neo4jRepository<Domain, String> {

    @Query("MATCH(a:Domain {domainName: $domain}),(b:Concept{conceptName: $conceptName}) CREATE(b)-[:concept_of]->(a)")
    public void domainConceptRelationship(String domain, String conceptName);

    @Query("MATCH(b: Concept {conceptName: $concept}) DETACH DELETE b")
    public void deleteNode(String concept);

    @Query("MATCH(a:Concept {parent:$parentName}) RETURN a")
    public Concept findByConceptName(String parentName);

}
