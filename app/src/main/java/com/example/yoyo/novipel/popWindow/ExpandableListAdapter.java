package com.example.yoyo.novipel.popWindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import static android.support.v7.appcompat.R.attr.alpha;

import com.example.yoyo.novipel.MainActivity;
import com.example.yoyo.novipel.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//
//import static android.R.attr.alpha;
//import static android.R.attr.alpha;

/**
 * Created by yoyo on 6/2/2017.
 */
//creates the accordion like ui form
public class ExpandableListAdapter extends BaseExpandableListAdapter
{

    public Context context;
    public List<String> listDataHeader;
    public HashMap<String,List<String>> listHashMap;
    LinkedHashMap<String,List<String>>  solidHashMap;
    //list of lists
    public HashMap<Integer,List<String>> helper;
    public List<String> begginerheader;
   // private MainActivity activity;

    public ExpandableListAdapter(Context context, List<String>listDataHeader, LinkedHashMap<String,List<String>> listHashMap
    , LinkedHashMap<String,List<String>> solidHashMap, List<String> begginerheader, HashMap<Integer,List<String>> helper)//it is here
            //the tree datatsources so we operate easily with them
    {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
        this.solidHashMap = solidHashMap;
        this.begginerheader = begginerheader;
        this.helper = helper;

    }

    @Override
    public int getGroupCount() {

       // return listDataHeader.size();
        return  begginerheader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return listHashMap.get(listDataHeader.get(groupPosition)).size();
        return solidHashMap.get(begginerheader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        //return listDataHeader.get(groupPosition);
        return  begginerheader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        //return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
        return solidHashMap.get(begginerheader.get(groupPosition)).get(childPosition);

//        HashMap map = solidHashMap.get(begginerheader.get(groupPosition));
//        List<String> l = new ArrayList<String>(map.keySet());
//        return l.get(childPosition);

//        for (String key : map.keySet())
//        {
//            System.out.println("------------------------------------------------");
//            System.out.println("Iterating or looping map using java5 foreach loop");
//            System.out.println("key: " + key + " value: " + loans.get(key));
//        }

//        Iterator itr2 = map.keySet().iterator();
//        while(itr2.hasNext())
//        {
//            String key = itr2.next();
//            testMap.get(key);
//        }
   // return    childPosition;

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
       // return null;
        String headerTitle = (String) getGroup(groupPosition);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
        }

        int height =  parent.getMeasuredHeight();
        int minH = height/4;
        TextView btd = (TextView)view.findViewById(R.id.btd);
//////////////////////

//        DisplayMetrics dm = new DisplayMetrics();
//        this.getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout((int)(width *0.85 ), (int)(height * 0.79));
       // btd.setHeight(30);
       // btd.setMinimumHeight(30);
/////////////////////////////
       // btd.getBackground().setAlpha(150);
        btd.setMinimumHeight(minH);
        btd.setTextColor(Color.argb(alpha, 177, 178, 181));
        btd.setTypeface(null, Typeface.BOLD);
        btd.setText(headerTitle);
        return view;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent)
    {
//        final String chidText = (String)getChild(groupPosition,childPosition);
//        if(view == null)
//        {
//            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.list_item,parent,false);
//        }
//        TextView textListChild = (TextView)view.findViewById(R.id.list_ha);
//        textListChild.setTextColor(Color.argb(alpha, 177, 178, 181));
//        textListChild.setText(chidText);
//
//        return view;

        CustExpListview SecondLevelexplv = new CustExpListview(context);
        SecondLevelexplv.setAdapter(new SecondLevelAdapter());
        SecondLevelexplv.setGroupIndicator(null);
        return SecondLevelexplv;

    }

    public class CustExpListview extends ExpandableListView {

        int intGroupPosition, intChildPosition, intGroupid;

