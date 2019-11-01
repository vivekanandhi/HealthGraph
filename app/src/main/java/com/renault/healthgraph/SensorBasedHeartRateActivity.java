package com.renault.healthgraph;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.renault.mqtthelper.MqttHelper;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SensorBasedHeartRateActivity extends ActionBarActivity {

    private MqttHelper mqttHelper;
    private TextView mqttDatafromServer;
    private ArrayList<Entry> mqttMessgeList;
    private ArrayList<Entry> mqttAlchList;
    private ArrayList<String> labelsforAlch;
    private Queue<Float> priorityQueueforAlc;
    // private BarChart barChartHeartRate;
    private ArrayList<String> labels;
    private LineChart mLineChart, mLineChartAlc;
    private Queue<Float> priorityQueue;

    private String[] paths;
    private String first, two;
    private String namepass[];
    private String heart_key;
    private String heart_value;
    private String alcho_key, alch_Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temprature);
        initilizeUI();
        startMqtt();
        loadarrayValue(getApplicationContext());
        updateGraph();
    }

    private void updateGraph() {
        final Handler handler = new Handler();

        final Runnable updateTask = new Runnable() {
            @Override
            public void run() {
                setChartData();
                Log.e("Mohan", "ChartData is Called" + "Size" + mqttMessgeList.size());
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(updateTask, 1000);
    }

    private void setChartData() {
        Log.e("Size", mqttMessgeList.toString());
        LineDataSet dataset = new LineDataSet(mqttMessgeList, "Time");
        LineData data = new LineData(labels, dataset);
        Log.e("Line Data", data.getDataSets().toString());
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);
        dataset.setColor(Color.GREEN);
        dataset.setFillColor(getResources().getColor(R.color.green));
        mLineChart.setDescription("Cardiograph");
        mLineChart.setData(data);
        mLineChart.notifyDataSetChanged(); // let the chart know it's data changed
        mLineChart.invalidate();
        //mLineChart.animateY(5000);

        Log.e("Size", mqttAlchList.toString());
        LineDataSet datasetAlc = new LineDataSet(mqttAlchList, "Time");
        LineData dataAlc = new LineData(labelsforAlch, datasetAlc);
        Log.e("Line Data", dataAlc.getDataSets().toString());
        datasetAlc.setColors(ColorTemplate.COLORFUL_COLORS); //
        datasetAlc.setDrawCubic(true);
        datasetAlc.setDrawFilled(true);
        datasetAlc.setColor(Color.RED);
        datasetAlc.setFillColor(getResources().getColor(R.color.red));
        mLineChartAlc.setDescription("Alcohol Level");
        mLineChartAlc.setData(dataAlc);
        mLineChartAlc.notifyDataSetChanged(); // let the chart know it's data changed
        mLineChartAlc.invalidate();
        //mLineChart.animateY(5000);
    }


    private void initilizeUI() {
        mqttDatafromServer = (TextView) findViewById(R.id.mqttText);
        mLineChart = (LineChart) findViewById(R.id.mChart);
        mLineChartAlc = (LineChart) findViewById(R.id.mChartAlc);
        BarChartBasicSettings(mLineChart);
        BarChartBasicSettingsAlg(mLineChartAlc);
        mqttMessgeList = new ArrayList<>();
        priorityQueue = new LinkedList<Float>();
        labels = new ArrayList<String>();

        mqttAlchList = new ArrayList<>();
        priorityQueueforAlc = new LinkedList<>();
        labelsforAlch = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            labels.add(0 + "Sec");
        }

        for (int i = 0; i < 5; i++) {
            labelsforAlch.add(0 + "Sec");
        }
    }

    private void startMqtt() {
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.w("Debug", "Connected");
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug", mqttMessage.toString());

                paths = mqttMessage.toString().split("/");
                first = paths[0];
                two = paths[1];
                System.out.println("first ==== " + first + " two  ==== " + two);

                String namepass[] = paths[0].split(":");

                heart_key = namepass[0];
                heart_value = namepass[1];

                System.out.println("Key === " + heart_key + " === Value ==== " + heart_value);

                String name[] = paths[1].split(":");
                alcho_key = name[0];
                alch_Value = name[1];

                System.out.println("Key === " + alcho_key + "    Value === " + alch_Value);

                if (mqttMessgeList.size() >= 5) {
                    priorityQueue.remove();
                }

                priorityQueue.add(Float.valueOf(heart_value));
                mqttMessgeList.clear();
                int i = 0;
                for (Float val : priorityQueue) {
                    mqttMessgeList.add(new Entry(val, i));
                    i++;
                }

                labels.clear();
                for (int j = 0; j < 5; j++) {
                    if (j < mqttMessgeList.size()) {
                        labels.add(mqttMessgeList.size() - j + "Sec");
                    } else {
                        labels.add("");
                    }
                }


                if (mqttAlchList.size() >= 5) {
                    priorityQueueforAlc.remove();
                }

                priorityQueueforAlc.add(Float.valueOf(alch_Value));
                mqttAlchList.clear();
                int k = 0;
                for (Float val : priorityQueueforAlc) {
                    mqttAlchList.add(new Entry(val, k));
                    k++;
                }

                labelsforAlch.clear();
                for (int j = 0; j < 5; j++) {
                    if (j < mqttAlchList.size()) {
                        labelsforAlch.add(mqttAlchList.size() - j + "Sec");
                    } else {
                        labelsforAlch.add("");
                    }
                }

                //saveArray(mqttMessgeList);
                Log.e("Mohan", "" + mqttMessgeList.size());
                //mqttDatafromServer.setText(mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temprature, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void BarChartBasicSettings(com.github.mikephil.charting.charts.LineChart lineChart) {
        lineChart.setDescription("");
        lineChart.setDrawGridBackground(true);
        // barChart.getLegend().setEnabled(false);
        lineChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);

        lineChart.animateXY(1000, 1000);
        lineChart.setDrawGridBackground(true);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setScaleEnabled(false);
        lineChart.setTouchEnabled(false);

        YAxis Axis1 = lineChart.getAxisLeft();
        Axis1.setAxisMaxValue(300f);
        Axis1.setDrawGridLines(true);
        Axis1.setDrawAxisLine(true);

        YAxis Axis2 = lineChart.getAxisRight();
        Axis2.setAxisMaxValue(100f);
        Axis2.setDrawGridLines(true);
        Axis2.setDrawAxisLine(true);
        Axis2.setDrawLabels(false);
        lineChart.invalidate();

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
    }


    private void BarChartBasicSettingsAlg(com.github.mikephil.charting.charts.LineChart lineChart) {
        lineChart.setDescription("");
        lineChart.setDrawGridBackground(true);
        // barChart.getLegend().setEnabled(false);
        lineChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);

        lineChart.animateXY(1000, 1000);
        lineChart.setDrawGridBackground(true);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setScaleEnabled(false);
        lineChart.setTouchEnabled(false);

        YAxis Axis1 = lineChart.getAxisLeft();
        Axis1.setAxisMaxValue(100f);
        Axis1.setDrawGridLines(true);
        Axis1.setDrawAxisLine(true);

        YAxis Axis2 = lineChart.getAxisRight();
        Axis2.setAxisMaxValue(100f);
        Axis2.setDrawGridLines(true);
        Axis2.setDrawAxisLine(true);
        Axis2.setDrawLabels(false);
        lineChart.invalidate();

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
    }

    public boolean saveArray(ArrayList<Entry> mqttMessgeList) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SensorBasedHeartRateActivity.this);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.putInt("Status_size", mqttMessgeList.size());

        for (int i = 0; i < mqttMessgeList.size(); i++) {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, String.valueOf(mqttMessgeList.get(i).getVal()));
        }

        return mEdit1.commit();
    }

    public void loadarrayValue(Context mContext) {
        SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
        mqttMessgeList.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);

        for (int i = 0; i < size; i++) {
            Log.e("Ben", mSharedPreference1.getString("Status_" + i, null));
            mqttMessgeList.add(new Entry(Float.parseFloat(mSharedPreference1.getString("Status_" + i, null)), i));
        }

    }
}
