package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private DeathNote deathNote;
    private static final String HUMAN_NAME = "Nicholas Magi"; 
    private static final String SECONDARY_HUMAN_NAME = "Davide Rossi";
    private static final String CAUSE_OF_DEATH = "heart attack";
    private static final String SECONDARY_CAUSE_OF_DEATH = "karting accident";
    private static final String DEATH_DETAILS = "ran for too long";

    @BeforeEach
    public void setUp(){
        deathNote = new DeathNoteImplementation();
    }


    @Test
    public void testGetNegativeOrZeroRule(){   
        
        final IllegalArgumentException zeroRuleException = assertThrows(
                IllegalArgumentException.class,
                ()->deathNote.getRule(0),
                "Getting rule number 0 should throw an error but it didn't");
            
        assertNotNull(zeroRuleException.getMessage());
        assertFalse(zeroRuleException.getMessage().isBlank());
        final IllegalArgumentException negativeRuleException = assertThrows(
                IllegalArgumentException.class,
                ()->deathNote.getRule(0),
                "Getting a negative rule should throw an error but it didn't");   
        assertNotNull(negativeRuleException.getMessage());
        assertFalse(negativeRuleException.getMessage().isBlank());

    }

    @Test
    public void testNoRulesAreEmpty(){
        for(String el : DeathNote.RULES){
            assertNotNull(el);
            assertFalse(el.isBlank());
        }
    }

    @Test
    public void testWritingNameToDeathNote(){
        assertFalse(deathNote.isNameWritten(HUMAN_NAME));
        deathNote.writeName(HUMAN_NAME);
        assertFalse(deathNote.isNameWritten(SECONDARY_HUMAN_NAME));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test 
    public void testWritingCauseOfDeath(){
        final IllegalStateException emptyDeathNoteException = assertThrows(
            IllegalStateException.class, 
            ()->deathNote.writeDeathCause(CAUSE_OF_DEATH),
            "Writing a cause of death to an empty death note should throw an error but it didn't");
        assertNotNull(emptyDeathNoteException.getMessage());
        assertFalse(emptyDeathNoteException.getMessage().isBlank());
        deathNote.writeName(HUMAN_NAME);
        assertEquals(CAUSE_OF_DEATH,deathNote.getDeathCause(HUMAN_NAME));
        deathNote.writeName(SECONDARY_HUMAN_NAME);
        assertTrue(deathNote.writeDeathCause(SECONDARY_CAUSE_OF_DEATH));
        assertEquals(SECONDARY_CAUSE_OF_DEATH,deathNote.getDeathCause(SECONDARY_HUMAN_NAME));
        try{
            Thread.sleep(100);
            assertFalse(deathNote.writeDeathCause(CAUSE_OF_DEATH));
            assertEquals(SECONDARY_CAUSE_OF_DEATH, deathNote.getDeathCause(SECONDARY_HUMAN_NAME));
        }catch(InterruptedException threadSleepException){
            System.err.println(threadSleepException.getMessage());
            Assertions.fail("Test failed due to an error not pertinent to the death note");
        }       
    }

    @Test
    public void testWritingDeathDetails(){
        final IllegalStateException emptyDeathNoteException = assertThrows(
            IllegalStateException.class, 
            ()->deathNote.writeDetails(DEATH_DETAILS),
            "Writing death details to an empty death note should throw an error but it didn't");
        assertNotNull(emptyDeathNoteException.getMessage());
        assertFalse(emptyDeathNoteException.getMessage().isBlank());
        deathNote.writeName(HUMAN_NAME);
        assertTrue(deathNote.getDeathDetails(HUMAN_NAME).isEmpty());
        assertTrue(deathNote.writeDetails(DEATH_DETAILS));
        assertEquals(DEATH_DETAILS, deathNote.getDeathDetails(HUMAN_NAME));
        deathNote.writeName(SECONDARY_HUMAN_NAME);
        try{
            Thread.sleep(6100);
            assertFalse(deathNote.writeDetails(DEATH_DETAILS));
            assertTrue(deathNote.getDeathDetails(SECONDARY_HUMAN_NAME).isEmpty());
        }catch(InterruptedException threadSleepException){
            System.err.println(threadSleepException.getMessage());
            Assertions.fail("Test failed due to an error not pertinent to the death note");    
        }
    }
}