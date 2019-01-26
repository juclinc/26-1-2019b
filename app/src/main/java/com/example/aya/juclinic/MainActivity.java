package com.example.aya.juclinic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText password;
    private Button Login;
    private int count=3;
    private FirebaseAuth firebase;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=(EditText) findViewById(R.id.etName);
        password=(EditText) findViewById(R.id.etPassword);
        Login=(Button) findViewById(R.id.bLogin);
        firebase  =FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        FirebaseUser user=firebase.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this,Selectdept.class));

        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), password.getText().toString());
            }
        });

    }

    private void validate(String username,String userpassword) {
        progressDialog.setMessage("Just a second");
        progressDialog.show();

            firebase.signInWithEmailAndPassword(username,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Selectdept.class));
                    }else{
                        Toast.makeText(MainActivity.this,"Login Failed!!",Toast.LENGTH_SHORT).show();
                        count--;
                        progressDialog.dismiss();
                        if(count==0)
                        {
                            Login.setEnabled(false);
                        }
                    }
                }
            });
    }
}
