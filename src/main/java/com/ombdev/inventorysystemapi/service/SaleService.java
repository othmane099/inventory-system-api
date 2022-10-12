package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Sale;
import com.ombdev.inventorysystemapi.repository.SaleRepository;
import com.ombdev.inventorysystemapi.request.DeleteRequest;
import com.ombdev.inventorysystemapi.request.ShowRequest;
import com.ombdev.inventorysystemapi.request.sale.SaleRequest;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
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

    public SaleResponse create(Sale sale){
        return Sale.toSaleResponse(saleRepository.save(sale));
    }

    public SaleResponse show(Long id){
        Sale sale = saleRepository.findById(id).get();
        return Sale.toSaleResponse(sale);
    }

    public SaleResponse update(Sale sale) {
        return Sale.toSaleResponse(saleRepository.save(sale));
    }

    public DeleteResponse delete(Long id) {
        saleRepository.deleteById(id);
        return new DeleteResponse("Sale deleted successfully :)");
    }
}