        public CustExpListview(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(20,
                    View.MeasureSpec.AT_MOST);
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(15,
                    View.MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    public class SecondLevelAdapter extends BaseExpandableListAdapter {


//        private List<String> l ;
//
//        HashMap map = solidHashMap.get(begginerheader.get(groupPosition));
//        l = new ArrayList<String>(map.keySet());

        //get all hashmaps inside solid
        //get all keys from each hashmap
//        LinkedHashMap<String,List<String>> maps =  (new ArrayList<String>(solidHashMap.values()));
//        solidHashMap.
//                List<S>

//        List<LinkedHashMap<String,List<String>>> arList;
//        List<String> listBigMama;
//        private void initData()
//            {
//                 arList = new ArrayList<>();
//
//                for(Map.Entry<String,LinkedHashMap<String,List<String>>> map : solidHashMap.entrySet())
//                {
//
//                    arList.add(map.getValue());
//
//                }
//
//            }

           //Babe use the dataHeader
//        public SecondLevelAdapter()
//        {
//           // initData();
//        }



        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View view, ViewGroup parent) {
//            TextView tv = new TextView(Vincent_ThreeelevellistActivity.this);
//            tv.setText("child");
//            tv.setTextColor(Color.BLACK);
//            tv.setTextSize(20);
//            tv.setPadding(15, 5, 5, 5);
//            tv.setBackgroundColor(Color.YELLOW);
//            tv.setLayoutParams(new ListView.LayoutParams(
//                    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            //*******************************************************************

                    final String chidText = (String)getChild(groupPosition,childPosition);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)ExpandableListAdapter.this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,parent,false);
        }
        TextView textListChild = (TextView)view.findViewById(R.id.list_ha);
        textListChild.setTextColor(Color.argb(alpha, 177, 178, 181));
        textListChild.setText(chidText);

        return view;



//
//            final String chidText = (String)getChild(groupPosition,childPosition);
//        if(view == null)
//        {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.list_item,parent,false);
//        }
//        TextView textListChild = (TextView)view.findViewById(R.id.list_ha);
//        textListChild.setTextColor(Color.argb(alpha, 177, 178, 181));
//        textListChild.setText(chidText);
//
//
//            return textListChild;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 5;

            //return listHashMap.get(listDataHeader.get(groupPosition)).size();
//            HashMap map = solidHashMap.get(begginerheader.get(groupPosition));
//            return map.size();
           // return listHashMap.get(listDataHeader.get(groupPosition)).size();
           // return solidHashMap.

           //HashMap map4e =  solidHashMap.get(begginerheader.get(groupPosition));


        }

        @Override
        public Object getGroup(int groupPosition) {
            //return groupPosition;
            //thats right get the data header
            //return listDataHeader.get(groupPosition);
//            HashMap map = solidHashMap.get(begginerheader.get(groupPosition));
//            return map.size();


//            HashMap map = solidHashMap.get(begginerheader.get(groupPosition));
//            l = new ArrayList<String>(map.keySet());
//            return l.get(groupPosition);

             //return listDataHeader.size();
            return
                    helper.get(groupPosition).size();
        }

        @Override
        public int getGroupCount() {
           // return 1;
//            HashMap map = solidHashMap.get(begginerheader.get(groupPosition));
//            return map.size();
            return 1;
                 //   helper.get(groupPosition).size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View view, ViewGroup parent) {
//            TextView tv = new TextView(Vincent_ThreeelevellistActivity.this);
//            tv.setText("-->Second Level");
//            tv.setTextColor(Color.BLACK);
//            tv.setTextSize(20);
//            tv.setPadding(12, 7, 7, 7);
//            tv.setBackgroundColor(Color.RED);
//
//            return tv;
            String headerTitle = (String) getGroup(groupPosition);
            if(view == null)
            {
                LayoutInflater inflater = (LayoutInflater)ExpandableListAdapter.this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_under_group,null);
            }

            int height =  parent.getMeasuredHeight();
            int minH = height/getGroupCount();
            TextView btd = (TextView)view.findViewById(R.id.btd);
//////////////////////

//        DisplayMetrics dm = new DisplayMetrics();
//        this.getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout((int)(width *0.85 ), (int)(height * 0.79));
            // btd.setHeight(30);
            // btd.setMinimumHeight(30);
/////////////////////////////
            // btd.getBackground().setAlpha(150);
            btd.setTextColor(Color.argb(alpha, 177, 178, 181));
            btd.setMinimumHeight(minH);
            btd.setTypeface(null, Typeface.BOLD);
            btd.setText(headerTitle);
            return view;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }

    }




    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
