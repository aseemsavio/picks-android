package com.aseemsavio.picks;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button resetP;
    EditText emailP;
    String emailToSend;
    FirebaseAuth fireAuth;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7332689293305822~4194672992");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        resetP = (Button) findViewById(R.id.reset);
        emailP = (EditText) findViewById(R.id.email);
        fireAuth = FirebaseAuth.getInstance();

        resetP.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                emailToSend = emailP.getText().toString();
                if(TextUtils.isEmpty(emailToSend)){
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your registered email Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                fireAuth.sendPasswordResetEmail(emailToSend).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this, "We've ent you instructions to reset your password to your registered email", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ForgotPasswordActivity.this, "Failed to send Instructions. Please try again!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }
}
