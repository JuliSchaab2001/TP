package TP.LAB5.demo.services;

import TP.LAB5.demo.domain.Shop;
import TP.LAB5.demo.repository.ShopRepository;
import TP.LAB5.demo.utils.PostResponse;
import org.hibernate.cfg.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static TP.LAB5.demo.utils.EntityURLBuilder.buildURL;
@Service
public class ShopService {

    private static String path = "shop";
    @Autowired
    private ShopRepository shopRepository;


    public ResponseEntity addShop(Shop shop) {
        Shop s = shopRepository.save(shop);

        return  ResponseEntity.status(HttpStatus.CREATED).location(buildURL(path, s.getId().toString())).build();
    }

    public ResponseEntity<List<Shop>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(shopRepository.findAll());
    }

    public ResponseEntity<Shop> getById(Integer shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "this shop not exist"));
        return ResponseEntity.status(HttpStatus.OK).body(shop);
    }
}
