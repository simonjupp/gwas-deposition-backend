package uk.ac.ebi.spot.gwas.deposition.rest.dto.templateschema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TemplateColumnDto implements Serializable {

    private static final long serialVersionUID = -8653172690497636682L;

    @NotEmpty
    @JsonProperty("columnName")
    private final String columnName;

    @NotEmpty
    @JsonProperty("columnHeading")
    private final String columnHeading;

    @NotEmpty
    @JsonProperty("baseType")
    private final String baseType;

    @NotEmpty
    @JsonProperty("required")
    private final Boolean required;

    @JsonProperty("pattern")
    private final String pattern;

    @JsonProperty("lowerBound")
    private final Double lowerBound;

    @JsonProperty("upperBound")
    private final Double upperBound;

    @JsonProperty("acceptedValues")
    private final List<String> acceptedValues;

    @JsonProperty("summaryStatsSchema")
    private final SummaryStatsSchemaDto summaryStatsSchema;

    @JsonCreator
    public TemplateColumnDto(@JsonProperty("columnName") String columnName,
                             @JsonProperty("columnHeading") String columnHeading,
                             @JsonProperty("baseType") String baseType,
                             @JsonProperty("required") Boolean required,
                             @JsonProperty("pattern") String pattern,
                             @JsonProperty("lowerBound") Double lowerBound,
                             @JsonProperty("upperBound") Double upperBound,
                             @JsonProperty("acceptedValues") List<String> acceptedValues,
                             @JsonProperty("summaryStatsSchema") SummaryStatsSchemaDto summaryStatsSchema) {
        this.columnName = columnName;
        this.columnHeading = columnHeading;
        this.baseType = baseType;
        this.required = required;
        this.pattern = pattern;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.acceptedValues = acceptedValues;
        this.summaryStatsSchema = summaryStatsSchema;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnHeading() {
        return columnHeading;
    }

    public String getBaseType() {
        return baseType;
    }

    public Boolean getRequired() {
        return required;
    }

    public String getPattern() {
        return pattern;
    }

    public Double getLowerBound() {
        return lowerBound;
    }

    public Double getUpperBound() {
        return upperBound;
    }

    public List<String> getAcceptedValues() {
        return acceptedValues;
    }

    public SummaryStatsSchemaDto getSummaryStatsSchema() {
        return summaryStatsSchema;
    }
}
