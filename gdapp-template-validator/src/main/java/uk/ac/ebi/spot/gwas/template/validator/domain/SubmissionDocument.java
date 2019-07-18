package uk.ac.ebi.spot.gwas.template.validator.domain;

import java.util.ArrayList;
import java.util.List;

public class SubmissionDocument {

    private List<Study> studyEntries;

    private List<Association> associationEntries;

    private List<Sample> sampleEntries;

    private List<Note> noteEntries;

    public SubmissionDocument() {
        studyEntries = new ArrayList<>();
        associationEntries = new ArrayList<>();
        sampleEntries = new ArrayList<>();
        noteEntries = new ArrayList<>();
    }

    public void addStudyEntry(Study study) {
        studyEntries.add(study);
    }

    public void addAssociation(Association association) {
        associationEntries.add(association);
    }

    public void addSample(Sample sample) {
        sampleEntries.add(sample);
    }

    public void addNote(Note note) {
        noteEntries.add(note);
    }

    public List<Study> getStudyEntries() {
        return studyEntries;
    }

    public List<Association> getAssociationEntries() {
        return associationEntries;
    }

    public List<Sample> getSampleEntries() {
        return sampleEntries;
    }

    public List<Note> getNoteEntries() {
        return noteEntries;
    }
}
