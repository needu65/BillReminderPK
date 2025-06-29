package com.example.billreminderpk.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.billreminderpk.R;
import com.example.billreminderpk.models.Bill;
import com.example.billreminderpk.utils.SharedPrefsHelper;
import java.util.Calendar;
import java.util.Date;

public class AddBillActivity extends AppCompatActivity {
    private Spinner billTypeSpinner, companySpinner;
    private EditText accountNumberEditText, amountEditText, dueDateEditText;
    private Button saveButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        initializeViews();
        setupSpinners();
        setupDatePicker();
        setupSaveButton();
    }

    private void initializeViews() {
        billTypeSpinner = findViewById(R.id.billTypeSpinner);
        companySpinner = findViewById(R.id.companySpinner);
        accountNumberEditText = findViewById(R.id.accountNumberEditText);
        amountEditText = findViewById(R.id.amountEditText);
        dueDateEditText = findViewById(R.id.dueDateEditText);
        saveButton = findViewById(R.id.saveButton);
    }

    private void setupSpinners() {
        // Bill type spinner
        ArrayAdapter<CharSequence> billTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.bill_types, android.R.layout.simple_spinner_item);
        billTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        billTypeSpinner.setAdapter(billTypeAdapter);
    }

    private void setupDatePicker() {
        dueDateEditText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year1, month1, dayOfMonth) -> {
                        String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                        dueDateEditText.setText(date);
                    }, year, month, day);
            datePickerDialog.show();
        });
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(v -> {
            String billType = billTypeSpinner.getSelectedItem().toString();
            String company = companySpinner.getSelectedItem().toString();
            String accountNumber = accountNumberEditText.getText().toString().trim();
            String amountStr = amountEditText.getText().toString().trim();
            String dueDateStr = dueDateEditText.getText().toString().trim();

            if (validateInputs(billType, company, accountNumber, amountStr, dueDateStr)) {
                double amount = Double.parseDouble(amountStr);
                // Parse due date (simplified for example)
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, 3); // Default to 3 days from now for demo
                
                Bill bill = new Bill(billType, company, accountNumber, 
                        calendar.getTime(), amount, SharedPrefsHelper.getCurrentUserId(this));
                
                SharedPrefsHelper.saveBill(this, bill);
                Toast.makeText(this, R.string.bill_saved_successfully, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private boolean validateInputs(String billType, String company, String accountNumber, 
                                 String amount, String dueDate) {
        if (accountNumber.isEmpty()) {
            accountNumberEditText.setError(getString(R.string.error_account_number));
            return false;
        }
        if (amount.isEmpty()) {
            amountEditText.setError(getString(R.string.error_amount));
            return false;
        }
        if (dueDate.isEmpty()) {
            dueDateEditText.setError(getString(R.string.error_due_date));
            return false;
        }
        return true;
    }
}