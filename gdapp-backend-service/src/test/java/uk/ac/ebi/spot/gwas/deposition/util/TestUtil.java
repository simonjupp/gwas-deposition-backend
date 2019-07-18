package uk.ac.ebi.spot.gwas.deposition.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import uk.ac.ebi.spot.gwas.deposition.constants.PublicationStatus;
import uk.ac.ebi.spot.gwas.deposition.domain.CorrespondingAuthor;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.domain.User;
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsRequestDto;
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsRequestEntryDto;
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsResponseDto;
import uk.ac.ebi.spot.gwas.deposition.dto.summarystats.SummaryStatsStatusDto;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.SummaryStatsSchemaDto;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateColumnDto;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSchemaDto;
import uk.ac.ebi.spot.gwas.deposition.dto.templateschema.TemplateSheetDto;

import java.util.Arrays;

public class TestUtil {

    public static User user() {
        return new User(RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10));
    }

    public static Publication publication() {
        return new Publication(RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                DateTime.now(),
                new CorrespondingAuthor(RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10)),
                PublicationStatus.ELIGIBLE.name());
    }

    public static TemplateSchemaDto templateSchemaDto() {
        return new TemplateSchemaDto(RandomStringUtils.randomAlphanumeric(10),
                templateSheetDto(false),
                templateSheetDto(true),
                templateSheetDto(false),
                templateSheetDto(false));
    }

    public static TemplateSheetDto templateSheetDto(boolean withSS) {
        return new TemplateSheetDto(RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                Arrays.asList(new TemplateColumnDto[]{new TemplateColumnDto(
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        true,
                        true,
                        RandomStringUtils.randomAlphanumeric(10),
                        10.0,
                        10.0,
                        Arrays.asList(new String[]{
                                RandomStringUtils.randomAlphanumeric(10),
                                RandomStringUtils.randomAlphanumeric(10),
                                RandomStringUtils.randomAlphanumeric(10)
                        }),
                        withSS ? summaryStatsSchemaDto() : null
                )}));
    }

    public static SummaryStatsRequestDto summaryStatsRequestDto() {
        return new SummaryStatsRequestDto(Arrays.asList(new SummaryStatsRequestEntryDto[]{
                new SummaryStatsRequestEntryDto(RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10)),
                new SummaryStatsRequestEntryDto(RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10))
        }));
    }

    public static SummaryStatsSchemaDto summaryStatsSchemaDto() {
        return new SummaryStatsSchemaDto(RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10));
    }

    public static SummaryStatsResponseDto summaryStatsResponseDto(String callbackId) {
        return new SummaryStatsResponseDto(callbackId,
                false,
                Arrays.asList(new SummaryStatsStatusDto[]{
                        new SummaryStatsStatusDto(
                                RandomStringUtils.randomAlphanumeric(10),
                                RandomStringUtils.randomAlphanumeric(10),
                                RandomStringUtils.randomAlphanumeric(10)),
                        new SummaryStatsStatusDto(
                                RandomStringUtils.randomAlphanumeric(10),
                                RandomStringUtils.randomAlphanumeric(10),
                                RandomStringUtils.randomAlphanumeric(10))
                }));
    }
}
