package model;

import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {
    private @Nullable String roomID;
    private String hotelID;
    private String roomType;
    private double price;
    private int capacity;
    private String description;
    private String availability;
    private String[] images;
    private Timestamp createdAt;

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = com.google.cloud.Timestamp.fromProto(Timestamps.parse(createdAt)).toProto();
    }
}
