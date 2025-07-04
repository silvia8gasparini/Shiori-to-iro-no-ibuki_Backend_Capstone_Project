package it.epicode.finalproject.service;

import it.epicode.finalproject.model.MicroSeason;
import it.epicode.finalproject.model.SeasonalColor;
import it.epicode.finalproject.repository.MicroSeasonRepository;
import it.epicode.finalproject.repository.SeasonalColorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class ImportService {

    private final MicroSeasonRepository microSeasonRepository;
    private final SeasonalColorRepository seasonalColorRepository;

    public void importaColoriDaCsv() {
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        try (
                InputStreamReader reader = new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream("colors.csv"),
                        StandardCharsets.UTF_8
                );
                CSVParser parser = new CSVParser(reader, format)
        ) {
            for (CSVRecord record : parser) {
                try {
                    String startDateStr = record.get(0).trim();
                    String endDateStr = record.get(1).trim();
                    if (startDateStr.isBlank() || endDateStr.isBlank()) continue;

                    LocalDate startDate = LocalDate.parse(startDateStr, formatter);
                    LocalDate endDate = LocalDate.parse(endDateStr, formatter);

                    MicroSeason season = new MicroSeason();
                    season.setJapaneseName(record.get(2).trim());
                    season.setItalianName(record.get(3).trim());
                    season.setStartDate(startDate);
                    season.setEndDate(endDate);
                    MicroSeason savedSeason = microSeasonRepository.save(season);

                    SeasonalColor color = new SeasonalColor();
                    color.setJapaneseName(record.get(4).trim());
                    color.setItalianName(record.get(5).trim());
                    color.setRgb(record.get(6).trim());
                    color.setDescription(record.get(7).trim());
                    color.setDetails(record.get(8).trim());
                    color.setImageUrl(record.get(9).trim());
                    color.setMicroSeason(savedSeason);

                    seasonalColorRepository.save(color);
                    count++;
                } catch (Exception e) {
                    System.err.println("Errore nel record numero " + record.getRecordNumber());
                    e.printStackTrace();
                }
            }

            System.out.println("Colori salvati: " + count);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file CSV: " + e.getMessage());
        }
    }
}
