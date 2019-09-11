package e.wolfsoft1.animated_android_app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.wx.wheelview.widget.WheelViewDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Adapter.Animation_Favourite_Adapter;
import Adapter.Favourite_Adapter;
import Model.Animation_Favourite_Model;
import Model.Favourite_Model_Class;

public class MainActivity extends AppCompatActivity {

    final static int REQ_CODE = 1;


    String newString;
    LinearLayout linear_layout;

    Toolbar toolbar;
    Dialog slideDialog;
    TextView seat_reserved_text;

    LinearLayout toolbar_linear, linear_recyclerview;
    TextView search;


    LinearLayout origin_linear, destination_linear;
    TextView favourite_text;
    View view;

    TextView origin, destination;
    ImageView imageview_interchange;

    private Favourite_Adapter favouriteAdapter;
    private RecyclerView favourite_recyclerview;
    private ArrayList<Favourite_Model_Class> favourite_model_classArrayList;


    private RecyclerView recyclerView;
    private Animation_Favourite_Adapter bingobas7_adapter;
    private ArrayList<Animation_Favourite_Model> bingobas7_models;


    private WheelView mainWheelView, subWheelView, child_wheelview;


    Integer favourite__field_icon[] = {R.drawable.ic_home, R.drawable.ic_work, R.drawable.ic_star, R.drawable.ic_home, R.drawable.ic_work, R.drawable.ic_star};
    String favourite_field_title_text[] = {"Home", "Work", "Favourite", "Home", "Work", "Favourite"};

    String location_text[] = {"Sanfrancisco", "Newyork", "Donaldfurt", "Sanfrancisco", "Newyork", "Donaldfurt"};
    String location_city_text[] = {"Newyork", "Sanfrancisco", "MattieMouth", "Newyork", "Sanfrancisco", "MattieMouth"};

    String type, brand, price;

    TextView text_bus_type, text_bus_brand, text_bus_price;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("origin", origin.getText().toString());
        outState.putString("destination", destination.getText().toString());
        outState.putString("reserveseat", seat_reserved_text.getText().toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        linear_recyclerview = findViewById(R.id.linear_recyclerview);
        origin_linear = findViewById(R.id.origin_linear);
        destination_linear = findViewById(R.id.destination_linear);
        seat_reserved_text = findViewById(R.id.seat_reserved_text);

        linear_layout = findViewById(R.id.linear_layout);

        imageview_interchange = findViewById(R.id.imageview_interchange);


        toolbar_linear = findViewById(R.id.toolbar_linear);
        origin = findViewById(R.id.origin);
        destination = findViewById(R.id.destination);

        imageview_interchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = origin.getText().toString();
                String s2 = destination.getText().toString();
                origin.setText(s2);
                destination.setText(s1);

            }
        });

        if (savedInstanceState != null) {
            origin.setText(savedInstanceState.getString("origin"));
            destination.setText(savedInstanceState.getString("destination"));
            seat_reserved_text.setText(savedInstanceState.getString("reserveseat"));
        }




        text_bus_type = findViewById(R.id.text_bus_type);

        text_bus_brand = findViewById(R.id.text_bus_brand);

        text_bus_price = findViewById(R.id.text_bus_price);
        Intent intent = getIntent();
        type = intent.getStringExtra("bus_type");
        brand = intent.getStringExtra("bus_brand");
        price = intent.getStringExtra("bus_price");

        if (type != null & brand != null & price != null) {

            linear_layout.setVisibility(View.VISIBLE);
            text_bus_type.setText(type);
            text_bus_brand.setText(brand);
            text_bus_price.setText(price);

        } else {
            linear_layout.setVisibility(View.GONE);

        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView = (RecyclerView) findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setNestedScrollingEnabled(false);

        runAnimation(recyclerView);


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                origin.setText("Origin");
                destination.setText("Destination");
                seat_reserved_text.setText("Any Seats Today");
                favourite_recyclerview.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });

        origin_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                origin.setText("South Kyle");
                origin.setTextColor(Color.parseColor("#000000"));

            }
        });
        destination_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText("East Rodrigo");
                destination.setTextColor(Color.parseColor("#000000"));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DestinationActivity.class));
