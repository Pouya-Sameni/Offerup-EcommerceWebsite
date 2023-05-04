package com.offerup.controller.dtos.catalogueDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private ObjectId id;
    private String itemName;
    private String description;
    private List<Shipping> shippingOptions = new ArrayList<>();

    @Override
    public int hashCode ()
    {
       return  this.getId().hashCode();

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (!this.getId().toString().equals(other.getId().toString()))
            return false;
        return true;
    }
}
