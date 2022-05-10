package API;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private int idsong;
    private String name;
    private String image;
    private String note;
    private Boolean status;
    private String link;

    protected Song(Parcel in) {
        idsong = in.readInt();
        name = in.readString();
        image = in.readString();
        note = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
        link = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public int getIdsong() {
        return idsong;
    }

    public void setIdsong(int idsong) {
        this.idsong = idsong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(idsong);
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(note);
        parcel.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
        parcel.writeString(link);
    }
}
