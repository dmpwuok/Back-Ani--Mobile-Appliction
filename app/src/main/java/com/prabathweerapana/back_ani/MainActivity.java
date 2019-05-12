package com.prabathweerapana.back_ani;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.prabathweerapana.back_ani.adapters.ViewPagerAdapter;
import com.prabathweerapana.back_ani.fragments.FragmentCalls;
import com.prabathweerapana.back_ani.fragments.FragmentContacts;
import com.prabathweerapana.back_ani.fragments.FragmentFav;

public class MainActivity extends AppCompatActivity {


    private Button btn;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private final int[] ICONS = {R.drawable.ic_call, R.drawable.ic_contacts, R.drawable.ic_star};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fab= findViewById(R.id.keypad);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentCalls(), "Calls");
        adapter.addFragment(new FragmentContacts(), "Contacts");
        adapter.addFragment(new FragmentFav(), "Fav");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            assert tab != null;
            tab.setIcon(ICONS[i]);
        }

        askPermission();


    /*TO RECEIVE CALL*/

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_PHONE_STATE)){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

            }else
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                }

        }else
            {
                //we
            }

        btn=findViewById(R.id.keypad);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToTheme();
            }
        });

    }

    private void moveToTheme()
    {
        Intent intent= new Intent(MainActivity.this,RotaryDialerActivity.class);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        switch (requestCode)
        {
            case 1:{

                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(this,"No permission granted!",Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }
    }

    private void askPermission()
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, 1);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        }
    }
}
