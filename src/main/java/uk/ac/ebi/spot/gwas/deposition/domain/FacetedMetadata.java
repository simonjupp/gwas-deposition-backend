package uk.ac.ebi.spot.gwas.deposition.domain;

import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode
public class FacetedMetadata {

    private int page;

    private int itemsPerPage;

    private int totalPages;

    private int totalItems;

    private Map<String, String> filters;

    public FacetedMetadata(int page, int itemsPerPage, int totalPages, int totalItems,
                           Map<String, String> filters) {
        this.page = page;
        this.itemsPerPage = itemsPerPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.filters = filters;
    }

    public int getPage() {
        return page;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public Map<String, String> getFilters() {
        return filters;
    }
}
