package com.renault.healthgraph;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class HeartRatePrototypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChartDay = (BarChart) findViewById(R.id.day);
        BarChart barChartMonth = (BarChart) findViewById(R.id.month);
        BarChart barChartYear = (BarChart) findViewById(R.id.year);

        BarChartBasicSettings(barChartDay);
        BarChartBasicSettings(barChartMonth);
        BarChartBasicSettings(barChartYear);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(92f, 0));
        entries.add(new BarEntry(99f, 1));
        entries.add(new BarEntry(93f, 2));
        entries.add(new BarEntry(90f, 3));
        entries.add(new BarEntry(92f, 4));
        entries.add(new BarEntry(97f, 5));
        entries.add(new BarEntry(90f, 6));
        entries.add(new BarEntry(98f, 7));
        entries.add(new BarEntry(96f, 8));
        entries.add(new BarEntry(95f, 9));
        entries.add(new BarEntry(99f, 10));
        entries.add(new BarEntry(93f, 11));
        entries.add(new BarEntry(95f, 12));
        entries.add(new BarEntry(98f, 13));
        entries.add(new BarEntry(96f, 14));
        entries.add(new BarEntry(95f, 15));
        entries.add(new BarEntry(99f, 16));
        entries.add(new BarEntry(99f, 17));
        entries.add(new BarEntry(94f, 18));
        entries.add(new BarEntry(98f, 19));
        entries.add(new BarEntry(96f, 20));
        entries.add(new BarEntry(92f, 21));
        entries.add(new BarEntry(98f, 22));
        entries.add(new BarEntry(99f, 23));

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1AM");
        labels.add("2AM");
        labels.add("3AM");
        labels.add("4AM");
        labels.add("5PM");
        labels.add("6AM");
        labels.add("7AM");
        labels.add("8AM");
        labels.add("9AM");
        labels.add("10AM");
        labels.add("11AM");
        labels.add("12AM");
        labels.add("1PM");
        labels.add("2PM");
        labels.add("3PM");
        labels.add("4PM");
        labels.add("5PM");
        labels.add("6PM");
        labels.add("7PM");
        labels.add("8PM");
        labels.add("9PM");
        labels.add("10PM");
        labels.add("11PM");
        labels.add("12PM");


        ArrayList<String> yearLabels = new ArrayList<String>();
        yearLabels.add("January");
        yearLabels.add("Feb");
        yearLabels.add("March");
        yearLabels.add("April");
        yearLabels.add("May");
        yearLabels.add("June");
        yearLabels.add("July");
        yearLabels.add("Augest");
        yearLabels.add("September");
        yearLabels.add("October");
        yearLabels.add("November");
        yearLabels.add("December");

        ArrayList<String> monthLabels = new ArrayList<String>();
        monthLabels.add("Oct 29");
        monthLabels.add("Oct 30 ");
        monthLabels.add("Oct 31");
        monthLabels.add("Nov 1");
        monthLabels.add("Nov 2");
        monthLabels.add("Nov 3");
        monthLabels.add("Nov 4");
        monthLabels.add("Nov 5");
        monthLabels.add("Nov 6");
        monthLabels.add("Nov 7");
        monthLabels.add("Nov 8");
        monthLabels.add("Nov 9");
        monthLabels.add("Nov 10");
        monthLabels.add("Nov 11");
        monthLabels.add("Nov 12");
        monthLabels.add("Nov 13");
        monthLabels.add("Nov 14");
        monthLabels.add("Nov 15");
        monthLabels.add("Nov 16");
        monthLabels.add("Nov 17");

        ArrayList<BarEntry> entriesMonth = new ArrayList<>();
        entriesMonth.add(new BarEntry(92f, 0));
        entriesMonth.add(new BarEntry(92f, 1));
        entriesMonth.add(new BarEntry(93f, 2));
        entriesMonth.add(new BarEntry(91f, 3));
        entriesMonth.add(new BarEntry(96f, 4));
        entriesMonth.add(new BarEntry(97f, 5));
        entriesMonth.add(new BarEntry(93f, 6));
        entriesMonth.add(new BarEntry(97f, 7));
        entriesMonth.add(new BarEntry(99f, 8));
        entriesMonth.add(new BarEntry(94f, 9));
        entriesMonth.add(new BarEntry(93f, 10));
        entriesMonth.add(new BarEntry(91f, 11));
        entriesMonth.add(new BarEntry(90f, 12));
        entriesMonth.add(new BarEntry(88f, 13));
        entriesMonth.add(new BarEntry(55f, 14));
        entriesMonth.add(new BarEntry(85f, 15));
        entriesMonth.add(new BarEntry(90f, 16));
        entriesMonth.add(new BarEntry(95f, 17));
        entriesMonth.add(new BarEntry(54f, 18));
        entriesMonth.add(new BarEntry(44f, 19));


        ArrayList<BarEntry> entriesYear = new ArrayList<>();
        entriesYear.add(new BarEntry(92f, 0));
        entriesYear.add(new BarEntry(99f, 1));
        entriesYear.add(new BarEntry(92f, 2));
        entriesYear.add(new BarEntry(99f, 3));
        entriesYear.add(new BarEntry(92f, 4));
        entriesYear.add(new BarEntry(99f, 5));
        entriesYear.add(new BarEntry(92f, 6));
        entriesYear.add(new BarEntry(99f, 7));
        entriesYear.add(new BarEntry(92f, 8));
        entriesYear.add(new BarEntry(99f, 9));
        entriesYear.add(new BarEntry(92f, 10));
        entriesYear.add(new BarEntry(99f, 11));


        BarDataSet dataset = new BarDataSet(entries, "Time");
        dataset.setColor(Color.rgb(0, 155, 0));
        dataset.setBarSpacePercent(-1f);
        BarDataSet datasetMonth = new BarDataSet(entriesMonth, "Month");
        BarDataSet datasetYear = new BarDataSet(entriesYear, "Year");

        BarData data = new BarData(labels, dataset);
        dataset.setColors(ColorTemplate.LIBERTY_COLORS);
        barChartDay.setData(data);
        barChartDay.animateY(5000);


        BarData dataMonth = new BarData(monthLabels, datasetMonth);
        datasetMonth.setColors(ColorTemplate.LIBERTY_COLORS);
        barChartMonth.setData(dataMonth);
        barChartMonth.animateY(5000);

        BarData dataYear = new BarData(yearLabels, datasetYear);
        datasetYear.setColors(ColorTemplate.LIBERTY_COLORS);
        barChartYear.setData(dataYear);
        barChartYear.animateY(5000);


    }


    private void BarChartBasicSettings(BarChart barChart) {
        barChart.setDescription("");
        barChart.setPinchZoom(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(true);
        barChart.setTouchEnabled(true);
        // barChart.getLegend().setEnabled(false);
        barChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);

        barChart.animateXY(1000, 1000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(false);

        YAxis Axis1 = barChart.getAxisLeft();
        Axis1.setAxisMaxValue(100f);
        Axis1.setDrawGridLines(true);
        Axis1.setDrawAxisLine(true);

        YAxis Axis2 = barChart.getAxisRight();
        Axis2.setAxisMaxValue(100f);
        Axis2.setDrawGridLines(true);
        Axis2.setDrawAxisLine(true);
        Axis2.setDrawLabels(false);
        barChart.invalidate();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
    }
}