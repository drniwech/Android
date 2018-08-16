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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnBasicSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
/*
        final EditText editTextMin = (EditText) findViewById(R.id.editTextMin);
        final String minValue = editTextMin.getText().toString();
        final EditText editTextMax = (EditText) findViewById(R.id.editTextMax);
        final String maxValue = editTextMax.getText().toString();

        btnBasicSearch = (Button)findViewById(R.id.btnBasicSearch);
        btnBasicSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listActivity = new Intent(MainActivity.this, ItemListActivity.class);
                listActivity.putExtra("minValue", minValue);
                listActivity.putExtra("maxValue", maxValue);
                startActivity(listActivity);
            }
        });*/

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
        /*if (id == R.id.action_settings) {
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
            Intent myIntent = new Intent(this,NameSearchActivity.class);
            //myIntent.putExtra("name", "Niwech!");
            startActivity(myIntent);

        } else if (id == R.id.nav_search_by_budget) {
            //startActivity(this.getIntent());
        } else if (id == R.id.nav_disclosure) {
            Intent disclosureIntent = new Intent(this, DisclosureDetailActivity.class);
            startActivity(disclosureIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void basicSearch(View v){
        final EditText editTextMin = (EditText) findViewById(R.id.editTextMin);
        final String minValue = editTextMin.getText().toString();
        final EditText editTextMax = (EditText) findViewById(R.id.editTextMax);
        final String maxValue = editTextMax.getText().toString();
        if(null!=minValue && !minValue.isEmpty() && null!=maxValue && !maxValue.isEmpty()){
            Intent listActivity = new Intent(this, ItemListActivity.class);
            listActivity.putExtra("minValue", minValue);
            listActivity.putExtra("maxValue", maxValue);
            startActivity(listActivity);
        }else {
            Toast msg = Toast.makeText(getBaseContext(), "Please enter Min, Max value!",
                    Toast.LENGTH_LONG);
            msg.show();
        }
    }
}
