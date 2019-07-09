package uk.ac.ebi.spot.gwas.deposition.rest.dto.assembly;

import uk.ac.ebi.spot.gwas.deposition.domain.Note;
import uk.ac.ebi.spot.gwas.deposition.rest.dto.NoteDto;

public class NoteDtoAssembler {

    public static NoteDto assemble(Note note) {
        return new NoteDto(note.getStudyTag(),
                note.getNote(),
                note.getNoteSubject(),
                note.getStatus());
    }
}
