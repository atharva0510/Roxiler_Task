package com.jspiders.roxilertask.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jspiders.roxilertask.pojo.Product;
import com.jspiders.roxilertask.repository.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping(path = "/init")
	public String initializeDatabase() {
		RestTemplate restTemplate = new RestTemplate();
		Product[] products = restTemplate.getForObject("https://s3.amazonaws.com/roxiler.com/product_transaction.json", Product[].class);
		List<Product> list = new ArrayList<Product>();
		for (Product p : products) {
			list.add(p);
		}
		productRepository.saveAll(list);
		return "Database initialized";
	}

	@GetMapping(path = "/products/{search}")
	public List<Product> fetchProducts(@PathVariable String search) {
		try {
			return productRepository.findProductByPrice(Double.parseDouble(search));
		} catch (Exception e) {
			return productRepository.findProductByTitleorDescription(search);
		}		
	}
	
	@GetMapping(path = "stats")
	public double[] getStats(@RequestParam(name = "month") String month) {
		double totalSaleAmount = 0;
		int sold = 0;
		int unsold = 0;
		List<Product> products = ProductRepository.findAll();
			for (Product product : products) {
				if (product.isSold()) {
					sold++;
					totalSaleAmount += product.getPrice();
				} else {
					unsold++;
				}
			}
			double[] stats = new double[3];
			stats[0] = totalSaleAmount;
			stats[1] = sold;
			stats[2] = unsold;
		return stats;
	}
	
	@GetMapping(path = "/bar-chart")
	public Map<String, Integer> getChart(@RequestParam(name = "month") String month) {
		List<Product> products = ProductRepository.findAll();
		int[] a = new int[10];
			for (Product product : products) {
				if (product.getDateOfSale().contains("-" + month +"-")) {
					double price = product.getPrice();
					if (price >=0 && price <= 100) {
						++a[0];
					} else  if(price >=101 && price <= 200) {
						++a[1];
					} else  if(price >=201 && price <= 300) {
						++a[2];
					} else  if(price >=301 && price <= 400) {
						++a[3];
					} else  if(price >=401 && price <= 500) {
						++a[4];
					} else  if(price >=501 && price <= 600) {
						++a[5];
					} else  if(price >=601 && price <= 700) {
						++a[6];
					} else  if(price >=701 && price <= 800) {
						++a[7];
					} else  if(price >=801 && price <= 900) {
						++a[8];
					} else  if(price >=901 && price <= 1000) {
						++a[9];
					} 
				} 
			}
			Map<String, Integer> map = new HashMap<>();
			map.put("0 - 100", a[0]);
			map.put("101 - 200", a[1]);
			map.put("201 - 300", a[2]);
			map.put("301 - 400", a[3]);
			map.put("401 - 500", a[4]);
			map.put("501 - 600", a[5]);
			map.put("601 - 700", a[6]);
			map.put("701 - 800", a[7]);
			map.put("801 - 900", a[8]);
			map.put("901 and above", a[9]);
		return map;
	}
	
}
