package com.samuelbostick.fitdeck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.samuelbostick.jdeck.Card;
import com.samuelbostick.jdeck.Deck;

public class WorkoutFragment extends Fragment {
    Deck _deck;
    View _view;

    public static WorkoutFragment newInstance(String[] suites){
        WorkoutFragment fragment = new WorkoutFragment();

        Bundle args = new Bundle();
        args.putStringArray("suites", suites);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout, container, false);

        String[] suites = getArguments().getStringArray("suites");
        _deck = new Deck();

        if(suites != null){
            for(String suite : suites){
                for(int i = 2; i < 15; i++){
                    _deck.add(new Card(suite, i));
                }
            }
        }

        _deck.shuffle();

        ProgressBar progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        progressBar.setMax(_deck.size());

        Button nextButton = (Button)v.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new OnNextClickListener(progressBar));

        _view = v;

        nextCard();
        return v;
    }

    public void nextCard(){
        if(_deck.size() <= 0) return;

        Card c = _deck.draw();

        TextView exerciseText = (TextView)_view.findViewById(R.id.exercise_text);
        String exerciseString = "Exercise: " + c.getSuite();
        exerciseText.setText(exerciseString);

        TextView repsText = (TextView)_view.findViewById(R.id.reps_text);
        String repsString = "Reps: " + c.getValue();
        repsText.setText(repsString);
    }

    public class OnNextClickListener implements View.OnClickListener{
        ProgressBar _progressBar;
        double _prog;

        OnNextClickListener(ProgressBar progressBar){
            _progressBar = progressBar;
            _prog = 0.0;
        }

        public void onClick(View v){
            _progressBar.incrementProgressBy(1);

            if(_prog < _progressBar.getMax()){
                TextView progressText = (TextView)_view.findViewById(R.id.progress_text);
                String progressString = (int)(100*(++_prog/_progressBar.getMax())) + "%";
                progressText.setText(progressString);

                nextCard();
            }
        }
    }
}
