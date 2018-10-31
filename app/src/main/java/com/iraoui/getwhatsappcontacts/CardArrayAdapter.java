package com.iraoui.getwhatsappcontacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IRAOUI on 29/10/2018.
 */

public class CardArrayAdapter  extends ArrayAdapter<WhtasppNumber> {
    private static final String TAG = "CardArrayAdapter";
    private List<WhtasppNumber> cardList = new ArrayList<>();

    static class CardViewHolder {
        TextView line1;
        TextView line2;
        ImageView imageView;
    }

    public CardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(WhtasppNumber object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public WhtasppNumber getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_card, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.line1 = (TextView) row.findViewById(R.id.line1);
            viewHolder.line2 = (TextView) row.findViewById(R.id.line2);
            viewHolder.imageView = (ImageView) row.findViewById(R.id.imgUser);

            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }

        WhtasppNumber card = getItem(position);
        /*
        Showing contact name with initial letter in circle
         */
        TextDrawable drawable = TextDrawable.builder()
                .buildRect(card.getName().substring(0, 1).toUpperCase(), Color.rgb(232,180,231));

        viewHolder.imageView.setImageDrawable(drawable);
        viewHolder.line1.setText(card.getName());
        viewHolder.line2.setText(card.getNumber());

        return row;
    }

}

