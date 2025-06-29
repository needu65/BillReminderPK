package com.example.billreminderpk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.billreminderpk.R;
import com.example.billreminderpk.adapters.BillAdapter;
import com.example.billreminderpk.models.Bill;
import com.example.billreminderpk.utils.SharedPrefsHelper;
import com.google.an