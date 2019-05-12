package com.prabathweerapana.back_ani.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prabathweerapana.back_ani.R;
import com.prabathweerapana.back_ani.adapters.ContactsRvAdapter;
import com.prabathweerapana.back_ani.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FragmentContacts extends Fragment {

    private View v;

    private RecyclerView recyclerView;

    public FragmentContacts() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.frag_contacts,container,false);

        recyclerView = v.findViewById(R.id.rv_contacts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView.LayoutManager layoutManager = linearLayoutManager;


        recyclerView.setLayoutManager(layoutManager);

        ContactsRvAdapter adapter = new ContactsRvAdapter(getContext(),getContacts());

        getContacts();
        recyclerView.setAdapter(adapter);
        return v;
    }

    private List<ModelContacts>getContacts()
    {
        List<ModelContacts> list = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }


        Cursor cursor = Objects.requireNonNull(getContext()).getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,ContactsContract.Contacts.DISPLAY_NAME +" ASC");

        assert cursor != null;
        cursor.moveToFirst();

        while (cursor.moveToNext())
        {
            list.add(new ModelContacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)), cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));
        }
        return list;
    }


}
