package it.epicode.finalproject.service;

import it.epicode.finalproject.enumeration.Availability;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.MicroSeason;
import it.epicode.finalproject.model.SeasonalColor;
import it.epicode.finalproject.repository.BookRepository;
import it.epicode.finalproject.repository.MicroSeasonRepository;
import it.epicode.finalproject.repository.SeasonalColorRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportService {

    private final MicroSeasonRepository microSeasonRepository;
    private final SeasonalColorRepository seasonalColorRepository;
    private final BookRepository bookRepository;

    public void importaColoriDaCsv() {
        int count = 0;
        int skipped = 0;
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
                    String microJapaneseName = record.get(2).trim();

                    boolean microExists = microSeasonRepository
                            .existsByJapaneseNameAndStartDate(microJapaneseName, startDate);
                    if (microExists) {
                        skipped++;
                        continue;
                    }

                    MicroSeason season = new MicroSeason();
                    season.setJapaneseName(microJapaneseName);
                    season.setItalianName(record.get(3).trim());
                    season.setStartDate(startDate);
                    season.setEndDate(endDate);
                    MicroSeason savedSeason = microSeasonRepository.save(season);

                    String colorJapaneseName = record.get(4).trim();

                    boolean colorExists = seasonalColorRepository
                            .existsByJapaneseNameAndMicroSeason_JapaneseName(colorJapaneseName, microJapaneseName);
                    if (colorExists) {
                        skipped++;
                        continue;
                    }

                    SeasonalColor color = new SeasonalColor();
                    color.setJapaneseName(colorJapaneseName);
                    color.setItalianName(record.get(5).trim());
                    color.setRgb(record.get(6).trim());
                    color.setDescription(record.get(7).trim());
                    color.setDetails(record.get(8).trim());
                    color.setImageUrl(record.get(9).trim());
                    color.setTheme(record.get(10).trim());
                    color.setMicroSeason(savedSeason);

                    seasonalColorRepository.save(color);
                    count++;

                } catch (Exception e) {
                    System.err.println("Errore nel record #" + record.getRecordNumber());
                    e.printStackTrace();
                }
            }

            System.out.println("Colori salvati: " + count + ", saltati (duplicati): " + skipped);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file CSV: " + e.getMessage());
        }
    }


    public void importaLibriDaCsv() {
        int count = 0;
        int skipped = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");


        var stream = getClass().getClassLoader().getResourceAsStream("books.csv");
        if (stream == null) {
            System.err.println("File books.csv NON trovato nel classpath!");
            return;
        }

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        try (
                InputStreamReader reader = new InputStreamReader(
                        new BOMInputStream(getClass().getClassLoader().getResourceAsStream("books.csv")),
                        StandardCharsets.UTF_8
                );
                CSVParser parser = new CSVParser(reader, format)
        ) {
            for (CSVRecord record : parser) {
                try {
                    String title = record.get("title").trim();
                    String isbn = record.get("isbn").trim();
                    if (bookRepository.existsByIsbn(isbn)) {
                        skipped++;
                        continue;
                    }

                    LocalDate startDate = LocalDate.parse(record.get("startDate").trim(), formatter);
                    LocalDate endDate = LocalDate.parse(record.get("endDate").trim(), formatter);

                    String microJapanese = record.get("micro_japanese_name").trim();
                    List<MicroSeason> microSeasons = microSeasonRepository.findAllByJapaneseName(microJapanese);
                    if (microSeasons.isEmpty()) throw new RuntimeException("MicroSeason non trovata: " + microJapanese);
                    MicroSeason microSeason = microSeasons.get(0);

                    String colorJapanese = record.get("color_japanese_name").trim();
                    List<SeasonalColor> seasonalColors = seasonalColorRepository.findAllByJapaneseName(colorJapanese);
                    if (seasonalColors.isEmpty()) throw new RuntimeException("SeasonalColor non trovato: " + colorJapanese);
                    SeasonalColor seasonalColor = seasonalColors.get(0);

                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(record.get("author").trim());
                    book.setYear(record.get("year").trim());
                    book.setPublisher(record.get("publisher").trim());
                    book.setIsbn(record.get("isbn").trim());
                    book.setPrice(Double.parseDouble(record.get("price").trim().replace(",", ".")));
                    book.setAvailability(Availability.valueOf(record.get("availability").trim().toUpperCase()));
                    book.setDescription(record.get("description").trim());
                    book.setImageUrl(record.get("image_url").trim());
                    book.setMicroSeason(microSeason);
                    book.setSeasonalColor(seasonalColor);

                    bookRepository.save(book);
                    count++;

                } catch (Exception e) {
                    System.err.println("Errore nel record #" + record.getRecordNumber() + " (titolo: " + record.get("title") + ")");
                    e.printStackTrace();
                }
            }
            System.out.println("Libri salvati: " + count + ", saltati (duplicati): " + skipped);
        } catch (IOException e) {
            System.err.println("Errore nella lettura del CSV: " + e.getMessage());
        }
    }

    @PostConstruct
    public void init() {

            importaColoriDaCsv();
            System.out.println("Importazione colori completata.");

            importaLibriDaCsv();
            System.out.println("Importazione libri completata.");
    }
}
