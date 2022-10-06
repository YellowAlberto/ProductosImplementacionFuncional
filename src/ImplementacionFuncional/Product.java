package ImplementacionFuncional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Product {

	public String name;
	public Double price;
	public Tax tax;
	
	Product(String name, Double price, Tax tax){
		this.name = name;
		this.price = price;
		this.tax = tax;			
	}

	public static void main(String[] args) {
		List<Product> listaProductos = List.of(
				new Product ("Clothes", 15.90, Tax.NORMAL),
				new Product ("Bread", 1.5, Tax.SUPERREDUCED),
				new Product ("Meat", 13.99, Tax.REDUCED),
				new Product ("Cheese", 3.59, Tax.SUPERREDUCED),
				new Product ("Coke", 1.89, Tax.REDUCED),
				new Product ("Whiskey", 19.90, Tax.NORMAL));
		
		
		//Devolver los precios con impuestos usando bigDecimal
		
		//BigDecimal totalWithTaxes = listaProductos.stream()
                //.map(product -> product.price.add(product.price.multiply(new BigDecimal(product.tax.percent)).divide(new BigDecimal(100))))
                //.reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.CEILING); 
		
		
		//Devolver los precios con impuestos usando Integer
		Optional<Double> price = Optional.empty();
		price = listaProductos.stream().map(product -> product.price + ((product.price * product.tax.percent) / 100)).reduce((x,y) -> x+y);
		System.out.println("El total es de:" + price.get());
		
		//Devolver los productos que empiecen por la letra C y separados por comas.
		String productsStartsWithC = listaProductos.stream().filter(product -> product.name.startsWith("C"))
				.sorted((x,y) -> x.name.compareToIgnoreCase(y.name))
				.map(x -> x.name)
				.collect(Collectors.joining(",","Products starts with C","."));
		
		System.out.println(productsStartsWithC);
			
		
		//De acuerdo a la colección de productos dada calcular el total de la compra, en la 
		//cual solo se le sumaran los impuestos incurridos a los precios mayores de 10.
		Optional<Double> preciosMayoresque10 = Optional.empty();
		preciosMayoresque10 = listaProductos.stream().filter(product -> product.price > 10)
		.map(product -> product.price + ((product.price * product.tax.percent) / 100)).reduce((x,y) -> x+y);
		
		Optional<Double> preciosMenoresque10 = listaProductos.stream().filter(product -> product.price < 10)
				.map(product -> product.price).reduce((x,y) -> x+y);
		Double precioTotal = preciosMenoresque10.get() + preciosMayoresque10.get();
		System.out.println("El total de productos es:" + precioTotal);
	}
	
	
}


