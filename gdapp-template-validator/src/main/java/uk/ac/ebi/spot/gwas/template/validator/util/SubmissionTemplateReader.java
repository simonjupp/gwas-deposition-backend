package uk.ac.ebi.spot.gwas.template.validator.util;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

public interface SubmissionTemplateReader {

    Iterator<Sheet> sheets();

    void close();

    boolean isValid();

}
