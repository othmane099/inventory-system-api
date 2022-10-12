package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.model.Sale;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.sale.SaleRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.sale.SaleResponse;
import com.ombdev.inventorysystemapi.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping("/seller/sales")
    public List<SaleResponse> index(){
        return saleService.index();
    }

    @PostMapping("/seller/sales/create")
    public SaleResponse create(@RequestBody SaleRequest request){
        return saleService.create(SaleRequest.toEntity(request));
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
