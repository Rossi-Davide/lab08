package it.unibo.deathnote.impl;


import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.SequencedMap;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    private final SequencedMap<String,DeathRecord> deathNote;

    private static class DeathRecord{

        private static final String DEFAULT_CAUSE_OF_DEATH = "heart attack";
        private String causeOfDeath;
        private Date causeOfDeathTime;
        private String deathDetails;
        private Date deathDetailsTime;

        public DeathRecord(){
            this.causeOfDeath = DEFAULT_CAUSE_OF_DEATH;
            this.deathDetails = "";
        }

        public String getCauseOfDeath(){
            return this.causeOfDeath;
        }

        public Date getCauseOfDeathWritingTime(){
            return this.causeOfDeathTime;
        }

        public String getDeathDetails(){
            return this.deathDetails;
        }

        public Date getDeathDetailsWritingTime(){
            return this.deathDetailsTime;
        }

        public void setCauseOfDeath(final String causeOfDeath){
            this.causeOfDeath = causeOfDeath;
            this.causeOfDeathTime = new Date(System.currentTimeMillis());
        }

        public void setDeathDetails(final String deathDetails){
            this.deathDetails = deathDetails;
            this.deathDetailsTime = new Date(System.currentTimeMillis());
        }
    }


    public DeathNoteImplementation(){
        deathNote = new LinkedHashMap<>();
    }


    @Override
    public String getRule(int ruleNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRule'");
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
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

}