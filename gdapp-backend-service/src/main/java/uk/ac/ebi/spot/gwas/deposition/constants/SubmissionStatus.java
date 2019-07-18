package uk.ac.ebi.spot.gwas.deposition.constants;

public enum SubmissionStatus {

    STARTED,
    VALIDATING_METADATA,
    INVALID_METADATA,
    VALID_METADATA,
    VALIDATING_SUMMARY_STATS,
    INVALID_SUMMARY_STATS,
    VALID_SUMMARY_STATS,
    VALID,
    SUBMITTED,
    EXPORTED

}
