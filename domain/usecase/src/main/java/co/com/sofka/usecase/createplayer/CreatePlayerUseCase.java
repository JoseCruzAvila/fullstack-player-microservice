package co.com.sofka.usecase.createplayer;

import co.com.sofka.exceptions.PlayerException;
import co.com.sofka.generic.usecase.UseCase;
import co.com.sofka.model.events.PlayerCreated;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreatePlayerUseCase extends UseCase<PlayerCreated, Player> {
    private final PlayerRepository repository;

    public Mono<Player> createPlayer(Player player) {
        return repository.findById("email", player.getEmail())
                .flatMap(storedPlayer -> Mono.just(true))
                .onErrorResume(error -> Mono.just(false))
                .flatMap(this.getRightReturn(player));
    }

    private Function<Boolean, Mono<Player>> getRightReturn(Player player) {
        return alreadyExists -> Boolean.TRUE.equals(alreadyExists) ?
                Mono.error(new PlayerException("The given player already exists"))
                : repository.save(player);
    }
}
