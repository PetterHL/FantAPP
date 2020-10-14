package no.ntnu.PetterHL;

public class Item {
    private String title;
    private String description;
    private String price;
    private Long id;

    public Item(String name) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Long getID() {
        return id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }
    @Override
    public String toString() {
        return "Item{" +
                ", itemName='" + title + '\'' +
        ", descriptionView='" + description + '\'' +
        ", price=" + price +
                '}';
    }
}
