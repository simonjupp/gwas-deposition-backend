package uk.ac.ebi.spot.gwas.deposition.rest.dto;


import uk.ac.ebi.spot.gwas.deposition.domain.Association;
import uk.ac.ebi.spot.gwas.deposition.dto.AssociationDto;

public class AssociationDtoAssembler {

    public static AssociationDto assemble(Association association) {
        return new AssociationDto(association.getStudyTag(),
                association.getHaplotypeId(),
                association.getVariantId(),
                association.getPvalue(),
                association.getPvalueText(),
                association.getProxyVariant(),
                association.getEffectAllele(),
                association.getOtherAllele(),
                association.getEffectAlleleFrequency(),
                association.getOddsRatio(),
                association.getBeta(),
                association.getBetaUnit(),
                association.getCiLower(),
                association.getCiUpper(),
                association.getStandardError());
    }

}
