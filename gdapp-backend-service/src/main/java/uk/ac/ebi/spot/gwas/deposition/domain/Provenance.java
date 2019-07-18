package uk.ac.ebi.spot.gwas.deposition.domain;

import org.joda.time.DateTime;

public class Provenance {

    private DateTime timestamp;

    private String userId;

    public Provenance(DateTime timestamp, String userId) {
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }
}
