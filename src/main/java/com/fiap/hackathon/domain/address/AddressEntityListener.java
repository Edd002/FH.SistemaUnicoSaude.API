package com.fiap.hackathon.domain.address;

import com.fiap.hackathon.domain.address.entity.Address;
import jakarta.persistence.PostLoad;
import org.apache.commons.lang3.SerializationUtils;

public final class AddressEntityListener {

    @PostLoad
    public void postLoad(Address addressEntity) {
        addressEntity.saveState(SerializationUtils.clone(addressEntity));
    }
}