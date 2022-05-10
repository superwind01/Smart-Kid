package API;

public class VocabularyByTopicLesson {
    private int idtopic;
    private int idlesson;
    private int idvocabulary;
    private String link;

    public int getIdtopic() {
        return idtopic;
    }

    public void setIdtopic(int idtopic) {
        this.idtopic = idtopic;
    }

    public int getIdlesson() {
        return idlesson;
    }

    public void setIdlesson(int idlesson) {
        this.idlesson = idlesson;
    }

    public int getIdvocabulary() {
        return idvocabulary;
    }

    public void setIdvocabulary(int idvocabulary) {
        this.idvocabulary = idvocabulary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
