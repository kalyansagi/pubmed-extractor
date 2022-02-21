package main.java.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PubmedArticleSet")
public class PubmedArticleSet {
    PubmedArticle PubmedArticleObject;


    // Getter Methods

    @XmlElement(name = "PubmedArticle")
    public PubmedArticle getPubmedArticle() {
        return PubmedArticleObject;
    }

    // Setter Methods

    public void setPubmedArticle(PubmedArticle PubmedArticleObject) {
        this.PubmedArticleObject = PubmedArticleObject;
    }
}
