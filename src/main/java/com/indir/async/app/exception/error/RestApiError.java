package com.indir.async.app.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class RestApiError implements Serializable {
    public static final RestApiError INVALID_FILE_EXTENSION = new RestApiError("invalid_file_extension", "Invalid file extension");

    public static final String ERROR = "error";
    public static final String ERROR_DESCRIPTION = "error_description";
    @JsonProperty(ERROR)
    private String error;
    @JsonProperty(ERROR_DESCRIPTION)
    private String description;

    public RestApiError(String error, String description) {
        this.error = error;
        this.description = description;
    }
}
