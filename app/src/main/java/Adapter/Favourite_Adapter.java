package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Favourite_Model_Class;
import e.wolfsoft1.animated_android_app.R;

public class Favourite_Adapter extends RecyclerView.Adapter<Favourite_Adapter.MyViewHolder> {

    private Context context;
    private ArrayList<Favourite_Model_Class>favourite_model_classArrayList;

    public Favourite_Adapter(Context context, ArrayList<Favourite_Model_Class> favourite_model_classArrayList) {
        this.context = context;
        this.favourite_model_classArrayList = favourite_model_classArrayList;
    }

    @NonNull
    @Override
    public Favourite_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favourite,viewGroup,false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Favourite_Adapter.MyViewHolder myViewHolder, int i) {

        Favourite_Model_Class modelClass = favourite_model_classArrayList.get(i);

        myViewHolder.favourite_field_title_text.setText(modelClass.getFavourite_field_title_text());
        myViewHolder.location_city_text.setText(modelClass.getLocation_city_text());
        myViewHolder.location_text.setText(modelClass.getLocation_text());
        myViewHolder.favourite__field_icon.setImageResource(modelClass.getFavourite__field_icon());



    }

    @Override
    public int getItemCount() {

        return favourite_model_classArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView favourite__field_icon;

        TextView favourite_field_title_text,location_text,location_city_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            favourite__field_icon = itemView.findViewById(R.id.favourite__field_icon);


            favourite_field_title_text = itemView.findViewById(R.id.favourite_field_title_text);
            location_text = itemView.findViewById(R.id.location_text);
            location_city_text = itemView.findViewById(R.id.location_city_text);

        }
    }
}
