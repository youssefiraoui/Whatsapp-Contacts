package com.iraoui.getwhatsappcontacts.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.iraoui.getwhatsappcontacts.Adapters.CardArrayAdapter;
import com.iraoui.getwhatsappcontacts.Controllers.GetContacts;
import com.iraoui.getwhatsappcontacts.R;
import com.iraoui.getwhatsappcontacts.entities.WhtasppNumber;

import java.util.ArrayList;


public class CardListActivity extends AppCompatActivity {

    private ListView listView;
    GetContacts getContacts;
    TextView nameStyle;
    ArrayList<WhtasppNumber> whtasppNumbers;

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


        /*
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            count.setText(bundle.getString("user"));
        }*/


    }

    // call function and adapt on listView
    public void ShowMyWhatsappContacts() {

        whtasppNumbers = getContacts.getContacts();
        CardArrayAdapter contacts = new CardArrayAdapter(this, whtasppNumbers);
        listView.setAdapter(contacts);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CardListActivity.this, whtasppNumbers.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });

            System.out.println("--------Nombre :" + contacts.getCount());

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem) ;

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s)
           {
               ArrayList<WhtasppNumber> whtasppNumberArrayList = new ArrayList<>();

               for( WhtasppNumber temp : whtasppNumbers)
               {
                   if (temp.getName().toLowerCase().contains(s.toLowerCase()))
                   {
                       whtasppNumberArrayList.add(temp);
                   }
               }
               listView.setAdapter(new CardArrayAdapter(CardListActivity.this, whtasppNumberArrayList));
               return true;
           }

       });
        return super.onCreateOptionsMenu(menu);
    }
}
