package app.entities;

public class CartItem {
    private String  id;
    private String book;
    private Integer number;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return this.book;
    }

    public void setItem(String book) {
        this.book = book;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
