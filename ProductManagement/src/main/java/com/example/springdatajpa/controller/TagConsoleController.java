package com.example.springdatajpa.controller;

import com.example.springdatajpa.model.Tag;
import com.example.springdatajpa.service.TagService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class TagConsoleController {

    private final TagService tagService;
    private final Scanner scanner;

    public TagConsoleController(TagService tagService) {
        this.tagService = tagService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n--- TAG MANAGEMENT ---");
            System.out.println("1. Display all tags");
            System.out.println("2. Add a new tag");
            System.out.println("3. Delete a tag");
            System.out.println("0. Back to main menu");
            System.out.print("Your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayAllTags();
                        break;
                    case 2:
                        addNewTag();
                        break;
                    case 3:
                        deleteTag();
                        break;
                    case 0:
                        return; // Quay lại menu chính
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void displayAllTags() {
        List<Tag> tags = tagService.getAllTags();
        if (tags.isEmpty()) {
            System.out.println("No tags found.");
        } else {
            System.out.println("\n--- ALL TAGS ---");
            tags.forEach(t -> System.out.printf("ID: %d, Name: %s\n", t.getId(), t.getName()));
        }
    }

    private void addNewTag() {
        System.out.print("Enter new tag name: ");
        String name = scanner.nextLine();
        Tag newTag = tagService.createTag(name);
        System.out.println("Tag '" + newTag.getName() + "' created successfully with ID: " + newTag.getId());
    }

    private void deleteTag() {
        System.out.print("Enter tag ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        tagService.deleteTag(id);
        System.out.println("Tag with ID " + id + " deleted successfully.");
    }
}