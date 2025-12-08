package com.axa.ch.its;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository noteRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteController(NoteRepository noteRepository, JdbcTemplate jdbcTemplate) {
        this.noteRepository = noteRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @PostMapping
    public Note create(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    /**
     * ABSICHTLICHE SQL INJECTION:
     * http://localhost:8080/notes/search?owner=' OR '1'='1
     */
    @GetMapping("/search")
    public List<Note> searchByOwner(@RequestParam String owner) {
        String sql = "SELECT * FROM note WHERE owner = '" + owner + "'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }

    private Note mapRow(ResultSet rs) throws SQLException {
        Note n = new Note();
        n.setId(rs.getLong("id"));
        n.setOwner(rs.getString("owner"));
        n.setTitle(rs.getString("title"));
        n.setContent(rs.getString("content"));
        return n;
    }
}
