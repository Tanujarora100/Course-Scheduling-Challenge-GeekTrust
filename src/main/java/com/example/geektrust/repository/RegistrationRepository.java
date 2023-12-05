package com.example.geektrust.repository;

import com.example.geektrust.entity.Registration;

import java.util.*;

public class RegistrationRepository implements IRegistrationRepository {

    private final Map<String, Registration> registrationMap;

    public RegistrationRepository() {
        this.registrationMap = new TreeMap<>();
    }

    @Override
    public Registration save(Registration object) {
        registrationMap.put(object.getRegistrationID(), object);
        return object;
    }

    @Override
    public boolean existById(String s) {
        return registrationMap.containsKey(s);
    }

    @Override
    public Optional<Registration> findById(String registrationID) {
        return Optional.of(registrationMap.get(registrationID));
    }

    @Override
    public void delete(Registration object) {
        if (existById(object.getRegistrationID())) {
            registrationMap.remove(object.getRegistrationID());
        }
    }

    @Override
    public void deleteById(String s) {
        if (existById(s)) {
            registrationMap.remove(s);
        }
    }

    @Override
    public List<Registration> findAll() {
        return new ArrayList<>(registrationMap.values());
    }
}
