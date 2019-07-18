package uk.ac.ebi.spot.gwas.template.validator.component.parser;

public interface CellValidationParserAdapterFactory {

    CellValidationParser getAdapter(String adapterName);

}
