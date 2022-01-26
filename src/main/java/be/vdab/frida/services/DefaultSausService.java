package be.vdab.frida.services;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.repositories.SausRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
class DefaultSausService implements SausService{
    private final SausRepository sausRepository;

    DefaultSausService(@Qualifier("properties") SausRepository sausRepository) {
        this.sausRepository = sausRepository;
    }


    @Override
    public Stream<Saus> findAll() {
        return sausRepository.findAll();
    }

    @Override
    public Stream<Saus> findByNaamBegintMet(char letter) {
        return sausRepository.findAll()
                .filter(saus -> saus.getNaam().charAt(0) == letter);
    }

    @Override
    public Optional<Saus> findById(long id) {
        return sausRepository.findAll()
                .filter(saus -> saus.getId() == id)
                .findFirst();
    }
}
