package com.turkcell.rentacar.inventoryService.entities.concretes;

import com.turkcell.rentacar.inventoryService.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "brands")
@SequenceGenerator(
        name = "base_sequence_generator",
        sequenceName = "brand_sequence",
        allocationSize = 1
)
public class Brand extends BaseEntity<Integer> {

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "brand")
//    private List<Model> models;

}
