package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class StudyDto implements Serializable {

    private static final long serialVersionUID = -2822216095301860842L;

    @NotEmpty
    @JsonProperty("study_tag")
    private final String studyTag;

    @NotEmpty
    @JsonProperty("genotyping_technology")
    private final String genotypingTechnology;

    @JsonProperty("array_manufacturer")
    private final String arrayManufacturer;

    @JsonProperty("array_information")
    private final String arrayInformation;

    @NotNull
    @JsonProperty("imputation")
    private final Boolean imputation;

    @NotNull
    @JsonProperty("variant_count")
    private final Integer variantCount;

    @JsonProperty("statistical_model")
    private final String statisticalModel;

    @JsonProperty("study_description")
    private final String studyDescription;

    @NotEmpty
    @JsonProperty("trait")
    private final String trait;

    @JsonProperty("efo_trait")
    private final String efoTrait;

    @JsonProperty("background_trait")
    private final String backgroundTrait;

    @JsonProperty("background_efo_trait")
    private final String backgroundEfoTrait;

    @JsonProperty("summary_statistics_file")
    private final String summaryStatisticsFile;

    @JsonProperty("summary_statistics_assembly")
    private final String summaryStatisticsAssembly;

    @JsonCreator
    public StudyDto(@JsonProperty("study_tag") String studyTag,
                    @JsonProperty("genotyping_technology") String genotypingTechnology,
                    @JsonProperty("array_manufacturer") String arrayManufacturer,
                    @JsonProperty("array_information") String arrayInformation,
                    @JsonProperty("imputation") Boolean imputation,
                    @JsonProperty("variant_count") Integer variantCount,
                    @JsonProperty("statistical_model") String statisticalModel,
                    @JsonProperty("study_description") String studyDescription,
                    @JsonProperty("trait") String trait,
                    @JsonProperty("efo_trait") String efoTrait,
                    @JsonProperty("background_trait") String backgroundTrait,
                    @JsonProperty("background_efo_trait") String backgroundEfoTrait,
                    @JsonProperty("summary_statistics_file") String summaryStatisticsFile,
                    @JsonProperty("summary_statistics_assembly") String summaryStatisticsAssembly) {
        this.studyTag = studyTag;
        this.genotypingTechnology = genotypingTechnology;
        this.arrayManufacturer = arrayManufacturer;
        this.arrayInformation = arrayInformation;
        this.imputation = imputation;
        this.variantCount = variantCount;
        this.statisticalModel = statisticalModel;
        this.studyDescription = studyDescription;
        this.trait = trait;
        this.efoTrait = efoTrait;
        this.backgroundTrait = backgroundTrait;
        this.backgroundEfoTrait = backgroundEfoTrait;
        this.summaryStatisticsFile = summaryStatisticsFile;
        this.summaryStatisticsAssembly = summaryStatisticsAssembly;
    }

    public String getStudyTag() {
        return studyTag;
    }

    public String getGenotypingTechnology() {
        return genotypingTechnology;
    }

    public String getArrayManufacturer() {
        return arrayManufacturer;
    }

    public String getArrayInformation() {
        return arrayInformation;
    }

    public Boolean getImputation() {
        return imputation;
    }

    public Integer getVariantCount() {
        return variantCount;
    }

    public String getStatisticalModel() {
        return statisticalModel;
    }

    public String getStudyDescription() {
        return studyDescription;
    }

    public String getTrait() {
        return trait;
    }

    public String getEfoTrait() {
        return efoTrait;
    }

    public String getBackgroundTrait() {
        return backgroundTrait;
    }

    public String getBackgroundEfoTrait() {
        return backgroundEfoTrait;
    }

    public String getSummaryStatisticsFile() {
        return summaryStatisticsFile;
    }

    public String getSummaryStatisticsAssembly() {
        return summaryStatisticsAssembly;
    }

}
