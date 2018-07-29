package com.aseemsavio.picks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {


    EditText emailLoginET, pwordLoginET;
    Button signInButton, alreadyNOTaMemberButton, forgotPasswordButon;
    ProgressBar progressBar;
    FirebaseAuth authentication;
    public static final String name_file = "PREF_USER_DATA";
    String email, password, logedInStatus;
    Animation animation;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7332689293305822~4194672992");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Log.d("Aseem Savio", "ad loaded");

        emailLoginET = (EditText) findViewById(R.id.email);
        pwordLoginET = (EditText) findViewById(R.id.pwd);
        signInButton = (Button) findViewById(R.id.login_btn);
        alreadyNOTaMemberButton = (Button) findViewById(R.id.sign_up_call_btn);
        forgotPasswordButon = (Button) findViewById(R.id.forgot_pwd_btn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        animation = AnimationUtils.loadAnimation(this, R.anim.btn_scale);
        alreadyNOTaMemberButton.startAnimation(animation);

        authentication = FirebaseAuth.getInstance();

        alreadyNOTaMemberButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        forgotPasswordButon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
                startActivity(i);

            }
        });

        signInButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                email = emailLoginET.getText().toString();
                password = pwordLoginET.getText().toString();



                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter your EMAIL ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter your PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(LogInActivity.this, "Sorry, Could't sign In!", Toast.LENGTH_LONG).show();

                        } else {


                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String ass = currentUser.getUid();

                            SharedPreferences.Editor editor = getSharedPreferences("MY_PREF", MODE_PRIVATE).edit();
                            editor.putString("email", ass);
                            editor.commit();


                            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                            Toast.makeText(LogInActivity.this, "You are signed in!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });






    }
}
