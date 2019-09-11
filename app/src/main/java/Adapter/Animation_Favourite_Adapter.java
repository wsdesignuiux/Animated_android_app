package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import Model.Animation_Favourite_Model;
import e.wolfsoft1.animated_android_app.R;


public class Animation_Favourite_Adapter extends RecyclerView.Adapter<Animation_Favourite_Adapter.MyViewHolder> {

    private Context context;
    private ArrayList<Animation_Favourite_Model>favourite_model_classArrayList;

    public Animation_Favourite_Adapter(Context context, ArrayList<Animation_Favourite_Model> favourite_model_classArrayList) {
        this.context = context;
        this.favourite_model_classArrayList = favourite_model_classArrayList;
    }

    @NonNull
    @Override
    public Animation_Favourite_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_animation_favourite,viewGroup,false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Animation_Favourite_Adapter.MyViewHolder myViewHolder, int i) {

        Animation_Favourite_Model modelClass = favourite_model_classArrayList.get(i);

        myViewHolder.favourite_field_title_text.setText(modelClass.getFavourite_field_title_text());
        myViewHolder.location_city_text.setText(modelClass.getLocation_city_text());
        myViewHolder.location_text.setText(modelClass.getLocation_text());



    }

    @Override
    public int getItemCount() {

        return favourite_model_classArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView favourite_field_title_text,location_text,location_city_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            favourite_field_title_text = itemView.findViewById(R.id.favourite_field_title_text);
            location_text = itemView.findViewById(R.id.location_text);
            location_city_text = itemView.findViewById(R.id.location_city_text);

        }
    }
}
