package com.example.wiki2.Services;

import com.example.wiki2.Models.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriter;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLConnection;
import org.springframework.stereotype.Service;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.query.resultio.sparqljson.SPARQLResultsJSONWriterFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WikipediaScraper {
    public List<City> run () {
        List<City> result = new ArrayList<>();
        String sparqlEndpoint = "https://query.wikidata.org/sparql";
        SPARQLRepository repo = new SPARQLRepository(sparqlEndpoint);

        String userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)";
        repo.setAdditionalHttpHeaders( Collections.singletonMap("User-Agent", userAgent ) );

        String querySelect = "SELECT ?cityLabel ?population\n" +
                "WHERE {\n" +
                "  ?city wdt:P31/wdt:P279* wd:Q515.\n" +
                "  ?city wdt:P17 wd:Q159.\n" +
                "  ?city wdt:P1082 ?population.\n" +
                "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],ru\". }\n" +
                "}";
        TupleQuery tupleQuery = repo.getConnection().prepareTupleQuery(
                QueryLanguage.SPARQL, querySelect);
            for (BindingSet bs : QueryResults.asList(tupleQuery.evaluate())) {
                String cityLabel = bs.getValue("cityLabel").stringValue();
                String population = bs.getValue("population").stringValue();
                result.add(new City(cityLabel,population));
            }
            return result;

    }
}

