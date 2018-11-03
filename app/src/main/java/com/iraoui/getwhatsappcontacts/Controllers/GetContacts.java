package com.iraoui.getwhatsappcontacts.Controllers;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import com.iraoui.getwhatsappcontacts.Adapters.CardArrayAdapter;
import com.iraoui.getwhatsappcontacts.entities.WhtasppNumber;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by IRAOUI on 29/10/2018.
 */

public class GetContacts {

    private Context context;
    private CardArrayAdapter cardArrayAdapter;
    ArrayList<WhtasppNumber> whtasppNumbers ;

    public GetContacts(Context context)
    {
        this.context = context;
       // cardArrayAdapter = new CardArrayAdapter(this.context, R.layout.list_item_card,);
        whtasppNumbers = new ArrayList<>();
    }

    public ArrayList<WhtasppNumber> getContacts()
    {
        //This class provides applications access to the content model.
        ContentResolver cr = context.getContentResolver();

        //RowContacts for filter Account Types
        Cursor contactCursor = cr.query(
                ContactsContract.RawContacts.CONTENT_URI,
                new String[]{ContactsContract.RawContacts._ID,
                        ContactsContract.RawContacts.CONTACT_ID},
                ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
                new String[]{"com.whatsapp"},
                null);
        //ArrayList for Store Whatsapp Contact

        if (contactCursor != null) {
            if (contactCursor.getCount() > 0) {
                if (contactCursor.moveToFirst()) {
                    do {
                        //whatsappContactId for get Number,Name,Id ect... from  ContactsContract.CommonDataKinds.Phone
                        String whatsappContactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID));

                        if (whatsappContactId != null) {
                            //Get Data from ContactsContract.CommonDataKinds.Phone of Specific CONTACT_ID
                            Cursor whatsAppContactCursor = cr.query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    new String[]{ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                    new String[]{whatsappContactId}, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME +" ASC");

                            if (whatsAppContactCursor != null)
                            {
                                whatsAppContactCursor.moveToFirst();
                                String id = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                                String name = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                                String number = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                // trying to get whatsapp's pdp
                                Uri photoId = ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, Long.parseLong(whatsappContactId));
                                Uri photoUri = Uri.withAppendedPath(photoId, ContactsContract.RawContacts.DisplayPhoto.CONTENT_DIRECTORY);
                                Bitmap bitmap = getDisplayPhotoBitmap(Long.parseLong(whatsappContactId));
                                whatsAppContactCursor.close();

                                //Add Number to CardArrayAdapter
                                WhtasppNumber whtasppNumber = new WhtasppNumber( name , number, bitmap) ;

                                whtasppNumbers.add(whtasppNumber);

                                System.out.println("----------------------------------------------------");
                                System.out.println(" WhatsApp contact id  :  " + id);
                                System.out.println(" WhatsApp contact name :  " + name);
                                System.out.println(" WhatsApp contact number :  " + number);
                                System.out.println(" WhatsApp contact photo link :  " + photoUri);
                                System.out.println("----------------------------------------------------");
                            }
                        }
                    } while (contactCursor.moveToNext());
                    contactCursor.close();
                }
            }
        }

            Collections.sort(whtasppNumbers, new Comparator<WhtasppNumber>() {
                @Override
                public int compare(WhtasppNumber lhs, WhtasppNumber rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });
        return whtasppNumbers;
    }

    public Bitmap getDisplayPhotoBitmap(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        InputStream photoInputStream =
                ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), contactUri);
        Bitmap bitmap = BitmapFactory.decodeStream(photoInputStream);
        if (bitmap != null) {
            return bitmap;
        }
        return null;
    }
}
