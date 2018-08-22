package com.agnt45.revaplacement.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import com.firebase.client.Firebase;
import com.agnt45.revaplacement.Adapters.PlacementEventAdapter;
import com.agnt45.revaplacement.Classes.PlacementEvent;
import com.agnt45.revaplacement.R;
import com.firebase.client.Firebase;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.internal.FirebaseAppHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCards extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("PlacementEvent");
    private PlacementEventAdapter placementEventAdapter;
    private RecyclerView placementEventRecyclerView;
    public ViewCards() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_view_cards, container, false);
        Firebase.setAndroidContext(getActivity());
        Query query = collectionReference.orderBy("Desc");
        FirestoreRecyclerOptions<PlacementEvent> options = new FirestoreRecyclerOptions.Builder<PlacementEvent>()
                .setQuery(query,PlacementEvent.class).build();
        placementEventAdapter = new PlacementEventAdapter(options);
        placementEventRecyclerView = rootView.findViewById(R.id.placement_event);
        placementEventRecyclerView.setHasFixedSize(true);
        placementEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        placementEventRecyclerView.setAdapter(placementEventAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        placementEventAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        placementEventAdapter.stopListening();
    }
}
