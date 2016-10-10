package com.samuelbostick.jdeck;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> _deck;

    public Deck(){
        _deck = new ArrayList<>();
    }

    public void add(Card c){
        _deck.add(c);
    }

    public void burn(Card c){
        _deck.add(0, c);
    }

    public Card draw(){
        return _deck.remove(size()-1);
    }

    public Card peek(){
        return _deck.get(size()-1);
    }

    public void shuffle(){
        Collections.shuffle(_deck);
    }

    public int size(){
        return _deck.size();
    }
}
