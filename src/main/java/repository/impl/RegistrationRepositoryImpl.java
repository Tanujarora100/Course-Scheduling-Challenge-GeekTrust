package repository.impl;

import entity.Registration;
import repository.IRegistrationRepository;

import java.util.*;

public class RegistrationRepositoryImpl implements IRegistrationRepository {
    private final Map<String, Registration> registrationMap;

    public RegistrationRepositoryImpl(Map<String, Registration> registrationMap) {
        this.registrationMap = registrationMap;
    }

    public RegistrationRepositoryImpl() {
        this.registrationMap = new HashMap<>();
    }

    @Override
    public void save(Registration object) {
        registrationMap.put(object.getEmailAddress(), object);
    }

    @Override
    public List<Registration> findAll() {
        return new ArrayList<>(registrationMap.values());
    }

    @Override
    public Optional<Registration> findById(String emailAddress) {
        return Optional.of(registrationMap.get(emailAddress));
    }

    @Override
    public boolean existById(String emailAddress) {
        Optional<Registration> byId = findById(emailAddress);
        return byId.isPresent();
    }

    @Override
    public void delete(Registration object) {
        if (existById(object.getEmailAddress())) {
            registrationMap.remove(object.getEmailAddress());
        }
    }

    @Override
    public void deleteByID(String s) {
        if (existById(s)) {
            registrationMap.remove(s);
        }
    }
}
