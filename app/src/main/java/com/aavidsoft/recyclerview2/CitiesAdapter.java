package com.aavidsoft.recyclerview2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private ArrayList<City> citiesList;

    public CitiesAdapter(ArrayList<City> citiesList) {
        this.citiesList = citiesList;
    }

    public interface OnCityClickListener {
        void onCityImageClick(ImageView imageView, City city, int position);
        void onCityNameClick(TextView txtCity, City city, int position);
    }

    private OnCityClickListener onCityClickListener;

    public void setOnCityClickListener(OnCityClickListener onCityClickListener) {
        this.onCityClickListener = onCityClickListener;
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgCity;
        public TextView txtCityName;
        public TextView txtState;
        public TextView txtDescription;

        public CityViewHolder(View itemView) {
            super(itemView);

            imgCity = itemView.findViewById(R.id.imgCity);
            txtCityName = itemView.findViewById(R.id.txtCityName);
            txtState = itemView.findViewById(R.id.txtState);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            imgCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(onCityClickListener != null) {
                        onCityClickListener.onCityImageClick(
                                imgCity,
                                citiesList.get(getAdapterPosition()),
                                getAdapterPosition()
                        );
                    }

                    /*Toast.makeText(
                            view.getContext(),
                            "Image: " + citiesList.get(getAdapterPosition()).getName(),
                            Toast.LENGTH_SHORT
                    ).show();*/
                }
            });

            txtCityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(onCityClickListener != null) {
                        onCityClickListener.onCityNameClick(
                                txtCityName,
                                citiesList.get(getAdapterPosition()),
                                getAdapterPosition()
                        );
                    }

                    /*Toast.makeText(
                            view.getContext(),
                            "TxtCityName: " + citiesList.get(getAdapterPosition()).getName(),
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent = new Intent(view.getContext(), CityDetailsActivity.class);
                    intent.putExtra(City.KEY_CITY, citiesList.get(getAdapterPosition()));
                    view.getContext().startActivity(intent);*/
                }
            });

            /*imgCity = (ImageView) ((LinearLayout)itemView).getChildAt(0);
            txtCityName = (TextView) ((LinearLayout)itemView).getChildAt(1);*/
        }
    }

    @Override
    public int getItemCount() {
        if(citiesList == null)
            return 0;

        return citiesList.size();
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.city_view, null);

        //this is useless
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "View Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        */
        return new CityViewHolder(view);

        //way 1
        /*LinearLayout linearLayout = new LinearLayout(parent.getContext());
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setPadding(30, 20, 30, 20);

        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
        linearLayout.addView(imageView);

        TextView txtCityName = new TextView(parent.getContext());
        txtCityName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtCityName.setPadding(30, 0,  0,  0);
        linearLayout.addView(txtCityName);


        return new CityViewHolder(linearLayout);*/
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Random random = new Random();

        holder.itemView.setBackgroundColor(
                Color.rgb(
                        Math.abs(random.nextInt()) % 256,
                        Math.abs(random.nextInt()) % 256,
                        Math.abs(random.nextInt()) % 256
                )
        );

        City city = citiesList.get(position);

        holder.imgCity.setImageResource(city.getImageId());
        holder.txtCityName.setText(city.getName());
        holder.txtState.setText(city.getState());
        holder.txtDescription.setText(city.getDescription());

        //not a good way to setup listeners to the views inside onBind
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(
                        view.getContext(),
                        citiesList.get(position).getName(),
                        Toast.LENGTH_SHORT
                ).show();

                *//*Intent intent = new Intent(view.getContext(), CityDetailsActivity.class);
                view.getContext().startActivity(intent);*//*
            }
        });*/

    }
}
