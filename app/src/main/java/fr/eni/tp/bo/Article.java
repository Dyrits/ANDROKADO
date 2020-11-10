package fr.eni.tp.bo;


import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
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

    protected Article(Parcel in) {
        name = in.readString();
        description = in.readString();
        url = in.readString();
        price = in.readDouble();
        rating = in.readDouble();
        bought = in.readByte() != 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeDouble(price);
        dest.writeDouble(rating);
        dest.writeByte((byte) (bought ? 1 : 0));
    }
}

