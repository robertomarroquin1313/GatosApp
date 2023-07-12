    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.Gatos;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Roberto Marroquín
 */
public class GatosService {
    public static void verGatitos() throws IOException{
        //1. Traer los datos de la api
        OkHttpClient client = new OkHttpClient();
       
        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
        Response response = client.newCall(request).execute();
        
        String jsonResponse = response.body().string();
        
        //Cortar corchetes
        jsonResponse =jsonResponse.substring(1,jsonResponse.length());
        jsonResponse =jsonResponse.substring(0,jsonResponse.length()-1);
        
        //crea objeto de tipo Gson
        Gson gson = new Gson();
        Gatos gatitos = gson.fromJson(jsonResponse,Gatos.class);
        
        //redimencionar en caso de necesitar
        Image image = null;
        
        try{
            URL url = new URL(gatitos.getUrl());
            image = ImageIO.read(url);
            
            
            ImageIcon fondoGato = new ImageIcon(image);
            
            if(fondoGato.getIconWidth()>800){
                //redimencionar
                Image fondo = fondoGato.getImage();
                Image modifica = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoGato= new ImageIcon(modifica);
                
            }
            
            String menu = "Opciones: \n"
                    + "1. Ver otra Imagen\n"
                    + "2. Marcar como favorito\n"
                    + "3. Volver al menu\n";
            
            String[] btns = {
                "Ver otra imagen","Favoritos","Volver"
            };
            String idGato = gatitos.getIdGato();
            String op = (String) JOptionPane.showInputDialog(null,menu,idGato,JOptionPane.INFORMATION_MESSAGE,
                    fondoGato,btns,btns[0]);
            int selec = -1;
            
            for(int i = 0; i<btns.length;i++){
                if(op.equals(btns[i])){
                    selec= i;
                }
            }
            
            switch(selec){
                case 0:
                    verGatitos();
                    break;
                    
                case 1:
                    favoritoGato(gatitos);
                    break;
                    
                default:
                    break;
                    
            }
            
        }catch(IOException err){
            System.out.println("Error: "+err);
        }
        
    }
    
    public static void favoritoGato(Gatos gato){
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n\"image_id\":\""+gato.getIdGato()+"\"\r\n}");
            Request request = new Request.Builder()
            .url("https://api.thecatapi.com/v1/favourites")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-api-key", gato.getApiKey())
            .build();
            Response response = client.newCall(request).execute();
        }catch(IOException err){
            System.out.println("errpr: "+err);
        }
    }
}
