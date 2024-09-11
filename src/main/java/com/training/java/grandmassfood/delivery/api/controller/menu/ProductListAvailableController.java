package com.training.java.grandmassfood.delivery.api.controller.menu;

import com.training.java.grandmassfood.delivery.api.dao.products.dto.menu.ProductListAvailableResponse;
import com.training.java.grandmassfood.delivery.api.exception.apiresponse.BadRequestApiResponse;
import com.training.java.grandmassfood.delivery.api.exception.apiresponse.NotFoundApiResponse;
import com.training.java.grandmassfood.delivery.api.service.menu.ProductListAvailableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@Tag(name = "Menu Controllers")
public class ProductListAvailableController {
    private final ProductListAvailableService productListAvailableService;

    @GetMapping("/plain/text")
    @Operation(summary = "Get list products in plain text")
    @NotFoundApiResponse
    @BadRequestApiResponse
    @ResponseStatus(HttpStatus.OK)
    public ProductListAvailableResponse getListProductsAvailableText(){
        return productListAvailableService.getListProductsAvailableText();
    }
    @GetMapping("/application/pdf")
    @Operation(summary = "Get list products in plain text")
    @NotFoundApiResponse
    @BadRequestApiResponse
    @ResponseStatus(HttpStatus.OK)
    public ProductListAvailableResponse getListProductsAvailablePdf(@PathVariable String pdf){
        return productListAvailableService.getListProductsAvailablePdf(pdf);
    }
    @GetMapping("/vnd.openxmlformats-officedocument.wordprocessingml.document")
    @Operation(summary = "Get list products in plain text")
    @NotFoundApiResponse
    @BadRequestApiResponse
    @ResponseStatus(HttpStatus.OK)
    public ProductListAvailableResponse getListProductsAvailableWord(@PathVariable String word){
        return productListAvailableService.getListProductsAvailableWord(word);
    }
}
