package uk.ac.ebi.spot.gwas.deposition.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class AssociationDto implements Serializable {

    private static final long serialVersionUID = -8090471838029326631L;

    @NotBlank
    @JsonProperty("study_tag")
    private final String studyTag;

    @JsonProperty("haplotype_id")
    private final String haplotypeId;

    @NotBlank
    @JsonProperty("variant_id")
    private final String variantId;

    @NotBlank
    @JsonProperty("pvalue")
    private final Double pvalue;

    @JsonProperty("pvalue_text")
    private final String pvalueText;

    @JsonProperty("proxy_variant")
    private final String proxyVariant;

    @NotBlank
    @JsonProperty("effect_allele")
    private final String effectAllele;

    @JsonProperty("other_allele")
    private final String otherAllele;

    @NotBlank
    @JsonProperty("effect_allele_frequency")
    private final Double effectAlleleFrequency;

    @JsonProperty("odds_ratio")
    private final Double oddsRatio;

    @JsonProperty("beta")
    private final Double beta;

    @JsonProperty("beta_unit")
    private final String betaUnit;

    @JsonProperty("ci_lower")
    private final Double ciLower;

    @JsonProperty("ci_upper")
    private final Double ciUpper;

    @JsonProperty("standard_error")
    private final Double standardError;

    @JsonCreator
    public AssociationDto(@JsonProperty("study_tag") String studyTag,
                          @JsonProperty("haplotype_id") String haplotypeId,
                          @JsonProperty("variant_id") String variantId,
                          @JsonProperty("pvalue") Double pvalue,
                          @JsonProperty("pvalue_text") String pvalueText,
                          @JsonProperty("proxy_variant") String proxyVariant,
                          @JsonProperty("effect_allele") String effectAllele,
                          @JsonProperty("other_allele") String otherAllele,
                          @JsonProperty("effect_allele_frequency") Double effectAlleleFrequency,
                          @JsonProperty("odds_ratio") Double oddsRatio,
                          @JsonProperty("beta") Double beta,
                          @JsonProperty("beta_unit") String betaUnit,
                          @JsonProperty("ci_lower") Double ciLower,
                          @JsonProperty("ci_upper") Double ciUpper,
                          @JsonProperty("standard_error") Double standardError) {
        this.studyTag = studyTag;
        this.haplotypeId = haplotypeId;
        this.variantId = variantId;
        this.pvalue = pvalue;
        this.pvalueText = pvalueText;
        this.proxyVariant = proxyVariant;
        this.effectAllele = effectAllele;
        this.otherAllele = otherAllele;
        this.effectAlleleFrequency = effectAlleleFrequency;
        this.oddsRatio = oddsRatio;
        this.beta = beta;
        this.betaUnit = betaUnit;
        this.ciLower = ciLower;
        this.ciUpper = ciUpper;
        this.standardError = standardError;
    }

    public String getStudyTag() {
        return studyTag;
    }

    public String getHaplotypeId() {
        return haplotypeId;
    }

    public String getVariantId() {
        return variantId;
    }

    public Double getPvalue() {
        return pvalue;
    }

    public String getPvalueText() {
        return pvalueText;
    }

    public String getProxyVariant() {
        return proxyVariant;
    }

    public String getEffectAllele() {
        return effectAllele;
    }

    public String getOtherAllele() {
        return otherAllele;
    }

    public Double getEffectAlleleFrequency() {
        return effectAlleleFrequency;
    }

    public Double getOddsRatio() {
        return oddsRatio;
    }

    public Double getBeta() {
        return beta;
    }

    public String getBetaUnit() {
        return betaUnit;
    }

    public Double getCiLower() {
        return ciLower;
    }

    public Double getCiUpper() {
        return ciUpper;
    }

    public Double getStandardError() {
        return standardError;
    }

}
