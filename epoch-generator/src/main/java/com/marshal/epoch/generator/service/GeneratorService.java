package com.marshal.epoch.generator.service;



import com.marshal.epoch.generator.dto.GeneratorInfo;

import java.util.List;


public interface GeneratorService {
    List<String> showTables();

    void generatorFile(GeneratorInfo info) throws Exception;

}
