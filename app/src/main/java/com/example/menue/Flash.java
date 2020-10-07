package com.example.menue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Flash extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    //valuables
    Animation topanim, bottom;
    ImageView image;
    TextView title, abt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_flash);

//animations
        topanim = AnimationUtils.loadAnimation(this,R.anim.anim);
        bottom = AnimationUtils.loadAnimation(this,R.anim.anim);

        //hooks
        image = findViewById(R.id.circleImageView);
        title = findViewById(R.id.textView7);
        abt = findViewById(R.id.textView8);

        image.setAnimation(topanim);
        title.setAnimation(bottom);
        abt.setAnimation(bottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Flash.this,HomeActivity.class);
                Flash.this.startActivity(intent);
                Flash.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
