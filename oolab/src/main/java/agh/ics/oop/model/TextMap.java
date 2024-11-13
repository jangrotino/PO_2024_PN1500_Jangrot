package agh.ics.oop.model;

import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class TextMap implements WorldNumberPositionMap<String, Integer> {
    private final List<String> text = new ArrayList<>();

    @Override
    public boolean place(String word) {
        return text.add(word);
    }

    @Override
    public boolean canMoveTo(Integer pos) {
        return pos >= 0 && pos < text.size();
    }

    @Override
    public void move(String word, MoveDirection direction) {
        Integer curPos = text.indexOf(word);
        Integer nextPositon = switch (direction) {
            case LEFT, BACKWARD -> curPos - 1;
            case RIGHT, FORWARD -> curPos + 1;
        };

        if(canMoveTo(nextPositon)) {
            String toChangeString = text.get(nextPositon);
            text.set(curPos, toChangeString);
            text.set(nextPositon, word);
        }
    }

    @Override
    public boolean isOccupied(Integer pos) {
        return canMoveTo(pos);
    }

    @Override
    public String objectAt(Integer pos){
        if(!canMoveTo(pos)){
            return null;
        }
        return text.get(pos);
    }

    @Override
    public String toString(){
        return text.toString();
    }
}
