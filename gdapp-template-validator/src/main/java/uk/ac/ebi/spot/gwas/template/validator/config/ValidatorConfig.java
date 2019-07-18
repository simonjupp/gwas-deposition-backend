package uk.ac.ebi.spot.gwas.template.validator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import uk.ac.ebi.spot.gwas.template.validator.component.parser.CellValidationParserAdapterFactory;
import uk.ac.ebi.spot.gwas.template.validator.component.validator.TemplateValidatorAdapterFactory;

@Component
public class ValidatorConfig {

    @Bean
    public ServiceLocatorFactoryBean templateValidatorAdapterFactoryBean() {
        ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
        bean.setServiceLocatorInterface(TemplateValidatorAdapterFactory.class);
        return bean;
    }

    @Bean
    public ServiceLocatorFactoryBean cellValidationParserAdapterFactoryBean() {
        ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
        bean.setServiceLocatorInterface(CellValidationParserAdapterFactory.class);
        return bean;
    }

    @Bean
    public TemplateValidatorAdapterFactory templateValidatorAdapterFactory() {
        return (TemplateValidatorAdapterFactory) templateValidatorAdapterFactoryBean().getObject();
    }

    @Bean
    public CellValidationParserAdapterFactory cellValidationParserAdapterFactory() {
        return (CellValidationParserAdapterFactory) cellValidationParserAdapterFactoryBean().getObject();
    }

    @Value("${validator.default-schema.version}")
    private String defaultSchemaVersion;

    @Value("${validator.default-schema.file}")
    private String defaultSchemaFile;

    public String getDefaultSchemaVersion() {
        return defaultSchemaVersion;
    }

    public String getDefaultSchemaFile() {
        return defaultSchemaFile;
    }
}
