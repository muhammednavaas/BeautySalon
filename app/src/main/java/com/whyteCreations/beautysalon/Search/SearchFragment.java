package com.whyteCreations.beautysalon.Search;


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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.whyteCreations.beautysalon.Adapter.SearchAdapter;
import com.whyteCreations.beautysalon.Api.Common;
import com.whyteCreations.beautysalon.Api.NetworkClient;
import com.whyteCreations.beautysalon.Model.Salon;
import com.whyteCreations.beautysalon.Model.SaloneResponse;
import com.whyteCreations.beautysalon.R;

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
public class SearchFragment extends Fragment {
    AlertDialog dialog;
    Unbinder unbinder;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.rvSearch)
    RecyclerView recyclerView;
    @BindView(R.id.llNorecord)
    LinearLayout llNorecord;
    private SearchAdapters searchAdapter;
    private List<Salon> searchList;
    private int per_page = 1;
    String exploretitle;
    @BindView(R.id.tvSearch)
    TextView tvSearch;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String ID) {
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Common.searchKey, ID);
        searchFragment.setArguments(bundle);
        return searchFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        //exploretitle = getArguments().getString(Common.searchKey);
        edtSearch.setText(exploretitle);
        tvSearch.setText(exploretitle);
        search(per_page);
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    per_page = 1;
                    exploretitle = edtSearch.getText().toString();
                    tvSearch.setText(exploretitle);
                    searchList.clear();
                    search(per_page);
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void search(int per_page) {
        if (per_page == 1) {
            dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).setMessage("Please wait....").build();
            dialog.show();
        }


        Call<SaloneResponse> responseCall = NetworkClient.getInstance().getServiceApin().getSearchList(exploretitle);
        responseCall.enqueue(new Callback<SaloneResponse>() {
            @Override
            public void onResponse(Call<SaloneResponse> call, Response<SaloneResponse> response) {
                if (per_page == 1) {
                    dialog.dismiss();
                }
                if (response.body() == null) {
                    searchAdapter.setLoaded();
                    searchAdapter.notifyDataSetChanged();
                }
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    searchList = response.body().getSalonList();
                    SearchAdapter adapter = new SearchAdapter(getActivity(), searchList);
                    recyclerView.setAdapter(adapter);
                } else {
                    if (response.body() == null) {
                        recyclerView.setVisibility(View.GONE);
                        llNorecord.setVisibility(View.VISIBLE);
                        searchAdapter.setLoaded();
                        searchAdapter.notifyDataSetChanged();
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        llNorecord.setVisibility(View.VISIBLE);
                    }
                }


            }

            @Override
            public void onFailure(Call<SaloneResponse> call, Throwable t) {
                dialog.dismiss();
                Log.e("ERROR", t.getMessage());
            }
        });
    }
//
//    private void fetchSalonList() {
//        final AlertDialog dialog = new SpotsDialog.Builder().setContext(getActivity()).setCancelable(false).setMessage("Please wait....").build();
//        dialog.show();
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Call<SaloneResponse> responseCall = NetworkClient.getInstance().getServiceApin().getList();
//        responseCall.enqueue(new Callback<SaloneResponse>() {
//            @Override
//            public void onResponse(Call<SaloneResponse> call, Response<SaloneResponse> response) {
//                dialog.dismiss();
//                searchList = response.body().getSalonList();
//                SearchAdapter adapter = new SearchAdapter(getActivity(), searchList);
//                recyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<SaloneResponse> call, Throwable t) {
//                dialog.dismiss();
//                Log.e("ERROR", t.getMessage());
//            }
//        });
//
//
//    }

}
