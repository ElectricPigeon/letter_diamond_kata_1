package com.katas;


import java.util.*;

public class Diamond implements IDiamond {

    private static final Character BASIC_MIDDLE_CHAR = ' ';
    private Character middleChar; //for style

    private List<String> body;
    private Stack<Character>alphabetStack;
    private Queue<Character> inUseQueue;
    public String alphabetString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Diamond(){
        this.setBody(new ArrayList<>());
        Stack<Character>alphaStack = new Stack<>();
        this.setAlphabetString(reverse(alphabetString));
        for(int index=0; index < alphabetString.length(); index++){
            alphaStack.push(alphabetString.charAt(index));
        }
        this.setAlphabetStack(alphaStack);
        this.setInUseQueue(new PriorityQueue<Character>());
        this.setMiddleChar(BASIC_MIDDLE_CHAR);
    }

    public String get(int index){ return body.get(index); }

    public int getSize() { return body.size(); }

    @Override
    public String[] drawDiamond() {
        for(String line : this.getBody()){
            System.out.println(line);
        }
        return new String[0];
    }

    public void processInput(String input) {
        if(input.length()==1) {
            this.processChar(input.charAt(0));
        } else if(input.length() > 1) {
            this.processStringInput(input);
        }
    }

    private static Diamond processStringInput(String input) {
        return null; //TODO later
    }

    private void processChar(char c) {
        int size = alphabetString.indexOf(c) + 1;
        Character character = c;
        Character currentElement = this.getAlphabetStack().pop();
        this.getInUseQueue().add(currentElement);
        while (!currentElement.equals(character)){ //not thread safe? not an issue
            currentElement = this.getAlphabetStack().pop();
            this.getInUseQueue().add(currentElement);
        }
        int lineLength = this.getInUseQueue().size()-1;
        int placeOfChar = lineLength;
        //only line length is off - i think
        ArrayList<String> preparedBody = new ArrayList<>();
        while(this.getInUseQueue().size() > 0){
            Character processCharacter = this.getInUseQueue().remove(); //D-C-B-A
            String addString = processLine(processCharacter, placeOfChar, lineLength);
            preparedBody.add(addString);
            placeOfChar--;
        }
        this.setBody(preparedBody);
    }

    public void decorate() {
        //TODO later
    }

    public String processLine(Character input, int position, int lineLength) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while(index < position){
            sb.append(" ");
            index++;
        }
        sb.append(input);
        index++;
        while(index < lineLength){
            sb.append(" ");
            index++;
        }
        String otherSide = reverse(sb.toString());
        if (lineLength - position > 0) {
            sb.append(middleChar);
            sb.append(otherSide);
        }
        return sb.toString();
    }

    public String reverse(String input) {
        StringBuilder reversedSb = new StringBuilder();
        for(int index = input.length()-1; index >= 0; index--){
            reversedSb.append(input.charAt(index));
        }
        return reversedSb.toString();
    }

    public String createFullLine() {
        return null;
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
