package com.katas;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Diamond implements IDiamond, IProcessInput {

    private static final Character BASIC_MIDDLE_CHAR = ' ';
    private Character middleChar; //for style
    private List<String> body;
    private Stack<Character>alphabetStack;
    private Queue<Character> inUseQueue;
    public String alphabetString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Diamond(){
        this.setBody(new ArrayList<>());
        this.setAlphabetString(reverseString(alphabetString));
        this.setAlphabetStack(buildAlphabetStack(alphabetString));
        this.setInUseQueue(new LinkedList<>());
        this.setMiddleChar(BASIC_MIDDLE_CHAR);
    }

    private Stack<Character> buildAlphabetStack(String alphabetString) {
        alphabetStack = new Stack<Character>();
        for(int index=0; index < alphabetString.length(); index++){
            alphabetStack.push(alphabetString.charAt(index));
        }
        return alphabetStack;
    }

    public String get(int index){ return body.get(index); }

    public int getSize() { return body.size(); }

    public void draw() {
        for(String line : this.getBody()){
            System.out.println(line);
        }
    }

    public void processInput(String input) {
        if(input.length()==1) {
            this.processChar(input.charAt(0));
        } else if(input.length() > 1) {
            this.processStringInput(input);
        }
    }

    public void processStringInput(String input) {
        this.setAlphabetString(input.toUpperCase());
        this.buildAlphabetStack(this.alphabetString);
        processCharForStringInput(input);
    }

    private void processCharForStringInput(String input) {
        getStringInputInUseQueueFromSuppliedString(input);
        ArrayList<String> preparedBody = processDiamond();
        this.setBody(preparedBody);
    }

    private void getStringInputInUseQueueFromSuppliedString(String input) {
        input = input.toUpperCase();
        for(int i=0; i<input.length();i++){
            inUseQueue.add(input.charAt(i));
        }
    }

    private void processChar(char c) {
        getInUseQueueFromSuppliedCharacter(c);
        ArrayList<String> preparedBody = processDiamond();
        this.setBody(preparedBody);
    }

    private ArrayList<String> processDiamond() {
        ArrayList<String> preparedBody = new ArrayList<>();
        int lineLength = this.getInUseQueue().size()-1;
        int placeOfChar = lineLength;
        createTopHalfOfDiamondWithMiddleLine(preparedBody, lineLength, placeOfChar);
        createBottomHalfOfDiamond(preparedBody, lineLength);
        return preparedBody;
    }

    private void createBottomHalfOfDiamond(ArrayList<String>preparedBody, int lastIndexFromTopHalf) {
        int sizeOfBottomHalf = lastIndexFromTopHalf - 1;
        while(sizeOfBottomHalf >= 0) {
            preparedBody.add(preparedBody.size(), preparedBody.get(sizeOfBottomHalf));
            sizeOfBottomHalf--;
        }
    }

    private void createTopHalfOfDiamondWithMiddleLine(ArrayList<String> preparedBody, int lineLength, int placeOfChar) {
        while(this.getInUseQueue().size() > 0){
            preparedBody.add(processLine(lineLength, placeOfChar));
            placeOfChar--;
        }
    }

    private String processLine(int lineLength, int placeOfChar) {
        Character character = this.getInUseQueue().remove(); //D-C-B-A
        String addString = formatLine(character, placeOfChar, lineLength);
        return addString;
    }

    private void getInUseQueueFromSuppliedCharacter(char c) {
        c = Character.toUpperCase(c);
        Character currentElement = this.getAlphabetStack().pop();
        this.getInUseQueue().add(currentElement);
        while (!currentElement.equals(c)){
            currentElement = this.getAlphabetStack().pop();
            this.getInUseQueue().add(currentElement);
        }
    }

    private void getMinimumLengthInUseQueueFromSuppliedCharacter(char c, int length) {
        c = Character.toUpperCase(c);
        Character currentElement = this.getAlphabetStack().pop();
        this.getInUseQueue().add(currentElement);
        while (!currentElement.equals(c)){
            currentElement = this.getAlphabetStack().pop();
            this.getInUseQueue().add(currentElement);
        }
    }

    public void decorate() {
        //TODO later
    }

    public String formatLine(Character input, int position, int lineLength) {
        StringBuilder sb = new StringBuilder();
        processFistHalfOfLine(sb, input, position, lineLength);
        addSecondHalfIfNecessary(sb, position, lineLength);
        return sb.toString();
    }

    private void addSecondHalfIfNecessary(StringBuilder sb, int position, int lineLength) {
        String otherSide = reverseString(sb.toString());
        if (lineLength - position > 0) {
            sb.append(middleChar);
            sb.append(otherSide);
        }
    }

    private void processFistHalfOfLine(StringBuilder sb, Character input, int position, int lineLength) {
        int indexAfterFirstHalfSpaces = getSpacesUpToCharacter(sb, position);
        sb.append(input);
        indexAfterFirstHalfSpaces++;
        getSpacesAfterCharacter(sb, indexAfterFirstHalfSpaces, lineLength);
    }

    private void getSpacesAfterCharacter(StringBuilder sb, int currentIndex, int lineLength) {
        while(currentIndex < lineLength){
            sb.append(" ");
            currentIndex++;
        }
    }

    private int getSpacesUpToCharacter(StringBuilder sb, int position) {
        int index = 0;
        while(index < position){
            sb.append(" ");
            index++;
        }
        return index;
    }

    public String reverseString(String input) {
        StringBuilder reversedSb = new StringBuilder();
        for(int index = input.length()-1; index >= 0; index--){
            reversedSb.append(input.charAt(index));
        }
        return reversedSb.toString();
    }


    public void setAlphabetStack(Stack<Character> alphabetStack) { this.alphabetStack = alphabetStack; }

    public Stack<Character> getAlphabetStack(){ return this.alphabetStack; }

    public Queue<Character> getInUseQueue() { return inUseQueue; }

    private void setInUseQueue(Queue<Character> queue) { this.inUseQueue = queue; }

    public Character getMiddleChar() { return middleChar; }

    public void setMiddleChar(Character middleChar) { this.middleChar = middleChar; }

    public List<String> getBody() { return body; }

    public void setBody(ArrayList<String> body) { this.body = body; }

    private void setAlphabetString(String alphabetString) {
        this.alphabetString = alphabetString;
    }
}
