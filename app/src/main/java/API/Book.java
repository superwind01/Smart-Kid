package API;

public class Book {
    private int idBook;
    private String nameBook;
    private String symbol;
    private Boolean status;
    private String cpitClassBook;
    private String cpitTopicByClassBook;

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
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

    public String getCpitClassBook() {
        return cpitClassBook;
    }

    public void setCpitClassBook(String cpitClassBook) {
        this.cpitClassBook = cpitClassBook;
    }

    public String getCpitTopicByClassBook() {
        return cpitTopicByClassBook;
    }

    public void setCpitTopicByClassBook(String cpitTopicByClassBook) {
        this.cpitTopicByClassBook = cpitTopicByClassBook;
    }
}
