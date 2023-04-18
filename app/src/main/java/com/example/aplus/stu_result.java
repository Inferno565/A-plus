package com.example.aplus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;


public class stu_result extends Fragment {

    private BarChart barChart;
    private TextView uname,em;

    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        View view = inflater.inflate(R.layout.fragment_stu_result, container, false);
         barChart = (BarChart) view.findViewById(R.id.barChart);
       uname = (TextView) view.findViewById(R.id.t1);
         em = (TextView) view.findViewById(R.id.t2);
        Bundle bundle =getArguments();
        String username=bundle.getString("message_key1");
        em.setText(username);
        openHelper = new DatabaseHelper(getContext());
        sqLiteDatabase = openHelper.getReadableDatabase();

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(getContext(), R.color.peach));
        colors.add(ContextCompat.getColor(getContext(), R.color.blue));
        colors.add(ContextCompat.getColor(getContext(), R.color.yellow));
        colors.add(ContextCompat.getColor(getContext(), R.color.green));
        Cursor abc1 = sqLiteDatabase.rawQuery("Select FirstName from Student where Email='" + username + "'", null);
        while (abc1.moveToNext()) {
            uname.setText(abc1.getString(0));
        }
                queryXData();
                queryYData();
                final ArrayList<BarEntry> yvalue = new ArrayList<>();
                final ArrayList<String> ydata = queryYData();

                for (int i = 0; i < queryYData().size(); i++) {

                    BarEntry newBarEntry = new BarEntry(i, Float.parseFloat(queryYData().get(i)));
                    yvalue.add(newBarEntry);
                }
                final ArrayList<String> xvalue = new ArrayList<>();
                final ArrayList<String> xdata = queryXData();
                for (int i = 0; i < queryXData().size(); i++) {
                    xvalue.add(xdata.get(i));
                }
                BarDataSet dataSet = new BarDataSet(yvalue, "Subject");

                dataSet.setColors(colors);
                ArrayList<IBarDataSet> dataSets1 = new ArrayList<>();
                dataSets1.add(dataSet);
                BarData data = new BarData(dataSets1);
                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xvalue));
                barChart.setData(data);
                XAxis xAxis = barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawLabels(true);
                xAxis.isCenterAxisLabelsEnabled();
                xAxis.setGranularityEnabled(true);
                YAxis rightAxis = barChart.getAxisRight();
                rightAxis.setEnabled(false);
                barChart.setMaxVisibleValueCount(5);
                barChart.setFitBars(true);

        return view;
    }
    public ArrayList<String> queryXData () {
        sqLiteDatabase = openHelper.getWritableDatabase();
        ArrayList<String> XData = new ArrayList<String>();
        Cursor abc = sqLiteDatabase.rawQuery("Select Subject from Response where Username='" + uname.getText().toString() + "' GROUP BY Subject", null);
        for (abc.moveToFirst(); !abc.isAfterLast(); abc.moveToNext()) {
            XData.add(abc.getString(0));
        }
        //abc.close();
        return XData;
    }

    public ArrayList<String> queryYData () {
        sqLiteDatabase = openHelper.getWritableDatabase();
        ArrayList<String> YData = new ArrayList<String>();
        Cursor abc = sqLiteDatabase.rawQuery("Select SUM (Score) from Response where Score IS NOT NULL and Username='" + uname.getText().toString() + "'GROUP BY Subject", null);

        for (abc.moveToFirst(); !abc.isAfterLast(); abc.moveToNext()) {
            YData.add(abc.getString(0));
        }
        //abc.close();
        return YData;
    }

    // return rootview;

}
