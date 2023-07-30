package com.ascend.ascend.controller;

import com.ascend.ascend.service.UserService;
import com.plaid.client.model.CountryCode;
import com.plaid.client.model.LinkTokenCreateRequest;
import com.plaid.client.model.LinkTokenCreateRequestUser;
import com.plaid.client.model.LinkTokenCreateResponse;
import com.plaid.client.request.PlaidApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;


import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/plaid")
public class PlaidController {
   @Autowired
   UserService userService;

   private PlaidApi plaidClient;

   @Value("#{environment.PLAID_SECRET}")
   private String plaidSecret;
   private PlaidController(UserService userService) {}

   @PostMapping("/{user_id}/get_link_token")
   public String getLinkToken(@PathVariable String user_id) {
       LinkTokenCreateRequestUser linkTokenCreateRequestUser = new LinkTokenCreateRequestUser()
               .clientUserId(user_id.toString());

       LinkTokenCreateRequest linkTokenCreateRequest = new LinkTokenCreateRequest()
               .secret(plaidSecret)
               .clientName("Ascend")
               .language("en")
               .clientId(user_id.toString())
               .countryCodes(Arrays.asList(CountryCode.US));
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
}
