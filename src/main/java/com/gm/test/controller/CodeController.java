package com.gm.test.controller;

import com.gm.test.model.Code;
import com.gm.test.service.CodeService;
import com.gm.test.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.gm.test.repository.CodeRepository;

import java.util.List;

@RestController("/")
public class CodeController {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeService codeService;


    @PostMapping("/upload")
    public ResponseEntity<Response> uploadCodes(@RequestParam("file") MultipartFile file) {
        codeService.upload(file);
        String message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(message, null));
    }

    @GetMapping("/codes")
    public ResponseEntity<List<Code>> getAllCodes() {
        List<Code> codes = codeService.getAllCodes();
        return ResponseEntity.status(HttpStatus.OK).body(codes);
    }

    @GetMapping("/codes/{code}")
    public ResponseEntity<Response> getAllCodes(@PathVariable("code") String code) {
        Code data = codeService.getDataByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("success", data));
    }

    @DeleteMapping("/codes")
    public ResponseEntity<Response> deleteAllData() {
        codeService.deleteAllData();
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted all records successfully", null));
    }

    @DeleteMapping("/codes/{code}")
    public ResponseEntity<Response> deleteByCode(@PathVariable("code") String code) {
        codeService.deleteByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted record successfully", null));
    }
}
