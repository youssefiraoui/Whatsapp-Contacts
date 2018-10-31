package com.iraoui.getwhatsappcontacts;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CardListActivity extends Activity {

    private ListView listView;
    GetContacts getContacts;
    TextView nameStyle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView = (ListView) findViewById(R.id.card_listView);

        /*Apply the style open Sans*/
        nameStyle = (TextView) findViewById(R.id.line1);
        /*Typeface face = Typeface.createFromAsset(getAssets(),"fonts/opansans.ttf");
        nameStyle.setTypeface(face);*/

        getContacts = new GetContacts(this);


        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));


        ShowMyWhatsappContacts();

    }
// call function and adapt on listView
    public void ShowMyWhatsappContacts(){
        CardArrayAdapter contacts = getContacts.getContacts();
        listView.setAdapter(contacts);
    }
}

