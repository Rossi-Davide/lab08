package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private DeathNote deathNote;
    private static final String HUMAN_NAME = "Nicholas Magi"; 
    private static final String SECOND_HUMAN_NAME = "Davide Rossi";

    @BeforeEach
    public void setUp(){
        deathNote = new DeathNoteImplementation();
    }


    @Test
    public void testGetNegativeOrZeroRule(){   
        
        IllegalArgumentException zeroRuleException = assertThrows(
                IllegalArgumentException.class,
                ()->deathNote.getRule(0),
                "Getting rule number 0 should throw an error but it didn't");
            
        assertNotNull(zeroRuleException.getMessage());
        assertFalse(zeroRuleException.getMessage().isBlank());
        IllegalArgumentException negativeRuleException = assertThrows(
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
    public void testWritingToDeathNote(){
        assertFalse(deathNote.isNameWritten(HUMAN_NAME));
        deathNote.writeName(HUMAN_NAME);
        assertFalse(deathNote.isNameWritten(SECOND_HUMAN_NAME));
        assertFalse(deathNote.isNameWritten(SECOND_HUMAN_NAME));
    }


}