package com.example.aya.juclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Selectdept extends AppCompatActivity {

    private ImageButton dent;
    private ImageButton gene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdept);
        dent=(ImageButton) findViewById(R.id.ibden);
        gene=(ImageButton) findViewById(R.id.ibGent);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       getSupportActionBar().setTitle("Select Department");


        dent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Selectdept.this,TurnForDent.class));
            }
        });
        gene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Selectdept.this,TurnForGene.class));
            }
        });
    }
}
