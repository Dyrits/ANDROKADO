package fr.eni.tp.bo;


import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article implements Parcelable {
    private String name, description, url;
    private double price;
    int rating;
    private boolean bought;

    protected Article(Parcel in) {
        name = in.readString();
        description = in.readString();
        url = in.readString();
        price = in.readDouble();
        rating = in.readInt();
        bought = in.readByte() != 0;
    }

    public Article(String name, String description, String url, double price, int rating) {
        this(name, description, url, price, rating, false);
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

    public String getPriceToString() {
        return String.valueOf(price) + "€";
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
        dest.writeInt(rating);
        dest.writeByte((byte) (bought ? 1 : 0));
    }
}

