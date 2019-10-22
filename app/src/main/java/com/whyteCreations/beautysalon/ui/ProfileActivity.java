package com.whyteCreations.beautysalon.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.whyteCreations.beautysalon.Api.NetworkClient;
import com.whyteCreations.beautysalon.Model.UpdateUserResponse;
import com.whyteCreations.beautysalon.R;
import com.whyteCreations.beautysalon.Utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.btn_update)
    Button update;
    @BindView(R.id.et_name)
    TextInputEditText name;
    @BindView(R.id.et_location)
    TextInputEditText et_location;
    @BindView(R.id.et_buildingnumber)
    TextInputEditText et_buildingnumber;
    @BindView(R.id.et_zone_number)
    TextInputEditText et_zone_number;
    @BindView(R.id.et_street_Number)
    TextInputEditText et_street_Number;
    @BindView(R.id.et_sreet_name)
    TextInputEditText et_sreet_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        initView();
    }


    private void initView() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new SpotsDialog.Builder().setContext(ProfileActivity.this).setCancelable(false).setMessage("Please wait....").build();
                dialog.show();
                String Name = name.getText().toString().trim();
                String Location = et_location.getText().toString().trim();
                String BuildingNumber = et_buildingnumber.getText().toString().trim();
                String ZoneNumber = et_zone_number.getText().toString();
                String StreetNumber = et_street_Number.getText().toString();
                String StreetName = et_sreet_name.getText().toString();

                Call<UpdateUserResponse> userResponseCall = NetworkClient.getInstance().getServiceApin()
                        .updateProfile("33", Name, Location, BuildingNumber, ZoneNumber, StreetNumber, StreetName);
                userResponseCall.enqueue(new Callback<UpdateUserResponse>() {
                    @Override
                    public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                        dialog.dismiss();
                        UpdateUserResponse updateUserResponse = response.body();
                        if (updateUserResponse.isStatus()){
                            PrefUtils.storeApiKey(getApplicationContext(), updateUserResponse.getApiKey());

                            Toast.makeText(getApplicationContext(), updateUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(getApplicationContext(), updateUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
    }
}
