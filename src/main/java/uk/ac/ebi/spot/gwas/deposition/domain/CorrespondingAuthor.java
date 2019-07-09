package uk.ac.ebi.spot.gwas.deposition.domain;

public class CorrespondingAuthor {

    private String authorName;

    private String email;

    public CorrespondingAuthor(String authorName, String email) {
        this.authorName = authorName;
        this.email = email;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getEmail() {
        return email;
    }
}
