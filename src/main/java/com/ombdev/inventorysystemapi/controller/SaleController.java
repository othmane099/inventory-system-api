package com.ombdev.inventorysystemapi.controller;

import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.sale.CreateSaleRequest;
import com.ombdev.inventorysystemapi.request.sale.UpdateSaleRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.sale.CreateSaleResponse;
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
    public CreateSaleResponse create(@RequestBody CreateSaleRequest request){
        return saleService.create(request);
    }

    @GetMapping("/seller/sales/show")
    public SaleResponse show(ShowRequest request){
        return saleService.show(request);
    }

    @DeleteMapping("/seller/sales/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest request){
        return saleService.delete(request);
    }

    @PutMapping("/seller/sales/update")
    public SaleResponse update(@RequestBody UpdateSaleRequest request){
        return saleService.update(request);
    }
}
