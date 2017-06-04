package com.example.kenneth.smartbudget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class GastosFragment extends Fragment {

    public static String DirecUser = "";
    public static int indexSpend;
    private String Spend = "";
    private ListView listView;
    private ImageView img_gastos;
    private TextView info_gastos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gastos, container, false);
        img_gastos = (ImageView) v.findViewById(R.id.info_gastos);
        info_gastos = (TextView) v.findViewById(R.id.gastos_lbl);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Gastos");
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getActivity().findViewById(R.id.list_gastos);
        final Adaptador adaptador = new Adaptador();
        listView.setAdapter(adaptador);
        if (adaptador == null) {
            img_gastos.setVisibility(View.INVISIBLE);
            info_gastos.setVisibility(View.INVISIBLE);

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

            }

        });

    }

    class Adaptador extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getActivity().getLayoutInflater().inflate(R.layout.content_list_gastos, null);
            TextView nombregasto = (TextView) convertView.findViewById(R.id.nombre_gasto_lbl);
            TextView tipo_gasto = (TextView) convertView.findViewById(R.id.tipo_gasto_lbl);
            TextView monto_gasto = (TextView) convertView.findViewById(R.id.total_gasto_lbl);
            TextView lugar_gasto = (TextView) convertView.findViewById(R.id.lugar_gasto_lbl);

            return convertView;
        }


    }


}
