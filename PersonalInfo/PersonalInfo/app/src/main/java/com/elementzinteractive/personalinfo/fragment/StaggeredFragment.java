package com.elementzinteractive.personalinfo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.elementzinteractive.personalinfo.API.UserInterface;
import com.elementzinteractive.personalinfo.R;
import com.elementzinteractive.personalinfo.adapter.StaggeredGridAdapter;
import com.elementzinteractive.personalinfo.model.JsonObj;
import com.elementzinteractive.personalinfo.model.Person;
import com.elementzinteractive.personalinfo.model.Result;
import com.etsy.android.grid.StaggeredGridView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StaggeredFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StaggeredFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaggeredFragment extends Fragment {

    String API = "http://api.randomuser.me";
    private StaggeredGridView mGridView;
    private StaggeredGridAdapter mGridAdapter;
    List<Person> listPersons;
    private RestAdapter restAdapter;
    private  int start=  0;
    ProgressBar progressBar;

    public StaggeredFragment() {
        // Required empty public constructor
    }

    //region LIFE CYCLE
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Fragment", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fragment", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staggered, container, false);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.toolbar_progress_bar);
        mGridView = (StaggeredGridView)view.findViewById(R.id.stgGridView);
        restAdapter = new RestAdapter.Builder().setEndpoint(API).build();
        listPersons = new ArrayList<Person>();
        mGridAdapter = new StaggeredGridAdapter(getContext(),listPersons);
        mGridView.setAdapter(mGridAdapter);
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    // LoadMoreUsers();
                }
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        generateData();
        return view;
    }

    private void generateData()
    {
        if(!isAdded()) return;
        start  =0;
        UserInterface userInterface = restAdapter.create(UserInterface.class);
        userInterface.getUsers("20", new Callback<JsonObj>() {
            @Override
            public void success(JsonObj model, Response response) {
                for (Result result : model.getResults()) {
                    String dateAsText = new SimpleDateFormat(getResources().getString(R.string.birthdate_format))
                            .format(new Date(result.getUser().getDob() * 1000L));
                    Person person = new Person(result.getUser().getPicture().getMedium(),
                            result.getUser().getName().getLast(),
                            result.getUser().getName().getFirst(),
                            result.getUser().getGender(),
                            dateAsText,
                            result.getUser().getPhone(),
                            result.getUser().getEmail());
                    listPersons.add(person);
                    mGridAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void LoadMoreUsers() {
        if(!isAdded()) return;
        if (start == listPersons.size()) return;
        start++;
        //mGridAdapter.add(listPersons.get(start));
        mGridAdapter.notifyDataSetChanged();
        start += 1;
    }

}
