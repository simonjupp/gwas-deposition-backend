package uk.ac.ebi.spot.gwas.template.validator.component.validator;

public interface TemplateValidatorAdapterFactory {

    TemplateValidator getAdapter(String adapterName);

}
