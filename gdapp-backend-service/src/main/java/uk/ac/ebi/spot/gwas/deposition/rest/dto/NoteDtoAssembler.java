package uk.ac.ebi.spot.gwas.deposition.rest.dto;


import uk.ac.ebi.spot.gwas.deposition.domain.Note;
import uk.ac.ebi.spot.gwas.deposition.dto.NoteDto;

public class NoteDtoAssembler {

    public static NoteDto assemble(Note note) {
        return new NoteDto(note.getStudyTag(),
                note.getNote(),
                note.getNoteSubject(),
                note.getStatus());
    }
}
