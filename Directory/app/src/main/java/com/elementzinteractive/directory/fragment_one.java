package com.elementzinteractive.directory;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_one.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_one#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_one extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("fr On Attach","On Attach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("fr On Create","On Create");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("fr On Activity Created", "On Activity Created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("fr On Start", "On Start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fr On Resume", "On Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fr On Pause", "On Pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("fr On Stop", "On Stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fr On Destroy View", "On Destroy View");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fr On Destroy", "On Destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("fr On Detach", "On Detach");
    }

}
