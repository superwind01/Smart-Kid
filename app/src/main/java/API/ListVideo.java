package API;

import java.util.ArrayList;

public class ListVideo {
    private int type;
    private ArrayList<Topic> topics;
    private ArrayList<Song> songs;

    public ListVideo(int type, ArrayList<Topic> topics, ArrayList<Song> songs) {
        this.type = type;
        this.topics = topics;
        this.songs = songs;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
