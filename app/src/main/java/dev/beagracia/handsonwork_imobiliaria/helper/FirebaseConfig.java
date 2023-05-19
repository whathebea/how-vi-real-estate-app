package dev.beagracia.handsonwork_imobiliaria.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Classe helper para config do Firebase.
 */
public class FirebaseConfig {
    private static DatabaseReference databaseReference;
    private static FirebaseAuth firebaseAuth;

    /**
     * Recupera uma instancia de FirebaseAuth.
     * Se nao estiver instanciada, inicializa e retorna.
     *
     * @return instancia FirebaseAuth
     */
    public static FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    /**
     * Recupera uma instancia de DatabaseReference.
     * Se nao estiver instanciada, inicializa e retorna.
     *
     * @return instancia DatabaseReference
     */
    public static DatabaseReference getDatabaseReference() {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return databaseReference;
    }


}
