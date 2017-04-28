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

import com.bumptech.glide.Glide;


public class NoticiasFragment extends Fragment {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_noticias, container, false);


        return v;
    }

    String[] images_articles = {
            "https://cdn.forbes.com.mx/2015/05/klings-youtube-640x400.jpg",
            "https://cdn.forbes.com.mx/2015/07/enrique-640x400.jpg",
            "https://cdn.forbes.com.mx/2014/01/reu_reto-640x400.jpg",
            "http://www.elfinancierocr.com/finanzas/Banco_Central-compra-titulos_propios-deuda_estandarizada-liquidez_ELFIMA20161223_0024_9.jpg",
            "http://www.elfinancierocr.com/tecnologia/Elempleo-com-informaticos-tecnologia_ELFIMA20170425_0009_5.jpg",
            "http://www.elfinancierocr.com/noticias/Gerencia-Harvard_Business_Review-NYT-millennials-trabajo-equipo-organizacion-vacaciones-remuneracion_ELFIMA20170421_0017_9.jpg",
            "http://www.nacion.com/economia/empresarial/costo-mensual-vivir-micro-apartamento_LNCIMA20170425_0046_5.jpg",
            "http://www.elfinancierocr.com/tecnologia/Nova-nueva-app-transporte-colaborativo_ELFIMA20170425_0008_9.jpg",
            "http://www.elfinancierocr.com/negocios/Mabe-refrigeradoras-Camara_de_Industrias-Inteco_ELFIMA20151211_0044_9.jpg",
            "http://www.elfinancierocr.com/finanzas/tarjetas_de_credito-millas-cash_back-beneficios-tasas_de_interes_ELFIMA20161111_0036_5.jpg",
            "http://www.elfinancierocr.com/noticias/Gerencia-Jose_Alberto_Carpio-gerente-emprendedor-liderazgo_ELFIMA20170421_0018_9.jpg"

    };
    String[] titles_articles = {
            "Klings, la app mexicana del millón de usuarios.",
            "8 frases de Carlos Slim para mejorar tus finanzas personales.",
            "19 cosas difíciles que nadie hará por ti",
            "Banco Central sube la Tasa de Política Monetaria por segunda vez en 2017 y llega a 2,50%",
            "Salarios más atractivos en Costa Rica se ofrecen en tecnología y servicios",
            "Contrario a lo que se cree, los millennials son adictos al trabajo",
            "Vivir en un microapartamento en San José costará desde $2.200 al mes",
            "Nova, app que se suma a los competidores de Uber",
            "Administración Trump podría incidir en inversión de industriales costarricenses",
            "Esto es lo que debe revisar cuando necesite cerrar una tarjeta de crédito",
            "Las 10 características del buen gerente y emprendedor"

    };
    String[] descriptions_articles = {
            "El boom de las aplicaciones continúa y los desarrolladores siguen tratando de encontrar el hilo negro para lograr que su idea se traduzca en descargas, y llegó el momento de Klings, la app mexicana que ya cuenta con un millón de usuarios registrados.",
            "Si el segundo hombre más rico del mundo ha logrado construir un imperio siguiendo sus propios criterios, quizá valga la pena adoptarlos y ver qué pueden hacer por nuestras finanzas.",
            "Lo sabemos, es fácil y cómodo evitar el esfuerzo y rehuir de la responsabilidad, pero si quieres ser exitoso tendrás que poner mucho de tu parte. Dan Waldschmidt te ofrece algunos consejos.",
            "Además del aumento en la TPM, también se fijó la tasa de interés bruta de los depósitos  a un día plazo (DON) en 1,14% anual, a partir del 27 de abril.",
            "En compensación informáticos también obtienen los mejores beneficios.",
            "Nuevo estudio revela que incluso no toman vacaciones y temen ser reemplazados",
            "Nuevo Hotel Balmoral lanza opción de alquiler en el centro de la capital",
            "Tarifa para choferes será de $35 mensuales y para los clientes el pago será en efectivo",
            "Presidente de Cámara, Enrique Egloff, afirma que la entidad se reunió con autoridades estadounidenses y hace un llamado a disipar la incertidumbre ",
            "Saldos pendientes, programas de lealtad y millas son solo algunos factores a los que debe prestar atención",
            "El líder exitoso siempre se cuestiona y pregunta cómo puede mejorar"
    };

    String[] urls_articles = {
            "https://www.forbes.com.mx/klings-la-app-mexicana-del-millon-de-usuarios/",
            "https://www.forbes.com.mx/8-frases-de-carlos-slim-para-mejorar-tus-finanzas-personales/",
            "https://www.forbes.com.mx/19-cosas-dificiles-que-nadie-hara-por-ti/",
            "http://www.elfinancierocr.com/finanzas/Banco-Central-Tasa-Politica-Monetaria-sube-segunda_vez_0_1165083490.html",
            "http://www.elfinancierocr.com/tecnologia/Elempleo-com-Talent_Partner-informaticos-tecnologia-salarios_0_1164483552.html",
            "http://www.elfinancierocr.com/gerencia/Gerencia-Harvard_Business_Review-NYT-millennials-trabajo-equipo-organizacion-vacaciones-remuneracion_0_1162083794.html",
            "http://www.nacion.com/economia/empresarial/Vivir-apartamento-San-Jose-costara_0_1629837035.html",
            "http://www.elfinancierocr.com/tecnologia/Nova-nueva-suma-competidores-Uber_0_1164483543.html",
            "http://www.elfinancierocr.com/economia-y-politica/Administracion-Trump-inversion-industriales-costarricenses_0_1164483549.html",
            "http://www.elfinancierocr.com/finanzas/tarjeta_de_credito-cierre-tarjetas-deudas-morosidad_0_1162083783.html",
            "http://www.elfinancierocr.com/gerencia/Gerencia-Jose_Alberto_Carpio-gerente-emprendedor-liderazgo_0_1162083795.html"
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getActivity().findViewById(R.id.article_list);
        final Adaptador adaptador = new Adaptador();
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), DetalleActivity.class);
                bundle.putString("url", urls_articles[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });

    }

    class Adaptador extends BaseAdapter {

        @Override
        public int getCount() {
            return images_articles.length;
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
            convertView = getActivity().getLayoutInflater().inflate(R.layout.content_list_article, null);
            ImageView image_video = (ImageView) convertView.findViewById(R.id.image_article);
            TextView title_video = (TextView) convertView.findViewById(R.id.title_article);
            TextView description_video = (TextView) convertView.findViewById(R.id.description_article);
            title_video.setText(titles_articles[position]);
            description_video.setText(descriptions_articles[position]);
            Glide.with(getActivity().getApplicationContext()).load(images_articles[position]).into(image_video);

            return convertView;
        }


    }
}
