package mychati.app.Client.ClientBottomInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import mychati.app.R;

public class ClientInfoFromZakaz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info_from_zakaz);


        Intent kfh=getIntent();

        Log.d("bkgkhkb",kfh.getStringExtra("TovarValue"));
    }
}