package com.ombdev.inventorysystemapi.service;

import com.ombdev.inventorysystemapi.model.Sale;
import com.ombdev.inventorysystemapi.model.SortBy;
import com.ombdev.inventorysystemapi.repository.SaleRepository;
import com.ombdev.inventorysystemapi.response.DeleteResponse;
import com.ombdev.inventorysystemapi.response.sale.SaleResponse;
import com.ombdev.inventorysystemapi.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public Page<SaleResponse> index(String keyword, int page, int size, SortBy sortBy){

        Page<Sale> salesPage = saleRepository.findAllBySaleCodeContainingOrderByIdDesc(keyword, PageRequest.of(page, size));

        List<SaleResponse> sales = salesPage
                .stream()
                .map(Sale::toSaleResponse)
                .collect(Collectors.toList());

        System.out.println(sales.size());

        return new PageImpl<>(sales, PageRequest.of(page, size), salesPage.getTotalElements());
    }

    public SaleResponse store(Sale sale){
        int randomNumber = new Random().nextInt(9000) + 1000;
        sale.setSaleCode("s"+ randomNumber);
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
