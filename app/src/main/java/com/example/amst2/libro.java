package com.example.amst2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class libro {
    public String titulo,sinopsis,autor,genero,url,editorial,id ;
    public ImageView imageView;
    //$result2.="$row[id]~"."$row[titulo]~"."$row[autor]~"."$row[editorial]~"."$row[resumen]~"."///";
    libro(String id ,String titulo,String autor,String editorial, String sinopsis,String genero,String url){
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.autor = autor;
        this.genero = genero;
        this.url=url;
        this.editorial = editorial;
        this.id = id;
        //imageView = new ImageView(null);
        //consulta_imagen(url,imageView);
    }

    public void consulta_imagen(String url, ImageView imageView){
        String[] resultado = null;

        try {
            String[] datos = new String[]{
                    "call",
                    url
            };
            AsyncImageQuery async = new AsyncImageQuery();
            resultado = async.execute(datos).get();

            byte[] decodedString = Base64.decode(resultado[0], Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public ImageView getImageView() {
        return imageView;
    }
    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getGenero() {
        return genero;
    }

    public String getId() {
        return id;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getTitulo() {
        return titulo;
    }

}
