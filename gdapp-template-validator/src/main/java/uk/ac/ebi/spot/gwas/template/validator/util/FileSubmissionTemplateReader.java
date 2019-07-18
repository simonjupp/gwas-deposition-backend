package uk.ac.ebi.spot.gwas.template.validator.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class FileSubmissionTemplateReader implements SubmissionTemplateReader {

    private FileInputStream excelFile;

    private Workbook importedWorkbook;

    private boolean valid;

    private String schemaVersion;

    public FileSubmissionTemplateReader(File input) {
        valid = true;
        try {
            readFile(input);
        } catch (IOException e) {
            System.err.println("Unable to read submission document: " + e.getMessage());
            valid = false;
        }
    }

    private void readFile(File input) throws IOException {
        excelFile = new FileInputStream(input);
        importedWorkbook = new XSSFWorkbook(excelFile);

        XSSFWorkbook xssfWorkbook = (XSSFWorkbook) importedWorkbook;
        if (xssfWorkbook.getProperties() != null) {
            if (xssfWorkbook.getProperties().getCoreProperties() != null) {
                schemaVersion = xssfWorkbook.getProperties().getCoreProperties().getDescription();
            }
        }
    }

    @Override
    public Iterator<Sheet> sheets() {
        return importedWorkbook.sheetIterator();
    }

    @Override
    public void close() {
        try {
            importedWorkbook.close();
            excelFile.close();
        } catch (IOException e) {
            System.err.println("Unable to close submission document: " + e.getMessage());
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}
