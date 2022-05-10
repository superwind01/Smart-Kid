package API;
import java.util.ArrayList;

import API.Class;
public class ModelCommon {
    private ArrayList<Class> classes = new ArrayList<>();
    private ArrayList<Vocabulary> vocabularies = new ArrayList<>();
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Topic> topics = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Speak> speaks = new ArrayList<>();
    private ArrayList<Lesson> lessons = new ArrayList<>();
    private ArrayList<VocabularyByTopicLesson> vocabularyByTopicLessons = new ArrayList<>();

    public ArrayList<VocabularyByTopicLesson> getVocabularyByTopicLessons() {
        return vocabularyByTopicLessons;
    }

    public void setVocabularyByTopicLessons(ArrayList<VocabularyByTopicLesson> vocabularyByTopicLessons) {
        this.vocabularyByTopicLessons = vocabularyByTopicLessons;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Speak> getSpeaks() {
        return speaks;
    }

    public void setSpeaks(ArrayList<Speak> speaks) {
        this.speaks = speaks;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
}
