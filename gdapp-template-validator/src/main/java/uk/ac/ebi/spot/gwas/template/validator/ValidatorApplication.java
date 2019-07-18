package uk.ac.ebi.spot.gwas.template.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.ebi.spot.gwas.template.validator.service.TemplateValidatorService;

@SpringBootApplication
public class ValidatorApplication implements CommandLineRunner {

    @Autowired
    private TemplateValidatorService templateValidatorService;

    public static void main(String[] args) {
        SpringApplication.run(ValidatorApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length != 1) {
            System.err.println("Please specify an input file.");
        }
        /*
        SubmissionTemplateReader submissionTemplateReader = new FileSubmissionTemplateReader(new File(args[0]));
//        SubmissionTemplateReader submissionTemplateReader = new FileSubmissionTemplateReader(new File("/home/tudor/dev/gwas-template-validator/src/test/resources/incorrect_accepted_value.xlsx"));
        ValidationOutcome validationOutcome = templateValidatorService.validate(submissionTemplateReader);

        if (validationOutcome.isValid()) {
            System.out.println("No errors found while validating submission!");
        } else {
            Map<String, List<String>> errors = validationOutcome.getErrorMessages();
            System.err.println("Errors found while validating submission!");
            for (String sheet : errors.keySet()) {
                System.err.println("Sheet: " + sheet);
                for (String error : errors.get(sheet)) {
                    System.err.println(" - " + error);
                }
            }
        }

        submissionTemplateReader.close();
         */
    }
}
