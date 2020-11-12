package fr.eni.tp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.adapter.ContactAdapter;
import fr.eni.tp.databinding.ActivityRecyclerViewBinding;
import fr.eni.tp.entities.Article;
import fr.eni.tp.entities.Contact;

public class ListContactsActivity extends AppCompatActivity {
    List<Contact> contacts = new ArrayList<>();
    Article article;
    ActivityRecyclerViewBinding layout;
    RecyclerView recyclerView;
    ContactAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    static String[] permissions = new String[] {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS
    };
    View.OnClickListener onClickV = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            int position = Integer.parseInt(view.getTag().toString());
            Contact contact = contacts.get(position);
            sendSMS(contact);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        recyclerView = layout.getRoot();
        setContentView(recyclerView);
        article = getIntent().getParcelableExtra("article");
        if (!hasPermissions(this)) { ActivityCompat.requestPermissions(this, permissions, 1000); }
        else { loadContacts(); }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerView();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1000 && hasPermissions(this)) { loadContacts(); }
//    }

    public static boolean hasPermissions(Context context) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void loadContacts() {
        Cursor contactsCursor = getContentResolver()
                .query(Contacts.CONTENT_URI, null, null, null, Contacts.DISPLAY_NAME);
        while (contactsCursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(Long.parseLong(contactsCursor.getString(contactsCursor.getColumnIndex(Contacts._ID))));
            contact.setName(contactsCursor.getString(contactsCursor.getColumnIndex(Contacts.DISPLAY_NAME)));
            Cursor  phonesCursor = getContentResolver()
                    .query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=" + contact.getId(), null, null);
            if (phonesCursor.moveToNext()) {
                contact.setPhone(phonesCursor.getString(phonesCursor.getColumnIndex(Phone.NUMBER)));
            }
            contacts.add(contact);
            phonesCursor.close();
        }
        contactsCursor.close();
    }

    protected void loadRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactAdapter(contacts, onClickV);
        recyclerView.setAdapter(adapter);
    }

    protected void sendSMS(Contact contact) {
        SmsManager.getDefault().sendTextMessage(
                        "+33769982390",
                        null,
                        "Voici un cadeau qui me ferait plaisir: " + article.getName(),
                        null,
                        null
                );
        Toast.makeText(this, "SMS Envoyé", Toast.LENGTH_SHORT).show();
    }
}