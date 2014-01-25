package com.gamepad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class LobbyAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> lobbyCollections;

    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public LobbyAdapter(Activity context, Map<String, List<String>> lobbyCollections) {
        this.context = context;
        this.lobbyCollections = lobbyCollections;
    }

    public void openLobby()
    {

    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        String key = getGroupValue(groupPosition);
        return lobbyCollections.get(key).get((childPosititon));
    }

    public String getGroupValue(int groupPosition) {
        Iterator it = lobbyCollections.keySet().iterator();
        int i = 0;
        while(it.hasNext()) {
            String key = it.next().toString();
            if(groupPosition == i) {
                return key;
           }
            i++;
       }
       return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.lobby_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.playerName);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return lobbyCollections.get(getGroupValue(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return getGroupValue(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return lobbyCollections.keySet().size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final int position = groupPosition;
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.lobby_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lobbyName);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        Button joinBtn = (Button) convertView.findViewById(R.id.joinBn);
        joinBtn.setFocusable(false);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                System.out.println(  Integer.toString(position) );
            }
        });

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}