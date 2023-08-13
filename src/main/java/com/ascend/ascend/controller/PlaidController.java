package com.ascend.ascend.controller;

import com.ascend.ascend.service.UserService;
import com.plaid.client.ApiClient;
import com.plaid.client.model.*;
import com.plaid.client.request.PlaidApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.util.Arrays;
import java.util.HashMap;

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
       } catch (Exception e) {
           System.out.println("Failed to create link token");
           return null;
       }
    }

    @PostMapping("/{user_id}/exchange_public_token")
    public void exchangePublicTokenForAccessToken(@RequestBody String publicToken, @PathVariable Long user_id) {
        ItemPublicTokenExchangeRequest itemPublicTokenExchangeRequest = new ItemPublicTokenExchangeRequest()
                .publicToken(publicToken);

        Response<ItemPublicTokenExchangeResponse> itemPublicTokenExchangeResponse = null;

        try {
            itemPublicTokenExchangeResponse = plaidClient
                    .itemPublicTokenExchange(itemPublicTokenExchangeRequest)
                    .execute();
            if (itemPublicTokenExchangeResponse.isSuccessful()) {
                String accessToken = itemPublicTokenExchangeResponse.body().getAccessToken();
                userService.savePlaidAccessToken(user_id, accessToken);
            } else {
                System.out.println("Failed to exchange public token for access token: " + itemPublicTokenExchangeResponse.errorBody().string());
            }
        } catch (Exception e) {
            System.out.println("Failed to exchange public token for access token: " + e.toString());
        }
    }
}
