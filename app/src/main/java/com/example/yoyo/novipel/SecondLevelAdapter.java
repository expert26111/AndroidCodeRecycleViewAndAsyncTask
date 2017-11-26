package com.example.yoyo.novipel;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.yoyo.novipel.R.attr.alpha;

/**
 * Created by yoyo on 6/22/2017.
 */

public class SecondLevelAdapter extends BaseExpandableListAdapter
{
    private final Context mContext;
    private Typeface typeface;
    private final List<String> mListDataHeader;
    private final Map<String, List<String>> mListDataChild;
    public SecondLevelAdapter(Context mContext, List<String> mListDataHeader, Map<String, List<String>> mListDataChild) {
        this.mContext = mContext;
        this.mListDataHeader = mListDataHeader;
        this.mListDataChild = mListDataChild;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                .get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
        }
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.list_ha);
        txtListChild.setTextColor(Color.argb(alpha, 177, 178, 181));
       // txtListChild.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        txtListChild.setText(childText);
        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition)
    {
        try {
            return this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).size();
        } catch (Exception e) {
            return 0;
        }
    }
    @Override
    public Object getGroup(int groupPosition)
    {
        return this.mListDataHeader.get(groupPosition);
    }
    @Override
    public int getGroupCount()
    {
        return this.mListDataHeader.size();
    }
    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent)
    {

        AssetManager am = this.mContext.getAssets();
        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Roboto-Thin.ttf"));


        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, parent, false);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.btd);
        lblListHeader.setText(headerTitle);
       // lblListHeader.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        lblListHeader.setTextColor(Color.argb(android.support.v7.appcompat.R.attr.alpha, 177, 178, 181));
      //  lblListHeader.setTypeface(null, Typeface.BOLD);
       // lblListHeader.setTextColor(Color.YELLOW);
        lblListHeader.setTypeface(typeface);
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
