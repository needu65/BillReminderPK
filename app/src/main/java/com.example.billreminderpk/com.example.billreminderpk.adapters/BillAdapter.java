package com.example.billreminderpk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.billreminderpk.R;
import com.example.billreminderpk.models.Bill;
import com.example.billreminderpk.utils.AppConstants;
import com.example.billreminderpk.utils.DateUtils;

import java.util.List;

public class BillAdapter extends ArrayAdapter<Bill> {

    public BillAdapter(@NonNull Context context, List<Bill> bills) {
        super(context, 0, bills);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_bill, parent, false);
        }

        Bill bill = getItem(position);
        if (bill != null) {
            // Initialize views
            ImageView billIcon = convertView.findViewById(R.id.billIcon);
            TextView billType = convertView.findViewById(R.id.billTypeText);
            TextView company = convertView.findViewById(R.id.companyText);
            TextView dueDate = convertView.findViewById(R.id.dueDateText);
            TextView amount = convertView.findViewById(R.id.amountText);
            TextView daysLeft = convertView.findViewById(R.id.daysLeftText);

            // Set bill type icon
            setBillIcon(billIcon, bill.getBillType());

            // Set bill details
            billType.setText(getLocalizedBillType(bill.getBillType()));
            company.setText(bill.getCompany());
            dueDate.setText(DateUtils.formatDate(bill.getDueDate(), AppConstants.DATE_FORMAT_DISPLAY));
            amount.setText(String.format(getContext().getString(R.string.amount_format), bill.getAmount()));

            // Calculate and display days left
            int days = DateUtils.daysBetweenTodayAnd(bill.getDueDate());
            updateDaysLeftView(daysLeft, days);
        }

        return convertView;
    }

    private void setBillIcon(ImageView imageView, String billType) {
        int iconResId;
        switch (billType) {
            case AppConstants.BILL_TYPE_ELECTRICITY:
                iconResId = R.drawable.ic_electricity;
                break;
            case AppConstants.BILL_TYPE_GAS:
                iconResId = R.drawable.ic_gas;
                break;
            case AppConstants.BILL_TYPE_WATER:
                iconResId = R.drawable.ic_water;
                break;
            case AppConstants.BILL_TYPE_LANDLINE:
                iconResId = R.drawable.ic_phone;
                break;
            case AppConstants.BILL_TYPE_MOBILE:
                iconResId = R.drawable.ic_mobile;
                break;
            default:
                iconResId = R.drawable.ic_bill;
        }
        imageView.setImageResource(iconResId);
    }

    private String getLocalizedBillType(String billType) {
        Context context = getContext();
        switch (billType) {
            case AppConstants.BILL_TYPE_ELECTRICITY:
                return context.getString(R.string.electricity);
            case AppConstants.BILL_TYPE_GAS:
                return context.getString(R.string.gas);
            case AppConstants.BILL_TYPE_WATER:
                return context.getString(R.string.water);
            case AppConstants.BILL_TYPE_LANDLINE:
                return context.getString(R.string.landline);
            case AppConstants.BILL_TYPE_MOBILE:
                return context.getString(R.string.mobile);
            default:
                return billType;
        }
    }

    private void updateDaysLeftView(TextView daysLeftView, int days) {
        Context context = getContext();
        if (days < 0) {
            daysLeftView.setText(R.string.overdue);
            daysLeftView.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            daysLeftView.setText(context.getResources().getQuantityString(
                    R.plurals.days_left, days, days));
            daysLeftView.setTextColor(ContextCompat.getColor(context, 
                    days <= AppConstants.DAYS_BEFORE_REMINDER ? R.color.orange : R.color.green));
        }
    }
}