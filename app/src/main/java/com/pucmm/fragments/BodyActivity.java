package com.pucmm.fragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.pucmm.fragments.Classes.Concept;
import com.pucmm.fragments.Classes.PlaceholderContent;

public class BodyActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mBody;
    private TextView mfecha;
    private CheckBox mSupported;
    private Button mlink;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body_fragment);

        mTitle = findViewById(R.id.txt_title);
        mBody = findViewById(R.id.txt_body);
        mfecha = findViewById(R.id.releasedate);
        mSupported = findViewById(R.id.checkBox);
        mlink = findViewById(R.id.button);

        Bundle extras = getIntent().getExtras();


        mTitle.setText(PlaceholderContent.ITEMS.get(extras.getInt("position")).getName());
        mBody.setText(PlaceholderContent.ITEMS.get(extras.getInt("position")).getDetails());
        mfecha.setText(PlaceholderContent.ITEMS.get(extras.getInt("position")).getReleaseDate().toString());
        mSupported.setChecked(PlaceholderContent.ITEMS.get(extras.getInt("position")).isSupported());

        mlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = PlaceholderContent.ITEMS.get(extras.getInt("position")).getLink();

                Intent openBrowser = new Intent(Intent.ACTION_VIEW);
                openBrowser.setData(Uri.parse(url));
                startActivity(openBrowser);
            }
        });


    }
}