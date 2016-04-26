package com.example.coursera.modernartui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private SeekBar mSeekBr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView4 = (TextView) findViewById(R.id.textView4);
        mTextView5 = (TextView) findViewById(R.id.textView5);
        mSeekBr = (SeekBar) findViewById(R.id.seekBar);

        mSeekBr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateUI(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_moreinfo) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_title);
            builder.setMessage(R.string.dialog_body);
            builder.setPositiveButton(R.string.pos_btn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                    startActivity(intent);

                }
            });
            builder.setNegativeButton(R.string.neg_btn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //do nothing

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void changeColor(int progress, int color, TextView tv) {
        int blueComp = Color.blue(color);
        int greenComp = Color.green(color);
        int redComp = Color.red(color);

        blueComp = Math.max(Math.min(blueComp + (int)(progress*1.5),255),0);
        greenComp = Math.max(Math.min(greenComp + (int)(progress*1.3),255),0);
        redComp = Math.max(Math.min(redComp + (int)(progress*0.9),255),0);
        int newColor = Color.rgb(redComp,greenComp,blueComp);
        tv.setBackgroundColor(newColor);
    }

    private void updateUI(int progress) {
        changeColor(progress, getResources().getColor(R.color.green), mTextView1);
        changeColor(progress, getResources().getColor(R.color.pink), mTextView3);
        changeColor(progress, getResources().getColor(R.color.blue), mTextView4);
        changeColor(progress, getResources().getColor(R.color.orange), mTextView5);
    }
}