//                linear_layout.setVisibility(View.GONE);
            }
        });
        toolbar_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });


        favourite_recyclerview = findViewById(R.id.favourite_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        favourite_recyclerview.setLayoutManager(layoutManager);
        favourite_recyclerview.setItemAnimator(new DefaultItemAnimator());


        favourite_model_classArrayList = new ArrayList<>();

        for (int i = 0; i < favourite__field_icon.length; i++) {
            Favourite_Model_Class model = new Favourite_Model_Class(favourite__field_icon[i], favourite_field_title_text[i], location_text[i], location_city_text[i]);
            favourite_model_classArrayList.add(model);
        }

        favouriteAdapter = new Favourite_Adapter(MainActivity.this, favourite_model_classArrayList);
        favourite_recyclerview.setAdapter(favouriteAdapter);

        seat_reserved_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                slideDialog = new Dialog(MainActivity.this, R.style.CustomDialogAnimation);
                slideDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // Setting dialogview
                Window window = slideDialog.getWindow();
                window.setGravity(Gravity.BOTTOM);

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                slideDialog.setContentView(R.layout.pop_up_main);


                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                slideDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
                layoutParams.copyFrom(slideDialog.getWindow().getAttributes());

                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.70);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.40);

                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = height;
                layoutParams.gravity = Gravity.BOTTOM;

                slideDialog.getWindow().setAttributes(layoutParams);
                slideDialog.setCancelable(true);
                slideDialog.setCanceledOnTouchOutside(true);

                mainWheelView = slideDialog.findViewById(R.id.main_wheelview);
                mainWheelView.setWheelAdapter(new ArrayWheelAdapter(MainActivity.this));
                mainWheelView.setSkin(WheelView.Skin.Holo);
                mainWheelView.setWheelData(createMainDatas());
                WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
                style.selectedTextSize = 20;
                style.textSize = 13;
                mainWheelView.setStyle(style);

                subWheelView = slideDialog.findViewById(R.id.sub_wheel_view);
                subWheelView.setWheelAdapter(new ArrayWheelAdapter(MainActivity.this));
                subWheelView.setSkin(WheelView.Skin.Holo);
                subWheelView.setWheelData(createSubDatas().get(createMainDatas().get(mainWheelView.getSelection())));
                subWheelView.setStyle(style);

                child_wheelview = slideDialog.findViewById(R.id.child_wheelview);
                child_wheelview.setWheelAdapter(new ArrayWheelAdapter(MainActivity.this));
                child_wheelview.setSkin(WheelView.Skin.Holo);
                child_wheelview.setWheelData(createChildDatas().get(createMainDatas().get(mainWheelView.getSelection())));
                child_wheelview.setStyle(style);


                search = slideDialog.findViewById(R.id.search);


                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String no_of_seat = String.valueOf(mainWheelView.getSelectionItem());
                        String time = String.valueOf(child_wheelview.getSelectionItem());
                        String day = String.valueOf(subWheelView.getSelectionItem());
                        seat_reserved_text.setText(no_of_seat + " " + day + " near " + time);
                        linear_recyclerview.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        slideDialog.dismiss();


                    }
                });

                slideDialog.show();
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_source, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private List<String> createMainDatas() {
        String[] strings = {"1 Seat", "2 Seat", "3 Seat", "4 Seat", "5 Seat", "6 Seat"};
        return Arrays.asList(strings);
    }


    private ArrayList<String> createArrays() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            list.add("item" + i);
        }

        return list;


    }

    public void showDialog(View view) {
        WheelViewDialog dialog = new WheelViewDialog(this);
        dialog.setTitle("wheelview dialog").setItems(createArrays()).setButtonText("Monday").setDialogStyle(Color
                .parseColor("#757575")).setCount(5).show();
    }


    private HashMap<String, List<String>> createSubDatas() {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        String[] strings = {"1 Seat", "2 Seat", "3 Seat", "4 Seat", "5 Seat", "6 Seat"};
        String[] s1 = {"Today", "Tommorow", "Thurs", "Mon", "Tues", "Satur"};
        String[] s2 = {"Today", "Tommorow", "Thurs", "Mon", "Tues", "Satur"};
        String[] s3 = {"Today", "Tommorow", "Thurs", "Mon", "Tues", "Satur"};
        String[] s4 = {"Today", "Tommorow", "Thurs", "Mon", "Tues", "Satur"};
        String[] s5 = {"Today", "Tommorow", "Thurs", "Mon", "Tues", "Satur"};
        String[] s6 = {"Today", "Tommorow", "Thurs", "Mon", "Tues", "Satur"};

        String[][] ss = {s1, s2, s3, s4, s5, s6};
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], Arrays.asList(ss[i]));
        }
        return map;
    }

    private HashMap<String, List<String>> createChildDatas() {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        String[] strings = {"1 Seat", "2 Seat", "3 Seat", "4 Seat", "5 Seat", "6 Seat"};

        String[] s1 = {"07 : 30 AM", "08 : 00 AM", "08 : 30 AM", "08 : 00 AM", "09 : 30 AM", "10 : 00 AM"};
        String[] s2 = {"07 : 30 AM", "08 : 00 AM", "08 : 30 AM", "08 : 00 AM", "09 : 30 AM", "10 : 00 AM"};
        String[] s3 = {"07 : 30 AM", "08 : 00 AM", "08 : 30 AM", "08 : 00 AM", "09 : 30 AM", "10 : 00 AM"};
        String[] s4 = {"07 : 30 AM", "08 : 00 AM", "08 : 30 AM", "08 : 00 AM", "09 : 30 AM", "10 : 00 AM"};
        String[] s5 = {"07 : 30 AM", "08 : 00 AM", "08 : 30 AM", "08 : 00 AM", "09 : 30 AM", "10 : 00 AM"};
        String[] s6 = {"07 : 30 AM", "08 : 00 AM", "08 : 30 AM", "08 : 00 AM", "09 : 30 AM", "10 : 00 AM"};

        String[][] ss = {s1, s2, s3, s4, s5, s6};
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], Arrays.asList(ss[i]));
        }
        return map;
    }

    private void runAnimation(RecyclerView recyclerview) {
        Context context = recyclerview.getContext();
        LayoutAnimationController controller = null;

        bingobas7_models = new ArrayList<>();

        bingobas7_models.add(new Animation_Favourite_Model("Visakhapatnam", "Andhra Pradesh", "7 : 40 AM"));
        bingobas7_models.add(new Animation_Favourite_Model("Bangalore", "Arunachal Pradesh", "8 : 00 AM"));
        bingobas7_models.add(new Animation_Favourite_Model("Ahmedabad", "Gujarat", "8 : 25 AM"));
        bingobas7_models.add(new Animation_Favourite_Model("Pimpri-Chinchwad", "Bihar", "9 : 00 AM"));
        bingobas7_models.add(new Animation_Favourite_Model("Ludhiana", "Chhattisgarh", "12 : 00 AM"));
        bingobas7_models.add(new Animation_Favourite_Model("Kalyan-Dombivli", "Goa", "1 : 30 PM"));
        bingobas7_models.add(new Animation_Favourite_Model("Bangalore", "Arunachal Pradesh", "3 : 00 PM"));

        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_bottom);

        bingobas7_adapter = new Animation_Favourite_Adapter(MainActivity.this, bingobas7_models);
        recyclerview.setAdapter(bingobas7_adapter);

        recyclerview.setLayoutAnimation(controller);
        recyclerview.getAdapter().notifyDataSetChanged();
        recyclerview.scheduleLayoutAnimation();
    }
}
