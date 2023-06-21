package com.beam.fileupload.account;

import com.beam.fileupload.Base.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document
@TypeAlias("Account")
public class Account extends Base {

    String userName;
    String password;
}
