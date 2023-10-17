package edu.famu.booking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import model.Hotels;
import model.PaymentInformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class HotelsService {
    private Firestore firestore;

    public HotelsService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    private Hotels documentSnapshotToHotels(DocumentSnapshot document)
    {
        Hotels hotels = null;
        if(document.exists()){
            List<String> amenities = document.get("amenities", List.class);
            hotels = new Hotels(document.getId(),document.getString("name"),document.getString("description"),document.getDouble("rating"),document.getString("address"), document.getString("contactInformation"),amenities, document.getTimestamp("createdAt").toProto());
        }
        return document.toObject(Hotels.class);

    }
    public ArrayList<Hotels> getAllHotels() throws ExecutionException, InterruptedException {
        CollectionReference hotelsCollection = firestore.collection("Hotels");
        ApiFuture<QuerySnapshot> future = hotelsCollection.get();

        ArrayList<Hotels> hotelsList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments())
        {
            Hotels hotels = documentSnapshotToHotels(document);
            if(hotels != null)
                hotelsList.add(hotels);
        }
        return hotelsList;
    }

    public Hotels getHotelsById(String hotelID) throws ExecutionException, InterruptedException {
        CollectionReference hotelsCollection = firestore.collection("Hotels");
        ApiFuture<DocumentSnapshot> future = hotelsCollection.document(hotelID).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToHotels(document);
    }
}
