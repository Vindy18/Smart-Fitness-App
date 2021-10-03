package com.smartFitness.home.AdminWeightTrainer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smartFitness.home.AdminNutritionists.Admin_View_Nutritionists_List;
import com.smartFitness.home.AdminNutritionists.Edit_Nutritionists;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.Model.WeightTrainer;
import com.smartFitness.home.R;

import java.util.List;


public class WeightTrainerListView extends ArrayAdapter<WeightTrainer> {
    Context context;

    List<WeightTrainer> weightTrainers;

    String emailExtra;

    WeightTrainerListView(Context context,List<WeightTrainer> weightTrainers,String emailExtra ) {
        super(context, R.layout.single_row_w,weightTrainers);
        this.context  = context;
        this.weightTrainers = weightTrainers;
        this.emailExtra = emailExtra;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.single_row_w,parent, false);

        Button btn_edit = row.findViewById(R.id.btn_wedit);
        Button btn_delete = row.findViewById(R.id.btn_wdelete);
        TextView wt_name= row.findViewById(R.id.wt_name);
        TextView wt_location= row.findViewById(R.id.wt_location);
        TextView wt_phone= row.findViewById(R.id.wt_phone);

        //get nutritionist by position
        WeightTrainer weightTrainer = weightTrainers.get(position);

        wt_name.setText(weightTrainer.name);
        wt_location.setText(weightTrainer.location);
        wt_phone.setText(weightTrainer.contactNumber);

        //button -> move "Customer_View_Nutritionist_Profile_View" page
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pass intent
                Intent intent = new Intent(context, Admin_View_Edit_WeightTrainer.class);
                intent.putExtra("emailaddress",emailExtra);
                intent.putExtra("weighttraineremail",weightTrainer.email);
                context.startActivity(intent);

                //Toast massage
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show();
            }
        });

        //Delete row
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelperWeightTrainer dbHelper = new DBHelperWeightTrainer(context);
                dbHelper.deleteWeightTrainer(weightTrainer.email);

                Toast.makeText(context,"Deleting..",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, Admin_View_WeightTrainerList.class);
                intent.putExtra("emailaddress",emailExtra);
                context.startActivity(intent);
            }
        });

        return row;
    }
}
