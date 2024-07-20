package org.biblio.biblio;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.biblio.biblio.models.*;
import org.biblio.biblio.repositories.AuteurRepository;
import org.biblio.biblio.repositories.EmpruntRepository;
import org.biblio.biblio.repositories.LivreRepository;
import org.biblio.biblio.repositories.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Component
public class initBDD {

    private UserRepository userRepository;
    private LivreRepository livreRepository;
    private AuteurRepository auteurRepository;
    private EmpruntRepository empruntRepository;

    public static List<?> resizeArrayList(List<?> list, int newSize) {
        if (newSize > list.size()) {
            for (int i = list.size(); i < newSize; i++) {
                list.add(null);
            }
        }
        return list;
    }

    public void initializeDB() {
        Faker faker = new Faker();
        List<User> users = IntStream.range(1,5).mapToObj(index ->
            User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .userName(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build()
        ).collect(Collectors.toList());
        userRepository.saveAll(users);

        List<Auteur> auteurs = IntStream.range(1,10).mapToObj(index ->
            Auteur.builder()
                .name(faker.name().fullName())
                .country(faker.country().name())
                .build()
        ).collect(Collectors.toList());
        auteurRepository.saveAll(auteurs);

        List<Livre> livres = IntStream.range(1,20).mapToObj(index ->
            Livre.builder()
                .title(faker.book().title())
                .category(faker.book().genre())
                .auteurs((List<Auteur>) resizeArrayList(auteurs, 3))
                .build()
        ).collect(Collectors.toList());
        livreRepository.saveAll(livres);

        List<Emprunt> emprunts = IntStream.range(1,15).mapToObj(index ->
           Emprunt.builder()
                .id(new EmpruntId(
                    users.get(faker.random().nextInt(users.size())).getId(),
                    livres.get(faker.random().nextInt(livres.size())).getId()
                ))
                .livre(livres.get(faker.random().nextInt(auteurs.size())))
                .utilisateur(users.get(faker.random().nextInt(users.size())))
                .startDate(faker.date().past(30, TimeUnit.DAYS))
                .endDate(faker.date().future(50,TimeUnit.DAYS))
                .build()
        ).collect(Collectors.toList());
        empruntRepository.saveAll(emprunts);
    }
}
