package com.gm.test.service;

import com.gm.test.model.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gm.test.repository.CodeRepository;

import java.io.ByteArrayInputStream;
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
            List<Code> codes = csvService.csvToCode(file.getInputStream());
            codeRepository.saveAll(codes);
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

    public ByteArrayInputStream downloadCSV() {
        List<Code> codes = codeRepository.findAll();

        ByteArrayInputStream in = CSVService.dataToCSV(codes);
        return in;
    }

    public ByteArrayInputStream downloadCSVByCode(String code) {
        Code data = codeRepository.findByCode(code);
        ByteArrayInputStream in = CSVService.dataToCSV(List.of(data));
        return in;
    }


}
