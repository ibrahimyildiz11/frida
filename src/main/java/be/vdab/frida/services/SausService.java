package be.vdab.frida.services;

import be.vdab.frida.domain.Saus;

import java.util.Optional;
import java.util.stream.Stream;

public interface SausService {
    Stream<Saus> findAll();
    Stream<Saus> findByNaamBegintMet(char letter);
    Optional<Saus> findById(long id);
}
