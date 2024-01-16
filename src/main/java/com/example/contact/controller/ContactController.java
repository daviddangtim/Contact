package com.example.contact.controller;

import com.example.contact.domain.Contact;
import com.example.contact.repository.ContactRepository;
import com.example.contact.service.ContactService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public String getAllContacts(Model model)
    {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "contact list";
    }

    @GetMapping("/addContact")
    public String addContactForm(Model model)
    {
        Contact contact = new Contact();
        model.addAttribute("contact",contact);
        return "add contact";
    }

    @PostMapping("/saveContact")
    public String saveContact(@ModelAttribute("contact") Contact contact)
    {
        contactRepository.save(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/updateForm/{id}")
    public String updateContactForm(@PathVariable Long id, Model model) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            model.addAttribute("contact", optionalContact.get());
            return "update contact";
        } else {
            return "redirect:/contacts";
        }
    }

    @PostMapping("/update/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute("contact") Contact updatedContact) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setName(updatedContact.getName());
            existingContact.setNumber(updatedContact.getNumber());

            contactRepository.save(existingContact);
        }
        return "redirect:/contacts";
    }

    @GetMapping("/deleteForm/{id}")
    public String deleteContactForm(@PathVariable Long id, Model model) {
        Optional<Contact> optionalBook = contactRepository.findById(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("contact", optionalBook.get());
            return "delete contact";
        } else {
            return "redirect:/contacts";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            contactRepository.deleteById(id);
        }
        return "redirect:/contacts";
    }
}


