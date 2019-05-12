package com.prabathweerapana.back_ani;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class RotaryDialerActivity extends Theme1 {

    private EditText digits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RotaryDialerView rotaryDialerView = findViewById(R.id.rotary_dialer);
        digits = findViewById(R.id.digits);

        rotaryDialerView.addDialListener(new RotaryDialerView.DialListener() {
            @Override
            public void onDial(int number) {
                digits.append(String.valueOf(number));
            }

        });
        ImageButton backspace = findViewById(R.id.backspace);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (digits.getText().toString().length()>0)
                {
                    digits.getText().delete(digits.getText().length()-1,digits.getText().length());
                }

            }
        });

        backspace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (digits.getText().toString().length() >0)
                {
                    digits.getText().clear();
                    return true;
                }
                return false;
            }
        });

        digits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode==KeyEvent.KEYCODE_CALL)
        {
            makeCall();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void makeCall()
    {
        if (digits.getText().toString().length()>0)
        {
            String toDial = "tel:" +digits.getText().toString();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(toDial)));
        }
    }
}


