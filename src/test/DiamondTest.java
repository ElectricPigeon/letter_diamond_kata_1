package test;
import com.katas.Diamond;

import java.util.List;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.Test;

public class DiamondTest {

    @Test
    public void testForCorrectNumberOfSpaces(){
        char letter = 'B';
        Diamond diamond = new Diamond();
        diamond.processInput(String.valueOf(letter));
        assert diamond.get(1).equals("B   B");
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
        Diamond testDiamond = new Diamond();
        testDiamond.processInput("D");
        String lineB = testDiamond.get(1);
        assert lineB.equals("  B B  ");
    }

    @Test
    public void testCorrectLettersForGivenString(){
        //tbd
    }

    @Ignore
    @Test
    public void testDiamondCorrectlyReverses(){
        Diamond result = new Diamond();
        result.processInput("argh");
        List<String> resultBody = result.getBody();
        for (String line: resultBody){
            int divider = line.length()/2;
            String firstHalf = line.substring(0, divider);
            String secondHalf = line.substring(divider + 1); //get past the middle thing
            String firstHalfReversed = result.reverse(firstHalf);
            assert secondHalf.equals(firstHalfReversed);
        }
    }

}

