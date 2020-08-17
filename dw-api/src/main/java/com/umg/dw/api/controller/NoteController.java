package com.umg.dw.api.controller;

import com.umg.dw.api.exception.ResourceNotFoundException;
import com.umg.dw.api.repository.NoteRepository;
import com.umg.dw.core.entities.Note;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "/", description = "REST note")
@RestController
@RequestMapping("/notes")
public class NoteController {

    final
    NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @CrossOrigin

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Note> getAll() {
        return (List<Note>) noteRepository.findAll();
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public Note getNoteById(@PathVariable(value = "id") Integer noteId) {
        return noteRepository.findById(noteId).get();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json")
    public Note create(@RequestBody Note note) {
        note = noteRepository.save(note);
        return note;
    }
    @RequestMapping(
            method = RequestMethod.PUT,
            produces = "application/json")
    public Note update(@RequestBody Note note) {
        note = noteRepository.save(note);
        return note;
    }

}
