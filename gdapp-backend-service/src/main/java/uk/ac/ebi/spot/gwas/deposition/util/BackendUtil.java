package uk.ac.ebi.spot.gwas.deposition.util;

public class BackendUtil {

    public static int computeTotalPages(int totalItems, int itemsPerPage) {
        int division = totalItems / itemsPerPage;
        int rest = totalItems % itemsPerPage;
        return rest == 0 ? division : division + 1;
    }

}
