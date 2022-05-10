package API;

public class ListNameVocabulary {
    private String nameVocabulary;
    private Boolean type;

    public ListNameVocabulary(String nameVocabulary, Boolean type) {
        this.nameVocabulary = nameVocabulary;
        this.type = type;
    }

    public String getNameVocabulary() {
        return nameVocabulary;
    }

    public void setNameVocabulary(String nameVocabulary) {
        this.nameVocabulary = nameVocabulary;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
