/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Roberto Marroquín
 */
public class Gatos {
    String id;
    String url;
    String apiKey = "live_DWTmVl6GnwqPQo9rUqRhAofUVY0chPxQROrWjlPN7RvXbuDiUkMvjZwyVxNbmYQ6";
    String image;

    public String getId() {
        return id;
    }

    public void setId(String idGato) {
        this.id = idGato;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
