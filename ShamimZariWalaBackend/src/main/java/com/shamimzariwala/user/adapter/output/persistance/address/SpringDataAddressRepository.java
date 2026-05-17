package com.shamimzariwala.user.adapter.output.persistance.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAddressRepository extends JpaRepository<AddressEntity, Long> {
    Page<AddressEntity> findAllByUserId(Long userId, Pageable pageable);
}
