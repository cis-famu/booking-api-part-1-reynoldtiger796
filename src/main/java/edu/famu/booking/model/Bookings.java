package edu.famu.booking.model;

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
public class Bookings {
    private @Nullable String bookingID;
    private String userID;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private float totalPrice;
    private String status;
    private String paymentStatus;
    private Timestamp createdAt;

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = com.google.cloud.Timestamp.fromProto(Timestamps.parse(createdAt)).toProto();
    }
    public void setCheckInDate(String checkInDate) throws ParseException {
        this.checkInDate = com.google.cloud.Timestamp.fromProto(Timestamps.parse(checkInDate)).toProto();
    }
    public void setCheckOutDate(String checkOutDate) throws ParseException {
        this.checkOutDate = com.google.cloud.Timestamp.fromProto(Timestamps.parse(checkOutDate)).toProto();
    }
}
