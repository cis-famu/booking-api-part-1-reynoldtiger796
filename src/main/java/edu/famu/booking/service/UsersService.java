package edu.famu.booking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import model.PaymentInformation;
import model.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class UsersService {
    private Firestore firestore;

    public UsersService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    private Users documentSnapshotToUsers(DocumentSnapshot document)
    {
        Users users = null;
        if(document.exists()){

            users = new Users(document.getId(),document.getString("name"),document.getString("email"),document.getString("phone"), (DocumentReference)document.get("paymentInformation").toObject(PaymentInformation.class), (document.getTimestamp("createdAt")));
        }
        return document.toObject(Users.class);

    }
    public ArrayList<Users> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = usersCollection.get();

        ArrayList<Users> usersList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments())
        {
            Users users = documentSnapshotToUsers(document);
            if(users != null)
                usersList.add(users);
        }
        return usersList;
    }

    public Users getUsersById(String userID) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = usersCollection.document(userID).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToUsers(document);
    }
}
