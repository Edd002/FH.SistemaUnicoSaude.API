package com.fiap.hackathon.domain.address;

import com.fiap.hackathon.domain.address.entity.Address;
import com.fiap.hackathon.global.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends IBaseRepository<Address> {
}