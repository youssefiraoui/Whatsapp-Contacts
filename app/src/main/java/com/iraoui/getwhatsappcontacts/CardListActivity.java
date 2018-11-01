package com.iraoui.getwhatsappcontacts;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CardListActivity extends Activity {

    private ListView listView;
    GetContacts getContacts;
    TextView nameStyle, count;
    CardArrayAdapter contacts;
    EditText searchField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView = (ListView) findViewById(R.id.card_listView);
        searchField = (EditText) findViewById(R.id.searchFiled);
        count = findViewById(R.id.countId);
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
    public void ShowMyWhatsappContacts() {
        ArrayList<WhtasppNumber> whtasppNumbers = getContacts.getContacts();
        CardArrayAdapter contacts = new CardArrayAdapter(this, whtasppNumbers);
        listView.setAdapter(contacts);
        count.setText(String.valueOf(contacts.getCount()));
        //TODO
//         if(contacts != null)
//         {
//             searchField.addTextChangedListener(new TextWatcher() {
//                 @Override
//                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                 }
//
//                 @Override
//                 public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                     (CardListActivity.this).contacts.getFilter().filter(charSequence);
//
//
//                 }
//
//                 @Override
//                 public void afterTextChanged(Editable editable) {
//
//                 }
//             });
        System.out.println("--------Nombre :" + contacts.getCount());

    }
}
