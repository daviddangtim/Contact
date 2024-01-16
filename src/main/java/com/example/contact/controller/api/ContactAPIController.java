package com.example.contact.controller.api;
import com.example.contact.domain.Contact;
import com.example.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ContactAPIController {

    @Autowired
    ContactService contactService;

    @GetMapping("/getContacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok().body(contactService.getAllContacts());
    }

    @GetMapping("/getContact/{id}")
    public ResponseEntity<Contact> getContactByNumber(@PathVariable long id) {
        return ResponseEntity.ok().body(contactService.getContactByNumber(id));
    }

    @PostMapping("/addContact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok().body(contactService.saveContact(contact));
    }

    @PostMapping("/addContacts")
    public List<Contact> addContacts(@RequestBody List<Contact> contacts) {
        return contactService.saveContacts(contacts);
    }

    @PutMapping("/updateContact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable long id, @RequestBody Contact contact) {
        return ResponseEntity.ok().body(contactService.updateContact(contact));
    }

    @DeleteMapping("/deleteContact/{id}")
    public HttpStatus deleteContact(@PathVariable long id) {
        contactService.deleteContact(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/deleteContacts")
    public String deleteContacts() {
        try {
            contactService.deleteContacts();
            return "All Contacts Deleted";
        } catch (Exception e) {
            // Log the exception or handle it accordingly
            return "Error deleting contacts: " + e.getMessage();
        }
    }
}
