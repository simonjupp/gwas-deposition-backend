package uk.ac.ebi.spot.gwas.deposition.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import uk.ac.ebi.spot.gwas.deposition.constants.PublicationStatus;
import uk.ac.ebi.spot.gwas.deposition.domain.CorrespondingAuthor;
import uk.ac.ebi.spot.gwas.deposition.domain.Publication;
import uk.ac.ebi.spot.gwas.deposition.domain.User;

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
                Arrays.asList(new String[]{
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10)
                }),
                DateTime.now(),
                new CorrespondingAuthor(RandomStringUtils.randomAlphanumeric(10),
                        RandomStringUtils.randomAlphanumeric(10)),
                PublicationStatus.ELIGIBLE.name());
    }
}
