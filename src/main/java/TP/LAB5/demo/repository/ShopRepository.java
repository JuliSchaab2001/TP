package TP.LAB5.demo.repository;

import TP.LAB5.demo.domain.Shop;
import com.sun.istack.Interned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Integer> {
}
