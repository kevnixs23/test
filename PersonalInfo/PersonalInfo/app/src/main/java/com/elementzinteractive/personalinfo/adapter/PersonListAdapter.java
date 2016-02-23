package com.elementzinteractive.personalinfo.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elementzinteractive.personalinfo.R;
import com.elementzinteractive.personalinfo.Singleton.CurrentUser;
import com.elementzinteractive.personalinfo.activity.MainActivity;
import com.elementzinteractive.personalinfo.helper.ItemTouchHelperAdapter;
import com.elementzinteractive.personalinfo.model.Person;
import com.squareup.picasso.Picasso;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private ArrayList<String> mDataset;
    List<Person> mListPerson;
    private Context mContext;
    private Activity mActivity;

    public PersonListAdapter(Context context, List<Person> personList)
    {
        this.mContext = context;
        mListPerson = personList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView tvImageURL;
        public TextView tvFirstName;
        public TextView tvLastName;
        public TextView tvGender;
        public TextView tvBirthDate;
        public TextView tvName;
        public TextView tvPhone;
        public TextView tvEmail;
        public ImageView imgView;

        public ViewHolder(View v) {
            super(v);
            tvImageURL= (TextView) v.findViewById(R.id.tvImageURL);
            tvFirstName= (TextView) v.findViewById(R.id.tvFirstName);
            tvLastName= (TextView) v.findViewById(R.id.tvLastName);
            tvGender= (TextView) v.findViewById(R.id.tvGender);
            tvBirthDate= (TextView) v.findViewById(R.id.tvBirthDate);
            tvName= (TextView) v.findViewById(R.id.tvName);
            tvPhone= (TextView) v.findViewById(R.id.tvPhone);
            tvEmail= (TextView) v.findViewById(R.id.tvEmail);
            imgView= (ImageView) v.findViewById(R.id.imgView);
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String name = ((TextView) v.findViewById(R.id.tvName)).getText().toString();
                    String lastname = ((TextView) v.findViewById(R.id.tvLastName)).getText().toString();
                    String firstname = ((TextView) v.findViewById(R.id.tvFirstName)).getText().toString();
                    String gender = ((TextView) v.findViewById(R.id.tvGender)).getText().toString();
                    String birthdate = ((TextView) v.findViewById(R.id.tvBirthDate)).getText().toString();
                    String number = ((TextView) v.findViewById(R.id.tvPhone)).getText().toString();
                    String email = ((TextView) v.findViewById(R.id.tvEmail)).getText().toString();
                    String url = ((TextView) v.findViewById(R.id.tvImageURL)).getText().toString();

                    CurrentUser.getInstance().setPerson(new Person(url, lastname, firstname, gender, birthdate, number, email));
                    //((MainActivity) getActivity()).setBundle(bundle);
                    ((MainActivity) mActivity).setCurrentPagerItem(1);
                }
            });
        }

    }


    public void add(int position, Person person) {
        //mDataset.add(position, item);
        mListPerson.add(position, person);
        notifyItemInserted(position);
    }

    public void remove(Person person) {
        int position = mListPerson.indexOf(person);
        mListPerson.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemDismiss(int position) {
        mListPerson.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mListPerson, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public PersonListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvImageURL.setText(mListPerson.get(position).getAvatarURL());
        holder.tvFirstName.setText(mListPerson.get(position).getFirstName());
        holder.tvLastName.setText(mListPerson.get(position).getLastName());
        holder.tvGender.setText(mListPerson.get(position).getGender());
        holder.tvBirthDate.setText(mListPerson.get(position).getBirthDate());
        holder.tvName.setText(mListPerson.get(position).getFirstName() + " "+mListPerson.get(position).getLastName());
        holder.tvPhone.setText(mListPerson.get(position).getPhone());
        holder.tvEmail.setText(mListPerson.get(position).getEmail());
       // holder.imgView.setText(mListPerson.get(position).getAvatarURL());
        Picasso.with(mContext)
                .load(mListPerson.get(position).getAvatarURL())
                .placeholder(R.drawable.user) // optional
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return mListPerson.size();
    }
    public void setActivity(Activity pActivity){
        mActivity = pActivity;
    }

} 