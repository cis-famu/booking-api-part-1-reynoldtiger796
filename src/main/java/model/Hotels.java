package model;

import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotels {

private @Nullable String hotelID;
private String name;
private String description;
private double rating;
private String address;
private String contactInformation;
private List<String> amenities;
private Timestamp createdAt;

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = com.google.cloud.Timestamp.fromProto(Timestamps.parse(createdAt)).toProto();
    }
}
