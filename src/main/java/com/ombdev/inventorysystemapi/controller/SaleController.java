package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.sale.SaleRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.sale.SaleResponse;
import com.ombdev.inventorysystemapi.service.SaleService;
import com.ombdev.inventorysystemapi.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(Constants.CLIENT_BASE_URL)
public class SaleController {

    private final SaleService saleService;

    @GetMapping("/seller/sales")
    public Page<SaleResponse> index(@RequestParam String keyword,
                                    @RequestParam int page,
                                    @RequestParam int size,
                                    @RequestParam SortBy sortBy){
        return saleService.index(keyword, page, size, sortBy);
    }

    @PostMapping("/seller/sales/store")
    public SaleResponse store(@RequestBody SaleRequest request){
        return saleService.store(SaleRequest.toEntity(request));
    }

    @GetMapping("/seller/sales/show")
    public SaleResponse show(@RequestBody ShowRequest request){
        return saleService.show(request.id());
    }

    @DeleteMapping("/seller/sales/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return saleService.delete(request.id());
    }

    @PutMapping("/seller/sales/update")
    public SaleResponse update(@RequestBody SaleRequest request){
        return saleService.update(SaleRequest.toEntity(request));
    }
}
