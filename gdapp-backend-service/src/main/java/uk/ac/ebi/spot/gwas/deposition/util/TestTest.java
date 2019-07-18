package uk.ac.ebi.spot.gwas.deposition.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSchemaDto;

import java.io.File;
import java.io.IOException;

public class TestTest {

    public static void main(String[] args) throws IOException {
        ObjectMapper om = new ObjectMapper();
        String content = FileUtils.readFileToString(new File("/home/tudor/deploy/template_schema.json"));
        TemplateSchemaDto templateSchemaDto = om.readValue(content, TemplateSchemaDto.class);

    }

}
