package com.turkcell.rentacar.inventoryService.business.concretes;

import com.turkcell.rentacar.inventoryService.business.abstracts.BrandService;
import com.turkcell.rentacar.inventoryService.business.dtos.requests.Brand.CreateBrandRequest;
import com.turkcell.rentacar.inventoryService.business.dtos.requests.Brand.UpdateBrandRequest;
import com.turkcell.rentacar.inventoryService.business.dtos.responses.Brand.CreatedBrandResponse;
import com.turkcell.rentacar.inventoryService.business.dtos.responses.Brand.GetAllBrandResponse;
import com.turkcell.rentacar.inventoryService.business.dtos.responses.Brand.GetBrandResponseById;
import com.turkcell.rentacar.inventoryService.business.dtos.responses.Brand.UpdatedBrandResponse;
import com.turkcell.rentacar.inventoryService.business.rules.BrandBusinessRules;
import com.turkcell.rentacar.inventoryService.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.inventoryService.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.inventoryService.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {
        brandBusinessRules.brandNameCannotBeDuplicated(createBrandRequest.getName());
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreateDate(LocalDateTime.now());

        Brand savedBrand = brandRepository.save(brand);

        CreatedBrandResponse createdBrandResponse =
                this.modelMapperService.forResponse().map(savedBrand, CreatedBrandResponse.class);

        return createdBrandResponse;
    }

    @Override
    public List<GetAllBrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandResponse> getBrandResponseList = new ArrayList<>();
        for (Brand brand : brands) {
            GetAllBrandResponse getBrandResponse = this.modelMapperService.forResponse().map(brand, GetAllBrandResponse.class);
            getBrandResponseList.add(getBrandResponse);
        }
        return getBrandResponseList;
    }

    @Override
    public GetBrandResponseById getById(int id) {
        brandBusinessRules.brandMustExists(id);
        Brand brand = brandRepository.findById(id).orElse(null);
        return this.modelMapperService.forResponse().map(brand, GetBrandResponseById.class);
    }

    @Override
    public UpdatedBrandResponse update(UpdateBrandRequest updateBrandRequest) {
        brandBusinessRules.brandMustExists(updateBrandRequest.getId());
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        brand.setUpdateDate(LocalDateTime.now());
        Brand updatedBrand = brandRepository.save(brand);
        UpdatedBrandResponse updatedBrandResponse = this.modelMapperService.forResponse().map(updatedBrand, UpdatedBrandResponse.class);
        updatedBrandResponse.setUpdateDate(updatedBrand.getUpdateDate());
        return updatedBrandResponse;
    }

    @Override
    public void delete(int id) {
        brandBusinessRules.brandMustExists(id);
        brandRepository.deleteById(id);
    }
}
