package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Sale;
import com.ombdev.inventorysystemapi.repository.SaleRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.sale.CreateSaleRequest;
import com.ombdev.inventorysystemapi.request.sale.UpdateSaleRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.sale.CreateSaleResponse;
import com.ombdev.inventorysystemapi.response.sale.SaleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public List<SaleResponse> index(){
        return saleRepository.findAll()
                .stream()
                .map(Sale::toSaleResponse)
                .collect(Collectors.toList());
    }

    public CreateSaleResponse create(CreateSaleRequest request){
        Sale sale = CreateSaleRequest.toEntity(request);
        return Sale.toCreateSaleResponse(saleRepository.save(sale));
    }

    public SaleResponse show(ShowRequest request){
        Sale sale = saleRepository.findById(request.id()).get();
        return Sale.toSaleResponse(sale);
    }

    public SaleResponse update(UpdateSaleRequest request) {
        Sale sale = UpdateSaleRequest.toEntity(request);
        return Sale.toSaleResponse(saleRepository.save(sale));
    }

    public DeleteResponse delete(DeleteRequest request) {
        saleRepository.deleteById(request.id());
        return new DeleteResponse("Sale deleted successfully :)");
    }
}
