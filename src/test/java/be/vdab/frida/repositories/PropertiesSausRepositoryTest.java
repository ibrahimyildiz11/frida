package be.vdab.frida.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@PropertySource("application.properties")
@Import(PropertiesSausRepository.class)
class PropertiesSausRepositoryTest {
    private final PropertiesSausRepository repository;
    private final Path pad;

    PropertiesSausRepositoryTest(PropertiesSausRepository repository,@Value("${propertiesSausenPad}") Path pad) {
        this.repository = repository;
        this.pad = pad;
    }

    @Test
    void erZijnEvenveelSauzenAlsErRegelsZijnInHetCSVBestand() throws IOException {
        assertThat(repository.findAll()).hasSameSizeAs(Files.readAllLines(pad));
    }

    @Test
    void deEersteSausBevatDeDataVanDeEersteRegelInHetCSVBestand() throws IOException {
        var eersteRegel = Files.lines(pad).findFirst().get();
        var eersteSaus = repository.findAll().findFirst().get();
        assertThat(eersteSaus.getId() + ":" + eersteSaus.getNaam() + "," +
                Arrays.stream(eersteSaus.getIngredienten())
                        .collect(Collectors.joining(",")))
                        .isEqualTo(eersteRegel);
    }

}