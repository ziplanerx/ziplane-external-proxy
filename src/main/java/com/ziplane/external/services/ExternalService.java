package com.ziplane.external.services;

import com.google.gson.Gson;
import com.ziplane.external.dto.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigDecimal;
import java.util.Optional;

import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class ExternalService {
    RestTemplate restTemplate = new RestTemplate();

    public VATResponse verifyVAT(String vatNumber) {
        String verifyVATUrl = "https://api.service.hmrc.gov.uk/organisations/vat/check-vat-number/lookup/" + vatNumber;
        VATResponse vatResponse = restTemplate.getForObject(
                verifyVATUrl, VATResponse.class);
        return vatResponse;
    }

    public String getToken(String code) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=authorization_code&client_id=sandbox-sandboxziplane-f39ba5&client_secret=947544cd-1435-4c25-a573-53ddfce4d8eb&redirect_uri=http://localhost:4200/register/doctor&code=" + code);
        Request request = new Request.Builder()
                .url("https://auth.truelayer-sandbox.com/connect/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        System.out.println(responseString);
        Gson gson = new Gson();
        var resp = gson.toJson(responseString);
        Token entity = gson.fromJson(responseString, Token.class);
        return getAccountDetails(entity.getAccess_token());
    }

    public String getAccountDetails(String bearerToken) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"name\": \"John Smith\"\n}");
        Request request = new Request.Builder()
                .url("https://api.truelayer-sandbox.com/verification/v1/verify")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String accountData = response.body().string();
        System.out.println(accountData);
        return accountData;

    }

    public ReviewResponse getGoogleReviews() {
        ReviewResponse reviewResponse = new ReviewResponse();
        String url = "https://serpapi.com/search?engine=google_maps&q=mango+pharmacy+Edgware&ll=@51.61157608,-0.2800325751,14z&hl=en&type=search&api_key=007519ddf00c7b81bfe9c3e914ac0c35300ad3c209ef7d4e02ae2202278a0d1b";
        GoogleResponse response = restTemplate.getForObject(url, GoogleResponse.class);
        if (response.getPlace_results() != null) {
            reviewResponse = restTemplate.getForObject(response.getPlace_results().getPlace_id_search() + "&api_key=007519ddf00c7b81bfe9c3e914ac0c35300ad3c209ef7d4e02ae2202278a0d1b", ReviewResponse.class);

        } else {
            Optional<LocalResults> localResults = response.local_results.stream().filter(a -> a.getAddress().contains("Edgware")).findFirst();
            if (localResults.isPresent()) {
                reviewResponse = restTemplate.getForObject(localResults.get().getPlace_id_search() + "&api_key=007519ddf00c7b81bfe9c3e914ac0c35300ad3c209ef7d4e02ae2202278a0d1b", ReviewResponse.class);

            }
        }

        return reviewResponse;
    }

    public BigDecimal getNhsReview(String odsCode) throws IOException {
        BigDecimal score = new BigDecimal("0");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.nhs.uk/comments/ratings?odsCode=" + odsCode)
                .method("GET", null)
                .addHeader("subscription-key", "f2f75684ce194528b48bcdd829949793")
                .build();
        Response response = client.newCall(request).execute();
        String a = response.body().string();
        Gson gson = new Gson();
        var resp = gson.toJson(a);
        NhsReviewResponse nhsReviewResponse = gson.fromJson(a, NhsReviewResponse.class);
        if (nhsReviewResponse != null && !CollectionUtils.isEmpty(nhsReviewResponse.getAggregatedRatings()))
            score = nhsReviewResponse.getAggregatedRatings().get(0).getAverageScore();
        return score;
    }
}
