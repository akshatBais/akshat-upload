package com.gerimedica.test.service;

import com.gerimedica.test.model.Code;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CSVService {

    public static List<Code> csvToCode(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Code> codeList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Date date = new Date();
                ObjectId objectIdDate = new ObjectId(date);
                Code code = new Code(
                        objectIdDate.toString(),
                        csvRecord.get("source"),
                        csvRecord.get("codeListCode"),
                        csvRecord.get("code"),
                        csvRecord.get("displayValue"),
                        csvRecord.get("longDescription"),
//                        new Date(csvRecord.get("fromDate")),
//                        new Date(csvRecord.get("toDate")),
                        null,null,
                        csvRecord.get("sortingPriority")
                );

                codeList.add(code);
            }

            return codeList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
