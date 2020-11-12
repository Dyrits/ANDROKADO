package fr.eni.tp.entities;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "price")
    public double price;

    @ColumnInfo(name = "rating")
    public float rating;

    @ColumnInfo(name = "bought")
    public boolean bought;

    protected Article(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        url = in.readString();
        price = in.readDouble();
        rating = in.readFloat();
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

    public String getPriceToString() {
        return String.valueOf(price) + "€";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeDouble(price);
        dest.writeFloat(rating);
        dest.writeByte((byte) (bought ? 1 : 0));
    }
}

