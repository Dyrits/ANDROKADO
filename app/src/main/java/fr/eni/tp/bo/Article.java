package fr.eni.tp.bo;


public class Article {
    private String name, description, url;
    private double price, rating;
    private boolean bought;

    public Article(String name, String description, String url, double price, double rating, boolean bought) {
        setName(name);
        setDescription(description);
        setUrl(url);
        setPrice(price);
        setRating(rating);
        setBought(bought);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceToString() {
        return String.valueOf(price) + "€";
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", bought=" + bought +
                '}';
    }
}

