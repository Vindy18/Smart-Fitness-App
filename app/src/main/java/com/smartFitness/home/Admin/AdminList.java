package com.smartFitness.home.Admin;

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
import com.smartFitness.home.Model.Admin;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.R;

import java.util.List;

public class AdminList extends ArrayAdapter<Admin> {

    Context context;

    List<Admin> Admins;

    AdminList(Context context,List<Admin> Admins ) {
        super(context, R.layout.single_row_admin,Admins);
        this.context  = context;
        this.Admins = Admins;
    }
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        //layout inflater object fetched
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //get single row view object
        View row = inflater.inflate(R.layout.single_row_admin,parent, false);

        //get elements by id
        TextView ad_name = row.findViewById(R.id.ad_name);
        TextView ad_email = row.findViewById(R.id.ad_email);
        TextView ad_phone = row.findViewById(R.id.ad_phone);

        //get admin by position
        Admin admin = Admins.get(position);

        String fullName = admin.firstName +" "+ admin.lastName ;

        ad_name.setText(fullName);
        ad_email.setText(admin.email);
        ad_phone.setText(admin.mobileNumber);

        return row;
    }

}
