package com.example.uvgram.Repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.Account;
import com.example.uvgram.Models.AccountResponse;
import com.example.uvgram.Models.BlockResponses.BlockResponse;
import com.example.uvgram.Models.BlockResponses.UnblockResponse;
import com.example.uvgram.Models.EditProfileResponses.EditProfileResponse;
import com.example.uvgram.Models.FollowResponses.FollowResponse;
import com.example.uvgram.Models.FollowResponses.UnfollowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    private final MutableLiveData<FollowResponse> followResponse = new MutableLiveData<>();
    private final MutableLiveData<UnfollowResponse> unfollowResponse = new MutableLiveData<>();
    private final MutableLiveData<BlockResponse> blockResponse = new MutableLiveData<>();
    private final MutableLiveData<UnblockResponse> unblockResponse = new MutableLiveData<>();
    private final MutableLiveData<AccountResponse> accountResponse = new MutableLiveData<>();
    private final MutableLiveData<EditProfileResponse> editProfileResponse = new MutableLiveData<>();


    SharedPreferences sharedPreferences;
    Context context;

    public ProfileRepository(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public MutableLiveData<FollowResponse> followUser(String username) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<FollowResponse> call = UVGramAPIAdapter
                .getApiService()
                .followUser("Bearer " + accessToken, username);

        call.enqueue(new Callback<FollowResponse>() {
            @Override
            public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                if (response.isSuccessful()) {
                    followResponse.setValue(response.body());
                } else {
                    FollowResponse responseCode = new FollowResponse();
                    responseCode.setHttpCode(response.code());
                    followResponse.setValue(responseCode);
                }
            }
            @Override
            public void onFailure(Call<FollowResponse> call, Throwable t) {

            }
        });
        return followResponse;
    }

    public MutableLiveData<UnfollowResponse> unfollowUser(String username) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<UnfollowResponse> call = UVGramAPIAdapter
                .getApiService()
                .unfollowUser("Bearer " + accessToken, username);

        call.enqueue(new Callback<UnfollowResponse>() {
            @Override
            public void onResponse(Call<UnfollowResponse> call, Response<UnfollowResponse> response) {
                if (response.isSuccessful()) {
                    unfollowResponse.setValue(response.body());
                    unfollowResponse.getValue().setHttpCode(response.code());
                } else {
                    UnfollowResponse responseCode = new UnfollowResponse();
                    responseCode.setHttpCode(response.code());
                    unfollowResponse.setValue(responseCode);
                }
            }
            @Override
            public void onFailure(Call<UnfollowResponse> call, Throwable t) {

            }
        });
        return unfollowResponse;
    }

    public MutableLiveData<BlockResponse> blockUser(String username) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<BlockResponse> call = UVGramAPIAdapter
                .getApiService()
                .blockUser("Bearer " + accessToken, username);

        call.enqueue(new Callback<BlockResponse>() {
            @Override
            public void onResponse(Call<BlockResponse> call, Response<BlockResponse> response) {
                if (response.isSuccessful()) {
                    blockResponse.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<BlockResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return blockResponse;
    }

    public MutableLiveData<UnblockResponse> unblockUser(String username) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<UnblockResponse> call = UVGramAPIAdapter
                .getApiService()
                .unblockUser("Bearer " + accessToken, username);

        call.enqueue(new Callback<UnblockResponse>() {
            @Override
            public void onResponse(Call<UnblockResponse> call, Response<UnblockResponse> response) {
                if (response.isSuccessful()) {
                    unblockResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UnblockResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return unblockResponse;
    }

    public MutableLiveData<AccountResponse> getAccount() {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<AccountResponse> call = UVGramAPIAdapter
                .getApiService()
                .getAccount("Bearer " + accessToken);

        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    accountResponse.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return accountResponse;
    }

    // TODO: Implementar manejo de errores

    public MutableLiveData<EditProfileResponse> editProfile(Account account) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<EditProfileResponse> call = UVGramAPIAdapter
                .getApiService().editProfile(
                        "Bearer " + accessToken,
                        account.getName(),
                        account.getPresentation(),
                        account.getUsername(),
                        account.getPhoneNumber(),
                        account.getEmail(),
                        account.getBirthday(),
                        "MASCULINO",
                        1
                );

        call.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if (response.isSuccessful()) {
                    editProfileResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return editProfileResponse;
    }

}
