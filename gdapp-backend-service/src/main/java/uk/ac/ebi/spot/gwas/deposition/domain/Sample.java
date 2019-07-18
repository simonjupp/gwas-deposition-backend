package uk.ac.ebi.spot.gwas.deposition.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "samples")
public class Sample {

    @Id
    private String id;

    private String studyTag;

    private String stage;

    private Integer size;

    private Integer cases;

    private Integer controls;

    private String sampleDescription;

    private String ancestryCategory;

    private String ancestry;

    private String ancestryDescription;

    private String countryRecruitement;

    public Sample() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudyTag() {
        return studyTag;
    }

    public void setStudyTag(String studyTag) {
        this.studyTag = studyTag;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getControls() {
        return controls;
    }

    public void setControls(Integer controls) {
        this.controls = controls;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public String getAncestryCategory() {
        return ancestryCategory;
    }

    public void setAncestryCategory(String ancestryCategory) {
        this.ancestryCategory = ancestryCategory;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getAncestryDescription() {
        return ancestryDescription;
    }

    public void setAncestryDescription(String ancestryDescription) {
        this.ancestryDescription = ancestryDescription;
    }

    public String getCountryRecruitement() {
        return countryRecruitement;
    }

    public void setCountryRecruitement(String countryRecruitement) {
        this.countryRecruitement = countryRecruitement;
    }
}
