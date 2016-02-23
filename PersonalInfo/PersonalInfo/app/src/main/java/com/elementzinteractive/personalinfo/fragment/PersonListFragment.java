package com.elementzinteractive.personalinfo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.elementzinteractive.personalinfo.API.UserInterface;
import com.elementzinteractive.personalinfo.R;
import com.elementzinteractive.personalinfo.adapter.PersonListAdapter;
import com.elementzinteractive.personalinfo.adapter.PersonAdapter;
import com.elementzinteractive.personalinfo.helper.OnStartDragListener;
import com.elementzinteractive.personalinfo.helper.SimpleItemTouchHelperCallback;
import com.elementzinteractive.personalinfo.model.JsonObj;
import com.elementzinteractive.personalinfo.model.Person;
import com.elementzinteractive.personalinfo.model.Result;

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
 * {@link PersonListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonListFragment extends Fragment implements OnStartDragListener {

    //region VARIABLES
    String API = "http://api.randomuser.me";
    ProgressBar progressBar;
    PersonAdapter adapter;

    ListView listView;
    List<Person> listPersons;
    List<Person> listAllPersons;
    RestAdapter restAdapter;
    boolean isScrollDown = false;
    int start = 0;
    int end = 0;

    PersonListAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private int firstVisibleItem, visibleItemCount, totalItemCount, previousTotal = 0;
    private final int VISIBLE_THRESHOLD = 5;
    //endregion

    //region CONSTRUCTOR
    public PersonListFragment() {
        // Required empty public constructor
    }
    //endregion

    //region INSTANCE
    public static PersonListFragment newInstance(Bundle bundle) {
        PersonListFragment personListFragment = new PersonListFragment();
        personListFragment.setArguments(bundle);
        return personListFragment;
    }
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.toolbar_progress_bar);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        listPersons = new ArrayList<Person>();
        listAllPersons = new ArrayList<Person>();
        restAdapter = new RestAdapter.Builder().setEndpoint(API).build();
        //listView = (ListView) view.findViewById(R.id.lvwPersons);
        // adapter = new PersonAdapter(getContext(), listPersons);
        // listView.setAdapter(adapter);

        recyclerViewAdapter = new PersonListAdapter(getContext(),listPersons);
        recyclerViewAdapter.setActivity(getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalItemCount = linearLayoutManager.getItemCount();
                visibleItemCount = linearLayoutManager.getChildCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (isScrollDown) {
                    if (totalItemCount > previousTotal) {
                        isScrollDown = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!isScrollDown && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
                    LoadMoreUsers();
                    isScrollDown = true;
                }
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerViewAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        //region OLD CODE
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {

                String name = ((TextView) view.findViewById(R.id.tvName)).getText().toString();
                String lastname = ((TextView) view.findViewById(R.id.tvLastName)).getText().toString();
                String firstname = ((TextView) view.findViewById(R.id.tvFirstName)).getText().toString();
                String gender = ((TextView) view.findViewById(R.id.tvGender)).getText().toString();
                String birthdate = ((TextView) view.findViewById(R.id.tvBirthDate)).getText().toString();
                String number = ((TextView) view.findViewById(R.id.tvPhone)).getText().toString();
                String email = ((TextView) view.findViewById(R.id.tvEmail)).getText().toString();
                String url = ((TextView) view.findViewById(R.id.tvImageURL)).getText().toString();

                CurrentUser.getInstance().setPerson(new Person(url, lastname, firstname, gender, birthdate, number, email));
                //((MainActivity) getActivity()).setBundle(bundle);
                ((MainActivity) getActivity()).setCurrentPagerItem(1);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    if (!isScrollDown) {
                        LoadMoreUsers();
                    }
                } else
                    isScrollDown = false;
            }
        });*/
        //endregion
        progressBar.setVisibility(View.VISIBLE);
        initialLoadUser();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void initialLoadUser() {
        if(!isAdded()) return;
        start = 0;
        UserInterface userInterface = restAdapter.create(UserInterface.class);
        userInterface.getUsers(getResources().getString(R.string.initial_list_count), new Callback<JsonObj>() {
            @Override
            public void success(JsonObj model, Response response) {
                for (Result result : model.getResults()) {
                    String dateAsText = new SimpleDateFormat(getResources().getString(R.string.birthdate_format))
                            .format(new Date(result.getUser().getDob() * 1000L));

                    listAllPersons.add(new Person(result.getUser().getPicture().getMedium(),
                            result.getUser().getName().getLast(),
                            result.getUser().getName().getFirst(),
                            result.getUser().getGender(),
                            dateAsText,
                            result.getUser().getPhone(),
                            result.getUser().getEmail()));

                    if (start < 7)
                        start++;
                    else continue;

                    listPersons.add(new Person(result.getUser().getPicture().getMedium(),
                            result.getUser().getName().getLast(),
                            result.getUser().getName().getFirst(),
                            result.getUser().getGender(),
                            dateAsText,
                            result.getUser().getPhone(),
                            result.getUser().getEmail()));
                    recyclerViewAdapter.notifyDataSetChanged();
                    //adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
            }
        });
    }

    public void LoadMoreUsers() {
        if(!isAdded()) return;
        if (start == listAllPersons.size()) return;
        listPersons.addAll(listAllPersons.subList(start, start + 1));
        recyclerViewAdapter.notifyDataSetChanged();
        start += 1;
       // isScrollDown = true;
        //region OLD CODE
       /* UserInterface userInterface = restAdapter.create(UserInterface.class);
        userInterface.getUsers(getResources().getString(R.string.load_more_count), new Callback<JsonObj>() {
            @Override
            public void success(JsonObj model, Response response) {
                for (Result result : model.getResults()) {
                    String dateAsText = new SimpleDateFormat(getResources().getString(R.string.birthdate_format))
                            .format(new Date(result.getUser().getDob() * 1000L));

                    listPersons.add(new Person(result.getUser().getPicture().getMedium(),
                            result.getUser().getName().getLast(),
                            result.getUser().getName().getFirst(),
                            result.getUser().getGender(),
                            dateAsText,
                            result.getUser().getPhone(),
                            result.getUser().getEmail()));
                }
               // listView.removeFooterView(footerView);
                adapter.notifyDataSetChanged();
                isScrollDown = true;
            }
            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
            }
        });*/
        //endregion
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
