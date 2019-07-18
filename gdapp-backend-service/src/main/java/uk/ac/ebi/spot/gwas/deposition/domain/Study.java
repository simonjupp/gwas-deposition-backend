package uk.ac.ebi.spot.gwas.deposition.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "studies")
public class Study {

    @Id
    private String id;

    private String studyTag;

    private String genotypingTechnology;

    private String arrayManufacturer;

    private String arrayInformation;

    private Boolean imputation;

    private Integer variantCount;

    private String statisticalModel;

    private String studyDescription;

    private String trait;

    private String efoTrait;

    private String backgroundTrait;

    private String backgroundEfoTrait;

    private String summaryStatisticsFile;

    private String summaryStatisticsAssembly;

    public Study() {

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

    public String getGenotypingTechnology() {
        return genotypingTechnology;
    }

    public void setGenotypingTechnology(String genotypingTechnology) {
        this.genotypingTechnology = genotypingTechnology;
    }

    public String getArrayManufacturer() {
        return arrayManufacturer;
    }

    public void setArrayManufacturer(String arrayManufacturer) {
        this.arrayManufacturer = arrayManufacturer;
    }

    public String getArrayInformation() {
        return arrayInformation;
    }

    public void setArrayInformation(String arrayInformation) {
        this.arrayInformation = arrayInformation;
    }

    public Boolean getImputation() {
        return imputation;
    }

    public void setImputation(Boolean imputation) {
        this.imputation = imputation;
    }

    public Integer getVariantCount() {
        return variantCount;
    }

    public void setVariantCount(Integer variantCount) {
        this.variantCount = variantCount;
    }

    public String getStatisticalModel() {
        return statisticalModel;
    }

    public void setStatisticalModel(String statisticalModel) {
        this.statisticalModel = statisticalModel;
    }

    public String getStudyDescription() {
        return studyDescription;
    }

    public void setStudyDescription(String studyDescription) {
        this.studyDescription = studyDescription;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getEfoTrait() {
        return efoTrait;
    }

    public void setEfoTrait(String efoTrait) {
        this.efoTrait = efoTrait;
    }

    public String getBackgroundTrait() {
        return backgroundTrait;
    }

    public void setBackgroundTrait(String backgroundTrait) {
        this.backgroundTrait = backgroundTrait;
    }

    public String getBackgroundEfoTrait() {
        return backgroundEfoTrait;
    }

    public void setBackgroundEfoTrait(String backgroundEfoTrait) {
        this.backgroundEfoTrait = backgroundEfoTrait;
    }

    public String getSummaryStatisticsFile() {
        return summaryStatisticsFile;
    }

    public void setSummaryStatisticsFile(String summaryStatisticsFile) {
        this.summaryStatisticsFile = summaryStatisticsFile;
    }

    public String getSummaryStatisticsAssembly() {
        return summaryStatisticsAssembly;
    }

    public void setSummaryStatisticsAssembly(String summaryStatisticsAssembly) {
        this.summaryStatisticsAssembly = summaryStatisticsAssembly;
    }
}
