package API;

public class Book {
    private int idbook;
    private String image;
    private String nameBook;
    private String symbol;
    private Boolean status;
//    private String cpitClassBook;
//    private String cpitTopicByClassBook;

    public int getIdBook() {
        return idbook;
    }

    public void setIdBook(int idBook) {
        this.idbook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public String getCpitClassBook() {
//        return cpitClassBook;
//    }
//
//    public void setCpitClassBook(String cpitClassBook) {
//        this.cpitClassBook = cpitClassBook;
//    }
//
//    public String getCpitTopicByClassBook() {
//        return cpitTopicByClassBook;
//    }
//
//    public void setCpitTopicByClassBook(String cpitTopicByClassBook) {
//        this.cpitTopicByClassBook = cpitTopicByClassBook;
//    }
}
