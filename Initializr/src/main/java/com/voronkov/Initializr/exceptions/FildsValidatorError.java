package com.voronkov.Initializr.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FildsValidatorError {
    private List<String> errorMessage;

    public FildsValidatorError(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
