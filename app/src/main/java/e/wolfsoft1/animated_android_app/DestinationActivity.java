package e.wolfsoft1.animated_android_app;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DestinationActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout destinationFrameLayout;

    TextView bus_type_ac_text, bus_type_sleeper_text, bus_type_multi_axle_text, bus_type_normal_text;
    TextView bus_brand_volvo_text, bus_brand_mercedes_text, bus_brand_scania_text;
    TextView price_low_text, price_medium_text, price_royal_text;

    ImageView done_selection;


    private String bus_type_selected;
    private String bus_brand_selected;
    private String bus_price_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);


        done_selection = findViewById(R.id.done_selection);

        bus_type_ac_text = findViewById(R.id.bus_type_ac_text);
        bus_type_sleeper_text = findViewById(R.id.bus_type_sleeper_text);
        bus_type_multi_axle_text = findViewById(R.id.bus_type_multi_axle_text);
        bus_type_normal_text = findViewById(R.id.bus_type_normal_text);

        bus_brand_volvo_text = findViewById(R.id.bus_brand_volvo_text);
        bus_brand_mercedes_text = findViewById(R.id.bus_brand_mercedes_text);
        bus_brand_scania_text = findViewById(R.id.bus_brand_scania_text);

        bus_type_ac_text.setOnClickListener(this);
        bus_type_sleeper_text.setOnClickListener(this);
        bus_type_multi_axle_text.setOnClickListener(this);
        bus_type_normal_text.setOnClickListener(this);


        bus_brand_volvo_text.setOnClickListener(this);
        bus_brand_mercedes_text.setOnClickListener(this);
        bus_brand_scania_text.setOnClickListener(this);



        price_low_text = findViewById(R.id.price_low_text);
        price_medium_text = findViewById(R.id.price_medium_text);
        price_royal_text = findViewById(R.id.price_royal_text);


        price_low_text.setOnClickListener(this);
        price_medium_text.setOnClickListener(this);
        price_royal_text.setOnClickListener(this);

        done_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(DestinationActivity.this,MainActivity.class);
               intent.putExtra("bus_type",bus_type_selected);
               intent.putExtra("bus_brand",bus_brand_selected);
               intent.putExtra("bus_price",bus_price_selected);
               startActivity(intent);
               finish();
            }
        });



        destinationFrameLayout = (RelativeLayout) findViewById(R.id.destination_layout);

        ////////////////////////////////////////////////////////////mation//
        ///////////////////////////////////////////////////////////////////
        //        //We call the Transparent layer inorder to show a smooth ani/////////


        if (savedInstanceState == null) {
            destinationFrameLayout.setVisibility(View.INVISIBLE);
            ViewTreeObserver viewTreeObserver = destinationFrameLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealTransition(); //
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            destinationFrameLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            destinationFrameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    private void circularRevealTransition() {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Set the 'X' and 'Y' values for your requirement, Here it is set for the fab being as the source of the circle reveal //
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int X = 9 * destinationFrameLayout.getWidth() / 10;
        int Y = 9 * destinationFrameLayout.getHeight() / 10;
        int Duration = 1500;

        float finalRadius = Math.max(destinationFrameLayout.getWidth(), destinationFrameLayout.getHeight()); //The final radius must be the end points of the current activity

        // create the animator for this view, with the start radius as zero
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(destinationFrameLayout, X, Y, 0, finalRadius);
        circularReveal.setDuration(Duration);

        // set the view visible and start the animation
        destinationFrameLayout.setVisibility(View.VISIBLE);
        // start the animation
        circularReveal.start();
    }


//    TODO Auto-generated method stub
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bus_type_ac_text:

                bus_type_selected=bus_type_ac_text.getText().toString();

                bus_type_ac_text.setTextColor(Color.parseColor("#ffffff"));
                bus_type_sleeper_text.setTextColor(Color.parseColor("#000000"));
                bus_type_multi_axle_text.setTextColor(Color.parseColor("#000000"));
                bus_type_normal_text.setTextColor(Color.parseColor("#000000"));

                bus_type_ac_text.setBackgroundResource(R.drawable.dodgerblue_rect_with_radius);
                bus_type_sleeper_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_multi_axle_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_normal_text.setBackgroundResource(R.drawable.white_rect_with_radius);

                break;

            case R.id.bus_type_sleeper_text:

                bus_type_selected=bus_type_sleeper_text.getText().toString();

                bus_type_ac_text.setTextColor(Color.parseColor("#000000"));
                bus_type_sleeper_text.setTextColor(Color.parseColor("#ffffff"));
                bus_type_multi_axle_text.setTextColor(Color.parseColor("#000000"));
                bus_type_normal_text.setTextColor(Color.parseColor("#000000"));


                bus_type_ac_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_sleeper_text.setBackgroundResource(R.drawable.dodgerblue_rect_with_radius);
                bus_type_multi_axle_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_normal_text.setBackgroundResource(R.drawable.white_rect_with_radius);

                break;


            case R.id.bus_type_multi_axle_text:
                bus_type_selected=bus_type_multi_axle_text.getText().toString();

                bus_type_ac_text.setTextColor(Color.parseColor("#000000"));
                bus_type_sleeper_text.setTextColor(Color.parseColor("#000000"));
                bus_type_multi_axle_text.setTextColor(Color.parseColor("#ffffff"));
                bus_type_normal_text.setTextColor(Color.parseColor("#000000"));


                bus_type_ac_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_sleeper_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_multi_axle_text.setBackgroundResource(R.drawable.dodgerblue_rect_with_radius);
                bus_type_normal_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                break;


            case R.id.bus_type_normal_text:

                bus_type_selected=bus_type_normal_text.getText().toString();


                bus_type_ac_text.setTextColor(Color.parseColor("#000000"));
                bus_type_sleeper_text.setTextColor(Color.parseColor("#000000"));
                bus_type_multi_axle_text.setTextColor(Color.parseColor("#000000"));
                bus_type_normal_text.setTextColor(Color.parseColor("#ffffff"));


                bus_type_ac_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_sleeper_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_multi_axle_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_type_normal_text.setBackgroundResource(R.drawable.dodgerblue_rect_with_radius);

                break;

            case R.id.bus_brand_volvo_text:

                bus_brand_selected=bus_brand_volvo_text.getText().toString();

                bus_brand_volvo_text.setTextColor(Color.parseColor("#ffffff"));
                bus_brand_mercedes_text.setTextColor(Color.parseColor("#000000"));
                bus_brand_scania_text.setTextColor(Color.parseColor("#000000"));


                bus_brand_volvo_text.setBackgroundResource(R.drawable.green_rect_with_radius);
                bus_brand_mercedes_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_brand_scania_text.setBackgroundResource(R.drawable.white_rect_with_radius);

                break;


            case R.id.bus_brand_mercedes_text:

                bus_brand_selected=bus_brand_mercedes_text.getText().toString();

                bus_brand_volvo_text.setTextColor(Color.parseColor("#000000"));
                bus_brand_mercedes_text.setTextColor(Color.parseColor("#ffffff"));
                bus_brand_scania_text.setTextColor(Color.parseColor("#000000"));


                bus_brand_volvo_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_brand_mercedes_text.setBackgroundResource(R.drawable.green_rect_with_radius);
                bus_brand_scania_text.setBackgroundResource(R.drawable.white_rect_with_radius);

                break;

            case R.id.bus_brand_scania_text:


                bus_brand_selected=bus_brand_scania_text.getText().toString();

                bus_brand_volvo_text.setTextColor(Color.parseColor("#000000"));
                bus_brand_mercedes_text.setTextColor(Color.parseColor("#000000"));
                bus_brand_scania_text.setTextColor(Color.parseColor("#ffffff"));


                bus_brand_volvo_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_brand_mercedes_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                bus_brand_scania_text.setBackgroundResource(R.drawable.green_rect_with_radius);

                break;

            case R.id.price_low_text:

                bus_price_selected=price_low_text.getText().toString();

                price_low_text.setTextColor(Color.parseColor("#ffffff"));
                price_medium_text.setTextColor(Color.parseColor("#000000"));
                price_royal_text.setTextColor(Color.parseColor("#000000"));


                price_low_text.setBackgroundResource(R.drawable.mustered_rect_with_radius);
                price_medium_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                price_royal_text.setBackgroundResource(R.drawable.white_rect_with_radius);

                break;

            case R.id.price_medium_text:

                bus_price_selected=price_medium_text.getText().toString();

                price_low_text.setTextColor(Color.parseColor("#000000"));
                price_medium_text.setTextColor(Color.parseColor("#ffffff"));
                price_royal_text.setTextColor(Color.parseColor("#000000"));


                price_low_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                price_medium_text.setBackgroundResource(R.drawable.mustered_rect_with_radius);
                price_royal_text.setBackgroundResource(R.drawable.white_rect_with_radius);

                break;

            case R.id.price_royal_text:

                bus_price_selected=price_royal_text.getText().toString();

                price_low_text.setTextColor(Color.parseColor("#000000"));
                price_medium_text.setTextColor(Color.parseColor("#000000"));
                price_royal_text.setTextColor(Color.parseColor("#ffffff"));


                price_low_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                price_medium_text.setBackgroundResource(R.drawable.white_rect_with_radius);
                price_royal_text.setBackgroundResource(R.drawable.mustered_rect_with_radius);

                break;


        }
    }


}
