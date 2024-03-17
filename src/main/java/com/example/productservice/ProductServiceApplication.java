package com.example.productservice;

import com.example.productservice.entity.Customer;
import com.example.productservice.entity.Invoice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication

public class ProductServiceApplication {

	private static List<Customer> customers = new ArrayList<>();
	private static List<Invoice> invoices = new ArrayList<>();

	public static void main(String[] args) {
		Customer customer1 = new Customer("Company A", "Technology");
		Customer customer2 = new Customer("Company B", "Finance");
		Customer customer3 = new Customer("Company C", "Retail");

		customers.addAll(Arrays.asList(customer1, customer2, customer3));


		Invoice invoice1 = new Invoice(LocalDate.of(2024, 6, 5), 1200);
		Invoice invoice2 = new Invoice(LocalDate.of(2024, 6, 10), 1800);
		Invoice invoice3 = new Invoice(LocalDate.of(2024, 7, 15), 2000);
		Invoice invoice4 = new Invoice(LocalDate.of(2024, 6, 20), 1000);

		invoices.addAll(Arrays.asList(invoice1, invoice2, invoice3, invoice4));

		listAllCustomers();
		listCustomersWithNameContainingC();
		listTotalInvoicesForJune();
		listAllInvoices();
		listInvoicesAbove1500();
		calculateAverageInvoicesAbove1500();
		listCustomersWithInvoicesBelow500();
		listSectorsWithAverageInvoiceBelow750ForJune();
	}

	private static void listAllCustomers() {
		System.out.println("Tüm Müşteriler:");
		customers.forEach(customer -> System.out.println(customer.getName()));
		System.out.println();
	}

	private static void listCustomersWithNameContainingC() {
		System.out.println("İçerisinde 'C' harfi olan müşteriler:");
		customers.stream()
				.filter(customer -> customer.getName().contains("C"))
				.forEach(customer -> System.out.println(customer.getName()));
		System.out.println();
	}

	private static void listTotalInvoicesForJune() {
		System.out.println("Haziran ayında kayıt olan müşterilerin fatura toplamları:");
		double totalAmount = invoices.stream()
				.filter(invoice -> invoice.getDate().getMonth() == LocalDate.of(2024, 6, 1).getMonth())
				.mapToDouble(Invoice::getAmount)
				.sum();
		System.out.println(totalAmount);
		System.out.println();
	}

	private static void listAllInvoices() {
		System.out.println("Tüm Faturalar:");
		invoices.forEach(invoice -> System.out.println("Tarih: " + invoice.getDate() + ", Tutar: " + invoice.getAmount()));
		System.out.println();
	}

	private static void listInvoicesAbove1500() {
		System.out.println("1500TL üstündeki Faturalar:");
		invoices.stream()
				.filter(invoice -> invoice.getAmount() > 1500)
				.forEach(invoice -> System.out.println("Tarih: " + invoice.getDate() + ", Tutar: " + invoice.getAmount()));
		System.out.println();
	}

	private static void calculateAverageInvoicesAbove1500() {
		System.out.println("1500TL üstündeki Faturaların Ortalaması:");
		double average = invoices.stream()
				.filter(invoice -> invoice.getAmount() > 1500)
				.mapToDouble(Invoice::getAmount)
				.average()
				.orElse(0);
		System.out.println(average);
		System.out.println();
	}

	private static void listCustomersWithInvoicesBelow500() {
		System.out.println("500TL altındaki faturalara sahip müşteriler:");
		Set<String> customerNames = new HashSet<>();
		invoices.stream()
				.filter(invoice -> invoice.getAmount() < 500)
				.forEach(invoice -> customers.stream()
						.filter(customer -> customer.getInvoices().contains(invoice.getAmount()))
						.forEach(customer -> customerNames.add(customer.getName())));
		customerNames.forEach(System.out::println);
		System.out.println();
	}

	private static void listSectorsWithAverageInvoiceBelow750ForJune() {
		System.out.println("Haziran ayında fatura ortalaması 750 altı olan müşterilerin sektörleri:");
		Set<String> sectors = new HashSet<>();
		invoices.stream()
				.filter(invoice -> invoice.getDate().getMonth() == LocalDate.of(2024, 6, 1).getMonth())
				.forEach(invoice -> customers.stream()
						.filter(customer -> customer.getInvoices().contains(invoice.getAmount()))
						.forEach(customer -> {
							double average = customer.getInvoices().stream()
									.mapToDouble(Double::doubleValue)
									.average()
									.orElse(0);
							if (average < 750)
								sectors.add(customer.getSector());
						}));
		sectors.forEach(System.out::println);
	}

}
