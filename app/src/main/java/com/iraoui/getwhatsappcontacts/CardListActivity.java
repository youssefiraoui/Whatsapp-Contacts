package com.iraoui.getwhatsappcontacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CardListActivity extends Activity {

    private ListView listView;
    GetContacts getContacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView = (ListView) findViewById(R.id.card_listView);
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

