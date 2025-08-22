package org.iclass.board.controller;

import java.util.List;
import java.util.Map;

import org.iclass.board.dto.CommunityCommentDTO;
import org.iclass.board.service.CommunityCommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class APICommentsController {
    private CommunityCommentsService service;

@PostMapping("/api/comments")
public ResponseEntity<?> reply(@RequestBody CommunityCommentDTO dto){
int result = service.reply(dto);
return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success",result));
}

@DeleteMapping("/api/comments/{mref}/{idx}")
public ResponseEntity<?> delete(@PathVariable int idx, @PathVariable int mref){
int result = service.delete(idx, mref);
if(result == 0){
return ResponseEntity.badRequest().body(Map.of("failed, no content",result));
}else{
return ResponseEntity.ok().body(Map.of("success",result));
}}

@GetMapping("/api/comments/{mref}")
public ResponseEntity<List<CommunityCommentDTO>> cmtsList(@PathVariable int mref){
    return ResponseEntity.ok().body(service.clist(mref));
}
}
