package com.example.kenneth.smartbudget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import static com.example.kenneth.smartbudget.IngresosFragment.MyAcounts;
import static com.example.kenneth.smartbudget.IngresosFragment.MyForms;
import static com.example.kenneth.smartbudget.IngresosFragment.cont;

/**
 * Created by ariel on 30/5/2017.
 */

public class MiAdaptador extends BaseExpandableListAdapter{

    Context contexto;
    TextView tv;

    public MiAdaptador(Context contexto) {
        this.contexto = contexto;
    }

    public void CreateFather(String NameFather){
        MyAcounts.add(NameFather);
    }

    @Override
    public int getGroupCount() {
        return MyAcounts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        tv = new TextView(contexto);
        tv.setText("           "+ MyAcounts.get(groupPosition));
        tv.setTextSize(25);
        tv.setHeight(200);
        tv.setBackgroundColor(Color.rgb(0,161,181));
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        tv.setBackgroundColor(Color.WHITE);
        tv.setTextColor(Color.BLACK);
        if(groupPosition > cont) {
            cont +=1;
            MyForm newForm = new MyForm(contexto, cont);
            MyForms.add(newForm);
            newForm.LoadSave();
            return newForm.GetForm();
        }
        else {
            MyForms.get(groupPosition).LoadSave();
            return MyForms.get(groupPosition).GetForm();
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
