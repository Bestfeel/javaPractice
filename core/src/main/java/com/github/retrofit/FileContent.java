package com.github.retrofit;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class FileContent {
    private List<String> file;
}
