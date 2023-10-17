package edu.famu.booking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.booking.model.Rooms;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class RoomsService {
    private Firestore firestore;

    public RoomsService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    private Rooms documentSnapshotToRooms(DocumentSnapshot document)
    {
        Rooms rooms = null;
        if(document.exists()){
            ArrayList<String> images = (ArrayList<String>) document.get("images");
            rooms = new Rooms(document.getId(),document.getString("hotelID"),document.getString("roomType"),document.getDouble("price"),document.getDouble("capacity").intValue(), document.getString("description"),document.getString("availability"),images, document.getTimestamp("createdAt"));
        }
        return rooms;

    }
    public ArrayList<Rooms> getAllRooms() throws ExecutionException, InterruptedException {
        CollectionReference roomsCollection = firestore.collection("Rooms");
        ApiFuture<QuerySnapshot> future = roomsCollection.get();

        ArrayList<Rooms> roomsList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments())
        {
            Rooms rooms = documentSnapshotToRooms(document);
            if(rooms != null)
                roomsList.add(rooms);
        }
        return roomsList;
    }

    public Rooms getRoomsById(String roomID) throws ExecutionException, InterruptedException {
        CollectionReference roomsCollection = firestore.collection("Rooms");
        ApiFuture<DocumentSnapshot> future = roomsCollection.document(roomID).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToRooms(document);
    }
}
