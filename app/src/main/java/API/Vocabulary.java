package API;

public class Vocabulary {
    private int idvocabulary;
    private String name;
    private String pronounce;
    private String description;

    public Vocabulary(int idvocabulary, String name, String pronounce, String description, String vnName, boolean status) {
        this.idvocabulary = idvocabulary;
        this.name = name;
        this.pronounce = pronounce;
        this.description = description;
        this.vnName = vnName;
        this.status = status;
    }

    public int getIdVocabulary() {
        return idvocabulary;
    }

    public void setIdVocabulary(int idVocabulary) {
        this.idvocabulary = idVocabulary;
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
