package com.whyteCreations.beautysalon.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whyteCreations.beautysalon.Adapter.SearchAdapter;
import com.whyteCreations.beautysalon.Api.NetworkClient;
import com.whyteCreations.beautysalon.Model.Salon;
import com.whyteCreations.beautysalon.Model.SaloneResponse;
import com.whyteCreations.beautysalon.R;
import com.whyteCreations.beautysalon.Search.SearchFragment;

import java.util.List;

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
public class HomeFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    List<Salon> salonLists;
    private String exploretitle;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        fetchSalonList();
        intiView();
        return view;
    }

    private void intiView() {
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    exploretitle = edtSearch.getText().toString();
                    InputMethodManager methodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    methodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    SearchFragment searchDetailsFragment = SearchFragment.newInstance(exploretitle);
                    loadFragment(searchDetailsFragment);
                    edtSearch.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(SearchFragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.fl_container, fragment, null);
            ft.hide(HomeFragment.this);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }


    private void fetchSalonList() {
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).setMessage("Please wait....").build();
        dialog.show();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<SaloneResponse> responseCall = NetworkClient.getInstance().getServiceApin().getList();
        responseCall.enqueue(new Callback<SaloneResponse>() {
            @Override
            public void onResponse(Call<SaloneResponse> call, Response<SaloneResponse> response) {
                dialog.dismiss();
                salonLists = response.body().getSalonList();
                SearchAdapter adapter = new SearchAdapter(getActivity(), salonLists);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<SaloneResponse> call, Throwable t) {
                dialog.dismiss();
                Log.e("ERROR", t.getMessage());
            }
        });


    }
}
