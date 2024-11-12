package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private DeathNote deathNote;

    @BeforeEach
    public void setUp(){
        deathNote = new DeathNoteImplementation();
    }


    @Test
    public void testGetNegativeOrZeroRule(){   
        
        try{
            deathNote.getRule(0);
            Assertions.fail("Getting rule number 0 should throw an error but it didn't");
        }catch(Exception zeroRuleException){
            assertEquals(IllegalArgumentException.class, zeroRuleException.getClass());
            assertNotNull(zeroRuleException.getMessage());
            assertFalse(zeroRuleException.getMessage().isBlank());
        }

        try{
            deathNote.getRule(-1);
            Assertions.fail("Getting a negative rule should throw an error but it didn't");
        }catch(Exception negativeRuleException){
            assertEquals(IllegalArgumentException.class, negativeRuleException.getClass());
            assertNotNull(negativeRuleException.getMessage());
            assertFalse(negativeRuleException.getMessage().isBlank());
        }
    }

    @Test
    public void testNoRulesAreEmpty(){
        for(String el : DeathNote.RULES){
            assertNotNull(el);
            assertFalse(el.isBlank());
        }
    }


}