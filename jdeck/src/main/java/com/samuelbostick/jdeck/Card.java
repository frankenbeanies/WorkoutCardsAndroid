package com.samuelbostick.jdeck;

/**
 * Created by samuel on 10/9/16.
 */

public class Card {
    private String _suite;
    private int _value;

    public Card(String suite, int value){
        _suite = suite;
        _value = value;
    }

    public int compare(Card c){
        return _value - c.getValue();
    }

    public String getSuite(){
        return _suite;
    }

    public int getValue(){
        return _value;
    }
}
