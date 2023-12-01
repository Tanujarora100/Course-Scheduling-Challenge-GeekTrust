package com.geektrust.backend.repository;

import entity.Registration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.IRegistrationRepository;
import repository.impl.RegistrationRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationRepositoryTest {
    private IRegistrationRepository registrationRepository;

    @BeforeEach
    public void setUp() {
        registrationRepository = new RegistrationRepositoryImpl();
    }

    @Test
    void saveAndGetById() {
        // Create a registration for a real-life scenario
        Registration registration1 = new Registration("john.doe@example.com","CS101","123",true);
        registrationRepository.save(registration1);

        Optional<Registration> retrievedRegistration = registrationRepository.findById("john.doe@example.com");
        assertTrue(retrievedRegistration.isPresent());
        assertEquals("john.doe@example.com", retrievedRegistration.get().getEmailAddress());
        // Assert other properties as needed
    }

    @Test
    void findAll() {
        // Create multiple registrations for a real-life scenario
        Registration registration1 = new Registration("john.doe@example.com","CS101","123",true);
        Registration registration2 = new Registration("jane.smith@example.com", "CS102", "123", true);

        registrationRepository.save(registration1);
        registrationRepository.save(registration2);

        List<Registration> allRegistrations = registrationRepository.findAll();
        assertEquals(2, allRegistrations.size());
        // Assert other properties as needed
    }

    @Test
    void delete() {
        // Create a registration and delete it for a real-life scenario
        Registration registration1 = new Registration("john.doe@example.com","CS101","123",true);
        registrationRepository.save(registration1);

        assertTrue(registrationRepository.existById("john.doe@example.com"));

        registrationRepository.delete(registration1);

        assertFalse(registrationRepository.existById("john.doe@example.com"));
    }

    @Test
    void deleteById() {
        // Create a registration and delete it by ID for a real-life scenario
        Registration registration1 = new Registration("john.doe@example.com","CS101","123",true);
        registrationRepository.save(registration1);

        assertTrue(registrationRepository.existById("john.doe@example.com"));

        registrationRepository.deleteByID("john.doe@example.com");

        assertFalse(registrationRepository.existById("john.doe@example.com"));
    }

    @Test
    void additionalTest() {
        // Create additional test scenarios here
        Registration registration1 = new Registration("john.doe@example.com","CS101","123",true);
        registrationRepository.save(registration1);

        Optional<Registration> retrievedRegistration = registrationRepository.findById("test.user@example.com");
        assertTrue(retrievedRegistration.isPresent());
        assertEquals("test.user@example.com", retrievedRegistration.get().getEmailAddress());
        // Assert other properties as needed
    }
}
