package com.fiap.hackathon.domain.address;

import com.fiap.hackathon.domain.address.entity.Address;
import com.fiap.hackathon.global.base.BaseServiceGateway;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceGateway extends BaseServiceGateway<IAddressRepository, Address> {

    @Override
    public Address findByHashId(String hashId) {
        return super.findByHashId(hashId, String.format("O endereço com hash id %s não foi encontrado.", hashId));
    }
}