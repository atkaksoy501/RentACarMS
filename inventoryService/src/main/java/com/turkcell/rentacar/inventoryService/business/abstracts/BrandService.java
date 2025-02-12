package com.turkcell.rentacar.inventoryService.business.abstracts;

import com.turkcell.rentacar.inventoryService.business.dtos.requests.Brand.*;
import com.turkcell.rentacar.inventoryService.business.dtos.responses.Brand.*;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest brand);
    List<GetAllBrandResponse> getAll();
    GetBrandResponseById getById(int id);
    UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest);
    void delete(int id);

}
