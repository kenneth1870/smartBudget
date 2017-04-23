package com.example.kenneth.smartbudget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


public class TipsFragment extends Fragment {

    private static final String API_KEY = "AIzaSyCaffM0P-TWq1Fl0abHYCS2GFlEVFE5Pwo";
    private static String VIDEO_ID = "03Kc_HNVKyM";

    ListView listView;

    private YouTubePlayer youTubePlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tips, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {


            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    if (youTubePlayer == null) {
                        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        player.loadVideo(VIDEO_ID);
                        player.play();
                        youTubePlayer = player;
                    }
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                // YouTube error
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });


        return v;

    }

    String[] images_video = {
            "https://i.ytimg.com/vi/03Kc_HNVKyM/hqdefault.jpg?custom=true&w=246&h=138&stc=true&jpg444=true&jpgq=90&sp=68&sigh=i9q-_U7WP4YUxgy1_97bOVBAViA",
            "https://i.ytimg.com/vi/Mw2HC1N5lfs/hqdefault.jpg?custom=true&w=168&h=94&stc=true&jpg444=true&jpgq=90&sp=67&sigh=F3zpNw2GbFXxqZbncmUu6vzPelI",
            "https://i.ytimg.com/vi/lZKSbJtN2fE/hqdefault.jpg?custom=true&w=168&h=94&stc=true&jpg444=true&jpgq=90&sp=68&sigh=HQviJLoTNqU13Z7u4Hi0_ZFi62k",
            "https://i.ytimg.com/vi/TDInPcEH5DU/hqdefault.jpg?custom=true&w=168&h=94&stc=true&jpg444=true&jpgq=90&sp=68&sigh=-47puFAeEvBLBT1C_Ha5lE0SiKM",
            "https://i.ytimg.com/vi/kLZuhijo7CI/hqdefault.jpg?custom=true&w=168&h=94&stc=true&jpg444=true&jpgq=90&sp=68&sigh=oIGLnbUjnT2S5sxwdiFGcJmEKPo",
            "https://i.ytimg.com/vi/Qd2h7_W9vFY/hqdefault.jpg?custom=true&w=168&h=94&stc=true&jpg444=true&jpgq=90&sp=67&sigh=BTWWd9bwMRKTGwQgImwCxfSHnwA"
    };
    String[] titles_video = {
            "Educación Financiera: El Ahorro",
            "4 Habitos que te mantienen pobre",
            "El hábito que te hará rico por (Robert Kiyosaki) Resumen Animado",
            "5 Secretos para Multiplicar Dinero",
            "Cómo Ahorrar Dinero | No importa cual sea tu salario",
            "El hábito de las 3 alcancías"
    };
    String[] descriptions_video = {
            "Conseguir la auténtica libertad financiera supone disponer de dinero para vivir durante años aunque no tengas ingresos por tu trabajo.",
            "En este vídeo educativo conocerás 4 hábitos que te hacen perder dinero.",
            "Gané dinero entonces ¿qué hago?, ¿Por qué no soy rico? Robert Kiyosaki dice que ser rico es más sencillo de lo que parece, pero depende mucho de nuestros hábitos financieros, si quieres llegar lejos con el dinero, escucha de que se trata este poderoso hábito.\n" +
                    "Disfruta del mejor resumen animado hecho por el canal SoloParaInteligentes.",
            "Producir Dinero es una Ciencia que Todos Podemos Aprender y así aumentar Nuestros Ingresos.",
            "¿Sientes que no puedes ahorrar, porque no te alcanza el dinero? Quiero convencerte de que cada uno puede hacerlo, incluso ganando el salario mínimo.",
            "Este buen habito, consiste en crear 3 fondos, uno destinado para ahorros, sería como un colchón de seguridad, otro para donaciones y otro para re-invertir en tu negocio."
    };


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getActivity().findViewById(R.id.video_list);
        final Adaptador adaptador = new Adaptador();
        listView.setAdapter(adaptador);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                switch (position) {
                    case 0:
                        playVideo("hZweLjSVoqU");
                        break;
                    case 1:
                        playVideo("Mw2HC1N5lfs");
                        break;
                    case 2:
                        playVideo("lZKSbJtN2fE");
                        break;
                    case 3:
                        playVideo("TDInPcEH5DU");

                        break;
                    case 4:
                        playVideo("kLZuhijo7CI");
                        break;
                    case 5:
                        playVideo("Qd2h7_W9vFY");
                        break;

                    default:
                        break;
                }

            }

        });


    }

    private void playVideo(String videoId) {
        if (youTubePlayer != null) {
            youTubePlayer.loadVideo(videoId);
        }
    }

    class Adaptador extends BaseAdapter {

        @Override
        public int getCount() {
            return images_video.length;
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
            convertView = getActivity().getLayoutInflater().inflate(R.layout.content_list_video, null);
            ImageView image_video = (ImageView) convertView.findViewById(R.id.image_video);
            TextView title_video = (TextView) convertView.findViewById(R.id.title_video);
            TextView description_video = (TextView) convertView.findViewById(R.id.description_video);
            title_video.setText(titles_video[position]);
            description_video.setText(descriptions_video[position]);
            Glide.with(getActivity().getApplicationContext()).load(images_video[position]).into(image_video);

            return convertView;
        }


    }


}
