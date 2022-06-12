package TP.LAB5.demo.repository;

import TP.LAB5.demo.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument,Integer> {

    List<Instrument> findAllByShopId(Integer shopId);
}
