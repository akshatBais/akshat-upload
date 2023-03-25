package com.gm.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Code {

    @Id
    public String id;

    public String source;

    public String codeListCode;

    @Indexed(unique = true)
    public String code;

    public String displayValue;

    public String longDescription;

    public String fromDate;

    public String toDate;

    public String sortingPriority;


}
