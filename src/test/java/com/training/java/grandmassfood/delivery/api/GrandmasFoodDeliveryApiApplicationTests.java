package com.training.java.grandmassfood.delivery.api;

import com.training.java.grandmassfood.delivery.api.controller.customers.CustomerController;
import com.training.java.grandmassfood.delivery.api.dao.customers.dto.CustomerResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GrandmasFoodDeliveryApiApplicationTests {

	@Autowired
	private CustomerController customerController;

    @Test
	@DisplayName("IT get customer")
	void it_Get() {
		String johnDoeDocument = "CC-1234567890";
		String johnDoeName = "John Doe";
		String johnDoeEmail = "johndoe@example.com";
		String johnDoePhone = "1234567890";
		String johnDoeShippingAddress = "123 Main St, Anytown, USA";

		CustomerResponse johnDoeCustomer = customerController.getCustomer(johnDoeDocument);

		assertEquals(johnDoeDocument, johnDoeCustomer.getDocumentNumber());
		assertEquals(johnDoeName, johnDoeCustomer.getFullName());
		assertEquals(johnDoeEmail, johnDoeCustomer.getEmail());
		assertEquals(johnDoePhone, johnDoeCustomer.getPhoneNumber());
		assertEquals(johnDoeShippingAddress, johnDoeCustomer.getShippingAddress());
	}

}
