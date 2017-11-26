package com.example.yoyo.novipel.popWindow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yoyo.novipel.MainActivity;
import com.example.yoyo.novipel.ParentLevelAdapter;
import com.example.yoyo.novipel.R;
import com.example.yoyo.novipel.Website;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by yoyo on 6/2/2017.
 */

// the pop window which is populated by ExpandableListAdapter
public class Pop extends Activity {

    private void redirect() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);
        List<String> listDataHeader = new ArrayList<>();
        String[] mItemHeaders = getResources().getStringArray(R.array.items_array_expandable_level_one);
        Collections.addAll(listDataHeader, mItemHeaders);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width), (int) (height * 0.90));
        ExpandableListView mExpandableListView = (ExpandableListView) findViewById(R.id.lvExp);
        if (mExpandableListView != null) {
            ParentLevelAdapter parentLevelAdapter = new ParentLevelAdapter(this, listDataHeader);
            mExpandableListView.setAdapter(parentLevelAdapter);
        }


        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Bundle dataBundle;
                        int index = -1;
                        Intent intent;

                switch (groupPosition)
                {
                    case 0:
                        index = 0;
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                         dataBundle = new Bundle();
                        dataBundle.putInt("index", index);
                        intent.putExtras(dataBundle);
                        startActivity(intent);
                        break;

                    case 1:
                        index = 1;
                         intent = new Intent(getApplicationContext(), MainActivity.class);
                         dataBundle = new Bundle();
                        dataBundle.putInt("index", index);
                        intent.putExtras(dataBundle);
                        startActivity(intent);
                       // return true;
                        break;

                    case 2:
                         index = 2;
                         intent = new Intent(getApplicationContext(), MainActivity.class);
                         dataBundle = new Bundle();
                        dataBundle.putInt("index", index);
                        intent.putExtras(dataBundle);
                        startActivity(intent);
                        break;


                }
                return true;
            }

        });

        // }
//    private ExpandableListView listView;
//
//    private ExpandableListAdapter listAdapter;
//    private List<String> listDataHeader;
//    private LinkedHashMap<String, List<String>> listHash;
//    private List<String> begginerheader;
//    private LinkedHashMap<String, List<String>> listHashbeg;
//    private LinkedHashMap<String,LinkedHashMap<String,List<String>>> solidHashMap;
//    private HashMap<Integer,List<String>> helper;
//
//    //
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.popupwindow);
//
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//
//        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.70));
//        listView = (ExpandableListView) findViewById(R.id.lvExp);
//        initData();
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash,listHashbeg,begginerheader );
//        listView.setAdapter(listAdapter);
//
//
//    }
//
//    private void initData() {
//        listDataHeader = new ArrayList<>();
//        listHash = new LinkedHashMap<>();
//
//        begginerheader = new ArrayList<>();
//        listHashbeg = new  LinkedHashMap<>();
//
//        begginerheader.add("Home");
//        begginerheader.add("Buy the Device");
//        begginerheader.add("Instructions");
//        begginerheader.add("FAQ");
//
//       // listDataHeader.
//
//
//
//
//
//        List<String> home = new ArrayList<>();
//        List<String> btd = new ArrayList<>();
//        btd.add("Hey");
//        List<String > ins = new ArrayList<>();
//        ins.add("Instructions");
//        List<String > faq = new ArrayList<>();
//        faq.add("Big fag");
//        faq.add("Big small");
//        faq.add("Big haha");
//
//        listHashbeg.put(begginerheader.get(0), home);
//        listHashbeg.put(begginerheader.get(1), btd);
//        listHashbeg.put(begginerheader.get(2), ins);
//        listHashbeg.put(begginerheader.get(3), faq);
//
//
//
//
//        helper  = new HashMap<>();
//        int count = -1;
//
//        solidHashMap = new LinkedHashMap<>();
//        //LinkedHashMap<String,List<String>> emptymap = new LinkedHashMap<>();
////        listDataHeader.add("Home");
////        listDataHeader.add("Buy the Device");
////        listDataHeader.add("Instructions");
////        listDataHeader.add("FAQ");
//
//
//
//
//        List<String> home = new ArrayList<>();
//        //home.add("Expand me baby");
//
////BTD
//        LinkedHashMap<String,List<String>> btd2 = new LinkedHashMap<>();
//
//        List<String> btd = new ArrayList<>();
//        btd.add("Please check our website for more info");
//        btd.add("Come on i worth it");
//        btd.add("just  more cash");
//        helper.put(count++,btd);
//        btd2.put(btd.get(0),null);
//        btd2.put(btd.get(1),null);
//        btd2.put(btd.get(2),null);
//
////
//
//
//
//// INSTRUCTIONS
//        LinkedHashMap<String,List<String>> ins2 = new LinkedHashMap<>();
//        List<String> ins = new ArrayList<>();
//        ins.add("Instructions");
//        ins2.put(ins.get(0),null);
//        helper.put(count++,ins);
// //
//
//
//
////FAQ
//        LinkedHashMap<String,List<String>> faq2 = new LinkedHashMap<>();
//
//        List<String> faq = new ArrayList<>();
//        faq.add("Question 1 ");
//        faq.add("Question 2 ");
//        faq.add("Question 3 ");
//        helper.put(count++,faq);
//
//        List<String> faqInfoQ1 = new ArrayList<>();
//        faqInfoQ1.add("Please insert novipel device inside the hole");
//        List<String> faqInfoQ2 = new ArrayList<>();
//        faqInfoQ2.add("Now turn on the device");
//        List<String> faqInfoQ3 = new ArrayList<>();
//        faqInfoQ3.add("Press the button and enjoy");
//        faq2.put(faq.get(0),faqInfoQ1);
//        faq2.put(faq.get(1),faqInfoQ2);
//        faq2.put(faq.get(2),faqInfoQ3);
////
//
//
//        //now add data to listHash
//       // listHash.put(listDataHeader.get(0), home);
////        listHash.put(listDataHeader.get(1), btd);
////        listHash.put(listDataHeader.get(2), ins);
////        listHash.put(listDataHeader.get(3), faq);
//
//
//        solidHashMap.put(begginerheader.get(0), null);
//        solidHashMap.put(begginerheader.get(1), btd2);
//        solidHashMap.put(begginerheader.get(2), ins2);
//        solidHashMap.put(begginerheader.get(3), faq2);


    }
}
//}