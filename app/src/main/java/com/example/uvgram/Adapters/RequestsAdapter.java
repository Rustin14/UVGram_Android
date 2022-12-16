package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uvgram.Models.FollowRequestResponse.Request;
import com.example.uvgram.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestsHolder> {

    private List<Request> requestList = new ArrayList<>();
    OnItemClickListener acceptOnItemClickListener;
    OnItemClickListener rejectOnItemClickListener;
    Context context;

    public interface OnItemClickListener {
        void onItemClick(Request request);
    }

    public void setOnAcceptItemClickListener(OnItemClickListener onItemClickListener) {
        this.acceptOnItemClickListener = onItemClickListener;
    }

    public void setOnRejectItemClickListener(OnItemClickListener onItemClickListener) {
        this.rejectOnItemClickListener = onItemClickListener;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RequestsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View requestView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.follow_request_item, parent, false);
        return new RequestsHolder(requestView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsHolder holder, int position) {
        Request currentRequest = requestList.get(position);
        holder.bind(currentRequest, acceptOnItemClickListener, rejectOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }


    class RequestsHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView usernameTextView;
        private MaterialButton acceptButton;
        private MaterialButton rejectButton;

        public RequestsHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            rejectButton = itemView.findViewById(R.id.rejectButton);
        }

        public void bind(final Request request, OnItemClickListener acceptListener,
                         OnItemClickListener rejectListener) {
            nameTextView.setText(request.getName());
            usernameTextView.setText(request.getUsername());

            acceptButton.setOnClickListener(view -> {
                acceptListener.onItemClick(request);
            });

            rejectButton.setOnClickListener(view -> {
                rejectListener.onItemClick(request);
            });
        }

    }

}
