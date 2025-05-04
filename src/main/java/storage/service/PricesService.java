package storage.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import storage.entity.Prices;
import storage.repository.PricesRepository;

@Service
public class PricesService {
	
	PricesRepository pricesRepository;
	
	PricesService(PricesRepository pricesRepository){
		this.pricesRepository = pricesRepository;
	}

	public Prices setPrices(Prices prices) {
		Prices savedPrices = new Prices();
		savedPrices.setHeightPrice(prices.getHeightPrice());
		savedPrices.setLengthPrice(prices.getLengthPrice());
		savedPrices.setWidthPrice(prices.getWidthPrice());
		return pricesRepository.save(savedPrices);
	}
	
	public Optional<Prices> getPrices(Integer id) {
		return pricesRepository.findById(id);
	}
	
	public Optional<Prices> findById(Integer id) {
		return pricesRepository.findById(id);
	}

}
