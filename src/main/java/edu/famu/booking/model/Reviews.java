package edu.famu.booking.model;

import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.text.ParseException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {
    private @Nullable String reviewID;
    private String hotelID;
    private String userID;
    private float rating;
    private String comment;
    private Timestamp date;
    private Timestamp createdAt;

    public void setDate(String date) throws ParseException {
        this.date = com.google.cloud.Timestamp.fromProto(Timestamps.parse(date)).toProto();
    }
    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = com.google.cloud.Timestamp.fromProto(Timestamps.parse(createdAt)).toProto();
    }

}
