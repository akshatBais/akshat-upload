package com.gm.test.service;

import com.gm.test.model.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gm.test.repository.CodeRepository;

import java.io.IOException;
import java.util.List;

@Service
public class CodeService {

    @Autowired
    private CSVService csvService;

    @Autowired
    private CodeRepository codeRepository;

    public void upload(MultipartFile file) {
        try {
            List<Code> tutorials = csvService.csvToCode(file.getInputStream());
            codeRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Code> getAllCodes() {
        try {
            return codeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Code getDataByCode(String code) {
        return codeRepository.findByCode(code);
    }

    public void deleteAllData() {
        try {
            codeRepository.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByCode(String code) {
        try {
            codeRepository.deleteByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
