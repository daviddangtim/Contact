package com.example.contact.service;

import com.example.contact.domain.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ContactService
{
    Contact saveContact(Contact contact);
    List<Contact> saveContacts(List<Contact> contacts);
    Contact getContactByNumber(long id);
    List<Contact> getAllContacts();
    Contact updateContact(Contact contact);
    void deleteContact(long id);
    void deleteContacts();
}
