package com.whyteCreations.beautysalon.Fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.whyteCreations.beautysalon.Api.NetworkClient;
import com.whyteCreations.beautysalon.Model.UpdateUserResponse;
import com.whyteCreations.beautysalon.R;
import com.whyteCreations.beautysalon.Utils.PrefUtils;
import com.whyteCreations.beautysalon.ui.ProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
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

    Unbinder unbinder;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).setMessage("Please wait....").build();
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
                            PrefUtils.storeApiKey(getActivity(), updateUserResponse.getApiKey());

                            Toast.makeText(getActivity(), updateUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(getActivity(), updateUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

}
