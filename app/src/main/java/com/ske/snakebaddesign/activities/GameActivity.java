package com.ske.snakebaddesign.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ske.snakebaddesign.R;
import com.ske.snakebaddesign.guis.BoardView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private int boardSize;
    private Player p1,p2;
    private Board board;
    private int turn;

    private BoardView boardView;
    private Button buttonTakeTurn;
    private Button buttonRestart;
    private TextView textPlayerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        resetGame();
    }

    private void initComponents() {
        boardView = (BoardView) findViewById(R.id.board_view);
        buttonTakeTurn = (Button) findViewById(R.id.button_take_turn);
        buttonTakeTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeTurn();
            }
        });
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
        textPlayerTurn = (TextView) findViewById(R.id.text_player_turn);

        p1 = new Player();
        p2 = new Player();
        board = Board.getInstance();
    }

    private void resetGame() {
        turn = 0;
        p1.setStart();
        p2.setStart();
        board.newBoard();
        boardSize = 6;
        boardView.setBoardSize(boardSize);
        boardView.setP1Position(p1.getPos());
        boardView.setP2Position(p2.getPos());
        textPlayerTurn.setText("Player 1's Turn");
    }

    private void takeTurn() {
        final int value = 1 + new Random().nextInt(6);
        String title = "You rolled a die";
        String msg = "You got " + value;
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveCurrentPiece(value);
                dialog.dismiss();
            }
        };
        displayDialog(title, msg, listener);
    }

    private void moveCurrentPiece(int value) {
        if (turn % 2 == 0) {
            p1.setPos(adjustPosition(p1.getPos(), value));
            p1.setCurrentLocation(board.getSquare(p1.getPos()));
            boardView.setP1Position(p1.getPos());
            textPlayerTurn.setText("Player 2's Turn");
        } else {
            p2.setPos(adjustPosition(p2.getPos(), value));
            p2.setCurrentLocation(board.getSquare(p2.getPos()));
            boardView.setP2Position(p2.getPos());
            textPlayerTurn.setText("Player 1's Turn");
        }
        checkWin();
        turn++;
    }

    private int adjustPosition(int current, int distance) {
        current = current + distance;
        int maxSquare = boardSize * boardSize - 1;
        if(current > maxSquare) {
            current = maxSquare - (current - maxSquare);
        }
        return current;
    }

    private void checkWin() {
        String title = "Game Over";
        String msg = "";
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
                dialog.dismiss();
            }
        };
        if (p1.getPos() == boardSize * boardSize - 1) {
            msg = "Player 1 won!";
        } else if (p2.getPos() == boardSize * boardSize - 1) {
            msg = "Player 2 won!";
        } else {
            return;
        }
        displayDialog(title, msg, listener);
    }

    private void displayDialog(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", listener);
        alertDialog.show();
    }

}
