package com.example.uvgram.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Models.Account;
import com.example.uvgram.Models.AccountResponse;
import com.example.uvgram.Models.BlockResponses.BlockResponse;
import com.example.uvgram.Models.BlockResponses.UnblockResponse;
import com.example.uvgram.Models.EditProfileResponses.EditProfileResponse;
import com.example.uvgram.Models.FollowRequestResponse.FollowStateResponse;
import com.example.uvgram.Models.FollowRequestResponse.RequestResponse;
import com.example.uvgram.Models.FollowResponses.FollowResponse;
import com.example.uvgram.Models.FollowResponses.UnfollowResponse;
import com.example.uvgram.Repositories.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    private final ProfileRepository repository;
    private MutableLiveData<AccountResponse> accountResponse = new MutableLiveData<>();
    private MutableLiveData<EditProfileResponse> editProfileResponse = new MutableLiveData<>();
    private MutableLiveData<FollowResponse> followResponse = new MutableLiveData<>();
    private MutableLiveData<UnfollowResponse> unfollowResponse = new MutableLiveData<>();
    private MutableLiveData<UnblockResponse> unblockResponse = new MutableLiveData<>();
    private MutableLiveData<RequestResponse> requestResponse = new MutableLiveData<>();

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfileRepository(application.getApplicationContext());
    }

    public MutableLiveData<AccountResponse> getAccount() {
        accountResponse = repository.getAccount();
        return accountResponse;
    }

    public MutableLiveData<EditProfileResponse> editProfile(Account account) {
        editProfileResponse = repository.editProfile(account);
        return editProfileResponse;
    }

    public MutableLiveData<FollowResponse> followUser(String username) {
        followResponse = repository.followUser(username);
        return followResponse;
    }

    public MutableLiveData<UnfollowResponse> unfollowUser(String username) {
        unfollowResponse = repository.unfollowUser(username);
        return unfollowResponse;
    }

    public MutableLiveData<BlockResponse> blockUser(String username) {
        MutableLiveData<BlockResponse> blockResponse = repository.blockUser(username);
        return blockResponse;
    }

    public MutableLiveData<UnblockResponse> unblockUser(String username) {
        unblockResponse = repository.unblockUser(username);
        return unblockResponse;
    }

    public MutableLiveData<RequestResponse> getPendingRequests() {
        requestResponse = repository.getPendingRequests();
        return requestResponse;
    }

    public MutableLiveData<FollowStateResponse> acceptRequest(String username) {
        MutableLiveData<FollowStateResponse> acceptResponse = repository.acceptRequest(username);
        return acceptResponse;
    }

    public MutableLiveData<FollowStateResponse> denyRequest(String username) {
        MutableLiveData<FollowStateResponse> denyResponse = repository.acceptRequest(username);
        return denyResponse;
    }

}
