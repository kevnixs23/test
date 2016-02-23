package com.elementzinteractive.personalinfo.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elementzinteractive.personalinfo.R;
import com.elementzinteractive.personalinfo.model.Person;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import static android.view.View.*;

/**
 * Created by Elementz on 2/15/2016.
 */
public class StaggeredGridAdapter extends BaseAdapter {
    private Context mContext;
    private static final String TAG = "SampleAdapter";
    private List<Person> mListContact;
    private List<View> mListView;
    private final Random mRandom;
    private final ArrayList<Integer> mColors;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public StaggeredGridAdapter(Context context, List<Person> list) {
        mContext = context;
        mListContact = list;
        mListView = new Vector<View>();
        mRandom = new Random();
        mColors = new ArrayList<Integer>();
        mColors.add(R.color.colorAccent);
        mColors.add(R.color.colorPrimaryDark);
        mColors.add(R.color.light);
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

        final Person entry = mListContact.get(pos);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.staggered_view, null);
        }
        final DynamicHeightImageView imgView = (DynamicHeightImageView) convertView.findViewById(R.id.dyImgView);

        double positionHeight = getPositionRatio(pos);
        imgView.setHeightRatio(positionHeight);
        Picasso.with(convertView.getContext())
                .load(entry.getAvatarURL())
                .placeholder(R.drawable.user) // optional
                .error(R.drawable.circle)// optional
                .into(imgView);
        final String name = entry.getFirstName() + " " + entry.getLastName();
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNameGridView);
        tvName.setText(name);
        imgView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                LoadPhoto((ImageView) imgView, name,v.getWidth(), v.getHeight());
            }
        });
        mListView.add(convertView);
        return convertView;
    }

    public List<View> getViewList() {
        return mListView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
    }

    private void LoadPhoto(ImageView imageView, String name, int width, int height) {

        ImageView tempImageView = imageView;
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.dialog_image_view, null);
        TextView tvName = (TextView) layout.findViewById(R.id.dialog_image_text);
        ImageView image = (ImageView) layout.findViewById(R.id.dialog_imageview);
        image.setImageDrawable(tempImageView.getDrawable());
        tvName.setText(name.toUpperCase());
        image.setMinimumHeight(height);
        image.setMinimumWidth(width);
        imageDialog.setView(layout);
        imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        imageDialog.create();
        imageDialog.show();
    }
}
