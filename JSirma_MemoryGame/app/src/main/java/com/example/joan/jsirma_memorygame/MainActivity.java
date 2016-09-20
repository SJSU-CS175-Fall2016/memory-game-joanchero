package com.example.joan.jsirma_memorygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    GameBoard cur;
    boolean run = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cur =new GameBoard();
    }

    public void onStart(View v) {
        if (run) {
            Intent mainIntent = new Intent(MainActivity.this, GamePlay.class);

            mainIntent.putExtra("CurrentBoard", cur);
            startActivityForResult(mainIntent,1);
        } else {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int choose) {

                    Intent mainIntent = new Intent(MainActivity.this, GamePlay.class);
                    switch (choose) {

                        case DialogInterface.BUTTON_POSITIVE:
                            mainIntent.putExtra("currentBoard", cur);
                            startActivityForResult(mainIntent, 1);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            mainIntent.putExtra("currentBoard", new GameBoard());
                            startActivityForResult(mainIntent, 1);
                            break;
                    }

                }
            };
        }
    }
}
