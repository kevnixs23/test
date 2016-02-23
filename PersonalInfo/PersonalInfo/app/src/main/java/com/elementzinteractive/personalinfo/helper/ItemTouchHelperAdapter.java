package com.elementzinteractive.personalinfo.helper;

/**
 * Created by Kevnixs on 2/22/2016.
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}