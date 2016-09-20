package com.example.joan.jsirma_memorygame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joan on 9/19/16.
 */
public class GamePlay extends AppCompatActivity {

    GameBoard cur;
    Boolean clicakable = true;
    private List<Drawable> images;

    Button button1, button2,button3,button4, button5,button6,button7,button8,button9, button10,
        button11, button12,button13, button14,button15,button16,button17,button18,button19,button20;

    private static final int [] ButtonNames= {
            R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,
            R.id.button6 ,R.id.button7 ,R.id.button8 ,R.id.button9 ,R.id.button10,
            R.id.button11,R.id.button12,R.id.button13,R.id.button14,R.id.button15,
            R.id.button16,R.id.button17,R.id.button18,R.id.button19,R.id.button20
    };

    int NumOfBottonSelected =0;
    Button selectedButton;
    String selected = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            GameBoard model = (GameBoard) getIntent().getSerializableExtra("cur");
            cur=model;
        }
        else{
            cur= new GameBoard();
        }

        loadBoard();
        if(cur.players.size()>0){
            markImageComplete();
        }
    }
    @Override
    /***********!!!!!!!!!!!*******/
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.button1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadBoard(){
        //Row 1
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        //Row 2
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);

        //Row 3
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);

        //Row 4
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);

        //Row 5
        button17 = (Button) findViewById(R.id.button17);
        button18 = (Button) findViewById(R.id.button18);
        button19 = (Button) findViewById(R.id.button19);
        button20 = (Button) findViewById(R.id.button20);

        for (int id: ButtonNames){
            ((Button) findViewById(id)).setTextSize(0);
            game(((Button)findViewById(id)));
        }
        populateImages();

    }


    public void markImageComplete(){
        for(String players: cur.getplayers()){
            if(players.length()>0){
                for(int id: ButtonNames){
                    if(((Button) findViewById(id)).getText().equals(players)){
                        ((Button) findViewById(id)).setEnabled(false);
                    }
                }
            }
        }

    }
    public void game(final Button button)
    {

        button.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) return true;
                if (event.getAction() == MotionEvent.ACTION_UP) return false;
                if (clicakable) {
                    if (NumOfBottonSelected == 0) {
                        selectedButton = button;
                        button.setPressed(true);
                        NumOfBottonSelected = 1;
                        selected = (String) button.getText();
                    } else if (NumOfBottonSelected == 1 && selected.equals((String) button.getText()) && button.getId() != selectedButton.getId()) {

                        cur.addPlayersClicked(selected);

                        button.setEnabled(false);
                        selectedButton.setEnabled(false);
                        NumOfBottonSelected = 0;
                        selected = "";
                        if (cur.equals(true)) {
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int choose) {

                                    Intent mainIntent = new Intent(GamePlay.this, MainActivity.class);
                                    switch (choose) {

                                        case DialogInterface.BUTTON_POSITIVE:
                                            mainIntent.putExtra("currentBoard", cur);
                                            startActivityForResult(mainIntent, 111);
                                            break;

                                        case DialogInterface.BUTTON_NEGATIVE:
                                            mainIntent.putExtra("currentBoard", new GameBoard());
                                            startActivityForResult(mainIntent, 111);
                                            break;
                                    }
                                }
                            };
                            AlertDialog.Builder builder = new AlertDialog.Builder(GamePlay.this);
                            builder.setMessage("Winner!!! Return?").setPositiveButton("Yes", dialogClickListener)
                                    .setNegativeButton("No", dialogClickListener).show();
                        }
                    } else if (NumOfBottonSelected == 1 && button.getId() == selectedButton.getId()) {

                    } else {
                        NumOfBottonSelected = 0;
                        selected = "";

                        button.setPressed(true);
                        selectedButton.setPressed(true);
                        clicakable = false;

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                button.setPressed(false);
                                selectedButton.setPressed(false);
                                clicakable = true;
                            }
                        }, 200);
                    }


                    //return true;
                }
                return true;
            }
    });

        }

    public void previewPokemon(View view) {

        for(int id :ButtonNames) {
            ((Button) findViewById(id)).setPressed(true);

        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int id : ButtonNames) {
                    ((Button) findViewById(id)).setPressed(false);
                }
            }
        }, 2000);

    }

    public void populateImages(){
        for(int i =0; i < cur.players.size(); i++){
            setNum(i + 1, cur.players.get(i));
        }


    }
    public void setNum(int i, String Players){
        switch(i){
            case 1:
                populateAll(button1,Players);
                break;
            case 2:
                populateAll(button2,Players);
                break;
            case 3:
                populateAll(button3,Players);
                break;
            case 4:
                populateAll(button4,Players);
                break;
            case 5:
                populateAll(button5,Players);
                break;
            case 6:
                populateAll(button6,Players);
                break;
            case 7:
                populateAll(button7,Players);
                break;
            case 8:
                populateAll(button8,Players);
                break;
            case 9:
                populateAll(button9,Players);
                break;
            case 10:
                populateAll(button10,Players);
                break;
            case 11:
                populateAll(button11,Players);
                break;
            case 12:
                populateAll(button12,Players);
                break;
            case 13:
                populateAll(button13,Players);
                break;
            case 14:
                populateAll(button14,Players);
                break;
            case 15:
                populateAll(button15,Players);
                break;
            case 16:
                populateAll(button16,Players);
                break;
            case 17:
                populateAll(button17,Players);
                break;
            case 18:
                populateAll(button18,Players);
                break;
            case 19:
                populateAll(button19,Players);
                break;
            case 20:
                populateAll(button20,Players);
                break;
            default:
                break;
        }

    }
    public void populateAll(Button b, String s)
    {

        if(s.equals("player1")){
             b.setBackgroundResource(R.drawable.card1);
            b.setText(s);
        }
        if(s.equals("player2")){
            b.setBackgroundResource(R.drawable.card2);
            b.setText(s);
        }
        if(s.equals("player3")){
            b.setBackgroundResource(R.drawable.card3);
            b.setText(s);
        }
        if(s.equals("player4")){
            b.setBackgroundResource(R.drawable.card4);
            b.setText(s);
        }
        if(s.equals("player5")){
            b.setBackgroundResource(R.drawable.card5);
            b.setText(s);
        }
        if(s.equals("player5")){
            b.setBackgroundResource(R.drawable.card6);
            b.setText(s);
        }
        if(s.equals("player7")){
            b.setBackgroundResource(R.drawable.card7);
            b.setText(s);
        }
        if(s.equals("player8")){
            b.setBackgroundResource(R.drawable.card8);
            b.setText(s);
        }
        if(s.equals("player9")){
            b.setBackgroundResource(R.drawable.card9);
            b.setText(s);
        }
        if(s.equals("player10")){
            b.setBackgroundResource(R.drawable.card10);
            b.setText(s);
        }


    }
    @Override
    public void onBackPressed() {

        Intent intent=new Intent();
        intent.putExtra("currentBoard", cur);
        setResult(2,intent);

        super.onBackPressed();
    }
}
