package main.java;

import main.java.entities.PubmedArticleSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class PubMedConversion {
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        File xmlFile = new File("src/main/resources/pubmed22n0150.xml");
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PubmedArticleSet.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            PubmedArticleSet pubmedArticleSet = (PubmedArticleSet) jaxbUnmarshaller.unmarshal(xmlFile);
            System.out.println(pubmedArticleSet);
            PostgreSQLConnector postgreSQLConnector = new PostgreSQLConnector();
            postgreSQLConnector.createTable();
            postgreSQLConnector.insertIntoPapers(pubmedArticleSet);
            final long endTime = System.currentTimeMillis();
            System.out.println("Total time taken for process:" + (endTime - startTime));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
