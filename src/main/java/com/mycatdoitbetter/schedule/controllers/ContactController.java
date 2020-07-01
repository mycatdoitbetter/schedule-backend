package com.mycatdoitbetter.schedule.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.ws.Response;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycatdoitbetter.schedule.models.Contact;
import com.mycatdoitbetter.schedule.repositories.ContactRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
// @RequestMapping("/api")

public class ContactController {

  @Autowired
  ContactRepository contactRepository;

  @PostMapping("/contacts")
  public ResponseEntity<Contact> create(@RequestBody Contact contact) {
    try {

      Contact _contact = contactRepository.save(new Contact(contact.getFirst(), contact.getLast(), contact.getEmail(),
          contact.getPhone(), contact.getHobby()));
      return new ResponseEntity<>(_contact, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }

  @GetMapping("/contacts")
  public ResponseEntity<List<Contact>> read() {
    try {
      List<Contact> contacts = new ArrayList<Contact>();

      contactRepository.findAll().forEach(contacts::add);
      if (contacts.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(contacts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }

  @PutMapping("/contacts/{id}")
  public ResponseEntity<Contact> update(@PathVariable("id") long id, @RequestBody Contact contact) {
    Optional<Contact> previewContact = contactRepository.findById(id);

    if (previewContact.isPresent()) {
      Contact _contact = previewContact.get();
      _contact.setFirst(contact.getFirst());
      _contact.setLast(contact.getLast());
      _contact.setPhone(contact.getPhone());
      _contact.setHobby(contact.getHobby());
      return new ResponseEntity<Contact>(contactRepository.save(_contact), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @DeleteMapping("/contacts/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
    try {
      Optional<Contact> previewContact = contactRepository.findById(id);
      if (previewContact.isPresent()) {
        contactRepository.deleteById(id);
      }
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
}
