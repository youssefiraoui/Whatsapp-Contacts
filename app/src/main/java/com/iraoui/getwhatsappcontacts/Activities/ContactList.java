package com.iraoui.getwhatsappcontacts.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.iraoui.getwhatsappcontacts.Adapters.CardArrayAdapter;
import com.iraoui.getwhatsappcontacts.Controllers.GetContacts;
import com.iraoui.getwhatsappcontacts.R;
import com.iraoui.getwhatsappcontacts.entities.WhtasppNumber;

import java.util.ArrayList;


public class ContactList extends AppCompatActivity {

    private ListView listView;
    GetContacts getContacts;
    TextView nameStyle;
    ArrayList<WhtasppNumber> whtasppNumbers;
    SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        settings = getSharedPreferences("mySharedPref", 0);

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
     public void ShowMyWhatsappContacts() {

        whtasppNumbers = getContacts.getContacts();
        CardArrayAdapter contacts = new CardArrayAdapter(this, whtasppNumbers);
        listView.setAdapter(contacts);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ContactList.this, whtasppNumbers.get(i).getName(), Toast.LENGTH_SHORT).show();
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
               listView.setAdapter(new CardArrayAdapter(ContactList.this, whtasppNumberArrayList));
               return true;
           }

       });
        MenuItem deconnexion = menu.findItem(R.id.deconnexion);
        deconnexion.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("connected", false);
                editor.commit();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
