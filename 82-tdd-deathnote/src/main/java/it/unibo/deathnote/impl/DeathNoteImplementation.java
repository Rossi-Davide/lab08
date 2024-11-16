package it.unibo.deathnote.impl;


import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.SequencedMap;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    private static final long TIME_CAUSE = 40;
    private static final long TIME_DETAILS = 6040;

    private final SequencedMap<String,DeathRecord> deathNote;

    private static class DeathRecord{

        private static final String DEFAULT_CAUSE_OF_DEATH = "heart attack";
        private Date nameTime;
        private String causeOfDeath;
        private Date causeOfDeathTime;
        private String deathDetails;

        public DeathRecord(){
            
            final long currentTimeMillis = System.currentTimeMillis(); 
            this.nameTime = new Date(currentTimeMillis);
            this.causeOfDeath = DEFAULT_CAUSE_OF_DEATH;
            this.deathDetails = "";
            this.causeOfDeathTime = new Date(currentTimeMillis);
        }

        public void setCauseOfDeath(final String causeOfDeath){
            this.causeOfDeath = causeOfDeath;
            this.causeOfDeathTime = new Date(System.currentTimeMillis());
        }

        public void setDeathDetails(final String deathDetails){
            this.deathDetails = deathDetails;
        }
    }

    public DeathNoteImplementation(){
        deathNote = new LinkedHashMap<>();
    }

    @Override
    public String getRule(final int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > DeathNote.RULES.size()){
            throw new IllegalArgumentException("The rule number requested is invalid because it's out of the bounds of the rules list");
        }

        return DeathNote.RULES.get(ruleNumber);
    }

    @Override
    public void writeName(final String name) {
        if(name == null){
            throw new NullPointerException("The given name is null");
        }
        if(!deathNote.containsKey(name)){
            deathNote.put(name,new DeathRecord());
        }
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        final Date newCauseTime = new Date(System.currentTimeMillis());
        if(cause == null){
            throw new IllegalStateException("The cause to write was null");
        }
        if(deathNote.isEmpty()){
            throw new IllegalStateException("It is not possible to add a cause since the death note is empty");
        }

        final DeathRecord lastRecord = deathNote.lastEntry().getValue();

        if((newCauseTime.getTime() - lastRecord.nameTime.getTime()) <= TIME_CAUSE){
            lastRecord.setCauseOfDeath(cause);
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(final String details) {
        final Date newDetailsTime = new Date(System.currentTimeMillis());
        if(details == null){
            throw new IllegalStateException("The cause to write was null");
        }
        if(deathNote.isEmpty()){
            throw new IllegalStateException("It is not possible to add details since the death note is empty");
        }

        final DeathRecord lastRecord = deathNote.lastEntry().getValue();

        if((newDetailsTime.getTime() - lastRecord.causeOfDeathTime.getTime()) <= TIME_DETAILS){
            lastRecord.setDeathDetails(details);
            return true;
        }
        return false;
    }

    @Override
    public String getDeathCause(final String name) {
        if(!deathNote.containsKey(name)){
            throw new IllegalArgumentException("The requested name is not in the death note");
        }
        return deathNote.get(name).causeOfDeath;
    }

    @Override
    public String getDeathDetails(final String name) {
        if(!deathNote.containsKey(name)){
            throw new IllegalArgumentException("The requested name is not in the death note");
        }
        return deathNote.get(name).deathDetails;
    }

    @Override
    public boolean isNameWritten(final String name) {
        return deathNote.containsKey(name);
    }

}