package com.example.hp.connect;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    Boolean gameIsactive=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int [][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
public void dropIn(View view) {
    ImageView counter = (ImageView) view;
    int tappedcounter = Integer.parseInt(counter.getTag().toString());
    if (gamestate[tappedcounter] == 2 && gameIsactive) {
        gamestate[tappedcounter] = activePlayer;
        counter.setTranslationY(-1000f);
        if (activePlayer == 0) {

            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }
        counter.animate().translationYBy(1000f).rotation(360f).getDuration();
        for (int[] winningposition:winningpositions){
            if (gamestate[winningposition[0]]==gamestate[winningposition[1]]&&
                    gamestate[winningposition[1]]==gamestate[winningposition[2]]
                    && gamestate[winningposition[0]] !=2) {
                String winner = "الاحمر";
                gameIsactive = false;
                if (gamestate[winningposition[0]] == 0) {
                    winner = "الاصفر";
                }
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
                linearLayout.bringToFront();
                TextView textwin = (TextView) findViewById(R.id.textmessage);
                textwin.setText(winner + "فاز !");
                linearLayout.setVisibility(View.VISIBLE);
            }else{
                    Boolean gameIsover=true;
                    for (int counterstate:gamestate){
                        if (counterstate==2) gameIsover=false;
                    }

                    if (gameIsover){
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
                        linearLayout.bringToFront();
                        TextView textwin = (TextView) findViewById(R.id.textmessage);
                        textwin.setText( "تعادل !");
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    }
    public void playAgain(View view){
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.LinearLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        gameIsactive=true;
         activePlayer=0;
        for (int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ( (ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
