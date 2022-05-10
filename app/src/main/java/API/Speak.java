package API;

public class Speak {
    private int id;
    private int idvocabulary;
    private int idclass;
    private int idclassNavigation;
    private int idvocabularyNavigation;
    private String sampleEn;
    private String sampleVn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdvocabulary() {
        return idvocabulary;
    }

    public void setIdvocabulary(int idvocabulary) {
        this.idvocabulary = idvocabulary;
    }

    public int getIdclass() {
        return idclass;
    }

    public void setIdclass(int idclass) {
        this.idclass = idclass;
    }

    public int getIdclassNavigation() {
        return idclassNavigation;
    }

    public void setIdclassNavigation(int idclassNavigation) {
        this.idclassNavigation = idclassNavigation;
    }

    public int getIdvocabularyNavigation() {
        return idvocabularyNavigation;
    }

    public void setIdvocabularyNavigation(int idvocabularyNavigation) {
        this.idvocabularyNavigation = idvocabularyNavigation;
    }

    public String getSampleEn() {
        return sampleEn;
    }

    public void setSampleEn(String sampleEn) {
        this.sampleEn = sampleEn;
    }

    public String getSampleVn() {
        return sampleVn;
    }

    public void setSampleVn(String sampleVn) {
        this.sampleVn = sampleVn;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSoundslow() {
        return soundslow;
    }

    public void setSoundslow(String soundslow) {
        this.soundslow = soundslow;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private String images;
    private String soundslow;
    private Boolean status;

}
