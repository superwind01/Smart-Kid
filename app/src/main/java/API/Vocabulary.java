package API;

public class Vocabulary {
    private int idVocabulary;
    private String name;
    private String pronounce;
    private String description;

    public int getIdVocabulary() {
        return idVocabulary;
    }

    public void setIdVocabulary(int idVocabulary) {
        this.idVocabulary = idVocabulary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVnName() {
        return vnName;
    }

    public void setVnName(String vnName) {
        this.vnName = vnName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String vnName;
    private boolean status;
}
