package main.java;

import main.java.entities.PubmedArticleSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class PubMedConversion {

    public static void main(String args[]) {

        File xmlFile = new File("pubmed.xml");

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PubmedArticleSet.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            PubmedArticleSet pubmedArticleSet = (PubmedArticleSet) jaxbUnmarshaller.unmarshal(xmlFile);
            System.out.println(pubmedArticleSet);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
}