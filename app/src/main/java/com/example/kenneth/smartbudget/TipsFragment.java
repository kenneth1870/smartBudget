package com.example.kenneth.smartbudget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;



public class TipsFragment extends Fragment {
    private VideoView reproductor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v =inflater.inflate(R.layout.fragment_tips, container, false);
        reproductor =(VideoView)v.findViewById(R.id.video);
//        MediaController mc= new MediaController(getActivity());
//        reproductor.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=iaMiWiHV7ug"));
//        reproductor.setMediaController(mc);
//        reproductor.start();
       // Intent intent = new Intent(getActivity(), DetalleActivity.class);
        //getActivity().startActivity(intent);

    return  v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
