package com.jeepc.binderdemo.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startCommon(View view) {
        startActivity(CommonActivity.class);
    }

    public void startNonuseAIDL(View view) {
        startActivity(NonuseAidlActivity.class);
    }

    public void startNonuseService(View view) {
        startActivity(NonuseServiceActivity.class);
    }

    private void startActivity(Class cls){
        this.startActivity(new Intent(this,cls));
    }
}
