package com.elementzinteractive.personalinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elementzinteractive.personalinfo.R;
import com.elementzinteractive.personalinfo.model.Person;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Vector;

/**
 * Created by Elementz on 2/15/2016.
 */
public class PersonAdapter extends BaseAdapter {
    private Context mContext;
    private List<Person> mListContact;
    private List<View> mListView;

    public PersonAdapter(Context context, List<Person> list) {
        mContext = context;
        mListContact = list;
        mListView = new Vector<View>();
    }

    @Override
    public int getCount() {
        return mListContact.size();
    }

    @Override
    public Object getItem(int pos) {
        return mListContact.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        // get selected entry
        Person entry = mListContact.get(pos);

        // inflating list view layout if null
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.person_list_view, null);
        }

        // set avatar
        ImageView ivAvatar = (ImageView) convertView.findViewById(R.id.imgView);
        //ivAvatar.setImageBitmap(entry.getAvatar());
//            ivAvatar.setImageResource(entry.getAvatar());
        //Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(entry.getAvatarURL()).getContent());
        //ivAvatar.setImageBitmap(bitmap);

        Picasso.with(convertView.getContext())
                .load(entry.getAvatarURL())
                .placeholder(R.drawable.user) // optional
                .error(R.drawable.circle)// optional
                .into(ivAvatar);

        TextView tvImageUrl = (TextView) convertView.findViewById(R.id.tvImageURL);
        tvImageUrl.setText(entry.getAvatarURL());

        // set name
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(entry.getFirstName() + " " + entry.getLastName());

        TextView tvLastName = (TextView) convertView.findViewById(R.id.tvLastName);
        tvLastName.setText(entry.getLastName());

        TextView tvFirstName = (TextView) convertView.findViewById(R.id.tvFirstName);
        tvFirstName.setText(entry.getFirstName());

        TextView tvGender = (TextView) convertView.findViewById(R.id.tvGender);
        tvGender.setText(entry.getGender());

        TextView tvBirthDate = (TextView) convertView.findViewById(R.id.tvBirthDate);
        tvBirthDate.setText(entry.getBirthDate());

        // set phone
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
        tvPhone.setText(entry.getPhone());

        // set email
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
        tvEmail.setText(entry.getEmail());
        mListView.add(convertView);
        return convertView;
    }

    public List<View> getViewList()
    {
        return mListView;
    }


}
