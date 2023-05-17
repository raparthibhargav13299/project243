package com.stackroute.graphcommandservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stackroute.graphcommandservice.model.ExcelSheetData;
import com.stackroute.graphcommandservice.model.Image;
import com.stackroute.graphcommandservice.model.Program;
import com.stackroute.graphcommandservice.service.ProgramService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProgramController {

    List<ExcelSheetData> excelSheetArray = new ArrayList<>();
    List<String> externalApiLink = new ArrayList<>();

    ProgramService programService;

    Gson gson = new Gson();

    @Autowired
    public ProgramController(ProgramService programService) {

        this.programService = programService;
    }

    @PostMapping("/program")
    public ResponseEntity<?> createConceptGraph(@RequestParam("file") MultipartFile[] file, @RequestParam("details") String details) throws IOException {
        try {
            List<ExcelSheetData> excelData = excelReader(file[0]);
            System.out.println("Excel array" + excelData);

            Image img = new Image(file[1].getOriginalFilename(), file[1].getContentType(), compressZLib(file[1].getBytes()));
            byte[] imageByte = compressZLib(file[1].getBytes());
//            Program readValue = new ObjectMapper().readValue(details, Program.class);
            Program readValue = gson.fromJson(details, Program.class);

            System.out.println("Read Value" + readValue);
            Program programDetails = new Program(readValue.getCreatedBy(), readValue.getProgramTitle(), readValue.getDomainName(), readValue.getDuration(), readValue.getDescription(),file[1].getOriginalFilename(), file[1].getContentType(),decompressZLib(imageByte) );
            Program programResult = programService.createConceptGraph(programDetails, excelData);
            return new ResponseEntity<>(programResult, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }

    }

    public List<ExcelSheetData> excelReader(MultipartFile reapExcelDataFile) throws IOException {

        excelSheetArray.clear();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            ExcelSheetData excelSheetValue = new ExcelSheetData();

            XSSFRow row = worksheet.getRow(i);
            excelSheetValue.setConceptName(row.getCell(0).getStringCellValue());
            excelSheetValue.setParent(row.getCell(1).getStringCellValue());
            excelSheetArray.add(excelSheetValue);
        }
        return excelSheetArray;
    }

    public static byte[] compressZLib(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }


    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressZLib(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

}
