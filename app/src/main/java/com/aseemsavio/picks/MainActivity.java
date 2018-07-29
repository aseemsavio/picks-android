package com.aseemsavio.picks;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {


    //public static int SPLASH_SCREEN_TIME_OUT = 4000;

    Animation animation1,animation2, animation3, animation4, animation5;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        final Button mOne = (Button) findViewById(R.id.one);
        final Button mTwo = (Button) findViewById(R.id.two);
        final Button mThree = (Button) findViewById(R.id.three);
        final TextView textView = (TextView) findViewById(R.id.text1);
        final ImageView imageView = (ImageView) findViewById(R.id.img);

        animation1 = AnimationUtils.loadAnimation(this, R.anim.view_one);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.view_two);
        animation3 = AnimationUtils.loadAnimation(this, R.anim.view_three);
        animation4 = AnimationUtils.loadAnimation(this, R.anim.view_text);
        animation5 = AnimationUtils.loadAnimation(this, R.anim.image_scale);

        mOne.startAnimation(animation1);
        mTwo.startAnimation(animation2);
        mThree.startAnimation(animation3);
        textView.startAnimation(animation4);
        imageView.startAnimation(animation5);


        imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {



/*

                  SharedPreferences.Editor editor = getSharedPreferences("MY_PREF", MODE_PRIVATE).edit();
                            editor.putString("email", "assss");
                            editor.commit();
                Intent i = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(i);

*/

                if(mAuth.getCurrentUser()!=null) {
                    Intent homeScreen = new Intent(MainActivity.this, FirstActivity.class);
                    startActivity(homeScreen);
                }
                else{
                    Intent i = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(i);
                }


            }
        });


    /*    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                Intent homeScreen = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(homeScreen);
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }

    */
    }
}