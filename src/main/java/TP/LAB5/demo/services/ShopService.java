package TP.LAB5.demo.services;

import TP.LAB5.demo.domain.Shop;
import TP.LAB5.demo.repository.ShopRepository;
import TP.LAB5.demo.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static TP.LAB5.demo.utils.EntityURLBuilder.buildURL;
@Service
public class ShopService {

    private static String path = "shop";
    @Autowired
    private ShopRepository shopRepository;


    public PostResponse addShop(Shop shop) {
        Shop s = shopRepository.save(shop);

        return PostResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .link(buildURL(path, s.getId().toString()))
                .build();
    }

    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    public Shop getById(Integer shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "this shop not exist"));
    }
}
