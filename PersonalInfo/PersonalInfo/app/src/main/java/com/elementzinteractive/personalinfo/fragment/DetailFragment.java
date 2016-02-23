package com.elementzinteractive.personalinfo.fragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.elementzinteractive.personalinfo.R;
import com.elementzinteractive.personalinfo.Singleton.CurrentUser;
import com.elementzinteractive.personalinfo.model.Person;
import com.squareup.picasso.Picasso;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final int RESULT_OK = 0;
    private static int RESULT_LOAD_IMG = 1;
    Bundle savedState;
    Uri selectedImage;
    ProgressBar progressBar;
    private DatePicker datePicker;
    private Calendar calendar;
    private ImageView imgAvatar;
    private TextView dateView;
    private int year, month, day;
    private Bitmap imgAvatarBp;
    private Bitmap imgAvatarBpClone;
    private String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };
    Person person;
    private View.OnClickListener profileClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectImage();
        }
    };
    private View.OnClickListener dateClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerFragment dialogFragment = new DatePickerFragment();
            dialogFragment.show(getFragmentManager(), "test");
            dialogFragment.mDateSetListener = myDateListener;
            //  getActivity().showDialog(999);
        }
    };

    public DetailFragment() {
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
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        // Inflate the layout for this fragment
        progressBar = (ProgressBar) getActivity().findViewById(R.id.toolbar_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        dateView = (TextView) view.findViewById(R.id.tvBirthDate);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        imgAvatar.setOnClickListener(profileClick);
        view.findViewById(R.id.btnSetDate).setOnClickListener(dateClick);
        //DatePickerDialog datePickerDialog = new  DatePickerDialog(getActivity(), myDateListener, year, month, day);
        progressBar.setVisibility(View.GONE);
        Log.e("Fragment", "onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e("Fragment", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            person = CurrentUser.getInstance().getPerson();
            ((EditText) getView().findViewById(R.id.edtFirstName)).setText(person.getFirstName());
            ((EditText) getView().findViewById(R.id.edtLastName)).setText(person.getLastName());
            ((EditText) getView().findViewById(R.id.edtPhone)).setText(person.getPhone());
            ((EditText) getView().findViewById(R.id.edtEmail)).setText(person.getEmail());
            ((RadioButton) getView().findViewById(R.id.rdbtnGenderFemale)).setChecked(person.getGender().toUpperCase().equals("FEMALE"));
            ((RadioButton) getView().findViewById(R.id.rdbtnGenderMale)).setChecked(person.getGender().toUpperCase().equals("MALE"));
            ((TextView) getView().findViewById(R.id.tvBirthDate)).setText(person.getBirthDate());
            String imgURL = person.getAvatarURL();
           // ((TextView) getView().findViewById(R.id.tvURL)).setText(imgURL);
            if (imgAvatarBp != null)
                imgAvatar.setImageBitmap(imgAvatarBp);
            else if (selectedImage != null)
                imgAvatar.setImageURI(selectedImage);
            else {
                Picasso.with(getContext())
                        .load(imgURL)
                        .placeholder(R.drawable.user) // optional
                        .error(R.drawable.circle)// optional
                        .into(imgAvatar);
            }
            //region OLD CODE
           /* Log.e("onResume Fradg", bundle.toString());
            //Log.e("dddB", bundle.toString());
            ((EditText) getView().findViewById(R.id.edtFirstName)).setText(bundle.getString("firstname"));
            ((EditText) getView().findViewById(R.id.edtLastName)).setText(bundle.getString("lastname"));
            ((EditText) getView().findViewById(R.id.edtPhone)).setText(bundle.getString("number"));
            ((EditText) getView().findViewById(R.id.edtEmail)).setText(bundle.getString("email"));
            ((RadioButton) getView().findViewById(R.id.rdbtnGenderFemale)).setChecked(bundle.getString("gender").toUpperCase().equals("FEMALE"));
            ((RadioButton) getView().findViewById(R.id.rdbtnGenderMale)).setChecked(bundle.getString("gender").toUpperCase().equals("MALE"));
            ((TextView) getView().findViewById(R.id.tvBirthDate)).setText(bundle.getString("birthdate"));
            String imgURL = bundle.getString("imgURL");
            ((TextView) getView().findViewById(R.id.tvURL)).setText(imgURL);
            if (imgAvatarBp != null)
                imgAvatar.setImageBitmap(imgAvatarBp);
            else if(selectedImage !=null)
                imgAvatar.setImageURI(selectedImage);
            else{
                Picasso.with(getContext())
                        .load(imgURL)
                        .placeholder(R.drawable.user) // optional
                        .error(R.drawable.circle)// optional
                        .into(imgAvatar);}
            ((MainActivity) getActivity()).setBundle(bundle);*/
            //endregion
            progressBar.setVisibility(View.GONE);
        } catch (Exception ex) {
           // Log.e("onResume", ex.getMessage());
        }
        Log.e("Fragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Fragment", "onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Fragment", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Fragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Fragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Fragment", "onDetach");
    }
    //endregion

    //region OVERRIDE METHODS
    protected Dialog onCreateDialog(int id) {
        if (id == 999) return new DatePickerDialog(getActivity(), myDateListener, year, month, day);
        return null;
    }

    //region SAVED INSTANCE
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.e("onSaveInst frag", savedInstanceState.toString());
    }
    //endregion

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_save) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region METHODS
    public void setDate(View view) {
        Log.e("test", "test");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Log.e(String.valueOf(requestCode), String.valueOf(resultCode));
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == getActivity().RESULT_OK) {
                    selectedImage = null;
                    imgAvatarBp = (Bitmap) data.getExtras().get("data");
                    imgAvatar.setImageBitmap(imgAvatarBp);

                }
                break;
            case 1:
                if (resultCode == getActivity().RESULT_OK) {
                    selectedImage = data.getData();
                    imgAvatarBp = null;
                    imgAvatar.setImageURI(selectedImage);
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = getResources().getStringArray(R.array.photo_selections);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent camIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camIntent, 0);
                } else if (items[item].equals("Choose from library")) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void showDate(int year, int month, int day) {
        dateView = (TextView) getView().findViewById(R.id.tvBirthDate);
        dateView.setText(new StringBuilder().append(monthNames[month - 1]).append(" ")
                .append(day).append(", ").append(year));
    }
    //endregion


}
