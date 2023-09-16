package com.ascend.ascend.controller;

import com.ascend.ascend.service.UserService;
import com.plaid.client.ApiClient;
import com.plaid.client.model.*;
import com.plaid.client.request.PlaidApi;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Response;


import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/plaid")
public class PlaidController {
    UserService userService;

    @Value("#{environment.PLAID_SECRET}")
    private String plaidSecret;

    @Value("#{environment.PLAID_CLIENT_ID}")
    private String plaidClientId;

    private PlaidApi plaidClient;

    private PlaidController(UserService userService) {
        this.userService = userService;

        HashMap<String, String> apiKeys = new HashMap<String, String>();
        apiKeys.put("clientId", plaidClientId);
        apiKeys.put("secret", plaidSecret);

        ApiClient apiClient = new ApiClient(apiKeys);
        apiClient.setPlaidAdapter(ApiClient.Sandbox);
        plaidClient = apiClient.createService(PlaidApi.class);
    }

    @PostMapping("/{user_id}/get_link_token")
    public String getLinkToken(@PathVariable String user_id) {
       LinkTokenCreateRequestUser linkTokenCreateRequestUser = new LinkTokenCreateRequestUser()
               .clientUserId(user_id.toString());

       LinkTokenCreateRequest linkTokenCreateRequest = new LinkTokenCreateRequest()
               .secret(plaidSecret)
               .clientName("Ascend")
               .language("en")
               .clientId(plaidClientId)
               .countryCodes(Arrays.asList(CountryCode.US))
               .user(linkTokenCreateRequestUser);

       Response<LinkTokenCreateResponse> linkTokenCreateResponse = null;
       try {
           linkTokenCreateResponse = plaidClient
                   .linkTokenCreate(linkTokenCreateRequest)
                   .execute();
           if (linkTokenCreateResponse.isSuccessful()) {
               return linkTokenCreateResponse.body().getLinkToken();
           } else {
               System.out.println("Failed to create link token: " + linkTokenCreateResponse.errorBody().string());
               return null;
           }
       } catch (IOException e) {
           System.out.println("Failed to create link token");
           return null;
       }
    }

    @PostMapping("/{user_id}/exchange_public_tokenreceive_public_token")
    public String exchangePublicToken(@RequestBody String publicToken @PathVariable String user_id) {
        ItemPublicTokenExchangeRequest itemPublicTokenExchangeRequest = new ItemPublicTokenExchangeRequest()
            .publicToken(publicToken);
        
        ItemPublicTokenExchangeResponse itemPublicTokenExchangeResponse = plaidClient
            .itemPublicTokenExchange(itemPublicTokenExchangeRequest)
            .execute();
    }
}

