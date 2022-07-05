package com.hoanpd.cmc2.reponsitory;

import com.hoanpd.cmc2.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT e FROM Customer e")
    Page<Customer> findCustomers(Pageable pageable);

}
