package com.example.yoyo.novipel;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.yoyo.novipel.popWindow.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.support.v7.appcompat.R.attr.alpha;

/**
 * Created by yoyo on 6/22/2017.
 */

public class ParentLevelAdapter  extends BaseExpandableListAdapter
{
    private final Context mContext;
    private Typeface typeface;
    private final List mListDataHeader;
    private final Map<String, List<String>> mListData_SecondLevel_Map;
    private final Map<String, List<String>> mListData_ThirdLevel_Map;
    public ParentLevelAdapter(Context mContext, List<String> mListDataHeader) {
        this.mContext = mContext;
        this.mListDataHeader = new ArrayList<>();
        this.mListDataHeader.addAll(mListDataHeader);
        // Init second level data
        String[] mItemHeaders;
        mListData_SecondLevel_Map = new HashMap<>();
        int parentCount = mListDataHeader.size();
        for (int i = 0; i < parentCount; i++)
        {
            String content = mListDataHeader.get(i);
            switch (content) {
                case "FAQ":
                    mItemHeaders = mContext.getResources().getStringArray(R.array.items_array_expandable_level_one_one_child);
                    break;
//                case "Level 1.2":
//                    mItemHeaders = mContext.getResources().getStringArray(R.array.items_array_expandable_level_one_two_child);
//                    break;

                default:
                    mItemHeaders = mContext.getResources().getStringArray(R.array.items_array_expandable_other_child);
            }
            mListData_SecondLevel_Map.put(mListDataHeader.get(i), Arrays.asList(mItemHeaders));
        }
        // THIRD LEVEL
        String[] mItemChildOfChild;
        List<String> listChild;
        mListData_ThirdLevel_Map = new HashMap<>();
        for (Object o : mListData_SecondLevel_Map.entrySet())
        {
            Map.Entry entry = (Map.Entry) o;
            Object object = entry.getValue();
            if (object instanceof List)
            {
                List<String> stringList = new ArrayList<>();
                Collections.addAll(stringList, (String[]) ((List) object).toArray());
                for (int i = 0; i < stringList.size(); i++)
                {
                    mItemChildOfChild = mContext.getResources().getStringArray(R.array.items_array_expandable_level_three);
                    listChild = Arrays.asList(mItemChildOfChild);
                    mListData_ThirdLevel_Map.put(stringList.get(i), listChild);
                }
            }
        }
    }
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
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final CustomExpListView secondLevelExpListView = new CustomExpListView(this.mContext);
        String parentNode = (String) getGroup(groupPosition);
        secondLevelExpListView.setAdapter(new SecondLevelAdapter(this.mContext, mListData_SecondLevel_Map.get(parentNode), mListData_ThirdLevel_Map));
        secondLevelExpListView.setGroupIndicator(null);
        return secondLevelExpListView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }
    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }
    @Override
    public int getGroupCount() {
        return this.mListDataHeader.size();
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {



        AssetManager am = this.mContext.getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Roboto-Thin.ttf"));


        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = layoutInflater.inflate(R.layout.list_group, parent, false);
//        }
            convertView = layoutInflater.inflate(R.layout.list_group,null);
        }

        int height =  parent.getMeasuredHeight();
        int minH = height/4;

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.btd);

        lblListHeader.setMinimumHeight(minH);
        lblListHeader.setTextColor(Color.argb(alpha, 177, 178, 181));
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        lblListHeader.setTypeface(typeface);
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        //if(childPosition == 0)

        return true;
    }

}
