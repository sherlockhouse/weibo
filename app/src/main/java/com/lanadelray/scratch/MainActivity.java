package com.lanadelray.scratch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lanadelray.scratch.ui.login.LoginActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (needslogin()) {
            //todo logout();
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.setClass(this, LoginActivity.class);
            startActivity(i);
            finish();

        } else {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.setClass(this, null); //todo
            //todo put extras
            startActivity(i);
            finish();

        }

    }

    private boolean needslogin() {
        return true;
        //todo
    }


}
