package edu.famu.booking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.booking.model.PaymentInformation;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PaymentInformationService {
    private Firestore firestore;

    public PaymentInformationService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    public PaymentInformation getPaymentInformation(DocumentReference docRef) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(PaymentInformation.class);
    }
}
