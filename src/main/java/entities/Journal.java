package main.java.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Journal")
public class Journal {
    ISSN ISSNObject;
    JournalIssue JournalIssueObject;
    private String Title;
    private String ISOAbbreviation;


    // Getter Methods

    public ISSN getISSN() {
        return ISSNObject;
    }

    public JournalIssue getJournalIssue() {
        return JournalIssueObject;
    }

    @XmlElement(name = "Title")
    public String getTitle() {
        return Title;
    }

    public String getISOAbbreviation() {
        return ISOAbbreviation;
    }

    // Setter Methods

    public void setISSN(ISSN ISSNObject) {
        this.ISSNObject = ISSNObject;
    }

    public void setJournalIssue(JournalIssue JournalIssueObject) {
        this.JournalIssueObject = JournalIssueObject;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setISOAbbreviation(String ISOAbbreviation) {
        this.ISOAbbreviation = ISOAbbreviation;
    }
}
