package com.elementzinteractive.testproject;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    private DatePicker datePicker;
    private Calendar calendar;
    private ImageView imgAvatar;
    private TextView dateView;
    private int year, month, day;
    private Bitmap imgAvatarBp;
    private String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };
    final String[] fragments ={
            "com.example.navigationdrawer.DetailFragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        dateView = (TextView) findViewById(R.id.tvBirthDate);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        getSupportActionBar().setIcon(R.drawable.icon_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setLogo(R.drawable.icon_user);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imgAvatar.setOnClickListener(profileClick);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private View.OnClickListener profileClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectImage();
        }
    };

    public void setDate(View view) {
        showDialog(999);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.e(String.valueOf(requestCode), String.valueOf(resultCode));
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    imgAvatarBp = (Bitmap) data.getExtras().get("data");
                    imgAvatar.setImageBitmap(imgAvatarBp);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    imgAvatar.setImageURI(selectedImage);
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = getResources().getStringArray(R.array.photo_selections);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent camIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camIntent, 0);
                } else if (items[item].equals("Choose from Library")) {
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

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private void showDate(int year, int month, int day) {
        dateView = (TextView) findViewById(R.id.tvBirthDate);
        dateView.setText(new StringBuilder().append(monthNames[month - 1]).append(" ")
                .append(day).append(", ").append(year));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        EditText editFirstName = ((EditText) findViewById(R.id.edtFirstName));
        savedInstanceState.putString("FirstName", ((EditText) findViewById(R.id.edtFirstName)).getText().toString());
        savedInstanceState.putString("LastName", ((EditText) findViewById(R.id.edtLastName)).getText().toString());
        savedInstanceState.putString("BirthDate", ((TextView) findViewById(R.id.tvBirthDate)).getText().toString());
        savedInstanceState.putParcelable("bitmap", ((BitmapDrawable) imgAvatar.getDrawable()).getBitmap());
        savedInstanceState.putBoolean("Male", ((RadioButton) findViewById(R.id.rdbtnGenderMale)).isChecked());
        savedInstanceState.putBoolean("Female", ((RadioButton)findViewById(R.id.rdbtnGenderFemale)).isChecked());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try
        {
            // Restore UI state from the savedInstanceState.
            // This bundle has also been passed to onCreate.
            String firstName = savedInstanceState.getString("FirstName");
            String lastName = savedInstanceState.getString("LastName");
            String birthDate = savedInstanceState.getString("BirthDate");
            boolean male = savedInstanceState.getBoolean("Male");
            boolean female = savedInstanceState.getBoolean("Female");
            // Integer gender = savedInstanceState.getInt("BirthDate");
            ((EditText) findViewById(R.id.edtFirstName)).setText(firstName);
            ((EditText) findViewById(R.id.edtLastName)).setText(lastName);
            ((TextView) findViewById(R.id.tvBirthDate)).setText(birthDate);
            ((RadioButton)findViewById(R.id.rdbtnGenderFemale)).setChecked(female);
            ((RadioButton)findViewById(R.id.rdbtnGenderMale)).setChecked(male);
            if (savedInstanceState != null) {
                imgAvatarBp = (Bitmap) savedInstanceState.getParcelable("bitmap");
                imgAvatar.setImageBitmap(imgAvatarBp);
            }
        }
        catch (Exception ex)
        {
            Log.e("MainActivity", ex.getMessage());
        }


    }
}
