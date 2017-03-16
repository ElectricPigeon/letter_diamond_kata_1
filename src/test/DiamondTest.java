package test;
import com.katas.Diamond;

import java.util.List;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

public class DiamondTest {


    @Test
    public void testForCorrectNumberOfSpaces(){
        char letter = 'B';
        Diamond d = new Diamond();
        d.processInput(String.valueOf(letter));
        assert d.get(1).equals("B   B");
    }

    @Test
    public void testMiddleLevelHasNoExtraSpaces(){
        Diamond d = new Diamond();
        d.processInput("A");
        assert d.get(0).charAt(0) == 'A';
        assert d.getSize() == 1;
    }

    @Test
    public void testGivenLevelHasLettersAtAppropriateIndices(){
        Diamond d = new Diamond();
        d.processInput("D");
        String lineB = d.get(1);
        assert lineB.equals("  B B  ");
    }

    @Test
    public void testCorrectLettersForGivenString(){
        Diamond d = new Diamond();
        d.processInput("D");
        String lineA = d.get(0);
        String lineB = d.get(1);
        String lineC = d.get(2);
        String lineD = d.get(3);
        assert lineA.equals("   A   ");
        assert lineB.equals("  B B  ");
        assert lineC.equals(" C   C ");
        assert lineD.equals("D     D");
    }

    @Test
    public void testLowercaseCharacterAccepted() {
        Diamond d = new Diamond();
        d.processInput("d");
        String lineA = d.get(0);
        String lineB = d.get(1);
        String lineC = d.get(2);
        String lineD = d.get(3);
        assert lineA.equals("   A   ");
        assert lineB.equals("  B B  ");
        assert lineC.equals(" C   C ");
        assert lineD.equals("D     D");
    }

    @Test
    public void correctlyTreatsStringInput(){
        Diamond d = new Diamond();
        d.processInput("argh");
        List<String> resultBody = d.getBody();
        for (String line: resultBody){
            int divider = line.length()/2;
            String firstHalf = line.substring(0, divider);
            String secondHalf = line.substring(divider + 1); //get past the middle thing
            String firstHalfReversed = d.reverseString(firstHalf);
            assert secondHalf.equals(firstHalfReversed);
        }
    }

}

