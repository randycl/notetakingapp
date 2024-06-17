package com.randy_tech.notetakingapp.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.randy_tech.notetakingapp.model.Note;
import com.randy_tech.notetakingapp.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

	@Autowired
    private NoteRepository noteRepository;

	@GetMapping
    public Page<Note> getAllNotes(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "1") int size, // Default to 1 note per page
        @RequestParam(defaultValue = "") String search
    ) {
        Pageable pageable = PageRequest.of(page, size);
        if (search.isEmpty()) {
            return noteRepository.findAll(pageable);
        } else {
            // Reset to the first page when a search term is provided
            page = 0;
            pageable = PageRequest.of(page, size);
            return noteRepository.findByTitleContainingOrContentContainingIgnoreCase(search, pageable);
        }
    }

    @PostMapping
    public Note createNote(@RequestBody Note newNote) {
        newNote.setId(null); // Make sure the ID is null so it gets generated
        return noteRepository.save(newNote);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            note.setStatus(updatedNote.getStatus());
            note.setLastUpdated(LocalDateTime.now());
            return noteRepository.save(note);
        }).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
    }
}
