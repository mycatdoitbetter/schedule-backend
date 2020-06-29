package com.mycatdoitbetter.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycatdoitbetter.schedule.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}