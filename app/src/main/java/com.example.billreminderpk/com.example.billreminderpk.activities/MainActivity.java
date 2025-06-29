package com.example.billreminderpk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.billreminderpk.R;
import com.example.billreminderpk.utils.SharedPrefsHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView welcomeText;
    private CardView addBillCard, viewBillsCard, upcomingBillsCard, settingsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        welcomeText = findViewById(R.id.welcomeText);
        addBillCard = findViewById(R.id.addBillCard);
        viewBillsCard = findViewById(R.id.viewBillsCard);
        upcomingBillsCard = findViewById(R.id.upcomingBillsCard);
        settingsCard = findViewById(R.id.settingsCard);

        // Set click listeners
        addBillCard.setOnClickListener(this);
        viewBillsCard.setOnClickListener(this);
        upcomingBillsCard.setOnClickListener(this);
        settingsCard.setOnClickListener(this);

        // Set welcome message with user's email
        String userEmail = SharedPrefsHelper.getCurrentUserId(this);
        if (userEmail != null) {
            welcomeText.setText(getString(R.string.welcome_user, userEmail));
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        
        if (id == R.id.addBillCard) {
            startActivity(new Intent(this, AddBillActivity.class));
        } else if (id == R.id.viewBillsCard) {
            startActivity(new Intent(this, BillListActivity.class));
        } else if (id == R.id.upcomingBillsCard) {
            Intent intent = new Intent(this, BillListActivity.class);
            intent.putExtra("SHOW_UPCOMING", true);
            startActivity(intent);
        } else if (id == R.id.settingsCard) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_logout) {
            SharedPrefsHelper.setLoggedIn(this, false, null);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            re