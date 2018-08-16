package org.ekspertsoft.zakool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Niwech Harnkham on 6/13/16.
 * Copyright EKSPERTSOFT, LLC. All rights reserved.
 */
public class NameSearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.content_name_search);
        setContentView(R.layout.activity_name_search_main);

        // receive the arguments from the previous Activity
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search_by_name) {
            // Handle the search by name action
            //startActivity(this.getIntent());


        } else if (id == R.id.nav_search_by_budget) {
            Intent myIntent = new Intent(this,MainActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_disclosure) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void searchByName(View v){
        final EditText editTextName = (EditText) findViewById(R.id.editTextName);
        final String text = editTextName.getText().toString();
        if(null!=text && !text.isEmpty()){
            Intent listActivity = new Intent(NameSearchActivity.this, ItemListActivity.class);
            listActivity.putExtra("name", editTextName.getText().toString());
            startActivity(listActivity);
        }else {
            Toast msg = Toast.makeText(getBaseContext(), "Please enter institution's name",
                    Toast.LENGTH_LONG);
            msg.show();
        }
    }

}
