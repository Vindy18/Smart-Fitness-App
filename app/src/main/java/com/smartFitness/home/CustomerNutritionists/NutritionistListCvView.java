package com.smartFitness.home.CustomerNutritionists;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smartFitness.home.AdminNutritionists.Add_new_nutritionist;
import com.smartFitness.home.AdminNutritionists.Admin_View_Nutritionists_List;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.R;

import java.util.List;

public class NutritionistListCvView extends ArrayAdapter<Nutritionist> {
    //variable
    Context context;

    List<Nutritionist> nutritionists;

    //constructor
    NutritionistListCvView(Context context, List<Nutritionist> nutritionists) {

        super(context, R.layout.single_row_ncv,nutritionists);
        this.context = context;
        this.nutritionists = nutritionists;

    }

    @Nullable
    @Override
    public View getView(int position,@Nullable View convertView,@NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //add one row to one layout
        View row = inflater.inflate(R.layout.single_row_ncv, parent, false);

        //get one row by ID
        Button btn_cvNutView = row.findViewById(R.id.btn_cvNutView);
        TextView ntr_nameView = row.findViewById(R.id.ntr_CVname);
        TextView ntr_locationView = row.findViewById(R.id.ntr_CVlocation);
        TextView ntr_phoneView = row.findViewById(R.id.ntr_CVphone);

        //get nutritionist by position
        Nutritionist nutritionist = nutritionists.get(position);

        //get one nutritionist
        ntr_nameView.setText(nutritionist.name);
        ntr_locationView.setText(nutritionist.location);
        ntr_phoneView.setText(nutritionist.mobileNumber);

        //button -> move "Customer_View_Nutritionist_Profile_View" page
        btn_cvNutView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // pass intent
                Intent intent = new Intent(context, Customer_View_Nutritionist_Profile_View.class);
                intent.putExtra("emailaddress",nutritionist.email);
                context.startActivity(intent);

                //Toast massage
                Toast.makeText(context, "Nutritionist Loading", Toast.LENGTH_SHORT).show();
            }
        });

        return row;

    }
}
