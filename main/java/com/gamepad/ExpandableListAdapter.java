package com.gamepad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * Created by sande_000 on 10-1-14.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter
{

    private Activity context;
    private Map<String, List<String>> lobbyCollections;

    public ExpandableListAdapter(Activity context, Map<String, List<String>> lobbyCollections) {
        this.context = context;
        this.lobbyCollections = lobbyCollections;
    }

    public Object getChild(int groupPosition, int childPosition) {
        String key = getGroupValue(groupPosition);
        return lobbyCollections.get(key).get((childPosition));
    }

    public String getGroupValue(int groupPosition)
    {
        Iterator it = lobbyCollections.keySet().iterator();
        int i = 0;
        while(it.hasNext())
        {

            String key = it.next().toString();
            if(groupPosition == i)
            {
                return key;
            }
            i++;
        }
        return null;
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String laptop = (String)getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.child_item, null);
        }

       TextView item = (TextView) convertView.findViewById(R.id.laptop);

        item.setText(laptop);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return lobbyCollections.get(getGroupValue(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return getGroupValue(groupPosition);
    }

    public int getGroupCount() {
        return lobbyCollections.keySet().size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.laptop);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
