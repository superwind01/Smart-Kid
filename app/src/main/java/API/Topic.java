package API;

import com.google.gson.annotations.SerializedName;

public class Topic {
    @SerializedName("idtopic")
    private int idtopic;
    private String name;
    private  String description;
    private String image;
    private boolean status;


    public int getIdTopic() {
        return idtopic;
    }

    public void setIdTopic(int idTopic) {
        this.idtopic = idTopic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
