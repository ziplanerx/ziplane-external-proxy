package com.ziplane.external.controller;


import com.ziplane.external.dto.NhsReviewResponse;
import com.ziplane.external.dto.ReviewResponse;
import com.ziplane.external.dto.VATResponse;
import com.ziplane.external.services.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;


@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = {"*", "http://localhost:4200"})
public class workflowController {
    @Autowired
    private ExternalService externalService;

    @GetMapping("vat/{vatNumber}")
    public ResponseEntity<VATResponse> verifyVAT(@PathVariable String vatNumber) {
        VATResponse response = externalService.verifyVAT(vatNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("token/{code}")
    public ResponseEntity<String> token(@PathVariable String code) throws IOException {
        String response = externalService.getToken(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping("google/reviews")
    public ResponseEntity<ReviewResponse> getGoogleReview() {
        ReviewResponse reviewResponse = externalService.getGoogleReviews();
        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping("nhs/reviews/{odsCode}")
    public ResponseEntity<BigDecimal> getNhsReview(@PathVariable String odsCode) throws IOException {
        BigDecimal reviewResponse = externalService.getNhsReview(odsCode);
        return ResponseEntity.ok(reviewResponse);
    }

}
