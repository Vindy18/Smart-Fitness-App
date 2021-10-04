package com.smartFitness.home.CustomerWeightTrainer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smartFitness.home.CustomerNutritionists.Customer_View_Nutritionist_Profile_View;
import com.smartFitness.home.Model.WeightTrainer;
import com.smartFitness.home.R;

import java.util.List;

public class WeightTrainerCvView extends ArrayAdapter<WeightTrainer>{

        //variable
        Context context;

        List<WeightTrainer> weightTrainers;

        //constructor
        WeightTrainerCvView(Context context, List<WeightTrainer> weightTrainers) {
                super(context, R.layout.single_row_wcv,weightTrainers);
                this.context = context;
                this.weightTrainers = weightTrainers;
        }

        @Nullable
        @Override
        public View getView(int position,@Nullable View convertView,@NonNull ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //add one row to one layout
                View row = inflater.inflate(R.layout.single_row_wcv, parent, false);

                //get one row by ID
                TextView wt_nameView = row.findViewById(R.id.wt_CVname);
                TextView wt_locationView = row.findViewById(R.id.Wt_CVlocation);
                TextView wt_phoneView = row.findViewById(R.id.wt_CVphone);
                Button wt_View = row.findViewById(R.id.btn_cvWTview);


                //get weightTrainer by position
                WeightTrainer weightTrainer = weightTrainers.get(position);

                //get one weightTrainer
                wt_nameView .setText(weightTrainer.name);
                wt_locationView.setText(weightTrainer.location);
                wt_phoneView .setText(weightTrainer.contactNumber);


                //button -> move "Customer_View_WeightTrainer_Profile_View" page
                wt_View .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                // pass intent
                                Intent intent = new Intent(context, Customer_View_WeightTrainer_Profile_View.class);
                                intent.putExtra("emailaddress",weightTrainer.email);
                                context.startActivity(intent);

                                //Toast massage
                                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show();
                        }
                });

                return row;

                }
}
