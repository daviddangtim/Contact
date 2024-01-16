package com.example.contact.service;

import com.example.contact.domain.Contact;
import com.example.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService
{
    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact)
    {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> saveContacts(List<Contact> contacts)
    {
        return contactRepository.saveAll(contacts);
    }

    @Override
    public Contact getContactByNumber(long id)
    {
        Optional<Contact> contact = contactRepository.findById(id);

        Contact emptyContact;
        if(contact.isPresent())
        {
            emptyContact = contact.get();
            return emptyContact;
        }
        else
        {
            throw new RuntimeException("Contact Not Found");
        }
    }

    @Override
    public List<Contact> getAllContacts()
    {
        return contactRepository.findAll();
    }

    @Override
    public Contact updateContact(Contact contact)
    {
        Optional<Contact> optionalContact = contactRepository.findById(contact.getId());

        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();

            existingContact.setName(contact.getName());
            existingContact.setNumber(contact.getNumber());

            contactRepository.save(existingContact);

            return existingContact;
        } else {
            throw new RuntimeException("Contact does not exist");
        }
    }

    @Override
    public void deleteContact(long id)
    {
        contactRepository.deleteById(id);
    }

    @Override
    public void deleteContacts()
    {
        contactRepository.deleteAll();
    }
}
