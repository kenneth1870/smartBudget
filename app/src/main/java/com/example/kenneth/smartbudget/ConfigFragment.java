package com.example.kenneth.smartbudget;

import android.content.Intent;
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


public class ConfigFragment extends Fragment {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    Integer[] imageIDs = {
            R.drawable.ic_notifications,
            R.drawable.ic_account
    };

    String[] titles = {
            "Notificaciones",
            "Cuenta"
    };


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Configuración");

        listView = (ListView) getActivity().findViewById(R.id.listview_config);
        final Adaptador adaptador = new Adaptador();
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(), NotificationsActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), ProfileActivity.class);
                        startActivity(intent2);
                        break;

                    default:
                        break;
                }

            }

        });
    }

    class Adaptador extends BaseAdapter {

        @Override
        public int getCount() {
            return imageIDs.length;
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
            convertView = getActivity().getLayoutInflater().inflate(R.layout.content_list_config, null);
            TextView title_config = (TextView) convertView.findViewById(R.id.txt_config);
            ImageView image_config = (ImageView) convertView.findViewById(R.id.image_config);
            title_config.setText(titles[position]);
            image_config.setImageResource(imageIDs[position]);


            return convertView;
        }


    }
}
