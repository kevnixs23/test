package com.elementzinteractive.directory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        /* Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),view.class);
                i.putExtra("pass_data","PASS DATA");
                startActivity(i);
            }
        });
        ListView listViewEmployee = (ListView)findViewById(R.id.listView);
        String[] items;
        items = new String[] { "Vegetables","Fruits","Flower Buds","Legumes","Bulbs","Tubers" };
        //ListAdapter = new ArrayAdapter<String>(this, Android.Resource.Layout.SimpleListItem1, items);
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("foo");
        your_array_list.add("bar");
        your_array_list.add("delete");
        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );
        Log.e("On Create","On Create");
        listViewEmployee.setAdapter(arrayAdapter);*/

        ListView listViewEmployee;
        listViewEmployee = (ListView) findViewById(R.id.listPhone);

        List<Contact> listPhoneBook = new ArrayList<Contact>();
        listPhoneBook.add(new Contact(R.drawable.avatar_jenny,
                "Maria Kahim", "010-9817-6331", "pete.houston.17187@gmail.com"));
        listPhoneBook.add(new Contact(R.drawable.avatar_lina,
                "Lina Cheng", "046-7764-1142", "lina.cheng011@sunny.com"));
        listPhoneBook.add(new Contact(R.drawable.avatar_jenny,
                "Jenny Nguyen", "0913-223-498", "jenny_in_love98@yahoo.com"));

        listPhoneBook.add(new Contact(R.drawable.avatar_jenny,
                "Jenny Nguyen", "0913-223-498", "jenny_in_love98@yahoo.com"));

        ContactAdapter adapter = new ContactAdapter(this, listPhoneBook);
        listViewEmployee.setAdapter(adapter);

        listViewEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                String mname = ((TextView) view.findViewById(R.id.tvName)).getText().toString();
                String number = ((TextView) view.findViewById(R.id.tvPhone)).getText().toString();
                String email = ((TextView) view.findViewById(R.id.tvEmail)).getText().toString();

                Intent intent = new Intent(getApplicationContext(), view.class);
                intent.putExtra("name", mname);
                intent.putExtra("phone", number);
                intent.putExtra("email", email);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "myPos " + i +"\n"+name, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("On Start", "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("On Resume", "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("On Pause", "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("On Stop", "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("On Destroy", "On Destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("On Restart", "On Restart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
