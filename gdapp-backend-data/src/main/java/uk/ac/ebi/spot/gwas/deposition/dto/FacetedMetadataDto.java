package uk.ac.ebi.spot.gwas.deposition.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Map;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class FacetedMetadataDto implements Serializable {

    private static final long serialVersionUID = -6055956661516071807L;

    @JsonProperty("page")
    private final Integer page;

    @JsonProperty("itemsPerPage")
    private final Integer itemsPerPage;

    @JsonProperty("totalPages")
    private final Integer totalPages;

    @JsonProperty("totalItems")
    private final Integer totalItems;

    @JsonProperty("filters")
    private final Map<String, String> filters;

    public FacetedMetadataDto(@JsonProperty("page") Integer page,
                              @JsonProperty("itemsPerPage") Integer itemsPerPage,
                              @JsonProperty("totalPages") Integer totalPages,
                              @JsonProperty("totalItems") Integer totalItems,
                              @JsonProperty("filters") Map<String, String> filters) {
        this.page = page;
        this.itemsPerPage = itemsPerPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.filters = filters;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public Integer getTotalItems() {
        return totalItems;
    }
}
