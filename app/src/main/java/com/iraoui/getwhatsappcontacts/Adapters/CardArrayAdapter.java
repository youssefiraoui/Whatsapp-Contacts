package com.iraoui.getwhatsappcontacts.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.iraoui.getwhatsappcontacts.R;
import com.iraoui.getwhatsappcontacts.entities.WhtasppNumber;

import java.util.ArrayList;

/**
 * Created by IRAOUI on 29/10/2018.
 */

public class CardArrayAdapter  extends ArrayAdapter<WhtasppNumber> {

    private static final String TAG = "CardArrayAdapter";

    private ArrayList<WhtasppNumber> cardList;
    private ArrayList<WhtasppNumber> mOriginalValues;

    private LayoutInflater layoutinflater;

    static class CardViewHolder
    {
        TextView line1;
        TextView line2;
        ImageView imageView;
    }

    public CardArrayAdapter(Context context, ArrayList<WhtasppNumber> whtasppNumbers)
    {
        super(context, 0,whtasppNumbers);
        cardList = whtasppNumbers;
    }

    @Override
    public void add(WhtasppNumber object)
    {
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        CardViewHolder viewHolder;

        if (row == null)
        {
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
        TextDrawable drawable= null;
        String nameX="A", nameY = "B";
        String[] words = card.getName().split(" ");
        Bitmap pic = card.getPicture();
        if (pic != null){
            viewHolder.imageView.setImageBitmap(pic);

        }else {
            if (words.length == 1)
            {
                drawable = TextDrawable.builder()
                        .buildRect(card.getName().substring(0, 1).toUpperCase(),
                                Color.rgb(232,180,231));

            }
            else if (words.length == 2)
            {
                nameX = words[0];
                nameY = words[1];
                drawable = TextDrawable.builder()
                        .buildRect(nameX.substring(0, 1).toUpperCase() + " "+ nameY.substring(0, 1).toUpperCase() ,
                                Color.rgb(232,180,231));

            }else
            {
                drawable = TextDrawable.builder()
                        .buildRect(card.getName().substring(0, 2).toUpperCase() ,
                                Color.rgb(232,180,231));

            }

            viewHolder.imageView.setImageDrawable(drawable);

        }

        viewHolder.line1.setText(card.getName());
        viewHolder.line2.setText(card.getNumber());

        return row;
    }

}

