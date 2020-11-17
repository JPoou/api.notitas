package com.umg.dw.api.controller;

import com.umg.dw.api.repository.NoteRepository;
import com.umg.dw.core.entities.Note;
import io.swagger.annotations.Api;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Api(value = "/", description = "REST note")
@RestController
@RequestMapping("/notes")
public class NoteController {

    final
    NoteRepository noteRepository;
    private EmitterProcessor<Note> notificationProcessor;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PostConstruct
    private void createProcessor() {
        notificationProcessor = EmitterProcessor.<Note>create();
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
        notificationProcessor.onNext(note);
        return note;
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = "application/json")
    public Note update(@RequestBody Note note) {
        note = noteRepository.save(note);
        return note;
    }

    private Flux<ServerSentEvent<Note>> getPersonaSSE() {

        return notificationProcessor
            .log().map(
                (note) -> {
                    System.out.println("Sending Note:" + note.getBody());
                    return ServerSentEvent.<Note>builder()
                            .id(UUID.randomUUID().toString())
                            .event("note-result")
                            .data(note)
                            .build();
                }).concatWith(Flux.never());
    }

    private Flux<ServerSentEvent<Note>> getNotificationHeartbeat() {
        return Flux.interval(Duration.ofSeconds(15))
            .map(i -> {
                System.out.println(String.format("Sending heartbeat [%s] ...", i.toString()));
                return ServerSentEvent.<Note>builder()
                        .id(String.valueOf(i))
                        .event("heartbeat-result")
                        .data(null)
                        .build();
            });
    }

    @GetMapping(
            value = "/notification/sse"
    )
    public Flux<ServerSentEvent<Note>> getJobResultNotification() {

        return Flux.merge(getNotificationHeartbeat(), getPersonaSSE());

    }

}
