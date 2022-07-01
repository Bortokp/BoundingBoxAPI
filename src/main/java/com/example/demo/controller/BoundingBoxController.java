package com.example.demo.controller;

import com.example.demo.model.BoundingBox;
import com.example.demo.model.BoundingBoxDTO;
import com.example.demo.service.BoundingBoxService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BoundingBoxController {

    private final BoundingBoxService service;

    @RequestMapping(value = "/addBoundingBox", method = RequestMethod.POST)
    public ResponseEntity<?> userInput(@RequestParam double x1, @RequestParam double x2, @RequestParam double y1, @RequestParam double y2) {
        try {
            service.addBoundingBox(x1, y1, x2, y2);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/getAllBoundingBoxes")
    public ResponseEntity<BoundingBoxDTO> dowABb() {
        return ResponseEntity.ok(service.getAllBoundingBoxes());
    }
    @GetMapping(path = "/getBoundingBoxByID")
    public ResponseEntity<BoundingBox> dowABb(@RequestParam int iD) throws Exception {
        return ResponseEntity.ok(service.getBoundingBoxByID(iD));
    }

    @RequestMapping(value = "/editBoundingBox", method = RequestMethod.POST)
    public ResponseEntity<?> userInput(@RequestParam int iD, @RequestParam double x1, @RequestParam double x2, @RequestParam double y1, @RequestParam double y2) {
        try {
            service.editBoundingBox(iD, x1, y1, x2, y2);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/removeBoundingBox", method = RequestMethod.POST)
    public ResponseEntity<?> userInput(@RequestParam int iD) {
        try {
            service.removeBB(iD);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/getconfig")
    public ResponseEntity<String> dow() {
        return ResponseEntity.ok(service.configcheck());
    }

    @GetMapping(path = "/getAllBoundingBoxes")
    public ResponseEntity<byte[]> downloadALL() throws Exception {
        BoundingBoxDTO bBDTO = service.getAllBoundingBoxes();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bBDTO.getBoundingBoxes());
        byte[] temp = json.getBytes();
        String fileName = "BoundingBoxList.json";
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(temp.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(temp, respHeaders, HttpStatus.OK);
    }

    @RequestMapping(path = "/downloadById", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadByID(@RequestParam int iD) throws Exception {
        BoundingBox bBDTO = service.getBoundingBoxByID(iD);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bBDTO);
        byte[] temp = json.getBytes();
        String fileName = "BoundingBox-" + iD + ".json";
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(temp.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(temp, respHeaders, HttpStatus.OK);
    }
}
